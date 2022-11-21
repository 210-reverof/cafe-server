package saffy.cafe.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saffy.cafe.domain.user.data.dto.res.OnboardingResDto;
import saffy.cafe.domain.user.repository.UserRepository;
import saffy.cafe.domain.user.repository.UserRepositorySupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;

    public OnboardingResDto getOnboarding(String kakakoId) {
        return userRepositorySupport.getOnboardingInfo(kakakoId);
    }
}
