package uk.bovykina._dhub_test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.bovykina._dhub_test.model.dto.CompanyDto;
import uk.bovykina._dhub_test.model.entity.Company;
import uk.bovykina._dhub_test.repo.CompanyRepo;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepo companyRepo;

    public CompanyDto getCompanyByName(String name) {
        Company company = companyRepo.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Company with name " + name + " was not found."));
        return toDto(company);
    }

    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company with ID " + id + " was not found."));
        return toDto(company);
    }

    private CompanyDto toDto(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .budget(company.getBudget())
                .employeeIds(company.getEmployees().stream()
                        .map(employee -> employee.getId())
                        .collect(Collectors.toList()))
                .build();
    }

    public void createCompany(CompanyDto companyDto) {
        if (companyDto == null) {
            throw new IllegalArgumentException("Company data cannot be null.");
        }

        Company company = Company.builder()
                .name(companyDto.getName())
                .budget(companyDto.getBudget())
                .build();

        companyRepo.save(company);
    }

}
