package br.com.lucasfuck.user.core.persistence;

import br.com.lucasfuck.user.core.persistence.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {

    private final UserJpaRepository repository;

    public UserRepository(UserJpaRepository repository) {
        this.repository = repository;
    }

    public User save(User entity) {
        return repository.save(entity);
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public List<User> findByName(String name) {
        return repository.findByName(name);
    }
}
