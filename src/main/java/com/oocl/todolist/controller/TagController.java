package com.oocl.todolist.controller;

import com.oocl.todolist.dto.TagRequest;
import com.oocl.todolist.dto.TagResponse;
import com.oocl.todolist.mapper.TagMapper;
import com.oocl.todolist.model.Tag;
import com.oocl.todolist.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @GetMapping
    public List<TagResponse> getAll() {
        return this.tagService.getAll().stream()
                .map(tagMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponse create(@RequestBody TagRequest newTagRequest) {
        Tag tag = this.tagMapper.toEntity(newTagRequest);
        Tag createdTag = this.tagService.create(tag);

        return this.tagMapper.toResponse(createdTag);
    }

    @PutMapping("/{tagId}")
    public TagResponse update(@PathVariable String tagId, @RequestBody TagRequest updateTagRequest) {
        Tag tag = this.tagMapper.toEntity(updateTagRequest);
        Tag updatedTag = this.tagService.update(tagId, tag);

        return this.tagMapper.toResponse(updatedTag);
    }

    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String tagId) {
        this.tagService.delete(tagId);
    }
}

