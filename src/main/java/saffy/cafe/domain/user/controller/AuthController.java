package saffy.cafe.domain.user.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import saffy.cafe.domain.user.data.dto.req.LoginReqDto;
import saffy.cafe.domain.user.data.dto.res.LoginResDto;
import saffy.cafe.domain.user.data.entity.User;
import saffy.cafe.domain.user.filter.JwtTokenProvider;
import saffy.cafe.domain.user.repository.UserRepository;
import saffy.cafe.domain.user.service.AuthService;
import saffy.cafe.response.DefaultRes;
import saffy.cafe.response.StatusCode;

@Api(tags="auth", value = "인증 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReqDto loginReqDto) {
        LoginResDto loginResDto = authService.login(loginReqDto.getKakaoAccessToken());
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "로그인 성공 및 토큰 발급 완료", loginResDto), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity test(@RequestHeader("Authorization") String token) {
        User user = userRepository.findByKakaoId(jwtTokenProvider.getKaKaoId(token.substring(7))).get();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "토큰 테스트", user.getNickname()), HttpStatus.OK);
    }
}
