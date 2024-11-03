package ru.vagapov.spring.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dto.Book;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.dto.UserForLogin;
import ru.vagapov.spring.entity.BookEntity;
import ru.vagapov.spring.entity.RoleEntity;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.mappingUtils.MappingUtils;
import ru.vagapov.spring.repository.BookJPARepository;
import ru.vagapov.spring.service.UserService;

import java.util.*;

import ru.vagapov.spring.repository.UserDaoJPARepository;


@Service
public class UserServiceImpl implements UserService {

    private final MappingUtils mappingUtils;
    private final UserDaoJPARepository userDaoJPARepository;
    private final UserRolesService userRolesService;
    private final BookJPARepository bookJPARepository;

    public UserServiceImpl(MappingUtils mappingUtils, UserDaoJPARepository userDaoJPAIRepository, UserRolesService userRolesService, BookJPARepository bookJPARepository) {
        this.mappingUtils = mappingUtils;
        this.userDaoJPARepository = userDaoJPAIRepository;
        this.userRolesService = userRolesService;
        this.bookJPARepository = bookJPARepository;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        userDaoJPARepository.save(userEntity);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Set<BookEntity> books = new HashSet<>();
        List<Long> dtoBooks = user.getBooks();
        for (Long bookId : dtoBooks) {
            books.add(bookJPARepository.getReferenceById(bookId));
        }
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        userEntity.setBooks(books);
        userDaoJPARepository.save(userEntity);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDaoJPARepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        User user = mappingUtils.userEntityToUserDto(userDaoJPARepository.findById(id));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUserName(String userName) {
        UserEntity userEntity = userDaoJPARepository.findByUserName(userName);
        User user = mappingUtils.userEntityToUserDto(Optional.ofNullable(userEntity));
        return user;

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllUsersWithRoles());
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsersByLastName(String lastName) {
        List<User> users = mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllByLastName(lastName));
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsersByPartOfNameOrLastName(String partOfName) {
        List<User> users = mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllUsersByPartOfNameOrLastName(partOfName));
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsersByBookId(Long bookId) {
       return mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllUsersWithBook(bookId));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserForLogin user = findUserByUserNameForLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return user;
    }

    private UserForLogin findUserByUserNameForLogin(String name) {
        UserEntity userEntity = userDaoJPARepository.findByUserName(name);
        UserForLogin user = mappingUtils.userDtoToUserEntityLogin(userEntity);
        user.setAuthorities(userRolesService.roleOfEntityToRoleDtoForLogin(userEntity.getRoles()));
        return user;
    }
}
