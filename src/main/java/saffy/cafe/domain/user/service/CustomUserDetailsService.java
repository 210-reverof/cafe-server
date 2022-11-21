package saffy.cafe.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import saffy.cafe.domain.user.data.entity.AuthUser;
import saffy.cafe.domain.user.data.entity.User;
import saffy.cafe.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String kakaoId) throws UsernameNotFoundException {
        User user = userRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new UsernameNotFoundException("회원이 존재하지 않습니다."));

        return new AuthUser(
                user.getId(),
                user.getKakaoId(),
                user.getRoles()
        );
    }
}
