package br.com.lucasfuck.user.core.service;

import br.com.lucasfuck.user.adapter.model.UserDto;
import br.com.lucasfuck.user.adapter.model.mapper.UserMapper;
import br.com.lucasfuck.user.core.exception.ResourceNotFoundException;
import br.com.lucasfuck.user.core.persistence.UserRepository;
import br.com.lucasfuck.user.core.persistence.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Component
@Validated
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository,
                       UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDto create(@Valid UserDto dto) {
        User user = repository.save(mapper.toEntity(dto));
        return mapper.toDto(user);
    }

    public UserDto update(Long id, @Valid UserDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.update(dto, user);
        User saved = repository.save(user);
        return mapper.toDto(saved);
    }

    public UserDto findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toDto(user);
    }

    public List<UserDto> findAllByName(String name) {
        List<User> userList = repository.findByName(name);
        return mapper.toDto(userList);
    }
}
