package com.mycaell.coursesspringapi.presentation.enums.dto;

import java.io.Serializable;

public class SelectOption<T> implements Serializable {

    private String label;
    private T value;

    public SelectOption() {
    }

    public SelectOption(String label, T value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SelectOption{" +
                "label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}
