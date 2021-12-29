package com.nnk.springboot.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    public void setRuleNameRepository(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    public List<RuleName> findAll(){
        return ruleNameRepository.findAll();
    }

    public RuleName findById(Integer id){
        //TODO : change null by custom exception
        return ruleNameRepository.findById(id).orElse(null);
    }

    public RuleName save (RuleName ruleName){
        return ruleNameRepository.save(ruleName);
    }

    public void delete(Integer id){
        RuleName rulenameToDelete = findById(id);
        ruleNameRepository.delete(rulenameToDelete);
    }
}
