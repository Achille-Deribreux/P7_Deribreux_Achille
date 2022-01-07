package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CurvePointServiceTest {

    @MockBean
    CurvePointRepository curvePointRepository;

    @Autowired
    CurveService curveService;

    @BeforeEach
    void setUp() {
        curveService.setCurvePointRepository(curvePointRepository);
    }

    @Test
    void findAllTest() {
        //When
        curveService.findAll();
        //Then
        verify(curvePointRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdTest() {
        //Given
        CurvePoint curvePoint = new CurvePoint(10,10.0,100.0);
        //When
        Mockito.when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        curveService.findById(1);
        //Then
        verify(curvePointRepository, Mockito.times(1)).findById(1);
        Assertions.assertEquals(curvePoint, curveService.findById(1));
    }

    @Test
    void findByIdExceptionTest() {
        assertThrows(ObjectNotFoundException.class,()->curveService.findById(1));
    }

    @Test
    void saveTest() {
        //Given
        CurvePoint curvePoint = new CurvePoint(10,10.0,100.0);
        //When
        curveService.save(curvePoint);
        //Then
        verify(curvePointRepository, Mockito.times(1)).save(curvePoint);
    }

    @Test
    void deleteTest() {
        //Given
        CurvePoint curvePoint = new CurvePoint(10,10.0,100.0);
        //When
        Mockito.when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        curveService.delete(1);
        //Then
        verify(curvePointRepository, Mockito.times(1)).delete(curvePoint);
    }
}
