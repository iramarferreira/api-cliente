package br.com.ifrndsc.CRUDCliente.model;

import lombok.*;

import javax.persistence.*;

// Anotações Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
// Anotações JPA
@Entity
@Table(name = "cliente")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @NonNull
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private String telefone;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private Endereco endereco;
}
