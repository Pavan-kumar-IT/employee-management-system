package com.pavan.employee_management.controller;


import com.pavan.employee_management.dto.EmployeeRequestDto;
import com.pavan.employee_management.dto.EmployeeResponseDto;
import com.pavan.employee_management.entity.Employee;
import com.pavan.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
//
//    @PostMapping
//    public Employee save(
//            @Valid @RequestBody Employee employee) {
//
//        return service.save(employee);
//    }
@PostMapping
public EmployeeResponseDto saveEmployee(
        @Valid @RequestBody EmployeeRequestDto dto) {

    return service.saveEmployee(dto);
}

    @GetMapping
    public Page<Employee> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return service.getAllEmployees(
                page,
                size,
                sortBy);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return service.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }

}