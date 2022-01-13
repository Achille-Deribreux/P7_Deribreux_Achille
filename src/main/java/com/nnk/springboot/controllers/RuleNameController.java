package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.RuleNameService;
import com.nnk.springboot.domain.RuleName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RuleNameController {
    @Autowired
    RuleNameService ruleNameService;

    private static final Logger LOGGER = LogManager.getLogger(RuleNameController.class);

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        LOGGER.info("Request at /ruleName/list");
        model.addAttribute("ruleNameList",ruleNameService.findAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        LOGGER.info("Request at /ruleName/add");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        LOGGER.info("Post request at /ruleName/validate");
        if(!result.hasErrors()){
            ruleNameService.save(ruleName);
            LOGGER.info("ruleName "+ruleName.getName()+" has been saved");
            return "redirect:/ruleName/list";
        }else{
            LOGGER.error("Error in BindingResult at /ruleName/validate");
            return "ruleName/add";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Get request at /ruleName/update/{id}");
        model.addAttribute("ruleName",ruleNameService.findById(id));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {
        LOGGER.info("Post request at /ruleName/update/{id}");
        if(!result.hasErrors()){
            ruleName.setId(id);
            ruleNameService.save(ruleName);
            LOGGER.info("ruleName "+ruleName.getName()+" has been updated");
            return "redirect:/ruleName/list";
        }else{
            LOGGER.error("Error in BindingResult at /ruleName/validate");
            return "/ruleName/update/{id}";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Get request at /ruleName/delete/{id}");
        ruleNameService.delete(id);
        LOGGER.info("ruleName with id n°"+id+" has been deleted");
        return "redirect:/ruleName/list";
    }
}
