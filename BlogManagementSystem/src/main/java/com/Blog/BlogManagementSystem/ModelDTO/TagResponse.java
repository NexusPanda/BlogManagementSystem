package com.Blog.BlogManagementSystem.ModelDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {

    private List<TagDTO> tagDTOList;
}
