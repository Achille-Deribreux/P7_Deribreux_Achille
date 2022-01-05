package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.TradeService;
import com.nnk.springboot.domain.Trade;
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
public class TradeController {
    @Autowired
    TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("tradeList", tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    //TODO : validate test
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if(!result.hasErrors()){
            tradeService.save(trade);
            return "redirect:/trade/list";
        } else{
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("trade",tradeService.findById(id));
        return "trade/update";
    }

    //TODO : validate test
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
        if(!result.hasErrors()){
            trade.setTradeId(id);
            tradeService.save(trade);
            return "redirect:/trade/list";
        } else{
            return "/trade/update/{id}";
        }
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.delete(id);
        return "redirect:/trade/list";
    }
}
