package com.epam.jmp.nosql.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.epam.jmp.nosql.model.Category;
import com.epam.jmp.nosql.model.Subtask;
import com.epam.jmp.nosql.model.Task;
import com.epam.jmp.nosql.repository.TaskRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TaskService {

    private static final String TOTAL_COUNT_TEMPLATE = "Total task count: {0}";
    private static final String TASK_HEADER_TEMPLATE = "----- {0} -----";
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void findAll() {
        List<Task> result = repository.findAll();
        printResult(result, "All tasks");
    }

    public void findAllOverdue() {
        List<Task> result = repository.findOverdueTasks();
        printResult(result, "Overdue tasks");
    }

    public void findBySpecificCategory(Category category) {
        String jobName = MessageFormat.format("Tasks by category [{0}]", category);
        List<Task> result = repository.findTasksBySpecificCategory(category);
        printResult(result, jobName);
    }

    public void findSubtasksBySpecificCategory(Category category) {
        String jobName = MessageFormat.format("Subtasks by category [{0}]", category);
        List<Task> result = repository.findSubtasksBySpecificCategory(category);
        printResult(result, jobName);
    }

    public void searchByDescription(String text) {
        String jobName = MessageFormat.format("Search by task description. Search value=[{0}]", text);
        List<Task> result = repository.findTasksByDescription(text);
        printResult(result, jobName);
    }

    public void searchBySubtaskDescription(String text) {
        String jobName = MessageFormat.format("Search by subtask description. Search value=[{0}]", text);
        List<Task> result = repository.findTasksBySubtasksDescription(text);
        printResult(result, jobName);
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void insert(String taskId, Subtask subtask) {
        Task task = repository.findById(taskId).orElseThrow(NoSuchElementException::new);
        task.setSubtasks(List.of(subtask));

        repository.save(task);
    }

    public void update(Task task) {
        repository.save(task);
    }

    public void delete(String taskId) {
        repository.deleteById(taskId);
    }

    private void printResult(List<Task> result, String name) {
        String header = MessageFormat.format(TASK_HEADER_TEMPLATE, name);
        System.out.println(header);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ". " + result.get(i));
        }

        System.out.println(MessageFormat.format(TOTAL_COUNT_TEMPLATE, result.size()));
        System.out.println("--------------------------------------------------------\n");
    }
}
