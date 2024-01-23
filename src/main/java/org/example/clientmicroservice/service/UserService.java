package org.example.clientmicroservice.service;


import lombok.RequiredArgsConstructor;
import org.example.clientmicroservice.dto.UserDto;
import org.example.clientmicroservice.dto.LoginDto;
import org.example.clientmicroservice.dto.SignUpDto;
import org.example.clientmicroservice.entity.User;
import org.example.clientmicroservice.exception.AppException;
import org.example.clientmicroservice.mappers.UserMapper;
import org.example.clientmicroservice.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(LoginDto loginDto){
        User user = userRepository.findByAdresseWallet(loginDto.adresseWallet())
                .orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));


            return userMapper.toClientDto(user);


    }

    public UserDto register(SignUpDto clientDto) {
        Optional<User> optionalClient = userRepository.findByAdresseWallet(clientDto.adresseWallet());

        if(optionalClient.isPresent()){
            throw new AppException("Address wallet incorrect", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToClient(clientDto);

        User savedUser = userRepository.save(user);

        return userMapper.toClientDto(savedUser);
    }

    public UserDto findByAdresseWallet(String adresseWallet){
        User user = userRepository.findByAdresseWallet(adresseWallet)
                .orElseThrow(() -> new AppException("Unknown wallet address ", HttpStatus.NOT_FOUND));

        return userMapper.toClientDto(user);

    }


}
