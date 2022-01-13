package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(RatingService.class);

    public void setRatingRepository(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Method who call the findAll method of the repository
     * @Return  a list of all the Rating (List of Rating)
     */
    public List<Rating> findAll(){
        LOGGER.info("Find All Rating");
        return ratingRepository.findAll();
    }

    /**
     * Method who call the findById method of the repository to search in the database the Rating with the given ID
     * @Param  Id of the Rating that we are searching for (Integer)
     * @Return  the Rating corresponding to the given Id (Rating)
     */
    public Rating findById(Integer id){
        LOGGER.info("Find Rating By id "+id);
        return ratingRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Rating", id));
    }

    /**
     * Method who call the save method of the repository to save the given Rating in the database
     * @Param  Rating to save(Rating)
     * @Return  Saved Rating, with Id (Rating)
     */
    public Rating save(Rating rating){
        LOGGER.info("Save Rating with getFitchRating:"+rating.getFitchRating());
        return ratingRepository.save(rating);
    }

    /**
     * Method who call the delete method of the repository to delete the wanted Rating in the database
     * @Param  Id of the Rating that we want to delete to save(Integer)
     */
    public void delete(Integer id){
        Rating ratingToDelete = findById(id);
        LOGGER.info("Delete Rating with getFitchRating:"+ratingToDelete.getFitchRating());
        ratingRepository.delete(ratingToDelete);
    }


}
