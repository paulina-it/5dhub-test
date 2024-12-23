package uk.bovykina._dhub_test.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.bovykina._dhub_test.model.dto.CompanyDto;
import uk.bovykina._dhub_test.service.CompanyService;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping("/name/{name}")
    public CompanyDto getCompanyByName(@PathVariable String name) {
        return companyService.getCompanyByName(name);
    }

    @GetMapping("/id/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public void createCompany(@RequestBody CompanyDto companyDto) {
        companyService.createCompany(companyDto);
    }
}

