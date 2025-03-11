package core.io.helpdesk.service;

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
import org.springframework.stereotype.Service;

import java.util.Arrays;



@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;


    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Shay", "290192091", "denis@gmail.com", "1222");
        tec1.addPerfil(Perfil.ADMIN);

        Tecnico tec2 = new Tecnico(null, "Shay", "2312312", "de2n3is@gmail.com", "1222");
        Tecnico tec3 = new Tecnico(null, "Denis", "3213222", "d3e2nis@gmail.com", "1222");
        Tecnico tec4 = new Tecnico(null, "Shay", "331222", "de232n3is@gmail.com", "1222");
        Tecnico tec5 = new Tecnico(null, "Denis", "23222", "den3is@gmail.com", "1222");

        Cliente clie1 = new Cliente(null, "Shay", "2322232", "sha2y@gmail.com", "21212");
        Cliente clie2 = new Cliente(null, "Shay", "2322232324", "sh3ay@gmail.com", "21212");
        Cliente clie3 = new Cliente(null, "Shay", "232253542", "sha5y@gmail.com", "21212");
        Cliente clie4 = new Cliente(null, "Shay", "2323122222", "sh5ay@gmail.com", "21212");
        Cliente clie5 = new Cliente(null, "Shay", "23244422", "sha6y@gmail.com", "21212");

        Chamado c1 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "chamado 01", "primeiro chamado", tec1, clie1);
        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "chamado 01", "primeiro chamado", tec1, clie1);
        Chamado c3 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "chamado 01", "primeiro chamado", tec1, clie1);
        Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.FECHADO, "chamado 01", "primeiro chamado", tec1, clie1);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "primeiro chamado", tec1, clie1);

        tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3,tec4,tec5));
        clienteRepository.saveAll(Arrays.asList(clie1,clie2,clie3,clie4,clie5));
        chamadoRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
    }
}
