package com.agro.agro.controller;

import com.agro.agro.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentController {
    
    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        
    }
    
}
