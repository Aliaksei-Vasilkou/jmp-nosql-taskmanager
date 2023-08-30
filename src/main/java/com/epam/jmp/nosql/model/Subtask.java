package com.epam.jmp.nosql.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
public class Subtask {

    private String name;

    private String description;
}
