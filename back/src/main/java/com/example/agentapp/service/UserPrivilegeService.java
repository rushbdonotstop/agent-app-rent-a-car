package com.example.agentapp.service;

import com.example.agentapp.dto.UserPrivilegeDTO;
import com.example.agentapp.dto.UserPrivilegeRequestDTO;
import com.example.agentapp.model.User;
import com.example.agentapp.model.UserPrivilege;
import com.example.agentapp.model.enums.Privilege;
import com.example.agentapp.repository.UserPrivilegeRepository;
import com.example.agentapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPrivilegeService {
    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserPrivilegeDTO> getAllUserPrivileges() {
        List<UserPrivilege> userPrivilegeList = userPrivilegeRepository.findAll();
        List<UserPrivilegeDTO> userPrivilegeDTOList = new ArrayList<UserPrivilegeDTO>();

        for (UserPrivilege userPrivilege : userPrivilegeList
        ) {
            UserPrivilegeDTO userPrivilegeDTOold = null;

            for (UserPrivilegeDTO up : userPrivilegeDTOList
            ) {
                if (up.getUserId() == userPrivilege.getUser().getId()) {
                    userPrivilegeDTOold = up;
                    break;
                }
            }

            if (userPrivilegeDTOold == null) {
                UserPrivilegeDTO userPrivilegeDTO = new UserPrivilegeDTO();
                userPrivilegeDTO.setUserId(userPrivilege.getUser().getId());
                userPrivilegeDTO.addPrivilege(userPrivilege.getPrivilege().toString());
                userPrivilegeDTOList.add(userPrivilegeDTO);
            } else {
                userPrivilegeDTOold.addPrivilege(userPrivilege.getPrivilege().toString());
            }
        }
        return userPrivilegeDTOList;
    }

    public UserPrivilegeDTO getOneUserPrivileges(Long id) throws EntityNotFoundException {
        try {
            userRepository.findOneById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User doesn't exists.");
        }

        List<UserPrivilege> userPrivilegeList = userPrivilegeRepository.findAllByUserId(id);

        UserPrivilegeDTO userPrivilegeDTO = new UserPrivilegeDTO();
        userPrivilegeDTO.setUserId(id);
        userPrivilegeList.stream().forEach(userPrivilege -> {
            userPrivilegeDTO.addPrivilege(userPrivilege.getPrivilege().toString());
        });

        return userPrivilegeDTO;
    }

    public void addPrivilege(Long id, UserPrivilegeRequestDTO userPrivilegeRequestDTO) throws EntityNotFoundException, Exception {
        User user;
        try {
            user = userRepository.findOneById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User doesn't exists.");
        }

        UserPrivilegeDTO userPrivilegeDTO = getOneUserPrivileges(id);
        if (userPrivilegeDTO.getUserPrivileges().contains(userPrivilegeRequestDTO.getUserPrivilege())) {
            throw new Exception("User already has " + userPrivilegeRequestDTO.getUserPrivilege() + " privilege.");
        } else {
            UserPrivilege userPrivilege = new UserPrivilege();
            userPrivilege.setUser(user);
            userPrivilege.setPrivilege(Privilege.toEnum(userPrivilegeRequestDTO.getUserPrivilege()));
            userPrivilegeRepository.save(userPrivilege);
        }
    }

    public void deleteUserPrivilege(Long id, UserPrivilegeRequestDTO userPrivilegeRequestDTO) throws Exception {
        User user;
        try {
            user = userRepository.findOneById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User doesn't exists.");
        }

        UserPrivilegeDTO userPrivilegeDTO = getOneUserPrivileges(id);
        if (!userPrivilegeDTO.getUserPrivileges().contains(userPrivilegeRequestDTO.getUserPrivilege())) {
            throw new Exception("User doesn't have " + userPrivilegeRequestDTO.getUserPrivilege() + " privilege.");
        } else {
            UserPrivilege userPrivilege = userPrivilegeRepository.findOneByUserIdAndPrivilege(id, Privilege.toEnum(userPrivilegeRequestDTO.getUserPrivilege()));
            userPrivilegeRepository.delete(userPrivilege);
        }
    }
}
