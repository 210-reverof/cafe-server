package saffy.cafe.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.boot.jaxb.internal.UrlXmlSource;
import org.springframework.stereotype.Service;
import saffy.cafe.domain.user.data.dto.req.OnboardingReqDto;
import saffy.cafe.domain.user.data.dto.res.OnboardingResDto;
import saffy.cafe.domain.user.data.entity.User;
import saffy.cafe.domain.user.repository.UserRepository;
import saffy.cafe.domain.user.repository.UserRepositorySupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;

    public OnboardingResDto getOnboarding(String kakaoId) {
        return userRepositorySupport.getOnboardingInfo(kakaoId);
    }

    public OnboardingResDto updateOnboarding(String kakaoId, OnboardingReqDto onboardingReqDto) {
        userRepositorySupport.updateOnboardingInfo(kakaoId, onboardingReqDto);
        return userRepositorySupport.getOnboardingInfo(kakaoId);
    }

    public boolean checkOnboarding(String kakakoId) {
        return !userRepository.findByKakaoId(kakakoId).get().getAddress().equals("");
    }
}
