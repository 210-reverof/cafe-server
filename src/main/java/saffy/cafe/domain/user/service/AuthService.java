package saffy.cafe.domain.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.Map;
import org.springframework.stereotype.Service;
import saffy.cafe.domain.user.data.dto.KakaoAccount;
import saffy.cafe.domain.user.data.dto.res.LoginResDto;
import saffy.cafe.domain.user.data.entity.User;
import saffy.cafe.domain.user.filter.JwtTokenProvider;
import saffy.cafe.domain.user.repository.UserRepository;


import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResDto login(String kakaoAccessToken) {
        KakaoAccount kakaoAccount = getKakaoAccount(kakaoAccessToken);

        int id = -1;
        if (!userRepository.existsByKakaoId(kakaoAccount.getKakaoId())) {
            id = save(kakaoAccount);
        } else {
            id = userRepository.findByKakaoId(kakaoAccount.getKakaoId()).get().getId();
        }

        if (id == -1) return null;

        return new LoginResDto(jwtTokenProvider.createToken(kakaoAccount.getKakaoId(),"USER"));
    }

    private int save(KakaoAccount kakaoAccount) {
        return userRepository.save(
                User.builder()
                .kakaoId(kakaoAccount.getKakaoId())
                .nickname(kakaoAccount.getNickname())
                .build()).getId();
    }

    private KakaoAccount getKakaoAccount(String kakaoAccessToken) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://kapi.kakao.com/v2/user/me");
            httpGet.addHeader("Authorization", "Bearer " + kakaoAccessToken);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            String properties = String.valueOf(map.get("properties"));
            String[] pros = properties.split(", ");

            String kakaoId = String.valueOf(map.get("id"));
            String nickname = "";

            return new KakaoAccount(kakaoId, nickname);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
