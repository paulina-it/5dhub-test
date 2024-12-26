package uk.bovykina._dhub_test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.bovykina._dhub_test.model.dto.UserDto;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/users/{lastName}")
    UserDto getUserByLastName(@PathVariable String lastName);
}