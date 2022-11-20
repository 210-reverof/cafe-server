package saffy.cafe.domain.user.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import saffy.cafe.domain.user.filter.JwtTokenProvider;
import saffy.cafe.response.DefaultRes;
import saffy.cafe.response.StatusCode;

@Api(tags="user", value = "사용자 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity register() {

        String token = jwtTokenProvider.createToken("wonyoung", "USER");
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "회원 가입 완료", ""), HttpStatus.OK);
    }

    @PostMapping("/update-user")
    public ResponseEntity updateUserData() {

        String token = jwtTokenProvider.createToken("wonyoung", "USER");
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "회원 정보 수정", ""), HttpStatus.OK);
    }

}
