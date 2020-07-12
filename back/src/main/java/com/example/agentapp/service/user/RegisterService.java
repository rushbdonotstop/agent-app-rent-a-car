package com.example.agentapp.service.user;

import com.example.agentapp.dto.user.EmailDTO;
import com.example.agentapp.dto.user.UserPrivilegeRequestDTO;
import com.example.agentapp.model.enums.UserType;
import com.example.agentapp.model.user.AgentRequest;
import com.example.agentapp.model.user.User;
import com.example.agentapp.model.user.UserDetails;
import com.example.agentapp.repository.user.AgentRequestRepository;
import com.example.agentapp.repository.user.UserDetailsRepository;
import com.example.agentapp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    AgentRequestRepository agentRequestRepository;

    @Autowired
    UserPrivilegeService userPrivilegeService;

    public EmailDTO registerUser(User user) throws Exception {
        UserDetails newUserDetails = user.getUserDetails();
        newUserDetails.setVehicleNum(0);
        UserDetails udFromBase = userDetailsRepository.save(newUserDetails);

        User newUser = user;

        newUser.setPassword(user.getPassword());
        newUser.setUserDetails(udFromBase);
        newUser.setVerified(false);
        newUser = userRepository.save(newUser);

        userPrivilegeService.addPrivilege(newUser.getId(), new UserPrivilegeRequestDTO("RENT_VEHICLE"));
        userPrivilegeService.addPrivilege(newUser.getId(), new UserPrivilegeRequestDTO("ADD_VEHICLE"));

        EmailDTO email = new EmailDTO();
        email.setEmailTo(udFromBase.getEmail());
        email.setSubject("Welcome to Rento!");
        if(newUserDetails.getUserType()==UserType.END_USER) {
            email.setBody(verificationTokenService.generateVerificationToken(newUser));
        } else {
            AgentRequest agentRequest = new AgentRequest();
            agentRequest.setUserId(newUser.getId());
            agentRequestRepository.save(agentRequest);

            udFromBase.setUserType(UserType.END_USER);
            email.setBody(verificationTokenService.generateVerificationToken(newUser) + "\n Registered as END USER. For agent privileges" +
                    "wait until administrator approves registration request.");
        }


        return email;
    }

    public boolean validate(User user) {
        boolean match1 = false;
        boolean match2 = false;
        boolean match3 = false;

        Pattern emailRegex = Pattern.compile("[^@]+@[^\\.]+\\..+", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailRegex.matcher(user.getUserDetails().getEmail());


        Pattern passwordRegex = Pattern.compile("(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}");
        Matcher passwordMatcher = passwordRegex.matcher(user.getPassword());

        Pattern usernameRegex = Pattern.compile("[a-zA-Z0-9]+");
        Matcher usernameMatcher = usernameRegex.matcher(user.getUsername());

        if (emailMatcher.find()) {
            match1 = true;
        }
        if (passwordMatcher.find()) {
            match2 = true;
        }
        if (usernameMatcher.find()) {
            match3 = true;
        }

        if (match1 && match2 && match3) {
            return true;
        } else {
            return false;
        }
    }

    public List<AgentRequest> getAllAgentRequests() {
        return agentRequestRepository.findAll();
    }

    public void deleteAgentRequest(Long id) {
        agentRequestRepository.deleteById(id);
    }

    public void approveAgent(Long agentRequestId) throws Exception {
        AgentRequest agentRequest = agentRequestRepository.findOneById(agentRequestId);
        User user = userRepository.findOneById(agentRequest.getUserId());
        user.getUserDetails().setUserType(UserType.AGENT);

        userPrivilegeService.addPrivilege(user.getId(), new UserPrivilegeRequestDTO("ADD_DISCOUNT"));
        userPrivilegeService.addPrivilege(user.getId(), new UserPrivilegeRequestDTO("GET_STATISTIC"));

        userRepository.save(user);
        agentRequestRepository.delete(agentRequest);

    }
}
