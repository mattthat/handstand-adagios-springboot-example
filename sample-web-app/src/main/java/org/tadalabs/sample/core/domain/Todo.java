package org.tadalabs.sample.core.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class Todo implements Serializable {

    private String todoId;
    private String value;
    private String sessionId;
    private TodoState todoState;

    public Todo() {}

    public Todo(String todoId, String value, String sessionId, TodoState todoState) {
        this.todoId = todoId;
        this.value = value;
        this.sessionId = sessionId;
        this.todoState = todoState;
    }

    @Override
    public boolean equals(Object other) {
        return getClass() == other.getClass() && EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getValue() {
        return value;
    }

    public String getTodoId() {
        return todoId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public TodoState getTodoState() {
        return todoState;
    }

    public void setTodoState(TodoState todoState) {
        this.todoState = todoState;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
