package com.agro.agro.service;

import com.agro.agro.dto.CommentDto;
import com.agro.agro.exceptions.ProductNotFoundException;
import com.agro.agro.mapper.CommentMapper;
import com.agro.agro.model.Comment;
import com.agro.agro.model.NotificationEmail;
import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import com.agro.agro.repository.CommentRepository;
import com.agro.agro.repository.ProductRepository;
import com.agro.agro.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentDto commentsDto) {
        Product product = productRepository.findById(commentsDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(commentsDto.getProductId().toString()));
        Comment comment = commentMapper.map(commentsDto, product, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(product.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, product.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    // TODO: collect(toList()) might be a problem!
    public List<CommentDto> getAllCommentsForProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId.toString()));
        return commentRepository.findByProduct(product)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    // TODO: collect(toList()) might be a problem!
    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
