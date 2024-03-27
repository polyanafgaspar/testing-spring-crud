package com.polyana.elotech.api.models;

import java.sql.Date;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = Pessoa.TABLE_NAME)
public class Pessoa {

    public interface CreatePessoa {
    }

    public interface UpdatePessoa {
    }

    public static final String TABLE_NAME = "pessoa";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    @NotNull(groups = { CreatePessoa.class, UpdatePessoa.class })
    @NotEmpty(groups = { CreatePessoa.class, UpdatePessoa.class })
    @Size(groups = { CreatePessoa.class, UpdatePessoa.class }, min = 2, max = 100)
    private String nome;

    @Column(name = "cpf", nullable = false)
    @NotNull(groups = { CreatePessoa.class, UpdatePessoa.class })
    @NotEmpty(groups = { CreatePessoa.class, UpdatePessoa.class })
    private Long cpf;

    @Column(name = "data_nascimento")
    private Date data_nascimento;

    // private List<Contato> contatos = new ArrayList<Contato>();

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, Long cpf, Date data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return this.cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Date getData_nascimento() {
        return this.data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nome='" + getNome() + "'" +
                ", cpf='" + getCpf() + "'" +
                ", data_nascimento='" + getData_nascimento() + "'" +
                "}";
    }

}
