package com.agro.agro.controller;

import com.agro.agro.dto.CommentDto;
import com.agro.agro.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by_product/{productId}")
    public ResponseEntity<List<CommentDto>> getAllCommentForProduct(@PathVariable Long productId) {
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForProduct(productId));
    }
    
}
