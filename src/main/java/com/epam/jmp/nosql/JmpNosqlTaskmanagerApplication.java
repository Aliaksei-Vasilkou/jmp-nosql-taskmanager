package com.epam.jmp.nosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.epam.jmp.nosql.model.Category;
import com.epam.jmp.nosql.service.TaskService;

@SpringBootApplication
public class JmpNosqlTaskmanagerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JmpNosqlTaskmanagerApplication.class, args);
        TaskService taskService = context.getBean(TaskService.class);

        taskService.findAll();
        taskService.findAllOverdue();
        taskService.findBySpecificCategory(Category.HOME);
        taskService.findSubtasksBySpecificCategory(Category.WORK);
        taskService.searchByDescription("felis");
        taskService.searchBySubtaskDescription("elementum");
    }
}
