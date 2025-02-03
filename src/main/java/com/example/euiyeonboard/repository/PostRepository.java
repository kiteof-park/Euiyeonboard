package com.example.euiyeonboard.repository;
import com.example.euiyeonboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    default Post findOne(Long id){
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다!"));
    }

}
