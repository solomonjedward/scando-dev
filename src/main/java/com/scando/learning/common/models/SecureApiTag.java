package com.scando.learning.common.models;

import springfox.documentation.service.Tag;

import java.util.ArrayList;
import java.util.List;

public enum SecureApiTag {

    STUDENT_MANAGEMENT(SwaggerHeads.STUDENT_API, SwaggerHeads.STUDENT_API_DESCRIPTION),
    TEACHER_MANAGEMENT(SwaggerHeads.TEACHER_API, SwaggerHeads.TEACHER_API_DESCRIPTION),
    AUTH_MANAGEMENT(SwaggerHeads.AUTH_API, SwaggerHeads.AUTH_API_DESCRIPTION),

    ;

    private final String name;

    private final String description;

    private SecureApiTag(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Tag[] getAll() {
        List<Tag> tags = new ArrayList<>();
        for (SecureApiTag secureApiTag : values()) {
            tags.add(new Tag(secureApiTag.name, secureApiTag.description));
        }
        Tag[] tagArray = new Tag[tags.size()];
        return tags.toArray(tagArray);
    }
}
