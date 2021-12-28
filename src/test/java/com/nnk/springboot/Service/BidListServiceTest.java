package com.nnk.springboot.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class BidListServiceTest {

    @MockBean
    BidListRepository bidListRepository;

    @Autowired
    BidListService bidListService;

    @BeforeEach
    void setUp() {
        bidListService.setBidListRepository(bidListRepository);
    }

    @Test
    void findAllTest() {
        //When
        bidListService.findAll();
        //Then
        verify(bidListRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdTest() {
        //When
        bidListService.findById(1);
        //Then
        verify(bidListRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void deleteTest() {
        //Given
        BidList bidList = new BidList("account","type",10.0);
        //When
        Mockito.when(bidListRepository.findById(1)).thenReturn(java.util.Optional.of(bidList));
        bidListService.delete(1);
        //Then
        verify(bidListRepository, Mockito.times(1)).delete(bidList);
    }
}
