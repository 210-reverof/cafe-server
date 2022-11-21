package saffy.cafe.domain.user.data.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OnboardingResDto {
    private Integer id;
    private String nickname;
    private String address;
    private Integer stamps;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    public OnboardingResDto() {
    }

    public OnboardingResDto(Integer id, String nickname, String address, Integer stamps, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.address = address;
        this.stamps = stamps;
        this.createdAt = createdAt;
    }

}
