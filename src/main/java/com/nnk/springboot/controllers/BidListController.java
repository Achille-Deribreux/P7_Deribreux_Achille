package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.BidListService;
import com.nnk.springboot.domain.BidList;
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

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidList", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    //TODO : validate test
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if(!result.hasErrors()){
            bidListService.save(bid);
            return "redirect:/bidList/list";
        }
        else{
            return "bidList/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        model.addAttribute("bidlist",bidList);
        return "bidList/update";
    }

    //TODO : validate test
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        if(!result.hasErrors()){
            bidList.setId(id);
            bidListService.save(bidList);
            return "redirect:/bidList/list";
        }
        else {
            return "/bidList/update/{id}";
        }
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.delete(id);
        return "redirect:/bidList/list";
    }
}
