package com.epam.jmp.nosql.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
@Document("TaskCollection")
public class Task {

    @Id
    private String id;

    private String name;

    private String description;

    private Category category;

    private LocalDate creationDate;

    private LocalDate deadlineDate;

    private List<Subtask> subtasks;
}
