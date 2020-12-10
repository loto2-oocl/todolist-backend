package com.oocl.todolist.repository;

import com.oocl.todolist.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
}
