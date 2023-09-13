package net.atos.employeeservices.common.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.atos.employeeservices.common.exception.AlreadyExistsException;
import net.atos.employeeservices.common.exception.NotFoundException;
import net.atos.employeeservices.repository.jparepository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeUtils {

    private final EmployeeRepository employeeRepository;

    public void existByIdOrFail(UUID employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            log.error("Employee not found");
            throw new NotFoundException("Employee with id " + employeeId + " not found");
        }
    }

    public void unexistByDasOrFail(String das) {
        if (employeeRepository.existsByDas(das)) {
            log.error("Employee already exist found");
            throw new AlreadyExistsException("Employee with das " + das + " already exist");
        }
    }

    public void unexistByCinOrFail(String cin) {
        if (employeeRepository.existsByCin(cin)) {
            log.error("Employee already exist found");
            throw new AlreadyExistsException("Employee with cin " + cin + " already exist");
        }
    }
}
