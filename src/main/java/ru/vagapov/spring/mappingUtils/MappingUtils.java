package ru.vagapov.spring.mappingUtils;

import org.springframework.stereotype.Component;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер для превращения UserEntity в UserDto и наоборот
 */
@Component
public class MappingUtils {

    public User userEntityToUserDto(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setAge(userEntity.getAge());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setLastName(userEntity.getLastName());
        return user;
    }

    public UserEntity userDtoToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(user.getAge());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setLastName(user.getLastName());
        return userEntity;
    }

    public List<User> listOfUserEntityToListOfUserDto(List<UserEntity> userEntityList) {
        List<User> listOfUserDto = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            listOfUserDto.add(userEntityToUserDto(userEntity));
        }
        return listOfUserDto;
    }
}
