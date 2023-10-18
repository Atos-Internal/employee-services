package net.atos.employeeservices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            nullable = false
    )
    private UUID employeeId;

    @Column(
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    @NotBlank(message = "das must be defined")
    private String das;

    @Column(
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    @NotBlank(message = "cin must be defined")
    private String cin;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "civility must be defined")
    private String civility;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "firstName must be defined")
    private String firstName;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "lastName must be defined")
    private String lastName;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "cnssNumber must be defined")
    private String cnssNumber;

    @Column(
            nullable = true,
            columnDefinition = "TEXT"
    )
    //@NotBlank(message = "typeContrat must be defined")
    private String typeContrat;

    @Column(
            nullable = true,
            columnDefinition = "DOUBLE PRECISION"
    )
    //@NotBlank(message = "cotisationCnss must be defined")
    private double cotisationCnss;

    @Column(
            nullable = true,
            columnDefinition = "DOUBLE PRECISION"
    )
    //@NotBlank(message = "cotisationCIMR must be defined")
    private double cotisationCIMR;

    @Column(
            nullable = true,
            columnDefinition = "DOUBLE PRECISION"
    )
    //@NotBlank(message = "assuranceMaladie must be defined")
    private double assuranceMaladie;

    @Column(
            nullable = true,
            columnDefinition = "DOUBLE PRECISION"
    )
    //@NotBlank(message = "retenuIR must be defined")
    private double retenuIR;

    @Column(
            nullable = true,
            columnDefinition = "DOUBLE PRECISION"
    )
    //@NotBlank(message = "totaleRetenue must be defined")
    private double totaleRetenue;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "position must be defined")
    private String position;

    @Column(
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotNull(message = "integrationDate must be defined")
    private LocalDate integrationDate;

    @Column(
            nullable = true,
            columnDefinition = "DATE"
    )
    // @NotBlank(message = "releaseDate must be defined")
    private LocalDate releaseDate;

    @Column(
            nullable = false,
            columnDefinition = "DOUBLE PRECISION"
    )
    @NotNull(message = "grossMonthlySalary must be defined")
    private Integer grossMonthlySalary;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "bankAccountNumber must be defined")
    private String bankAccountNumber;
}
