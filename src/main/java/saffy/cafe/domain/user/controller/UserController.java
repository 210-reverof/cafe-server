package saffy.cafe.domain.user.controller;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import saffy.cafe.domain.user.data.dto.res.OnboardingResDto;
import saffy.cafe.domain.user.filter.JwtTokenProvider;
import saffy.cafe.domain.user.service.UserService;
import saffy.cafe.response.DefaultRes;
import saffy.cafe.response.StatusCode;

@Api(tags="user", value = "사용자 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @GetMapping("/onboarding")
    public ResponseEntity register(@RequestHeader("Authorization") String token) {
        OnboardingResDto onboardingResDto = userService.getOnboarding(jwtTokenProvider.getKaKaoId(token.substring(7)));
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "사용자 정보 반환 완료", onboardingResDto), HttpStatus.OK);
    }


}
