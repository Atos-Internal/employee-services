package net.atos.employeeservices.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.atos.employeeservices.common.exception.BadRequestException;
import net.atos.employeeservices.common.exception.NotFoundException;
import net.atos.employeeservices.common.util.EmployeeUtils;
import net.atos.employeeservices.dto.EmployeeDTO;
import net.atos.employeeservices.entity.Employee;
import net.atos.employeeservices.repository.jparepository.EmployeeRepository;
import net.atos.employeeservices.repository.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final EmployeeUtils employeeUtils;

    public Employee getEmployee(UUID employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with id " + employeeId + " not found"));
    }

    public Page<Employee> getEmployees(Long page, Long size) {

        int defaultPage = Optional.ofNullable(page).map(Long::intValue).orElse(0);
        int defaultSize = Optional.ofNullable(size).map(Long::intValue).orElse(10);
        Sort sort = Sort.by("employeeId").ascending();
        PageRequest pageRequest = PageRequest.of(defaultPage, defaultSize, sort);

        return employeeRepository.findAll(pageRequest);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.EmployeeDTOtoEmployee(employeeDTO);

        if (employee.getDas() == null || employee.getDas().isBlank()) {
            throw new BadRequestException("DAS must be defined");
        } else {
            employeeUtils.unexistByDasOrFail(employee.getDas());
        }

        if (employee.getCin() == null || employee.getCin().isBlank()) {
            throw new BadRequestException("CIN must be defined");
        } else {
            employeeUtils.unexistByCinOrFail(employee.getCin());
        }

        if (employee.getFirstName() == null || employee.getFirstName().isBlank()) {
            throw new BadRequestException("firstName must be defined");
        }

        if (employee.getLastName() == null || employee.getLastName().isBlank()) {
            throw new BadRequestException("lastName must be defined");
        }

        if (employee.getCnssNumber() == null || employee.getCnssNumber().isBlank()) {
            throw new BadRequestException("cnssNumber must be defined");
        }

        if (employee.getPosition() == null || employee.getPosition().isBlank()) {
            throw new BadRequestException("position must be defined");
        }

        if (employee.getIntegrationDate() == null) {
            throw new BadRequestException("integrationDate must be defined");
        }

        if (employee.getGrossMonthlySalary() == null) {
            throw new BadRequestException("grossMonthlySalary must be defined");
        }

        if (employee.getBankAccountNumber() == null || employee.getBankAccountNumber().isBlank()) {
            throw new BadRequestException("bankAccountNumber must be defined");
        }

        employeeRepository.save(employee);
        log.info("Employee CREATED : " + employee);
    }

    public void updateEmployee(UUID employeeId, EmployeeDTO employeeDTO) {

        employeeRepository.findById(employeeId)
                .map(e -> {
                    if (employeeDTO.getDas() != null && !employeeDTO.getDas().isBlank()) {
                        if(!Objects.equals(employeeDTO.getDas(), e.getDas())) {
                            employeeUtils.unexistByDasOrFail(employeeDTO.getDas());
                            e.setDas(employeeDTO.getDas());
                        }
                    }

                    if (employeeDTO.getCin() != null && !employeeDTO.getCin().isBlank()) {
                        if(!Objects.equals(employeeDTO.getCin(), e.getCin())) {
                            employeeUtils.unexistByCinOrFail(employeeDTO.getCin());
                            e.setCin(employeeDTO.getCin());
                        }
                    }

                    if (employeeDTO.getFirstName() != null && !employeeDTO.getFirstName().isBlank()) {
                        e.setFirstName(employeeDTO.getFirstName());
                    }

                    if (employeeDTO.getLastName() != null && !employeeDTO.getLastName().isBlank()) {
                        e.setLastName(employeeDTO.getLastName());
                    }

                    if (employeeDTO.getCnssNumber() != null && !employeeDTO.getCnssNumber().isBlank()) {
                        e.setCnssNumber(employeeDTO.getCnssNumber());
                    }

                    if (employeeDTO.getPosition() != null && !employeeDTO.getPosition().isBlank()) {
                        e.setPosition(employeeDTO.getPosition());
                    }

                    if (employeeDTO.getIntegrationDate() != null) {
                        e.setIntegrationDate(employeeDTO.getIntegrationDate());
                    }

                    if (employeeDTO.getReleaseDate() != null) {
                        e.setReleaseDate(employeeDTO.getReleaseDate());
                    }

                    if (employeeDTO.getGrossMonthlySalary() != null) {
                        e.setGrossMonthlySalary(employeeDTO.getGrossMonthlySalary());
                    }

                    if (employeeDTO.getBankAccountNumber() != null && !employeeDTO.getBankAccountNumber().isBlank()) {
                        e.setBankAccountNumber(employeeDTO.getBankAccountNumber());
                    }

                    employeeRepository.save(e);
                    log.info("Employee UPDATED : " + e);
                    return e;
                })
                .orElseThrow(() -> new NotFoundException("Employee with id " + employeeId + " not found"));
    }

    public void deleteEmployee(UUID employeeId) {
        employeeUtils.existByIdOrFail(employeeId);
        employeeRepository.deleteById(employeeId);
    }
}
