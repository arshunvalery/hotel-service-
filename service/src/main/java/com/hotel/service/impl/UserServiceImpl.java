package com.hotel.service.impl;

import com.hotel.dao.api.UsrDao;
import com.hotel.dao.model.User;
import com.hotel.dao.model.enums.SortedKey;
import com.hotel.service.api.mapper.UserMapper;
import com.hotel.service.api.service.UserService;
import com.hotel.service.dto.UserDto;
import com.hotel.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UsrDao userDao;

    @Transactional
    @Override
    public UserDto create(UserDto dto) {
        log.info("Я - МЕТОД СОЗДАНИЯ В СЕРВИСЕ ЮЗЕРА!!!");
        User entity = mapper.toEntity(dto);
        return mapper.toDto(userDao.save(entity));
    }

    @Transactional
    @Override
    public Collection<UserDto> findAll() {
        log.info("Я - МЕТОД ВЫВОДА ВСЕГО В СЕРВИСЕ ЮЗЕРА!!!");
        return userDao.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto findById(UUID id) {
        log.info("Я - МЕТОД ВЫВОДА ОДНОГО В СЕРВИСЕ ЮЗЕРА!!!");
        return userDao.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        log.info("Я - МЕТОД УДАЛЕНИЯ ОДНОГО В СЕРВИСЕ ЮЗЕРА!!!");
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(UserDto dto) {
        log.info("Я - МЕТОД ИЗМЕНЕНИЯ ОДНОГО В СЕРВИСЕ ЮЗЕРА!!!");
        User entity = mapper.toEntity(dto);
        userDao.save(entity);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Я - МЕТОД АКТИВАЦИИ АДМИНА В СЕРВИСЕ ЮЗЕРА!!!");
        User user = userDao.loadUserByUsername(userName);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getStatus().getRole()))
        );
    }

    @Transactional
    @Override
    public Collection<UserDto> sortedByKey(SortedKey key) {
        log.info("Я - МЕТОД ВЫВОДА ВСЕГО ОТСОРТИРОВАННОГО В СЕРВИСЕ ЮЗЕРА!!!");
        return userDao.findAll()
                .stream()
                .sorted(getComparator(key))
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public Comparator<User> getComparator(SortedKey key) {
        Comparator<User> comparator = null;
        switch (key) {
            case USERNAME:
                comparator = Comparator.comparing(User::getUsername);
                break;
            case STATUS:
                comparator = Comparator.comparing(User::getStatus);
                break;
            case PASSWORD:
                comparator = Comparator.comparing(User::getPassword);
                break;
        }
        return comparator;
    }
}
