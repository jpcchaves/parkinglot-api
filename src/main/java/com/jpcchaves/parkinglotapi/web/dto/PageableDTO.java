package com.jpcchaves.parkinglotapi.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PageableDTO<T> {
    private List<T> content = new ArrayList<>();
    private boolean isFirst;
    private boolean isLast;

    @JsonProperty("pageNo")
    private int number;
    private int size;
    @JsonProperty("pageElements")
    private int numberOfElements;
    private int totalPages;
    private int totalElements;

    public PageableDTO() {
    }

    public PageableDTO(List<T> content,
                       boolean isFirst,
                       boolean isLast,
                       int number,
                       int size,
                       int numberOfElements,
                       int totalPages,
                       int totalElements) {
        this.content = content;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.number = number;
        this.size = size;
        this.numberOfElements = numberOfElements;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
