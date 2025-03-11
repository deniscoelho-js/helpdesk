package core.io.helpdesk.repository;

import core.io.helpdesk.domain.Chamado;
import core.io.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
