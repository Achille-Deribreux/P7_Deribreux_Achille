package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    private static final Logger LOGGER = LogManager.getLogger(RuleNameService.class);

    public void setRuleNameRepository(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * Method who call the findAll method of the repository
     * @Return  a list of all the RuleName (List of RuleName)
     */
    public List<RuleName> findAll(){
        LOGGER.info("Find All RuleName");
        return ruleNameRepository.findAll();
    }

    /**
     * Method who call the findById method of the repository to search in the database the RuleName with the given ID
     * @Param  Id of the RuleName that we are searching for (Integer)
     * @Return  the RuleName corresponding to the given Id (RuleName)
     */
    public RuleName findById(Integer id){
        LOGGER.info("Find RuleName By id "+id);
        return ruleNameRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("RuleName", id));
    }

    /**
     * Method who call the save method of the repository to save the given RuleName in the database
     * @Param  RuleName to save(RuleName)
     * @Return  Saved RuleName, with Id (RuleName)
     */
    public RuleName save (RuleName ruleName){
        LOGGER.info("Save RuleName with name:"+ruleName.getName());
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Method who call the delete method of the repository to delete the wanted RuleName in the database
     * @Param  Id of the RuleName that we want to delete to save(Integer)
     */
    public void delete(Integer id){
        RuleName rulenameToDelete = findById(id);
        LOGGER.info("Delete RuleName with  name:"+rulenameToDelete.getName());
        ruleNameRepository.delete(rulenameToDelete);
    }
}
