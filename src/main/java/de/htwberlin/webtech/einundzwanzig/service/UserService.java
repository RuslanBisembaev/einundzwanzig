package de.htwberlin.webtech.einundzwanzig.service;

import de.htwberlin.webtech.einundzwanzig.persistence.UserEntity;
import de.htwberlin.webtech.einundzwanzig.persistence.UserRepository;
import de.htwberlin.webtech.einundzwanzig.web.api.User;
import de.htwberlin.webtech.einundzwanzig.web.api.UserManipulationRequest;
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

    public User create(UserManipulationRequest request){
        var userEntity = new UserEntity(request.getUsername(), request.getCoins(), request.getWins(), request.getLosses(), request.getDraws());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public User update(Long id, UserManipulationRequest request){
        var userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()){
            return null;
        }
        var userEntity = userEntityOptional.get();
        userEntity.setUsername(request.getUsername());
        userEntity.setCoins(request.getCoins());
        userEntity.setWins(request.getWins());
        userEntity.setLosses(request.getLosses());
        userEntity.setDraws(request.getDraws());

        userEntity = userRepository.save(userEntity);

        return transformEntity(userEntity);
    }

    public boolean deleteById(Long id){
        if (!userRepository.existsById(id)){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }


    public User transformEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getCoins(),
                userEntity.getWins(),
                userEntity.getLosses(),
                userEntity.getDraws());
    }
}