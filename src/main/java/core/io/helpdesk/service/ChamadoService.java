package core.io.helpdesk.service;

import core.io.helpdesk.domain.Chamado;
import core.io.helpdesk.domain.Cliente;
import core.io.helpdesk.domain.Tecnico;
import core.io.helpdesk.domain.dto.ChamadoDTO;
import core.io.helpdesk.domain.enums.Prioridade;
import core.io.helpdesk.domain.enums.Status;
import core.io.helpdesk.exceptions.ObjectNotFoundException;
import core.io.helpdesk.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID" + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO));
    }

    private Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null){
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }


    public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        findById(id);
        Chamado oldChamadoDTO;
        oldChamadoDTO = newChamado(chamadoDTO);
        return chamadoRepository.save(oldChamadoDTO);
    }
}
