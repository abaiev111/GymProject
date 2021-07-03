package com.gmail.aba.controllers;

import com.gmail.aba.model.Gym;
import com.gmail.aba.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class GymController {

    @Autowired
    private GymRepository gymRepository;

    @GetMapping("/admin/gym")
    public String showGymList(Model model){
        model.addAttribute("gyms", gymRepository.findAll());
        return "index_gym";
    }


    //.........................ADD
    @GetMapping("/admin/gym/new")
    public String showAddGymForm(Gym gym){
        return "add_gym";
    }

    @PostMapping("/admin/addGym")
    public String addGym(@Valid Gym gym, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_gym";
        }
        gymRepository.save(gym);
        return "redirect:/admin/gym";
    }

    //...............................UPDATE
    @GetMapping("/admin/edit/{id}")
    public String showUpdateGymForm(@PathVariable("id") long id, Model model) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("gym", gym);
        return "update_gym";
    }

    @PostMapping("/admin/update/{id}")
    public String updateGym(@PathVariable("id") long id, @Valid Gym gym,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            gym.setId(id);
            return "update_gym";
        }

        gymRepository.save(gym);
        return "redirect:/admin/gym";
    }


    /*//............................DETETE
    @GetMapping("/delete/{id}")
    public String deleteGym(@PathVariable("id") long id, Model model) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        gymRepository.delete(gym);
        return "redirect:/gym";
    }*/


}
