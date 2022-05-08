package de.htwberlin.webtech.einundzwanzig.service;

import de.htwberlin.webtech.einundzwanzig.persistence.UserEntity;
import de.htwberlin.webtech.einundzwanzig.persistence.UserRepository;
import de.htwberlin.webtech.einundzwanzig.web.api.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userEntity ->  new User(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getCoins(), userEntity.getWins(), userEntity.getLosses(), userEntity.getDraws()))
                .collect(Collectors.toList());
    }
}