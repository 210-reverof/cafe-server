package saffy.cafe.domain.user.data.dto.res;

import lombok.Getter;

@Getter
public class LoginResDto {
    private String accessToken;

    public LoginResDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
