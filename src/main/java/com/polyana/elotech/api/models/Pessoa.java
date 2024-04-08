package com.polyana.elotech.api.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Pessoa.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    @NotNull(groups = { CreatePessoa.class, UpdatePessoa.class })
    private Date data_nascimento;


    @OneToMany(mappedBy = "pessoa")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Contato> contatos = new ArrayList<Contato>();

}
