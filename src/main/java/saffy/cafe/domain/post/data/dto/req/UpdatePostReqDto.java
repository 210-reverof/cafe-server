package saffy.cafe.domain.post.data.dto.req;

import lombok.Getter;

@Getter
public class UpdatePostReqDto {
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
    private boolean isEnd;
}
