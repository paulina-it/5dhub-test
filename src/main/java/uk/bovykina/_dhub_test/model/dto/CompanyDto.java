package uk.bovykina._dhub_test.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String name;
    private Float budget;
    private List<Long> employeeIds;

}
