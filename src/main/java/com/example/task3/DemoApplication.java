package com.example.task3;


import com.example.task3.model.User;
import com.example.task3.repository.UserRepository;
import com.example.task3.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, UserService userService) {
        return (args) -> {
            // Создание, сохранение
            User user1 = new User("чюпепчик", "alyaulu@mail.ru", LocalDate.now());
            User user2 = new User("аляулю", "chupepchik@mail.ru", LocalDate.now());
            User user3 = new User("анпала", "anpala@mail.ru", LocalDate.now());
            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);

            // Поиск по id
            userService.getUserById(1L);
            userService.getUserById(2L);

            // Апдейт
            userService.updateUser(1L, "леон", "leon@mail.ru", LocalDate.now());
            userService.updateUser(3L, "ада", "ada@mail.ru", LocalDate.now());

            // Удаление
            userService.deleteUser(1L);
        };
    }
}

