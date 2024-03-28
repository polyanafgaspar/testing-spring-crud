package com.polyana.elotech.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polyana.elotech.api.models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
    List<Contato> findByPessoa_Id(Long id);

}
