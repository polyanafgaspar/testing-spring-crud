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

import com.polyana.elotech.api.models.Pessoa;
import com.polyana.elotech.api.models.Pessoa.CreatePessoa;
import com.polyana.elotech.api.models.Pessoa.UpdatePessoa;
import com.polyana.elotech.api.services.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
@Validated
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("")
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> obj = this.pessoaService.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Pessoa obj = this.pessoaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated(CreatePessoa.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Pessoa obj) {
        this.pessoaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdatePessoa.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Pessoa obj, @PathVariable Long id) {
        obj.setId(id);
        this.pessoaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
