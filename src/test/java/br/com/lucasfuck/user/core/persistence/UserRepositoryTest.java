package br.com.lucasfuck.user.core.persistence;

import br.com.lucasfuck.user.core.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @InjectMocks
    private UserRepository userRepository;

    @Mock
    private UserJpaRepository repository;

    @Test
    void save() {
        userRepository.save(new User());
        verify(repository, Mockito.only()).save(any());
    }

    @Test
    void findById() {
        userRepository.findById(1L);
        verify(repository, Mockito.only()).findById(any());
    }

    @Test
    void findByName() {
        userRepository.findByName("name");
        verify(repository, Mockito.only()).findByName(any());
    }
}