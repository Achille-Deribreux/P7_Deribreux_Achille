package com.nnk.springboot.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class RatingServiceTest {
    @MockBean
    RatingRepository ratingRepository;

    @Autowired
    RatingService ratingService;

    @BeforeEach
    void setUp() {
        ratingService.setRatingRepository(ratingRepository);
    }

    @Test
    void findAllTest() {
        //When
        ratingService.findAll();
        //Then
        verify(ratingRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdTest() {
        //When
        ratingService.findById(1);
        //Then
        verify(ratingRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void saveTest() {
        //Given
        Rating rating = new Rating("moodysRating","SandPRating","fitchRating",10);
        //When
        ratingService.save(rating);
        //Then
        verify(ratingRepository, Mockito.times(1)).save(rating);
    }

    @Test
    void deleteTest() {
        //Given
        Rating rating = new Rating("moodysRating","SandPRating","fitchRating",10);
        //When
        Mockito.when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        ratingService.delete(1);
        //Then
        verify(ratingRepository, Mockito.times(1)).delete(rating);
    }
}
