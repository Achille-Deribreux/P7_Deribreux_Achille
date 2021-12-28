package com.nnk.springboot.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    public void setTradeRepository(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> findAll(){
        return tradeRepository.findAll();
    }

    public Trade findById(Integer id){
        //TODO: Change null by custom exception
        return tradeRepository.findById(id).orElse(null);
    }

    public void delete(Integer id){
        Trade tradeToDelete = findById(id);
        tradeRepository.delete(tradeToDelete);
    }
}