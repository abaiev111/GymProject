package com.gmail.aba.controllers;

import com.gmail.aba.model.Client;
import com.gmail.aba.model.Gym;
import com.gmail.aba.repository.ClientRepository;
import com.gmail.aba.repository.GymRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepository;

    private final GymRepository gymRepository;

    public ClientController(ClientRepository clientRepository, GymRepository gymRepository) {
        this.clientRepository = clientRepository;
        this.gymRepository = gymRepository;
    }



    @GetMapping
    public String showClientList(Model model){
        model.addAttribute("clients", clientRepository.findAll());
        return "index_client";
    }


    //.........................ADD
    @GetMapping("/new")
    public String showAddClientForm(Model model, Client client){

        List<Gym> listGym = gymRepository.findAll();
        model.addAttribute("listGym", listGym);

        return "add_client";
    }

    @PostMapping("/addClient")
    public String addClient(@Valid Client client, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add_client";
        }

        clientRepository.save(client);
        return "redirect:/client";
    }

    //...............................UPDATE

    @GetMapping("/edit/{id}")
    public String showUpdateClientForm(@PathVariable("id") long id, Model model) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<Gym> listGym = gymRepository.findAll();
        model.addAttribute("listGym", listGym);


        model.addAttribute("client", client);
        return "update_client";
    }

    @PostMapping("/edit/{id}")
    public String updateClient(@PathVariable("id") long id, @Valid Client client,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "update_client";
        }

        clientRepository.save(client);
        return "redirect:/client";
    }


    //............................DETETE
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        clientRepository.delete(client);
        return "redirect:/client";
    }
}
