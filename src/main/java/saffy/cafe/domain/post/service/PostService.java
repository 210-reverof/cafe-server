package saffy.cafe.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saffy.cafe.domain.post.data.dto.req.AddPostReqDto;
import saffy.cafe.domain.post.data.dto.req.UpdatePostReqDto;
import saffy.cafe.domain.post.data.dto.res.PostResDto;
import saffy.cafe.domain.post.data.entity.Post;
import saffy.cafe.domain.post.repository.PostRepository;
import saffy.cafe.domain.post.repository.PostRepositorySupport;
import saffy.cafe.domain.user.data.entity.User;
import saffy.cafe.domain.user.repository.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostRepositorySupport postRepositorySupport;
    private final UserRepository userRepository;

    public int addPost(String kakaoId, AddPostReqDto addPostReqDto) {
        User user = userRepository.findByKakaoId(kakaoId).get();
        return postRepository.save(Post.builder()
                        .user(user)
                        .title(addPostReqDto.getTitle())
                        .content(addPostReqDto.getContent())
                        .category(addPostReqDto.getCategory())
                        .departures(addPostReqDto.getDepartures())
                        .arrivals(addPostReqDto.getArrivals())
                        .headCount(addPostReqDto.getHeadCount())
                        .time(addPostReqDto.getTime())
                        .openChattingUrl(addPostReqDto.getOpenChattingUrl())
                        .productUrl(addPostReqDto.getProductUrl())
                        .location(addPostReqDto.getLocation())
                        .product(addPostReqDto.getProduct())
                        .price(addPostReqDto.getPrice())
                        .build()
                ).getId();
    }

    public List<PostResDto> getAllPost() {
        return postRepositorySupport.getAllPosts();
    }

    public boolean deletePost(Integer postId) {
        if (!postRepository.existsById(postId)) return false;
        postRepositorySupport.deletePost(postId);
        return true;
    }

    public PostResDto getCurrentPost(Integer postId) {
        if (!postRepository.existsById(postId)) return null;
        return postRepositorySupport.getCurrentPost(postId);
    }

    public List<PostResDto> getUserPost(Integer userId) {
        return postRepositorySupport.getUserPosts(userId);
    }

    public Integer updatePost(Integer postId, UpdatePostReqDto updatePostReqDto) {
        if (!postRepository.existsById(postId)) return -1;
        postRepositorySupport.updatePosts(postId, updatePostReqDto);
        return postId;
    }




}
