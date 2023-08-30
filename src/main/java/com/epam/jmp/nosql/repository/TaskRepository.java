package com.epam.jmp.nosql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.epam.jmp.nosql.model.Category;
import com.epam.jmp.nosql.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{'deadlineDate': {'$lte': new Date()}}")
    List<Task> findOverdueTasks();

    @Query("{'category': ?0}")
    List<Task> findTasksBySpecificCategory(Category category);

    @Query(value = "{'category': ?0}", fields = "{'subtasks':  1}")
    List<Task> findSubtasksBySpecificCategory(Category category);

    @Query("{'description': {$regex: ?0 }}")
    List<Task> findTasksByDescription(String text);

    @Query("{'subtasks.description': {$regex: ?0 }}")
    List<Task> findTasksBySubtasksDescription(String text);
}
