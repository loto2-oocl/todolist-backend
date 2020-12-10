package com.oocl.todolist.service;

import com.oocl.todolist.model.Tag;
import com.oocl.todolist.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAll() {
        return null;
    }
}
