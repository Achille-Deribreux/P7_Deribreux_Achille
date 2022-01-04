package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
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
        return tradeRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Trade", id));
    }

    public Trade save(Trade trade){
        return tradeRepository.save(trade);
    }

    public void delete(Integer id){
        Trade tradeToDelete = findById(id);
        tradeRepository.delete(tradeToDelete);
    }
}
