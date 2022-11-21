package saffy.cafe.domain.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffy.cafe.domain.user.data.dto.req.OnboardingReqDto;
import saffy.cafe.domain.user.data.dto.res.OnboardingResDto;
import saffy.cafe.domain.user.data.entity.QUser;

import java.util.List;

@Repository
public class UserRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public OnboardingResDto getOnboardingInfo(String kakaoId) {
        QUser u = QUser.user;

        List<OnboardingResDto> res = jpaQueryFactory
                .select(Projections.constructor(OnboardingResDto.class, u.id, u.nickname, u.address, u.stamps, u.createdAt))
                .from(u)
                .where(u.kakaoId.eq(kakaoId))
                .fetch();

        return res.get(0);
    }

    @Transactional
    public void updateOnboardingInfo(String kakaoId, OnboardingReqDto onboardingReqDto) {
        QUser u = QUser.user;

        jpaQueryFactory
                .update(u)
                .set(u.address, onboardingReqDto.getAddress())
                .set(u.nickname, onboardingReqDto.getNickname())
                .where(u.kakaoId.eq(kakaoId))
                .execute();
    }
}
