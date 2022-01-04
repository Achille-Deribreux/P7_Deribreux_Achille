package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.CurveService;
import com.nnk.springboot.domain.CurvePoint;
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

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
       model.addAttribute("curveList",curveService.findAll());
       return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if(!result.hasErrors()){
            curveService.save(curvePoint);
            return "redirect:/curvePoint/list";
        }
        else {
            return "curvePoint/add";
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("curvePoint",curveService.findById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if(!result.hasErrors()){
            curvePoint.setId(id);
            curveService.save(curvePoint);
            return "redirect:/curvePoint/list";
        }
        else {
            return "/curvePoint/update/{id}";
        }
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curveService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
