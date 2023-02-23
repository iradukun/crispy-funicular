package com.prwebsitebackend.model;

import javax.persistence.*;

@Entity
public class StatisticalFormColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String column_function;
    private String column_condition;
    private String column_option;
    @ManyToOne
    @JoinColumn(name="form_id")
    private StatisticalForm statisticalForm;

    public StatisticalFormColumn(String name, String column_function, String column_condition, String column_option, StatisticalForm statisticalForm) {
        this.name = name;
        this.column_function = column_function;
        this.column_condition = column_condition;
        this.column_option = column_option;
        this.statisticalForm = statisticalForm;
    }

    public StatisticalFormColumn() {

    }

    public StatisticalForm getStatisticalForm() {
        return statisticalForm;
    }

    public void setStatisticalForm(StatisticalForm statisticalForm) {
        this.statisticalForm = statisticalForm;
    }

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
