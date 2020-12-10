package com.oocl.todolist.service;

import com.oocl.todolist.exception.TagNotFoundException;
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
        return this.tagRepository.findAll();
    }

    public Tag create(Tag tag) {
        return this.tagRepository.insert(tag);
    }

    public Tag update(String id, Tag newTag) {
        if(!this.tagRepository.existsById(id)) {
            throw new TagNotFoundException(id);
        }

        newTag.setId(id);
        return this.tagRepository.save(newTag);
    }

    public void delete(String id) {
        this.tagRepository.deleteById(id);
    }
}
