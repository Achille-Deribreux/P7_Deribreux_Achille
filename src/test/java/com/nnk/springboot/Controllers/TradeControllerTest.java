package com.nnk.springboot.Controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.TradeService;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
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

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
@WebMvcTest(TradeController.class)
public class TradeControllerTest {

    @MockBean
    AuthenticationService authenticationService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TradeService tradeService;

    @Test
    void homeTest() throws Exception {
        //Given
        List<Trade> findAll = new ArrayList<>(Arrays.asList(new Trade("account", "type", 100.0),new Trade("account", "type", 100.0)));
        //WHEN
        Mockito.when(tradeService.findAll()).thenReturn(findAll);
        //THEN
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("tradeList"))
                .andExpect(MockMvcResultMatchers.model().attribute("tradeList", findAll))
                .andExpect(view().name("trade/list"));
    }

    @Test
    void addBidFormTest() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    void validateTest() throws Exception {
        //Given
        Trade trade = new Trade("account", "type", 100.0);
        //When
        Mockito.when(tradeService.save(trade)).thenReturn(trade);
        //Then
        mockMvc.perform(post("/trade/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(trade)).with(csrf())).andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        //Given
        Trade trade = new Trade("account", "type", 100.0);
        //When
        Mockito.when(tradeService.findById(1)).thenReturn(trade);
        //Then
        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("trade"))
                .andExpect(MockMvcResultMatchers.model().attribute("trade", trade))
                .andExpect(view().name("trade/update"));
    }

    @Test
    void updateTradeTest() throws Exception {
        //Given
        Trade trade = new Trade("account", "type", 100.0);
        //When
        Mockito.when(tradeService.save(trade)).thenReturn(trade);
        //Then
        mockMvc.perform(post("/trade/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(trade)).with(csrf())).andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void deleteTradeTest() throws Exception {
        //WHEN
        Mockito.doNothing().when(tradeService).delete(1);
        //THEN
        mockMvc.perform(get("/trade/delete/1")).andExpect(redirectedUrl("/trade/list"));
        verify(tradeService,Mockito.times(1)).delete(1);
    }
}

