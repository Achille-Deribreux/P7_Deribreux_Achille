package com.nnk.springboot.Controllers;


import com.nnk.springboot.Service.BidListService;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BidListController.class)
public class BidListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BidListService bidListService;

    @Test
    void findAllTest() throws Exception {
        //Given
        BidList bidList1 = new BidList("account","type",10.0);
        BidList bidList2 = new BidList("account2","type2",20.0);
        List<BidList> findAll = new ArrayList<>(Arrays.asList(bidList1,bidList2));
        //WHEN
        Mockito.when(bidListService.findAll()).thenReturn(findAll);
        //THEN
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"))
                .andExpect(MockMvcResultMatchers.model().attribute("bidList", Matchers.arrayContaining()));
    }
}
