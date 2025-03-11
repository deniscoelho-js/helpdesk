package core.io.helpdesk.repository;

import core.io.helpdesk.domain.Pessoa;
import core.io.helpdesk.domain.enums.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
