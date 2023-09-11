package com.example.blogproject.repository;

import com.example.blogproject.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {
}
