package com.polyana.elotech.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.polyana.elotech.api.models.Contato;
import com.polyana.elotech.api.models.Contato.CreateContato;
import com.polyana.elotech.api.models.Contato.UpdateContato;
import com.polyana.elotech.api.services.ContatoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contato")
@Validated
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        Contato obj = this.contatoService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<Contato>> findAllByPessoaId(@PathVariable Long pessoaId) {
        List<Contato> contatos = this.contatoService.findAllByPessoaId(pessoaId);
        return ResponseEntity.ok().body(contatos);
    }

    @PostMapping
    @Validated(CreateContato.class)
    public ResponseEntity<Contato> create(@Valid @RequestBody Contato obj) {
        this.contatoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateContato.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Contato obj, @PathVariable Long id) {
        obj.setId(id);
        this.contatoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
