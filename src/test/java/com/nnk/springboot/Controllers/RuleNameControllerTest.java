package com.nnk.springboot.Controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.RuleNameService;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
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
@WebMvcTest(RuleNameController.class)
public class RuleNameControllerTest {

    @MockBean
    AuthenticationService authenticationService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RuleNameService ruleNameService;

    @Test
    void homeTest() throws Exception {
        //Given
        List<RuleName> findAll = new ArrayList<>(Arrays.asList( new RuleName("name","desc","json", "temp", "sql", "sqlpart"), new RuleName("name","desc","json", "temp", "sql", "sqlpart")));
        //WHEN
        Mockito.when(ruleNameService.findAll()).thenReturn(findAll);
        //THEN
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleNameList"))
                .andExpect(MockMvcResultMatchers.model().attribute("ruleNameList", findAll))
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    void addBidFormTest() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validateTest() throws Exception {
        //Given
        RuleName ruleName = new RuleName("name","desc","json", "temp", "sql", "sqlpart");
        //When
        Mockito.when(ruleNameService.save(ruleName)).thenReturn(ruleName);
        //Then
        mockMvc.perform(post("/ruleName/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(ruleName)).with(csrf())).andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        RuleName ruleName = new RuleName("name","desc","json", "temp", "sql", "sqlpart");
        Mockito.when(ruleNameService.findById(1)).thenReturn(ruleName);

        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleName"))
                .andExpect(MockMvcResultMatchers.model().attribute("ruleName", ruleName))
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void updateRuleNameTest() throws Exception {
        //Given
        RuleName ruleName = new RuleName("name","desc","json", "temp", "sql", "sqlpart");
        //When
        Mockito.when(ruleNameService.save(ruleName)).thenReturn(ruleName);
        //Then
        mockMvc.perform(post("/ruleName/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(String.valueOf(ruleName)).with(csrf())).andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void deleteRuleNameTest() throws Exception {
        //WHEN
        Mockito.doNothing().when(ruleNameService).delete(1);
        //THEN
        mockMvc.perform(get("/ruleName/delete/1")).andExpect(redirectedUrl("/ruleName/list"));
        verify(ruleNameService,Mockito.times(1)).delete(1);
    }
}
