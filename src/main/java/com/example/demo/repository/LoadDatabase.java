package com.example.demo.repository;

import com.example.demo.model.PracticalTask;
import com.example.demo.model.Test;
import com.example.demo.model.TestResult;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    String initDatabase( TestRepository testRepository,
                                    PracticalTaskRepository taskRepository,
                                    UserRepository userRepository,
                                    TestResultRepository resultRepository) {

        Test test = new Test("test");
        testRepository.save(test);
        taskRepository.save(new PracticalTask("Test question", 5, test));
        User user = new User("Иванов", "Петр", "Иванович", 1, "22");
        userRepository.save(user);
        TestResult result = new TestResult(test, user);
         resultRepository.save(result);
        return "success";
    }
}
