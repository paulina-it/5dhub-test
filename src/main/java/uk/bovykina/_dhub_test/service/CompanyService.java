package uk.bovykina._dhub_test.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    public CompanyDto getCompanyByName(String name) {
        logger.info("Searching for company with name: {}", name);
        Company company = companyRepo.findByName(name)
                .orElseThrow(() -> {
                    logger.error("Company not found with name: {}", name);
                    return new EntityNotFoundException("Company not found with name: " + name);
                });
        logger.info("Company found: {}", company);
        return companyMapper.toDto(company);
    }

    public CompanyDto getCompanyById(Long id) {
        logger.info("Searching for company with ID: {}", id);
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Company not found with ID: {}", id);
                    return new EntityNotFoundException("Company not found with ID: " + id);
                });
        logger.info("Company found: {}", company);
        return companyMapper.toDto(company);
    }

    public void createCompany(CompanyDto companyDto) {
        logger.info("Creating company: {}", companyDto);
        if (companyDto == null) {
            logger.error("Company data cannot be null.");
            throw new IllegalArgumentException("Company data cannot be null.");
        }


        logger.info("Checking employee ids.");
        List<Long> validEmployeeIds = companyDto.getEmployeeIds().stream()
                .filter(userRepo::existsById)
                .toList();

        if (validEmployeeIds.size() != companyDto.getEmployeeIds().size()) {
            logger.error("Some employee IDs do not exist.");
            throw new IllegalArgumentException("Some employee IDs do not exist.");
        }


        logger.info("Mapping CompanyDto to entity.");
        Company company = companyMapper.toEntity(companyDto);
        company.setEmployeeIds(validEmployeeIds);
        companyRepo.save(company);
        logger.info("Company successfully saved: {}", company);
    }
}
