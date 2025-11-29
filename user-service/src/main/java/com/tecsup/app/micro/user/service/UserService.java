package com.tecsup.app.micro.user.service;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.UserEntity;
import com.tecsup.app.micro.user.mapper.UserMapper;
import com.tecsup.app.micro.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public User getUserById(Long id){
        log.info("Getting user by id: {}", id);
        
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return mapper.toDomain(entity);
    }


    public List<User> getAllUsers() {

        List<UserEntity> entities = userRepository.findAll();
        return this.mapper.toDomain(entities);
    }
}