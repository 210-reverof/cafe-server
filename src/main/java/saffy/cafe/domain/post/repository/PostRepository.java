package saffy.cafe.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saffy.cafe.domain.post.data.entity.Post;

import java.util.*;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post save(Post post);
    Optional<Post> findById(Integer id);

}
