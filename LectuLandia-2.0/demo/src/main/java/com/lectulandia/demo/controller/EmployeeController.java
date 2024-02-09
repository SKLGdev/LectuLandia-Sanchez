package com.lectulandia.demo.controller;

import com.lectulandia.demo.entity.EmployeeData;
import com.lectulandia.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index() {
        return "CONNECTED";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSaveEmployee(@RequestBody EmployeeData employee) {
        EmployeeData saveEmployee = employeeService.getEmployeeCreated(employee);

        try {
            return ResponseEntity.created(URI.create("")).body(saveEmployee);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping(value = "employee/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getClient(@PathVariable(name = "id") Long id) {
        Optional<EmployeeData> employee = employeeService.getEmployeeDataById(id);

        try {
            return ResponseEntity.ok(employee.get());

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all_employees", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllClients() {
        List<EmployeeData> employee = employeeService.getAllEmployeesData();

        try {
            return ResponseEntity.ok(employee);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/modified_employee/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public EmployeeData update(@PathVariable Long id, @PathVariable EmployeeData employee) {
        try {
            return employeeService.getEmployeeUpdatedById(id, employee);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return null;
        }
    }

    @PutMapping(value = "/salary_increase/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public EmployeeData increase(@PathVariable Long id) {
        try {
            return employeeService.getSalaryIncreaseById(id);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return null;
        }
    }

    @PutMapping(value = "/salary/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateSalary(@PathVariable Long id) {
        try {
            return employeeService.getSalaryDataById(id);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return null;
        }
    }

    @DeleteMapping(value = "/low/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String delete(@PathVariable Long id) {
        try {
            return employeeService.getClientDeleteById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
