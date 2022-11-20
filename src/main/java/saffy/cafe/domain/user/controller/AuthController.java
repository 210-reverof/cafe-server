package saffy.cafe.domain.user.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffy.cafe.domain.user.data.dto.req.LoginReqDto;
import saffy.cafe.domain.user.data.dto.res.LoginResDto;
import saffy.cafe.domain.user.service.AuthService;
import saffy.cafe.response.DefaultRes;
import saffy.cafe.response.StatusCode;

@Api(tags="auth", value = "인증 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReqDto loginReqDto) {
        LoginResDto loginResDto = authService.login(loginReqDto.getKakaoAccessToken());
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "로그인 성공 및 토큰 발급 완료", loginResDto), HttpStatus.OK);
    }
}
