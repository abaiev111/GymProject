package com.gmail.aba.controllers;

import com.gmail.aba.model.Employee;
import com.gmail.aba.model.Gym;
import com.gmail.aba.service.EmployeeService;
import com.gmail.aba.service.GymService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final GymService gymService;

    public EmployeeController(EmployeeService employeeService, GymService gymService) {
        this.employeeService = employeeService;
        this.gymService = gymService;
    }

    @GetMapping
    public String viewEmployeePage(Model model) {
        return findEmployeePaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findEmployeePaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("employees", listEmployees);
        return "admin_index_employee";
    }


    //.........................ADD
    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();

        List<Gym> listGym = gymService.getAllGums();

        model.addAttribute("listGym", listGym);
        model.addAttribute("employee", employee);
        return "admin_add_employee";
    }

    @PostMapping("/addEmployee")
    public String saveGym(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }

    //...............................UPDATE

    @GetMapping("/edit/{id}")
    public String showFormEmployeeUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        List<Gym> listGym = gymService.getAllGums();
        model.addAttribute("listGym", listGym);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

        //............................DETETE

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/employee";
    }

}
