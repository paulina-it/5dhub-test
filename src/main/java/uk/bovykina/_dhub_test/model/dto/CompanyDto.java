package uk.bovykina._dhub_test.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    private Float budget;
    private List<Long> employeeIds;

}
