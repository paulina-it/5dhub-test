package uk.bovykina._dhub_test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.bovykina._dhub_test.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLastName(String lastName);

    List<User> findAll();

}
