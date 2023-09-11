package com.example.blogproject.repository;

import com.example.blogproject.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Integer> {
//    @Modifying
//    @Query(value = "UPDATE board SET count = count + 1 WHERE id = :id", nativeQuery = true)
//    void incrementViews(@Param("id") int id);
}
