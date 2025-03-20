package core.io.helpdesk.controller;

import core.io.helpdesk.domain.Tecnico;
import core.io.helpdesk.domain.dto.TecnicoDTO;
import core.io.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tecnico = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDto = list.stream().map( obt -> new TecnicoDTO(obt)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico tecnico = tecnicoService.crate(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(null).build();
    }
}
