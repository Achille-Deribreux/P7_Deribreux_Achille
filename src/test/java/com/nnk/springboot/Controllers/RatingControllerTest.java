package com.nnk.springboot.Controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.RatingService;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @MockBean
    AuthenticationService authenticationService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RatingService ratingService;

    @Test
    void homeTest() throws Exception {
        //Given
        List<Rating> findAll = new ArrayList<>(Arrays.asList(new Rating("Moody","sandP","fitchRating", 10),new Rating("Moody","sandP","fitchRating",11)));
        //WHEN
        Mockito.when(ratingService.findAll()).thenReturn(findAll);
        //THEN
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("ratingList"))
                .andExpect(MockMvcResultMatchers.model().attribute("ratingList", findAll))
                .andExpect(view().name("rating/list"));
    }

    @Test
    void addBidFormTest() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    void validateTest() throws Exception {
        //Given
        Rating rating = new Rating("Moody","sandP","fitchRating", 10);
        //When
        Mockito.when(ratingService.save(rating)).thenReturn(rating);
        //Then
        mockMvc.perform(post("/rating/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(rating)).with(csrf())).andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        Rating rating = new Rating("Moody","sandP","fitchRating", 10);
        Mockito.when(ratingService.findById(1)).thenReturn(rating);

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("rating"))
                .andExpect(MockMvcResultMatchers.model().attribute("rating", rating))
                .andExpect(view().name("rating/update"));
    }

    @Test
    void updateBidTest() throws Exception {
        //Given
        Rating rating = new Rating("Moody","sandP","fitchRating", 10);
        //When
        Mockito.when(ratingService.save(rating)).thenReturn(rating);
        //Then
        mockMvc.perform(post("/rating/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(rating)).with(csrf())).andExpect(redirectedUrl("/rating/list"));
    }
}
