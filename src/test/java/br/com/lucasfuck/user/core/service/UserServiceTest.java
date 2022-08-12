package br.com.lucasfuck.user.core.service;

import br.com.lucasfuck.user.adapter.model.UserDto;
import br.com.lucasfuck.user.adapter.model.mapper.UserMapper;
import br.com.lucasfuck.user.core.exception.ResourceNotFoundException;
import br.com.lucasfuck.user.core.persistence.UserRepository;
import br.com.lucasfuck.user.core.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Test
    void testCreate() {
        UserDto dto = new UserDto(null, "79601676058", "email@email",
                "name", LocalDate.now());
        User user = new User();
        when(mapper.toEntity(dto)).thenReturn(user);

        service.create(dto);

        verify(repository, times(1)).save(user);
    }

    @Test
    void testUpdateWithoutUser() {
        UserDto dto = new UserDto(null, "79601676058", "email@email",
                "name", LocalDate.now());
        assertThrows(ResourceNotFoundException.class, () -> service.update(1L, dto));
    }

    @Test
    void testUpdateWithUser() {
        UserDto dto = new UserDto(null, "79601676058", "email@email",
                "name", LocalDate.now());
        User user = new User();
        user.setId(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(dto);

        assertDoesNotThrow(() -> service.update(1L, dto));
    }

    @Test
    void testFindByIdWithoutResource() {
        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void testFindById() {
        UserDto dto = new UserDto(null, "79601676058", "email@email",
                "name", LocalDate.now());
        User user = new User();
        user.setId(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        when(mapper.toDto(user)).thenReturn(dto);

        UserDto userDto = service.findById(1L);

        assertThat(userDto).isNotNull();
    }
}