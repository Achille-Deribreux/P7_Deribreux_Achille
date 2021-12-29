package com.nnk.springboot.Service;

import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.nnk.springboot.domain.Rating;
import javax.transaction.Transactional;

@Service
@Transactional
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public void setRatingRepository(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll(){
        return ratingRepository.findAll();
    }

    public Rating findById(Integer id){
        //TODO CHANGE NULL BY CUSTOM EXCEPTION
        return ratingRepository.findById(id).orElse(null);
    }

    public Rating save(Rating rating){
        return ratingRepository.save(rating);
    }

    public void delete(Integer id){
        Rating ratingToDelete = findById(id);
        ratingRepository.delete(ratingToDelete);
    }


}
