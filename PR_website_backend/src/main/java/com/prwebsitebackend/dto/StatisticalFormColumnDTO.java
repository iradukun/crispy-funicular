package com.prwebsitebackend.dto;

public class StatisticalFormColumnDTO {
    private Long id;
    private String name;
    private String column_function;
    private String column_condition;
    private String column_option;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn_function() {
        return column_function;
    }

    public void setColumn_function(String column_function) {
        this.column_function = column_function;
    }

    public String getColumn_condition() {
        return column_condition;
    }

    public void setColumn_condition(String column_condition) {
        this.column_condition = column_condition;
    }

    public String getColumn_option() {
        return column_option;
    }

    public void setColumn_option(String column_option) {
        this.column_option = column_option;
    }
}
