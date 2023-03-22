package com.agro.agro.repository;

import com.agro.agro.model.Comment;
import com.agro.agro.model.Product;
import com.agro.agro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProduct(Product product);

    List<Comment> findAllByUser(User user);
}
