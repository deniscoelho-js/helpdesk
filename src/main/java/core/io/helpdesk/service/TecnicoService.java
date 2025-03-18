package core.io.helpdesk.service;

import core.io.helpdesk.domain.Tecnico;
import core.io.helpdesk.domain.dto.TecnicoDTO;
import core.io.helpdesk.exceptions.ObjectNotFoundException;
import core.io.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID " + id));

    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();

    }

    public Tecnico crate(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }
}
