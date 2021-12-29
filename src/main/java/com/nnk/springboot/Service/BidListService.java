package com.nnk.springboot.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    public void setBidListRepository(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> findAll(){
        return bidListRepository.findAll();
    }

    public BidList findById(Integer id){
        //TODO CHANGE NULL BY CUSTOM EXCEPTION
        return bidListRepository.findById(id).orElse(null);
    }

    public BidList save(BidList bidList){
        return bidListRepository.save(bidList);
    }

    public void delete(Integer id){
        BidList bidListToDelete = findById(id);
        bidListRepository.delete(bidListToDelete);
    }

}
