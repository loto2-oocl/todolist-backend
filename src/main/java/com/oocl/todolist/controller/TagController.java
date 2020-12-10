package com.oocl.todolist.controller;

import com.oocl.todolist.model.Tag;
import com.oocl.todolist.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getAll() {
        return this.tagService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody Tag newTag) {
        return this.tagService.create(newTag);
    }

    @PutMapping("/{tagId}")
    public Tag update(@PathVariable String tagId, @RequestBody Tag updateTag) {
        return this.tagService.update(tagId, updateTag);
    }

    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String tagId) {
        this.tagService.delete(tagId);
    }
}

