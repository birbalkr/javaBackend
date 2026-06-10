package com.project.fitness.service;


import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(RegisterRequest registerRequest) {
        User user = new User(
                null,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                Instant.parse("2025-12-08T14:49:41.208Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                Instant.parse("2025-12-08T14:49:41.208Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                List.of(),
                List.of()
        );
        return userRepository.save(user);
    }
}
