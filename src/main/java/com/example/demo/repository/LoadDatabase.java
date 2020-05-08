//package com.example.demo.repository;
//
//import com.example.demo.model.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//
//
//@Configuration
//@Slf4j
//class LoadDatabase {
//
//    @Bean
//    String initDatabase( TestRepository testRepository,
//                                    PracticalTaskRepository ptaskRepository,
//                                    UserRepository userRepository,
//                                    TestTaskRepository ttaskRepository,
//                                     TestAnswerRepository tAnswerRepository,
//                                     PracticalAnswerRepository pAnswerRepository) {
//
//        //one test
//        Test test = new Test("test");
//        testRepository.save(test);
//
//        //two PracticalTask
//        PracticalTask task1 = new PracticalTask("Select all from table", "Select cats from table", 8, test);
//        PracticalTask task2 = new PracticalTask("Select cats from table", "Select * from table", 8, test);
//        ptaskRepository.save(task1);
//        ptaskRepository.save(task2);
//
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("vatiant 1");
//        answers.add("vatiant 2");
//        answers.add("vatiant 3");
//        answers.add("vatiant 4");
//
//        //two TestTask
//        TestTask task3 = new TestTask("Choose what you want", 8, answers,answers, test);
//        TestTask task4 = new TestTask("Choose right answers", 8, answers,answers, test);
//        ttaskRepository.save(task4);
//        ttaskRepository.save(task3);
//
//        //two PracticalAnswer
//        PracticalAnswer answer1 = new PracticalAnswer("Select cats from table", true, task1 );
//        PracticalAnswer answer2 = new PracticalAnswer("Select * from table", true,task2 );
//        pAnswerRepository.save(answer1);
//        pAnswerRepository.save(answer2);
//
//        //two TestAnswer
//        TestAnswer answer3 = new TestAnswer(answers, true, task3);
//        TestAnswer answer4 = new TestAnswer(answers, true, task4);
//        tAnswerRepository.save(answer3);
//        tAnswerRepository.save(answer4);
//
//
//        User user = new User("Иванов", "Петр", "Иванович", 1, "22");
//        userRepository.save(user);
//
//        return "success";
//    }
//}
