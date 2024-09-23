package ru.vagapov.spring.MappingUtils;

import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;

/**
 * Маппер для превращения UserEntity в UserDto и наоборот
 */
public class MappingUtils {
    public User userEntityToUserDto(UserEntity userEntity) {
        User user = new User();
        user.setAge(userEntity.getAge());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setLastName(userEntity.getLastName());
        return user;
    }

    public UserEntity userDtoToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setAge(user.getAge());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setLastName(user.getLastName());
        return userEntity;
    }
}
