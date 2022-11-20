package saffy.cafe.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saffy.cafe.domain.user.data.dto.KakaoAccount;
import saffy.cafe.domain.user.data.dto.res.LoginResDto;
import saffy.cafe.domain.user.data.entity.User;
import saffy.cafe.domain.user.filter.JwtTokenProvider;
import saffy.cafe.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResDto login(String kakaoAccessToken) {
        KakaoAccount kakaoAccount = getKaKaoAccount(kakaoAccessToken);

        int id = -1;
        if (!userRepository.existByKaKaoId(kakaoAccount.getKakaoId())) {
            id = save(kakaoAccount);
        }

        if (id == -1) return null;

        return new LoginResDto(jwtTokenProvider.createToken(kakaoAccount.getKakaoId(),"USER"));
    }

    private int save(KakaoAccount kakaoAccount) {
        return userRepository.save(
                User.builder()
                .kakaoId(kakaoAccount.getKakaoId())
                .nickname(kakaoAccount.getNickname())
                .build()).getId();
    }

    private KakaoAccount getKaKaoAccount(String kakaoAccessToken) {
        // TODO :: 카카오 요청

        String kakaoId = "111244334";
        String nickname = "홍길동";

        return new KakaoAccount(kakaoId, nickname);
    }
}
