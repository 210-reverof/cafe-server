package saffy.cafe.domain.user.data.dto;

import lombok.Getter;

@Getter
public class KakaoAccount {
    private String kakaoId;
    private String nickname;

    public KakaoAccount(String kakaoId, String nickname) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
    }
}
