package br.com.ifrndsc.CRUDCliente.repository;

import br.com.ifrndsc.CRUDCliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM cliente WHERE nome = ?1", nativeQuery = true)
    public List<Cliente> findByName(String nome);

    @Query(value = "SELECT * FROM cliente WHERE nome LIKE ?1%", nativeQuery = true)
    public List<Cliente> findByName2(String nome);

    @Query(value = "SELECT * FROM cliente WHERE email = ?1", nativeQuery = true)
    public Cliente findByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM cliente", nativeQuery = true)
    public int totalCliente();

}
