package uk.bovykina._dhub_test.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.bovykina._dhub_test.model.dto.CompanyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.bovykina._dhub_test.service.CompanyServiceInt;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyServiceInt companyService;

    @GetMapping("/name/{name}")
    public CompanyDto getCompanyByName(@PathVariable String name) {
        logger.info("Fetching company by name: {}", name);
        CompanyDto company = companyService.getCompanyByName(name);
        logger.info("Fetched company: {}", company);
        return company;
    }

    @GetMapping("/id/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        logger.info("Fetching company by ID: {}", id);
        CompanyDto company = companyService.getCompanyById(id);
        logger.info("Fetched company: {}", company);
        return company;
    }

    @PostMapping
    public void createCompany(@Valid @RequestBody CompanyDto companyDto) {
        logger.info("Creating company: {}", companyDto);
        companyService.createCompany(companyDto);
        logger.info("Company created successfully: {}", companyDto.getName());
    }
}


