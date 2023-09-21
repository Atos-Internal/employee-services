package net.atos.employeeservices.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EmployeeDTO {
    private UUID employeeId;
    private String das;
    private String cin;
    private String firstName;
    private String lastName;
    private String cnssNumber;
    private String position;
    private LocalDate integrationDate;
    private LocalDate releaseDate;
    private Integer grossMonthlySalary;
    private String bankAccountNumber;
}
