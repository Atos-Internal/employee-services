package net.atos.employeeservices.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ExportRequestDTO {
    @NotBlank(message = "documentType cannot be Blank")
    private String documentType;
    private String exportFormat;

    public List<String> UUIDs;



    public void setUUIDs(List<String> UUIDs) {
        this.UUIDs = UUIDs;
    }

}
