package core.io.helpdesk.service;

import core.io.helpdesk.domain.Pessoa;
import core.io.helpdesk.domain.Tecnico;
import core.io.helpdesk.domain.dto.TecnicoDTO;
import core.io.helpdesk.exceptions.ObjectNotFoundException;
import core.io.helpdesk.repository.PessoaRepository;
import core.io.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import core.io.helpdesk.exceptions.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID " + id));

    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();

    }

    public Tecnico crate(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaCpfAndEmail(tecnicoDTO);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

    private void validaCpfAndEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if(pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if(pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
