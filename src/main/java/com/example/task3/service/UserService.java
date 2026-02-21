package com.example.task3.service;
import com.example.task3.model.User;
import com.example.task3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        System.out.println("Пользователь создан");
        return savedUser;
    }


    public Optional<User> getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        System.out.println("Пользователь с id" + id + "найден");
        return user;
    }


    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
        return users;
    }


    public User updateUser(int id, String username, String mail, int date) {
        Optional<User> optionalUser = getUserById(id);
        User user = optionalUser.orElseThrow();
        user.setUsername(username);
        user.setMail(mail);
        user.setDate(date);
        System.out.println("Пользователь" + user.getUsername()  + "был успешно изменен");
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("юзер не найден"));
        System.out.println("удалили: " + user.getUsername());
        userRepository.delete(user);
    }

}
