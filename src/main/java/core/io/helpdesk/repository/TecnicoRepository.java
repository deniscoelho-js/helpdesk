package core.io.helpdesk.repository;

import core.io.helpdesk.domain.Cliente;
import core.io.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
