package com.Blog.BlogManagementSystem.Controller;

import com.Blog.BlogManagementSystem.ModelDTO.TagDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;
import com.Blog.BlogManagementSystem.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/tags")
    public ResponseEntity<TagDTO> addTag(@RequestBody TagDTO tagDTO) {
        return new ResponseEntity<>(tagService.addTag(tagDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/tags/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        return new ResponseEntity<>(tagService.updateTag(id, tagDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/tags/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>("Tag deleted successfully", HttpStatus.OK);
    }

    // Public access
    @GetMapping("/public/tags")
    public ResponseEntity<List<TagDTO>> getAllTags() {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    // Public access
    @GetMapping("/public/tags/{tagId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByTag(@PathVariable Long tagId) {
        return new ResponseEntity<>(tagService.getPostsByTag(tagId), HttpStatus.OK);
    }
}
