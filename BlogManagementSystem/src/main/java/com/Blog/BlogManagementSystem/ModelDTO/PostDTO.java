package com.Blog.BlogManagementSystem.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private boolean published;

    private Long categoryId;
    private List<Long> tagIds;

    private String authorName;
}
