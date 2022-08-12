package br.com.lucasfuck.user.adapter.model.mapper;

import br.com.lucasfuck.user.adapter.model.UserDto;
import br.com.lucasfuck.user.core.persistence.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User entity);

    List<UserDto> toDto(List<User> entityList);

    User toEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UserDto dto, @MappingTarget User entity);

}
