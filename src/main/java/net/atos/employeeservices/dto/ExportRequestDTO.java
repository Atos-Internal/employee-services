package net.atos.employeeservices.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ExportRequestDTO {
    private UUID employeeId;
    private String documentType;

    // + tous les informations qui n'existent pas dans la table Employee
}
