package uk.bovykina._dhub_test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.bovykina._dhub_test.model.entity.Company;

import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);

}
