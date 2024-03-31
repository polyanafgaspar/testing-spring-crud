package com.polyana.elotech.api.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polyana.elotech.api.models.Pessoa;
import com.polyana.elotech.api.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll(){

        List<Pessoa> pessoas = this.pessoaRepository.findAll();

        return pessoas;

    }

    public Pessoa findById(Long id){

        @SuppressWarnings("null")
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);

        return pessoa.orElseThrow(() -> new RuntimeException(
            "Pessoa não encontrada! Id: " + id + ", Tipo: " + Pessoa.class.getName()
        ));

    }

    @Transactional
    public Pessoa create(Pessoa obj) {
        obj.setId(null);
        obj = this.pessoaRepository.save(obj);
        return obj;
    }

    @Transactional
    public Pessoa update(Pessoa obj) {
        Pessoa newObj = findById(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setCpf(obj.getCpf());
        newObj.setData_nascimento(obj.getData_nascimento());

        return this.pessoaRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);

        try {
            this.pessoaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, pois há Contatos relacionados");
        }
    }

}
