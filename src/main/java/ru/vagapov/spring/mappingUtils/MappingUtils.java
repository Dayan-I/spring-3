package ru.vagapov.spring.mappingUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.dto.UserForLogin;
import ru.vagapov.spring.entity.UserEntity;
import java.util.List;
import java.util.Optional;

/**
 * Маппер для превращения UserEntity в UserDto и наоборот
 */
@Component
public class MappingUtils {

    private final PasswordEncoder passwordEncoder;


    public MappingUtils(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User userEntityToUserDto(Optional<UserEntity> userEntity) {
        User user = new User();
        user.setId(userEntity.orElseThrow().getId());
        user.setAge(userEntity.orElseThrow().getAge());
        user.setUserName(userEntity.orElseThrow().getUserName());
        user.setPassword(userEntity.orElseThrow().getPassword());
        user.setEmail(userEntity.orElseThrow().getEmail());
        user.setLastName(userEntity.orElseThrow().getLastName());
        return user;
    }

    public UserEntity userDtoToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setAge(user.getAge());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword((user.getPassword()));
        userEntity.setEmail(user.getEmail());
        userEntity.setLastName(user.getLastName());
        return userEntity;
    }

    public UserForLogin userDtoToUserEntityLogin(UserEntity user) {
        UserForLogin userForLogin = new UserForLogin();
        userForLogin.setUsername(user.getUserName());
        userForLogin.setPassword(user.getPassword());

        return userForLogin;
    }

    public List<User> listOfUserEntityToListOfUserDto(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(userEntity -> userEntityToUserDto(Optional.ofNullable(userEntity))).toList();

    }
}
