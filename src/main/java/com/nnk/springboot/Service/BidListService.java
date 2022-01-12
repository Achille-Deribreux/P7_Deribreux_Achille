package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    private static final Logger LOGGER = LogManager.getLogger(BidListService.class);

    public void setBidListRepository(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * Method who call the findAll method of the repository
     * @Return  a list of all the BidList (List of Bidlist)
     */
    public List<BidList> findAll(){
        LOGGER.info("Find All BidList");
        return bidListRepository.findAll();
    }

    /**
     * Method who call the findById method of the repository to search in the database the Bidlist with the given ID
     * @Param  Id of the Bidlist that we are searching for (Integer)
     * @Return  the Bidlist corresponding to the given Id (Bidlist)
     */
    public BidList findById(Integer id){
        LOGGER.info("Find BidList By id "+id);
        return bidListRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("BidList", id));
    }

    /**
     * Method who call the save method of the repository to save the given BidList in the database
     * @Param  BidList to save(BidList)
     * @Return  Saved BidList, with Id (Bidlist)
     */
    public BidList save(BidList bidList){
        LOGGER.info("Save BidList with account name:"+bidList.getAccount());
        return bidListRepository.save(bidList);
    }

    /**
     * Method who call the delete method of the repository to delete the wanted BidList in the database
     * @Param  Id of the BidList that we want to delete to save(Integer)
     */
    public void delete(Integer id){
        BidList bidListToDelete = findById(id);
        LOGGER.info("Delete BidList with account name:"+bidListToDelete.getAccount());
        bidListRepository.delete(bidListToDelete);
    }

}
