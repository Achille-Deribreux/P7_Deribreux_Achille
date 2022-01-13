package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    private static final Logger LOGGER = LogManager.getLogger(TradeService.class);

    public void setTradeRepository(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Method who call the findAll method of the repository
     * @Return  a list of all the BidList (List of Trade)
     */
    public List<Trade> findAll(){
        LOGGER.info("Find All Trade");
        return tradeRepository.findAll();
    }

    /**
     * Method who call the findById method of the repository to search in the database the Trade with the given ID
     * @Param  Id of the Trade that we are searching for (Integer)
     * @Return  the Trade corresponding to the given Id (Trade)
     */
    public Trade findById(Integer id){
        LOGGER.info("Find Trade By id "+id);
        return tradeRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Trade", id));
    }

    /**
     * Method who call the save method of the repository to save the given Trade in the database
     * @Param  Trade to save(Trade)
     * @Return  Saved Trade, with Id (Trade)
     */
    public Trade save(Trade trade){
        LOGGER.info("Save Trade with account name:"+trade.getAccount());
        return tradeRepository.save(trade);
    }

    /**
     * Method who call the delete method of the repository to delete the wanted Trade in the database
     * @Param  Id of the Trade that we want to delete to save(Integer)
     */
    public void delete(Integer id){
        Trade tradeToDelete = findById(id);
        LOGGER.info("Delete Trade with account name:"+tradeToDelete.getAccount());
        tradeRepository.delete(tradeToDelete);
    }
}
