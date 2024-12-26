package uk.bovykina._dhub_test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.bovykina._dhub_test.model.dto.CompanyDto;
import uk.bovykina._dhub_test.mapper.CompanyMapper;
import uk.bovykina._dhub_test.model.entity.Company;
import uk.bovykina._dhub_test.repo.CompanyRepo;
import uk.bovykina._dhub_test.repo.UserRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService implements CompanyServiceInt {

    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;
    private final CompanyMapper companyMapper;

    public CompanyDto getCompanyByName(String name) {
        Company company = companyRepo.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Company with name " + name + " was not found."));
        return companyMapper.toDto(company);
    }

    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company with ID " + id + " was not found."));
        return companyMapper.toDto(company);
    }

    public void createCompany(CompanyDto companyDto) {
        if (companyDto == null) {
            throw new IllegalArgumentException("Company data cannot be null.");
        }

        // Validate employee IDs
        List<Long> validEmployeeIds = companyDto.getEmployeeIds().stream()
                .filter(userRepo::existsById)
                .toList();

        if (validEmployeeIds.size() != companyDto.getEmployeeIds().size()) {
            throw new IllegalArgumentException("Some employee IDs do not exist.");
        }

        Company company = companyMapper.toEntity(companyDto);
        company.setEmployeeIds(validEmployeeIds); // Only store valid employee IDs
        companyRepo.save(company);
    }
}
