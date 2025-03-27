package core.io.helpdesk.service;

import core.io.helpdesk.domain.Pessoa;
import core.io.helpdesk.domain.Cliente;
import core.io.helpdesk.domain.dto.ClienteDTO;
import core.io.helpdesk.exceptions.DataIntegrityViolationException;
import core.io.helpdesk.exceptions.ObjectNotFoundException;
import core.io.helpdesk.repository.PessoaRepository;
import core.io.helpdesk.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID " + id));

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();

    }

    public Cliente crate(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        validaCpfAndEmail(clienteDTO);
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    private void validaCpfAndEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if(pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if(pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente cliente = findById(id);
        validaCpfAndEmail(clienteDTO);
        cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);
        if(cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente não pode ser deletado.");
        }
        clienteRepository.deleteById(id);
    }
}
