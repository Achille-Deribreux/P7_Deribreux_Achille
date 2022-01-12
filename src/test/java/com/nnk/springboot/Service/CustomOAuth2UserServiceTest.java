package com.nnk.springboot.Service;



import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CustomOAuth2UserServiceTest {

    private CustomOAuth2UserService userService = new CustomOAuth2UserService();

    @Test
    public void loadUserWhenUserRequestIsNullThenThrowIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> this.userService.loadUser(null));
    }
}
