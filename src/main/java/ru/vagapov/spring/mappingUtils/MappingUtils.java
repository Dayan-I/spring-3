package ru.vagapov.spring.mappingUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.dto.UserForLogin;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.service.impl.UserRolesService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Маппер для превращения UserEntity в UserDto и наоборот
 */
@Component
public class MappingUtils {

    private final PasswordEncoder passwordEncoder;
    private final UserRolesService userRolesService;
    private final BookMapper bookMapper;



    public MappingUtils(PasswordEncoder passwordEncoder, UserRolesService userRolesService, BookMapper bookMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRolesService = userRolesService;
        this.bookMapper = bookMapper;
    }

    public User userEntityToUserDto(Optional<UserEntity> userEntity) {
        User user = new User();
        user.setId(userEntity.orElseThrow().getId());
        user.setAge(userEntity.orElseThrow().getAge());
        user.setUserName(userEntity.orElseThrow().getUserName());
        user.setPassword(userEntity.orElseThrow().getPassword());
        user.setEmail(userEntity.orElseThrow().getEmail());
        user.setLastName(userEntity.orElseThrow().getLastName());
        user.setRoles(userRolesService.roleOfEntityToRoleDto(userEntity.get().getRoles()));
        user.setBooks(bookMapper.setOfBookEntityToBooksDto(userEntity.get().getBooks()));
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
        userEntity.setRoles(userRolesService.roleOfDtoToRoleEntity(user.getRoles()));

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

    public Set<UserEntity> listOfUserDtoToSetOfUserEntity(List<User> userDtoList) {
        return userDtoList.stream().map(this::userDtoToUserEntity).collect(Collectors.toSet());
    }
}
