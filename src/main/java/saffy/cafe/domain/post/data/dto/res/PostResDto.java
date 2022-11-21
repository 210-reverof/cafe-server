package saffy.cafe.domain.post.data.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResDto {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private int category;
    private String departures;
    private String arrivals;
    private int headCount;
    private String time;
    private String openChattingUrl;
    private String productUrl;
    private String location;
    private String product;
    private int price;
    private boolean isEnd = false;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    public PostResDto() {
    }

    public PostResDto(Integer id, Integer userId, String title, String content, int category, String departures, String arrivals, int headCount, String time, String openChattingUrl, String productUrl, String location, String product, int price, boolean isEnd, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
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
        this.isEnd = isEnd;
        this.createdAt = createdAt;
    }
}
