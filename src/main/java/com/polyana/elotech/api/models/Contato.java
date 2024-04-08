package com.polyana.elotech.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = Contato.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Contato {
    
    public interface CreateContato {
    }

    public interface UpdateContato {
    }
    
    public static final String TABLE_NAME = "contato";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false, updatable = false)
    private Pessoa pessoa;

    @Column(name = "nome", length = 100, nullable = false)
    @NotNull(groups = { CreateContato.class, UpdateContato.class })
    @NotEmpty(groups = { CreateContato.class, UpdateContato.class })
    @Size(groups = { CreateContato.class, UpdateContato.class }, min = 2, max = 100)
    private String nome;

    @Column(name = "telefone", nullable = false)
    @NotNull(groups = { CreateContato.class, UpdateContato.class })
    @NotEmpty(groups = { CreateContato.class, UpdateContato.class })
    private String telefone;

    @Column(name = "email", length = 100, nullable = false)
    @NotNull(groups = { CreateContato.class, UpdateContato.class })
    @NotEmpty(groups = { CreateContato.class, UpdateContato.class })
    @Size(groups = { CreateContato.class, UpdateContato.class }, min = 2, max = 100)
    private String email;

}
