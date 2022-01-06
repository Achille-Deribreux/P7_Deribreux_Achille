package com.nnk.springboot.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class RuleNameServiceTest {

    @Autowired
    RuleNameService ruleNameService;

    @MockBean
    RuleNameRepository ruleNameRepository;

    @BeforeEach
    void setUp() {
        ruleNameService.setRuleNameRepository(ruleNameRepository);
    }

    @Test
    void findAllTest() {
        //When
        ruleNameService.findAll();
        //Then
        verify(ruleNameRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdTest() {
        //Given
        RuleName ruleName = new RuleName("name","description", "json", "template", "sql", "sqlpart");
        //When
        Mockito.when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        ruleNameService.findById(1);
        //Then
        verify(ruleNameRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void saveTest() {
        //Given
        RuleName ruleName = new RuleName("name","description", "json", "template", "sql", "sqlpart");
        //When
        ruleNameService.save(ruleName);
        //Then
        verify(ruleNameRepository, Mockito.times(1)).save(ruleName);
    }

    @Test
    void deleteTest() {
        //Given
        RuleName ruleName = new RuleName("name","description", "json", "template", "sql", "sqlpart");
        //When
        Mockito.when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        ruleNameService.delete(1);
        //Then
        verify(ruleNameRepository, Mockito.times(1)).delete(ruleName);
    }
}
