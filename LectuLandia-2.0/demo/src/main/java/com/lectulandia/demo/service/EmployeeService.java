package com.lectulandia.demo.service;

import com.lectulandia.demo.entity.EmployeeData;
import com.lectulandia.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeData getEmployeeCreated(EmployeeData employee) {
        return employeeRepository.save(employee);
    }

    public Optional<EmployeeData> getEmployeeDataById(Long idEmployee) {
        return employeeRepository.findById(idEmployee);
    }

    public List<EmployeeData> getAllEmployeesData() {
        return employeeRepository.findAll();
    }

    public EmployeeData getEmployeeUpdatedById(Long id, EmployeeData employee) {
        EmployeeData updateEmployee = employeeRepository.findById(id).get();

        updateEmployee.setName(employee.getName());
        updateEmployee.setSurname(employee.getSurname());
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setPhone(employee.getPhone());
        updateEmployee.setAddress(employee.getAddress());
        updateEmployee.setCity(employee.getCity());
        updateEmployee.setYear(employee.getYear());
        updateEmployee.setMonth(employee.getMonth());
        updateEmployee.setDay(employee.getDay());
        updateEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(updateEmployee);
    }

//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }

    public String getClientDeleteById(Long id) {
        EmployeeData deleteClient = employeeRepository.findById(id).get();
        employeeRepository.delete(deleteClient);

        return "EMPLOYEE DELETED";
    }

    //FALTA OBTENER EL AUMENTO DE SUELDO Y ADEMAS UN INCENTIVO. EL INCENTIVO DEBE SER CREADO EN EL SERVICE
    public EmployeeData getSalaryIncreaseById(Long id) {
        EmployeeData increaseSalary = employeeRepository.findById(id).get();
        increaseSalary.setSalary(increaseSalary.getSalaryIncrease());

        return employeeRepository.save(increaseSalary);
    }
    //RETORNAR DALARIO, NOMBRE COMPLETO Y FECHA DE ALTA

    public String getSalaryDataById(Long id) {
        EmployeeData employeeData = employeeRepository.findById(id).get();

        return "COMPLETE NAME: " + employeeData.getName() + " " + employeeData.getSurname() + "\n" +
                "DISCHARGE DATE: " + employeeData.getContractRegistrationDate() + "\n" +
                "SALARY INCREASED: " + getSalaryIncreaseById(id);
    }

}
