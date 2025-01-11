package uk.bovykina._dhub_test.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 100, message = "Company name must be at most 100 characters")
    private String name;
    @NotBlank(message = "Budget cannot be null")
    @Min(value = 0, message = "Budget must be non-negative")
    private Float budget;
    private List<Long> employeeIds;

}
