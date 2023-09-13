package net.atos.employeeservices.repository.jparepository;

import net.atos.employeeservices.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByDas(String das);

    boolean existsByCin(String cin);
}
