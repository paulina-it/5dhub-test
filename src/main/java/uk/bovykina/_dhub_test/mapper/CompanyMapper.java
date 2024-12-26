package uk.bovykina._dhub_test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.bovykina._dhub_test.model.dto.CompanyDto;
import uk.bovykina._dhub_test.model.entity.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "employeeIds", source = "employeeIds")
    CompanyDto toDto(Company company);

    @Mapping(target = "employeeIds", source = "employeeIds")
    Company toEntity(CompanyDto companyDto);
}