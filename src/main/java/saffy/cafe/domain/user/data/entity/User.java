package saffy.cafe.domain.user.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/***
 * user
 * id
 * nick_name
 * address
 * stamps
 * created_at
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EnableJpaAuditing
@SpringBootApplication
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, length = 100)
    private String kakaoId;

    @Column(nullable = false, length = 100)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String address = "";

    @Column(nullable = false)
    private int stamps = 1;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @Builder
    public User(String kakaoId, String nickname) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
    }
}
