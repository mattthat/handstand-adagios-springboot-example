package org.tadalabs.sample.data.dynamo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.tadalabs.sample.core.domain.TodoState;
import org.tadalabs.sample.data.dynamo.converter.TodoStateConverter;

@DynamoDBTable(tableName = "todo")
public class TodoEntity {

    private String todoId;
    private String sessionId;
    private String value;

    private TodoState todoState;

    private String createdDate;
    private String lastModifiedDate;

    public TodoEntity() {}

    public TodoEntity(String value, String sessionId, TodoState todoState) {
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

    @DynamoDBHashKey(attributeName = "todo_id")
    @DynamoDBAutoGeneratedKey
    public String getTodoId() {
        return this.todoId;
    }

    @DynamoDBAttribute(attributeName="value")
    public String getValue() {
        return value;
    }

    @DynamoDBAttribute(attributeName="session_id")
    public String getSessionId() {
        return sessionId;
    }

    @DynamoDBAttribute(attributeName="todo_state")
    @DynamoDBTypeConverted(converter = TodoStateConverter.class)
    public TodoState getTodoState() {
        return todoState;
    }

    public void setTodoState(TodoState todoState) {
        this.todoState = todoState;
    }

    @DynamoDBAttribute(attributeName = "created_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @DynamoDBAttribute(attributeName = "last_modified_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.ALWAYS)
    public String getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setTodoId(String id) {
        this.todoId = id;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
