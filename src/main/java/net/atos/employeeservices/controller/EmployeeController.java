package net.atos.employeeservices.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.atos.employeeservices.dto.EmployeeDTO;
import net.atos.employeeservices.dto.ExportRequestDTO;
import net.atos.employeeservices.entity.Employee;
import net.atos.employeeservices.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/employee-services/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/{employeeId}")
    ResponseEntity<Employee> getEmployee(@PathVariable UUID employeeId) {
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<Page<Employee>> getEmployees(
            @Nullable @RequestParam Long page,
            @Nullable @RequestParam Long size
    ) {
        return new ResponseEntity<>(employeeService.getEmployees(page, size), HttpStatus.OK);
    }

    @GetMapping("/list")
    ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Void> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{employeeId}")
    ResponseEntity<Void> updateEmployee(
            @RequestBody EmployeeDTO employeeDTO,
            @PathVariable UUID employeeId
    ) {
        employeeService.updateEmployee(employeeId, employeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employeeId}")
    ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/{employeeId}/export")
    ResponseEntity<byte[]> exportEmployee(
            @PathVariable UUID employeeId,
            @Valid @RequestBody ExportRequestDTO exportRequestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + exportRequestDTO.getDocumentType() + ".docx");
        return new ResponseEntity<>(employeeService.exportEmployee(employeeId, exportRequestDTO), headers, HttpStatus.OK);
    }
}
