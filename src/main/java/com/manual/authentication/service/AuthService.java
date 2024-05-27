package com.manual.authentication.service;

import com.manual.authentication.dao.entity.User;
import com.manual.authentication.dao.repository.UserRepository;
import com.manual.authentication.dto.AuthRequestDTO;
import com.manual.authentication.dto.AuthResponseDTO;
import com.manual.authentication.dto.JwtPayload;
import com.manual.authentication.jwt.JwtUtil;
import com.manual.authentication.util.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO){
        Optional<User> userOpt = userRepository.findByUserCode(authRequestDTO.getUserCode());
        if(userOpt.isPresent()){
            User user = userOpt.get();

            String encodedPassword = user.getPassword();
            String rawPassword = authRequestDTO.getPassword();

            if(PasswordEncoderUtil.matches(rawPassword, encodedPassword)){
                JwtPayload jwtPayload = new JwtPayload();
                jwtPayload.setUserId(user.getId());
                String jwt = JwtUtil.generateToken(jwtPayload);

                AuthResponseDTO authResponseDTO = new AuthResponseDTO();
                authResponseDTO.setJwt(jwt);
                authResponseDTO.setStatus(true);
                authResponseDTO.setMessage("Success!");

                return authResponseDTO;
            }
        }
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setStatus(false);
        authResponseDTO.setMessage("Username or password is wrong!");

        return authResponseDTO;
    }
}
