package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.RuleNameService;
import com.nnk.springboot.domain.RuleName;
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

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNameList",ruleNameService.findAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    //TODO : validate test
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if(!result.hasErrors()){
            ruleNameService.save(ruleName);
            return "redirect:/ruleName/list";
        }else{
            return "ruleName/add";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ruleName",ruleNameService.findById(id));
        return "ruleName/update";
    }

    //TODO : validate test
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {
        if(!result.hasErrors()){
            ruleName.setId(id);
            ruleNameService.save(ruleName);
            return "redirect:/ruleName/list";
        }else{
            return "/ruleName/update/{id}";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.delete(id);
        return "redirect:/ruleName/list";
    }
}
