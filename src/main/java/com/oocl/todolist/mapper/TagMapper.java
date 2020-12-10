package com.oocl.todolist.mapper;

import com.oocl.todolist.dto.TagRequest;
import com.oocl.todolist.dto.TagResponse;
import com.oocl.todolist.model.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public Tag toEntity(TagRequest tagRequest) {
        Tag tag = new Tag();

        BeanUtils.copyProperties(tagRequest, tag);

        return tag;
    }

    public TagResponse toResponse(Tag tag) {
        TagResponse tagResponse = new TagResponse();

        BeanUtils.copyProperties(tag, tagResponse);

        return tagResponse;
    }
}
