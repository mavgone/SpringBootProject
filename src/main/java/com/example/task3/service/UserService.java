package com.example.task3.service;
import com.example.task3.model.User;
import com.example.task3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        System.out.println("юзер " + user.getUsername() + " создан");
        return savedUser;
    }


    public Optional<User> getUserById(Long id) {
        if (id == null || id <= 0) {
            System.out.println("айди " + id + " неадекватный братишка");
            return Optional.empty();
        }

        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> System.out.println("по id " + id +
                " мы нашли пользователя с именем " +
                value.getUsername()));
        return user;
    }


    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
        return users;
    }


    public User updateUser(Long id, String username, String mail, LocalDate date) {
        Optional<User> optionalUser = getUserById(id);
        User user = optionalUser.orElseThrow();
        user.setUsername(username);
        user.setMail(mail);
        user.setDate(date);

        User updatedUser = userRepository.save(user);
        System.out.println("юзер с id " + id + " обновлен");
        return updatedUser;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("юзер не найден"));
        System.out.println("удалили: " + user.getUsername());
        userRepository.delete(user);
    }
}