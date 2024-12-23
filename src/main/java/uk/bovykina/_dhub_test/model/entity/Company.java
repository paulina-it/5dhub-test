package uk.bovykina._dhub_test.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float budget;

    @OneToMany
    @JoinColumn(name = "company_id")
    private List<User> employees;
}
