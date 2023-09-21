package net.atos.employeeservices.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExportRequestDTO {
    @NotBlank(message = "documentType cannot be Blank")
    private String documentType;
    private String exportFormat;

    // + tous les informations qui n'existent pas dans la table Employee
}
