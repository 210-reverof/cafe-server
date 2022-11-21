package saffy.cafe.domain.post.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffy.cafe.domain.post.data.dto.req.AddPostReqDto;
import saffy.cafe.domain.post.data.dto.req.UpdatePostReqDto;
import saffy.cafe.domain.post.data.dto.res.PostResDto;
import saffy.cafe.domain.post.data.entity.Post;
import saffy.cafe.domain.post.service.PostService;
import saffy.cafe.domain.user.data.dto.res.OnboardingResDto;
import saffy.cafe.domain.user.filter.JwtTokenProvider;
import saffy.cafe.domain.user.service.UserService;
import saffy.cafe.response.DefaultRes;
import saffy.cafe.response.StatusCode;

import java.util.List;

@Api(tags="post", value = "게시물 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final JwtTokenProvider jwtTokenProvider;
    private final PostService postService;

    @PostMapping("")
    public ResponseEntity addPost(@RequestHeader("Authorization") String token, @RequestBody AddPostReqDto addPostReqDto) {
        int id = postService.addPost(jwtTokenProvider.getKaKaoId(token.substring(7)), addPostReqDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "게시글 추가 완료", id), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Integer postId) {
        boolean exsits = postService.deletePost(postId);
        return exsits? new ResponseEntity(DefaultRes.res(StatusCode.OK, "게시글 삭제 완료"), HttpStatus.OK)
                : new ResponseEntity(DefaultRes.res(StatusCode.OK, "게시글 삭제 실패 (없는 게시글 아이디)"), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getAllPost() {
        List<PostResDto> posts = postService.getAllPost();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "모든 게시글 반환 완료", posts), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity getCurrentPost(@PathVariable("postId") Integer postId) {
        PostResDto post = postService.getCurrentPost(postId);
        return post != null? new ResponseEntity(DefaultRes.res(StatusCode.OK, "게시글 반환 완료", post), HttpStatus.OK)
                : new ResponseEntity(DefaultRes.res(StatusCode.OK, "게시글 반환 실패 (없는 게시글 아이디)"), HttpStatus.OK);
    }

    @PostMapping("/{postId}")
    public ResponseEntity updatePost(@PathVariable("postId") Integer postId, @RequestBody UpdatePostReqDto updatePostReqDto) {
        int id = postService.updatePost(postId, updatePostReqDto);
        return id != -1? new ResponseEntity(DefaultRes.res(StatusCode.OK, "게시글 수정 완료", id), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "게시글 수정 실패 (없는 게시글 아이디)"), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getUserPost(@PathVariable("userId") Integer userId) {
        List<PostResDto> posts = postService.getUserPost(userId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "사용자 게시글 반환 완료", posts), HttpStatus.OK);
    }


}
