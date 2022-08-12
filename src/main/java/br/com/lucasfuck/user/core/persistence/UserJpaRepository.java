package br.com.lucasfuck.user.core.persistence;

import br.com.lucasfuck.user.core.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
}
