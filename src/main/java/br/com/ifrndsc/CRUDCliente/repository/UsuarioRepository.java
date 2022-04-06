package br.com.ifrndsc.CRUDCliente.repository;

import br.com.ifrndsc.CRUDCliente.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario WHERE username = ?1", nativeQuery = true)
    Usuario findByUsername(String username);

}
