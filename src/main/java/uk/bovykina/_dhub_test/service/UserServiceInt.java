package uk.bovykina._dhub_test.service;

import uk.bovykina._dhub_test.model.dto.UserDto;

public interface UserServiceInt {
    UserDto getUserByLastName(String lastName);
    void createUser(UserDto userDto);
}
