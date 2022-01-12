package com.nnk.springboot.Service;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CustomOAuth2UserServiceTest {

    private CustomOAuth2UserService userService = new CustomOAuth2UserService();

    @Test
    public void loadUserWhenUserRequestIsNullThenThrowIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> this.userService.loadUser(null));
    }
}
