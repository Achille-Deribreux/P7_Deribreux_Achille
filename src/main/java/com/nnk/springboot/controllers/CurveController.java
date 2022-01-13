package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.CurveService;
import com.nnk.springboot.domain.CurvePoint;
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
public class CurveController {
    @Autowired
    CurveService curveService;

    private static final Logger LOGGER = LogManager.getLogger(CurveController.class);

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        LOGGER.info("Request at /curvePoint/list");
       model.addAttribute("curveList",curveService.findAll());
       return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        LOGGER.info("Get request at /curvePoint/add");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        LOGGER.info("Post request at /curvePoint/validate");
        if(!result.hasErrors()){
            curveService.save(curvePoint);
            LOGGER.info("CurvePoint with CurveId "+curvePoint.getCurveId()+"has been saved !");
            return "redirect:/curvePoint/list";
        }
        else {
            LOGGER.error("Error in BindingResult at /curvePoint/validate");
            return "curvePoint/add";
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Get request at /curvePoint/update/{id}");
        model.addAttribute("curvePoint",curveService.findById(id));
        return "curvePoint/update";
    }
    
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
        LOGGER.info("Post request at /curvePoint/update/{id}");
        if(!result.hasErrors()){
            curvePoint.setId(id);
            curveService.save(curvePoint);
            LOGGER.info("CurvePoint with CurveId "+curvePoint.getCurveId()+"has been updated !");
            return "redirect:/curvePoint/list";
        }
        else {
            LOGGER.error("Error in BindingResult at /curvePoint/validate");
            return "/curvePoint/update/{id}";
        }
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Delete request at /curvePoint/delete/{id}");
        curveService.delete(id);
        LOGGER.info("CurvePoint with Id "+id+"has been deleted !");
        return "redirect:/curvePoint/list";
    }
}
