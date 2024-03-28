package com.polyana.elotech.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polyana.elotech.api.models.Contato;
import com.polyana.elotech.api.models.Pessoa;
import com.polyana.elotech.api.repositories.ContatoRepository;

import jakarta.transaction.Transactional;

@Service
public class ContatoService {
    
    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaService pessoaService;

    public Contato findById(Long id) {

        Optional<Contato> contato = this.contatoRepository.findById(id);

        return contato.orElseThrow(() -> new RuntimeException(
            "Pessoa não encontrada! Id: " + id + ", Tipo: " + Contato.class.getName()
        ));
    }

    @Transactional
    public Contato create(Contato obj) {
        Pessoa pessoa = this.pessoaService.findById(obj.getPessoa().getId());
        obj.setId(null);
        obj.setNome(obj.getNome());
        obj.setEmail(obj.getEmail());
        obj.setTelefone(obj.getTelefone());
        obj.setPessoa(pessoa);

        return obj;
    }

    @Transactional
    public Contato update(Contato obj) {
        Contato newObj = findById(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setTelefone(obj.getTelefone());

        return this.contatoRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.contatoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir o contato");
        }
    }

}
