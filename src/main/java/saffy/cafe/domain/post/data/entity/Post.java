package saffy.cafe.domain.post.data.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import saffy.cafe.domain.user.data.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * id
 * user_id
 * title
 * content
 * category
 * departures
 * arrivals
 * head_count
 * time
 * open_chatting_url
 * product_url
 * location
 * product
 * price
 * created_at
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EnableJpaAuditing
@SpringBootApplication
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int category;

    @Column(nullable = false)
    private String departures;

    @Column(nullable = false)
    private String arrivals;

    @Column(nullable = false)
    private int headCount;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String openChattingUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String productUrl;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean isEnd = false;

    @CreatedDate
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    public Post(User user, String title, String content, int category, String departures, String arrivals, int headCount, String time, String openChattingUrl, String productUrl, String location, String product, int price) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
        this.departures = departures;
        this.arrivals = arrivals;
        this.headCount = headCount;
        this.time = time;
        this.openChattingUrl = openChattingUrl;
        this.productUrl = productUrl;
        this.location = location;
        this.product = product;
        this.price = price;
    }
}
