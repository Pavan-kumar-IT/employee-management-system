package com.pavan.employee_management.service;





import com.pavan.employee_management.dto.EmployeeRequestDto;
import com.pavan.employee_management.dto.EmployeeResponseDto;
import com.pavan.employee_management.entity.Employee;
import com.pavan.employee_management.exception.ResourceNotFoundException;
import com.pavan.employee_management.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
//
//    public Employee save(Employee employee) {
//        return repository.save(employee);
//    }
public EmployeeResponseDto saveEmployee(
        EmployeeRequestDto dto) {

    Employee employee = new Employee();

    employee.setName(dto.getName());
    employee.setEmail(dto.getEmail());
    employee.setDepartment(dto.getDepartment());
    employee.setSalary(dto.getSalary());

    Employee savedEmployee =
            repository.save(employee);

    EmployeeResponseDto response =
            new EmployeeResponseDto();

    response.setId(savedEmployee.getId());
    response.setName(savedEmployee.getName());
    response.setEmail(savedEmployee.getEmail());
    response.setDepartment(savedEmployee.getDepartment());
    response.setSalary(savedEmployee.getSalary());

    return response;
}


    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"));
    }

    public Employee update(Long id,
                           Employee employee) {

        Employee existing = getById(id);

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public Page<Employee> getAllEmployees(
            int page,
            int size,
            String sortBy) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy));

        return repository.findAll(pageable);
    }

}
