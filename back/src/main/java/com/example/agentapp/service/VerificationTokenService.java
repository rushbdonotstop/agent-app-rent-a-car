package com.example.agentapp.service;

import com.example.agentapp.model.User;
import com.example.agentapp.model.VerificationToken;
import com.example.agentapp.repository.UserRepository;
import com.example.agentapp.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationTokenService {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    UserRepository userRepository;

    public User confirmVerification(String token) {
        VerificationToken vToken = verificationTokenRepository.findToken(token);
        if (vToken == null) {
            System.err.println("Token not found!");
            return null;
        } else {
            User user = userRepository.findOneById(vToken.getUser().getId());
            user.setVerified(true);
            userRepository.save(user);
            return user;
        }
    }

    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken vToken = new VerificationToken(token, user);
        verificationTokenRepository.save(vToken);

        String emailText = "You registered successfully, you can verify your account by following next link:\n" +
                "Verification link:\t" + "https://localhost:4200/registrationConfirm?token=" + token;

        return emailText;
    }
}
