package uk.bovykina._dhub_test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.bovykina._dhub_test.model.dto.UserDto;
import uk.bovykina._dhub_test.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);
}
