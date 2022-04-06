package br.com.ifrndsc.CRUDCliente.repository;

import br.com.ifrndsc.CRUDCliente.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
