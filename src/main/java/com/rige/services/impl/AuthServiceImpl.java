package com.rige.services.impl;

import com.rige.dtos.request.LoginRequest;
import com.rige.dtos.request.RegisterRequest;
import com.rige.entities.PersonEntity;
import com.rige.entities.RoleEntity;
import com.rige.entities.UserEntity;
import com.rige.projections.CareerCreditsUserProjection;
import com.rige.repositories.PersonRepository;
import com.rige.repositories.RoleRepository;
import com.rige.repositories.UserRepository;
import com.rige.security.JwtUtils;
import com.rige.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String login(LoginRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();


        CareerCreditsUserProjection projection = personRepository.getCareerIdByEmail(userRequest.getEmail());

        return jwtUtils.createToken(authenticatedUser.getId(), authenticatedUser.getEmail(),
                projection.getCareerId(),
                projection.getCredits(),
                authenticatedUser.getAuthorities());
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("The email address or username is already in use");
        }

        RoleEntity userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("The role USER does not exist in the database"));

        PersonEntity person = PersonEntity.builder()
                .id(registerRequest.getPersonId())
                .build();

        UserEntity newUser = UserEntity.builder()
                .email(registerRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .person(person)
                .roles(Set.of(userRole))
                .enabled(true)
                .expired(false)
                .locked(false)
                .build();

        userRepository.save(newUser);

        return "User registered successfully";
    }
}
