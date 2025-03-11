package core.io.helpdesk;

import core.io.helpdesk.domain.Chamado;
import core.io.helpdesk.domain.Cliente;
import core.io.helpdesk.domain.Tecnico;
import core.io.helpdesk.domain.enums.Perfil;
import core.io.helpdesk.domain.enums.Prioridade;
import core.io.helpdesk.domain.enums.Status;
import core.io.helpdesk.repository.ChamadoRepository;
import core.io.helpdesk.repository.ClienteRepository;
import core.io.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

}
