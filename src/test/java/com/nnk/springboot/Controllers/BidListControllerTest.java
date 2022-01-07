package com.nnk.springboot.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.BidListService;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.verify;

@WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
@WebMvcTest(BidListController.class)
public class BidListControllerTest {

    @MockBean
    AuthenticationService authenticationService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BidListService bidListService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void validateTest() throws Exception {
        //Given
        BidList bidList = new BidList("account","type",10.0);
        //When
        Mockito.when(bidListService.save(bidList)).thenReturn(bidList);
        //Then
        mockMvc.perform(post("/bidList/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(bidList)).with(csrf())).andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void homeTest() throws Exception {
        //Given
        List<BidList> findAll = new ArrayList<>(Arrays.asList(new BidList("account","type",10.0),new BidList("account2","type2",20.0)));
        //WHEN
        Mockito.when(bidListService.findAll()).thenReturn(findAll);
        //THEN
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"))
               .andExpect(MockMvcResultMatchers.model().attribute("bidList", findAll))
                .andExpect(view().name("bidList/list"));
    }

    @Test
    void addBidFormTest() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        BidList bidList = new BidList("account","type",10.0);
        Mockito.when(bidListService.findById(1)).thenReturn(bidList);

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidlist"))
                .andExpect(MockMvcResultMatchers.model().attribute("bidlist", bidList))
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void updateBidTest() throws Exception {
        //Given
        BidList bidList = new BidList("account","type",10.0);
        //When
        Mockito.when(bidListService.save(bidList)).thenReturn(bidList);
        //Then
        mockMvc.perform(post("/bidList/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(bidList)).with(csrf())).andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void deleteBidListTest() throws Exception {
        //WHEN
        Mockito.doNothing().when(bidListService).delete(1);
        //THEN
        mockMvc.perform(get("/bidList/delete/1")).andExpect(redirectedUrl("/bidList/list"));
        verify(bidListService,Mockito.times(1)).delete(1);
    }
}
