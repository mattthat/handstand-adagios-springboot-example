package org.tadalabs.sample.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tadalabs.sample.entity.TodoEntity;

import java.util.Optional;

@EnableScan
@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, String> {

    Optional<TodoEntity> findByTodoId(String sessionId);

}

