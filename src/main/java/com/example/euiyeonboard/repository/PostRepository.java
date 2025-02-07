package com.example.euiyeonboard.repository;
import com.example.euiyeonboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    default Post findOne(Long id){
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다!"));
    }

    Page<Post> findAll(Pageable pageable);


    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    Page<Post> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    Page<Post> findByTitle(@Param("keyword") String keyword, Pageable pageable);
}
