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
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "rua")
    private String rua;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "numero")
    private int numero;
    @NonNull
    @Column(name = "cidade", nullable = false)
    private String cidade;
    @NonNull
    @Column(name = "uf", nullable = false)
    private String uf;
    @Column(name = "cep")
    private String cep;

}
