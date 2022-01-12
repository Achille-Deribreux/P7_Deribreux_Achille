package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.BidListService;
import com.nnk.springboot.domain.BidList;
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
public class BidListController {

    @Autowired
    BidListService bidListService;

    private static final Logger LOGGER = LogManager.getLogger(BidListController.class);

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        LOGGER.info("Request at /bidList/list");

        model.addAttribute("bidList", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        LOGGER.info("Get request at /bidList/add");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        LOGGER.info("Post request at /bidList/validate");
        if(!result.hasErrors()){
            bidListService.save(bid);
            LOGGER.info("bid with account "+bid.getAccount()+" has been saved !");
            return "redirect:/bidList/list";
        }
        else{
            LOGGER.error("Error in BindingResult at /bidList/validate");
            return "bidList/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Get request at /bidList/update/{id}");
        BidList bidList = bidListService.findById(id);
        model.addAttribute("bidlist",bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        LOGGER.info("Post request at /bidList/update/{id}");
        if(!result.hasErrors()){
            bidList.setId(id);
            bidListService.save(bidList);
            LOGGER.info("bid with account "+bidList.getAccount()+" has been updated !");
            return "redirect:/bidList/list";
        }
        else {
            LOGGER.error("Error in BindingResult at /bidList/update");
            return "/bidList/update/{id}";
        }
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Delete request at /bidList/delete/{id}");
        bidListService.delete(id);
        LOGGER.info("bid with id " +id+" deleted !");
        return "redirect:/bidList/list";
    }
}
