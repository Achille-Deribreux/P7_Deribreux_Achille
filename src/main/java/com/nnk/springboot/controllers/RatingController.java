package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.RatingService;
import com.nnk.springboot.domain.Rating;
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
public class RatingController {
    @Autowired
    RatingService ratingService;

    private static final Logger LOGGER = LogManager.getLogger(RatingController.class);

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        LOGGER.info("Request at /rating/list");
        model.addAttribute("ratingList", ratingService.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        LOGGER.info("Get request at /rating/add");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        LOGGER.info("Post request at /rating/validate");
        if(!result.hasErrors()){
            ratingService.save(rating);
            LOGGER.info("Rating has been saved !");
            return "redirect:/rating/list";
        }
        else {
            LOGGER.error("Error in BindingResult at /rating/validate");
            return "rating/add";
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Get request at /rating/update/{id}");
        model.addAttribute("rating",ratingService.findById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model) {
        LOGGER.info("Post request at /rating/update/{id}");
        if(!result.hasErrors()){
            rating.setId(id);
            ratingService.save(rating);
            LOGGER.info("Rating has been updated !");
            return "redirect:/rating/list";
        }
        else {
            return "/rating/update/{id}";
        }
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Delete request at /rating/delete/{id}");
        ratingService.delete(id);
        LOGGER.info("rating with Id "+id+"has been deleted !");
        return "redirect:/rating/list";
    }
}
