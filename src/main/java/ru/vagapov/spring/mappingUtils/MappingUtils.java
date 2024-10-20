package ru.vagapov.spring.mappingUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.service.Impl.UserRolesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Маппер для превращения UserEntity в UserDto и наоборот
 */
@Component
public class MappingUtils {

    private final PasswordEncoder passwordEncoder;
    private final UserRolesService userRolesService;

    public MappingUtils(PasswordEncoder passwordEncoder, UserRolesService userRolesService) {
        this.passwordEncoder = passwordEncoder;
        this.userRolesService = userRolesService;
    }

    public User userEntityToUserDto(Optional<UserEntity> userEntity) {
        User user = new User();
        user.setId(userEntity.orElseThrow().getId());
        user.setAge(userEntity.orElseThrow().getAge());
        user.setUserName(userEntity.orElseThrow().getUserName());
        user.setPassword(userEntity.orElseThrow().getPassword());
        user.setEmail(userEntity.orElseThrow().getEmail());
        user.setLastName(userEntity.orElseThrow().getLastName());
        user.setRoles(userRolesService.roleOfEntityToRoleDto(userEntity.orElseThrow().getAuthorities()));
        return user;
    }

    public UserEntity userDtoToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(user.getAge());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmail(user.getEmail());
        userEntity.setLastName(user.getLastName());
        userEntity.setRoles(userRolesService.roleOfDtoToRoleEntity(user.getRoles()));
        return userEntity;
    }

    public UserEntity userDtoToUserEntityLogin(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(user.getAge());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setLastName(user.getLastName());
        userEntity.setRoles(userRolesService.roleOfDtoToRoleEntity(user.getRoles()));
        return userEntity;
    }

    public List<User> listOfUserEntityToListOfUserDto(List<UserEntity> userEntityList) {
        List<User> listOfUserDto = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            listOfUserDto.add(userEntityToUserDto(Optional.ofNullable(userEntity)));
        }
        return listOfUserDto;
    }
}
