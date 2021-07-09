package com.gmail.aba.controllers;

import com.gmail.aba.model.Client;
import com.gmail.aba.model.Gym;
import com.gmail.aba.service.ClientService;
import com.gmail.aba.service.GymService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final GymService gymService;

    public ClientController(ClientService clientService, GymService gymService) {
        this.clientService = clientService;
        this.gymService = gymService;
    }

    @GetMapping
    public String viewClientPage(Model model) {
        return findClientPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findClientPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Client> page = clientService.findPaginated(pageNo, pageSize);
        List<Client> listClients = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("clients", listClients);
        return "admin_index_client";
    }

    //.........................ADD

    @GetMapping("/new")
    public String showNewClientForm(Model model) {
        // create model attribute to bind form data
        Client client = new Client();

        List<Gym> listGym = gymService.getAllGums();

        model.addAttribute("listGym", listGym);
        model.addAttribute("client", client);
        return "admin_add_client";
    }

    @PostMapping("/addClient")
    public String saveClient(@ModelAttribute("client") Client client) {
        // save employee to database
        clientService.saveClient(client);
        return "redirect:/client";
    }

    //...............................UPDATE

    @GetMapping("/edit/{id}")
    public String showFormClientUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Client client = clientService.getClientById(id);

        List<Gym> listGym = gymService.getAllGums();
        model.addAttribute("listGym", listGym);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("client", client);
        return "update_client";
    }
    //............................DETETE

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.clientService.deleteClientById(id);
        return "redirect:/client";
    }

}
