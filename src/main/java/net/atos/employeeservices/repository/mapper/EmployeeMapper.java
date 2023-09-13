package net.atos.employeeservices.repository.mapper;

import net.atos.employeeservices.dto.EmployeeDTO;
import net.atos.employeeservices.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  Employee EmployeeDTOtoEmployee(EmployeeDTO employeeDTO);

  // DocumentCategoryDTO DocumentCategoryToDocumentCategoryDTO(DocumentCategory documentCategory);

  // List<DocumentCategory> mapToListOfEntity(List<DocumentCategoryDTO> documentCategoryDTOs);

  // List<DocumentCategoryDTO> mapToListOfDTO(List<DocumentCategory> documentCategorys);
}