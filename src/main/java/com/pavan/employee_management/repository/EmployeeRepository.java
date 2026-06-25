package com.pavan.employee_management.repository;





import com.pavan.employee_management.dto.EmployeeRequestDto;
import com.pavan.employee_management.dto.EmployeeResponseDto;
import com.pavan.employee_management.entity.Employee;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {


}
