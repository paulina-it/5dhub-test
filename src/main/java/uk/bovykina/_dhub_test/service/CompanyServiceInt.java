package uk.bovykina._dhub_test.service;

import uk.bovykina._dhub_test.model.dto.CompanyDto;

public interface CompanyServiceInt {
    CompanyDto getCompanyByName(String name);
    CompanyDto getCompanyById(Long id);
    void createCompany(CompanyDto companyDto);
}
