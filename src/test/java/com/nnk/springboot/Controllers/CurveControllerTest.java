package com.nnk.springboot.Controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.CurveService;
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;

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
@WebMvcTest(CurveController.class)
public class CurveControllerTest {

    @MockBean
    AuthenticationService authenticationService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CurveService curveService;

    @Test
    void homeTest() throws Exception {
        //Given
        List<CurvePoint> findAll = new ArrayList<>(Arrays.asList(new CurvePoint(1,10.0,100.0),new CurvePoint(2,20.0,200.0)));
        //WHEN
        Mockito.when(curveService.findAll()).thenReturn(findAll);
        //THEN
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("curveList"))
                .andExpect(MockMvcResultMatchers.model().attribute("curveList", findAll))
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    void addBidFormTest() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validateTest() throws Exception {
        //Given
        CurvePoint curve = new CurvePoint(1,10.0,100.0);
        //When
        Mockito.when(curveService.save(curve)).thenReturn(curve);
        //Then
        mockMvc.perform(post("/curvePoint/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(curve)).with(csrf())).andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        CurvePoint curve = new CurvePoint(1,10.0,100.0);
        Mockito.when(curveService.findById(1)).thenReturn(curve);

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePoint"))
                .andExpect(MockMvcResultMatchers.model().attribute("curvePoint", curve))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void updateBidTest() throws Exception {
        //Given
        CurvePoint curve = new CurvePoint(1,10.0,100.0);
        //When
        Mockito.when(curveService.save(curve)).thenReturn(curve);
        //Then
        mockMvc.perform(post("/curvePoint/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(curve)).with(csrf())).andExpect(redirectedUrl("/curvePoint/list"));
    }
}

