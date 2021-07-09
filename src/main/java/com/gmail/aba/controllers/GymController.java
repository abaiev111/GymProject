package com.gmail.aba.controllers;

import com.gmail.aba.model.Gym;
import com.gmail.aba.repository.GymRepository;
import com.gmail.aba.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/gym")
public class GymController {

    @Autowired
    GymRepository gymRepository;

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping
    public String viewGymPage(Model model) {
        return findGymPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findGymPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Gym> page = gymService.findPaginated(pageNo, pageSize);
        List<Gym> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("gyms", listEmployees);
        return "index_gym";
    }

    //.........................ADD

    @GetMapping("/new")
    public String showNewGymForm(Model model) {
        // create model attribute to bind form data
        Gym gym = new Gym();
        model.addAttribute("gym", gym);
        return "admin_add_gym";
    }

    @PostMapping("/addGym")
    public String saveGym(@ModelAttribute("gym") Gym gym) {
        // save employee to database
        gymService.saveGym(gym);
        return "redirect:/gym";
    }

    //...............................UPDATE
    @GetMapping("/edit/{id}")
    public String showFormGymUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Gym gym = gymService.getGymById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("gym", gym);
        return "update_gym";
    }

   /* //................DELETE
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.gymService.deleteGymById(id);
        return "redirect:/gym";
    }*/

}
