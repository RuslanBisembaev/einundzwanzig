package de.htwberlin.webtech.einundzwanzig.service;

import de.htwberlin.webtech.einundzwanzig.persistence.UserEntity;
import de.htwberlin.webtech.einundzwanzig.persistence.UserRepository;
import de.htwberlin.webtech.einundzwanzig.web.api.User;
import de.htwberlin.webtech.einundzwanzig.web.api.UserCreateRequest;
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
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public User findById(Long id){
        var userEntity = userRepository.findById(id);
        return userEntity.map(this::transformEntity).orElse(null);
    }

    public User create(UserCreateRequest request){
        var userEntity = new UserEntity(request.getUsername(), request.getEmail(), request.getCoins(), request.getWins(), request.getLosses(), request.getDraws());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public User transformEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getCoins(),
                userEntity.getWins(),
                userEntity.getLosses(),
                userEntity.getDraws());
    }
}