package com.nnk.springboot.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class TradeServiceTest {

    @MockBean
    TradeRepository tradeRepository;

    @Autowired
    TradeService tradeService;

    @BeforeEach
    void setUp() {
        tradeService.setTradeRepository(tradeRepository);
    }

    @Test
    void findAllTest() {
        //When
        tradeService.findAll();
        //Then
        verify(tradeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdTest() {
        //When
        tradeService.findById(1);
        //Then
        verify(tradeRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void deleteTest() {
        //Given
        Trade trade = new Trade("Account", "Type", 10.0);
        //When
        Mockito.when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        tradeService.delete(1);
        //Then
        verify(tradeRepository, Mockito.times(1)).delete(trade);
    }
}