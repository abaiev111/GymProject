package com.gmail.aba.controllers;

import com.gmail.aba.model.Employee;
import com.gmail.aba.model.Gym;
import com.gmail.aba.repository.EmployeeRepository;
import com.gmail.aba.repository.GymRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {


    private final EmployeeRepository employeesRepository;

    private final GymRepository gymRepository;

    public EmployeeController(EmployeeRepository employeesRepository, GymRepository gymRepository) {
        this.employeesRepository = employeesRepository;
        this.gymRepository = gymRepository;
    }

    @GetMapping("/admin/employee")
    public String showEmployeeList(Model model){
        model.addAttribute("employees", employeesRepository.findAll());
        return "index_employee";
    }


    //.........................ADD
    @GetMapping("/admin/employee/new")
    public String showAddEmployeeForm(Model model, Employee employee){

        List<Gym> listGym = gymRepository.findAll();
        model.addAttribute("listGym", listGym);
        return "add_employee";
    }

    @PostMapping("/admin/addEmployee")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add_employee";
        }
        employeesRepository.save(employee);
        return "redirect:/admin/employee";
    }

    //...............................UPDATE

    @GetMapping("/admin/employee/edit/{id}")
    public String showUpdateEmployeeForm(@PathVariable("id") long id, Model model) {

        Employee employee = employeesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<Gym> listGym = gymRepository.findAll();
        model.addAttribute("listGym", listGym);


        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @PostMapping("/admin/employee/edit/{id}")
    public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update_employee";
        }

        employeesRepository.save(employee);
        return "redirect:/admin/employee";
    }


    //............................DETETE
    @GetMapping("/admin/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        employeesRepository.delete(employee);
        return "redirect:/admin/employee";
    }
}