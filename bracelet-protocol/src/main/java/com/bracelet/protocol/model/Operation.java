package com.bracelet.protocol.model;

import java.util.Objects;

public class Operation{

    private int index;
    private int parentIndex;
    private long responseTimestamp;
    private String content;

    private Operation() {
    }

    public Operation(int index, int parentIndex, long responseTimestamp, String content) {
        this.index = index;
        this.parentIndex = parentIndex;
        this.responseTimestamp = responseTimestamp;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(long responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operation operation = (Operation) o;
        return index == operation.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
