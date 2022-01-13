package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.TradeService;
import com.nnk.springboot.domain.Trade;
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
public class TradeController {
    @Autowired
    TradeService tradeService;

    private static final Logger LOGGER = LogManager.getLogger(TradeController.class);

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        LOGGER.info("Request at /trade/list");
        model.addAttribute("tradeList", tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        LOGGER.info("Get request at /trade/add");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        LOGGER.info("Post request at /trade/validate");
        if(!result.hasErrors()){
            tradeService.save(trade);
            LOGGER.info("trade with account :"+trade.getAccount()+"has been saved");
            return "redirect:/trade/list";
        } else{
            LOGGER.error("Error in BindingResult at /trade/validate");
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Get request at /trade/update/{id}");
        model.addAttribute("trade",tradeService.findById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
        LOGGER.info("Post request at /trade/update/{id}");
        if(!result.hasErrors()){
            trade.setTradeId(id);
            tradeService.save(trade);
            LOGGER.info("trade with account :"+trade.getAccount()+"has been updated");
            return "redirect:/trade/list";
        } else{
            LOGGER.error("Error in BindingResult at /trade/update/{id}");
            return "/trade/update/{id}";
        }
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Get request at /trade/delete/{id}");
        tradeService.delete(id);
        LOGGER.info("trade with id :"+id+"has been deleted");
        return "redirect:/trade/list";
    }
}
