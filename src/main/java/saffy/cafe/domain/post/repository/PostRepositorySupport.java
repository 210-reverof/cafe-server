package saffy.cafe.domain.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffy.cafe.domain.post.data.dto.req.UpdatePostReqDto;
import saffy.cafe.domain.post.data.dto.res.PostResDto;
import saffy.cafe.domain.post.data.entity.QPost;

import java.util.List;

@Repository
public class PostRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<PostResDto> getAllPosts() {
        QPost p = QPost.post;

        return jpaQueryFactory
                .select(Projections.constructor(PostResDto.class, p.id, p.user.id, p.title, p.content, p.category,
                        p.departures, p.arrivals, p.headCount, p.time, p.openChattingUrl, p.productUrl,
                        p.location, p.product, p.price, p.isEnd, p.createdAt))
                .from(p)
                .fetch();
    }

    @Transactional
    public void deletePost(Integer postId) {
        QPost p = QPost.post;

        jpaQueryFactory.delete(p).where(p.id.eq(postId)).execute();
    }

    public PostResDto getCurrentPost(Integer postId) {
        QPost p = QPost.post;

        return jpaQueryFactory
                .select(Projections.constructor(PostResDto.class, p.id, p.user.id, p.title, p.content, p.category,
                        p.departures, p.arrivals, p.headCount, p.time, p.openChattingUrl, p.productUrl,
                        p.location, p.product, p.price, p.isEnd, p.createdAt))
                .from(p)
                .where(p.id.eq(postId))
                .fetch().get(0);
    }

    public List<PostResDto> getUserPosts(Integer userId) {
        QPost p = QPost.post;

        return jpaQueryFactory
                .select(Projections.constructor(PostResDto.class, p.id, p.user.id, p.title, p.content, p.category,
                        p.departures, p.arrivals, p.headCount, p.time, p.openChattingUrl, p.productUrl,
                        p.location, p.product, p.price, p.isEnd, p.createdAt))
                .from(p)
                .where(p.user.id.eq(userId))
                .fetch();
    }

    @Transactional
    public void updatePosts(Integer postId, UpdatePostReqDto updatePostReqDto) {
        QPost p = QPost.post;

        jpaQueryFactory
                .update(p)
                .set(p.title, updatePostReqDto.getTitle())
                .set(p.content, updatePostReqDto.getContent())
                .set(p.category, updatePostReqDto.getCategory())
                .set(p.departures, updatePostReqDto.getDepartures())
                .set(p.arrivals, updatePostReqDto.getArrivals())
                .set(p.headCount, updatePostReqDto.getHeadCount())
                .set(p.time, updatePostReqDto.getTime())
                .set(p.openChattingUrl, updatePostReqDto.getOpenChattingUrl())
                .set(p.productUrl, updatePostReqDto.getProductUrl())
                .set(p.location, updatePostReqDto.getLocation())
                .set(p.product, updatePostReqDto.getProduct())
                .set(p.price, updatePostReqDto.getPrice())
                .set(p.isEnd, updatePostReqDto.isEnd())
                .where(p.id.eq(postId))
                .execute();
    }
}
