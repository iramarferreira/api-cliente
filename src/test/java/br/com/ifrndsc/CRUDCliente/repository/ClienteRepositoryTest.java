package br.com.ifrndsc.CRUDCliente.repository;


import br.com.ifrndsc.CRUDCliente.model.Cliente;
import br.com.ifrndsc.CRUDCliente.model.Endereco;
import br.com.ifrndsc.CRUDCliente.util.ClienteCreator;
import ch.qos.logback.core.net.server.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName(value = "Teste para ClienteRepository")
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Test
    @DisplayName(value = "Salvar um cliente com sucesso")
    void salvarCliente_quandoSucesso(){
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(cliente.getEndereco());
        cliente.setEndereco(enderecoSalvo);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Verificar se cliente não é nulo
        Assertions.assertThat(clienteSalvo).isNotNull();
        Assertions.assertThat(clienteSalvo.getId()).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(clienteSalvo.getNome()).isEqualTo(cliente.getNome());
        Assertions.assertThat(clienteSalvo.getEmail()).isEqualTo(cliente.getEmail());
        Assertions.assertThat(clienteSalvo.getTelefone()).isEqualTo(cliente.getTelefone());
        Assertions.assertThat(clienteSalvo.getEndereco().getRua()).isEqualTo(cliente.getEndereco().getRua());
        Assertions.assertThat(clienteSalvo.getEndereco().getBairro()).isEqualTo(cliente.getEndereco().getBairro());
        Assertions.assertThat(clienteSalvo.getEndereco().getCep()).isEqualTo(cliente.getEndereco().getCep());
        Assertions.assertThat(clienteSalvo.getEndereco().getNumero()).isEqualTo(cliente.getEndereco().getNumero());
        Assertions.assertThat(clienteSalvo.getEndereco().getCidade()).isEqualTo(cliente.getEndereco().getCidade());
        Assertions.assertThat(clienteSalvo.getEndereco().getUf()).isEqualTo(cliente.getEndereco().getUf());
    }


    @Test
    @DisplayName(value = "Atualizar um cliente com sucesso")
    void atualizarCliente_quandoSucesso(){
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(cliente.getEndereco());
        cliente.setEndereco(enderecoSalvo);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        clienteSalvo.setNome("Fulano");
        clienteSalvo.setTelefone("84 22222-2222");
        Cliente clienteAtualizado = clienteRepository.save(clienteSalvo);


        Assertions.assertThat(clienteAtualizado).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(clienteSalvo.getNome());
        Assertions.assertThat(clienteAtualizado.getTelefone()).isEqualTo(cliente.getTelefone());

    }

    @Test
    @DisplayName(value = "Deletar um cliente com sucesso")
    void deletarCliente_quandoSucesso(){
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(cliente.getEndereco());
        cliente.setEndereco(enderecoSalvo);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Deletando
        clienteRepository.deleteById(clienteSalvo.getId());

        // Buscar cliente deletado
        Optional<Cliente> clienteDeletado = clienteRepository.findById(clienteSalvo.getId());

        Assertions.assertThat(clienteDeletado.isPresent()).isFalse();

    }


    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientes_quandoSucesso(){
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(cliente.getEndereco());
        cliente.setEndereco(enderecoSalvo);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        List<Cliente> clientes = clienteRepository.findAll();

        Assertions.assertThat(clientes).isNotEmpty();
        Assertions.assertThat(clientes.size()).isEqualTo(1);
        Assertions.assertThat(clientes).contains(clienteSalvo);

    }


    @Test
    @DisplayName(value = "Listar clientes pelo nome com sucesso")
    void listarClientesPeloNome_quandoSucesso(){
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(cliente.getEndereco());
        cliente.setEndereco(enderecoSalvo);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        List<Cliente> clientes = clienteRepository.findByName(clienteSalvo.getNome());

        Assertions.assertThat(clientes).isNotEmpty();
        Assertions.assertThat(clientes.size()).isEqualTo(1);
        Assertions.assertThat(clientes).contains(clienteSalvo);
    }

    @Test
    @DisplayName(value = "Buscar cliente pelo email com sucesso")
    void buscarClientesPeloEmail_quandoSucesso(){
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(cliente.getEndereco());
        cliente.setEndereco(enderecoSalvo);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        Cliente clienteBuscado  = clienteRepository.findByEmail(clienteSalvo.getEmail());

        // Verificar se cliente não é nulo
        Assertions.assertThat(clienteBuscado).isNotNull();
        Assertions.assertThat(clienteBuscado.getId()).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(clienteBuscado.getNome()).isEqualTo(clienteSalvo.getNome());
        Assertions.assertThat(clienteBuscado.getEmail()).isEqualTo(clienteSalvo.getEmail());
        Assertions.assertThat(clienteBuscado.getTelefone()).isEqualTo(clienteSalvo.getTelefone());
        Assertions.assertThat(clienteBuscado.getEndereco().getRua()).isEqualTo(clienteSalvo.getEndereco().getRua());
        Assertions.assertThat(clienteBuscado.getEndereco().getBairro()).isEqualTo(clienteSalvo.getEndereco().getBairro());
        Assertions.assertThat(clienteBuscado.getEndereco().getCep()).isEqualTo(clienteSalvo.getEndereco().getCep());
        Assertions.assertThat(clienteBuscado.getEndereco().getNumero()).isEqualTo(clienteSalvo.getEndereco().getNumero());
        Assertions.assertThat(clienteBuscado.getEndereco().getCidade()).isEqualTo(clienteSalvo.getEndereco().getCidade());
        Assertions.assertThat(clienteBuscado.getEndereco().getUf()).isEqualTo(clienteSalvo.getEndereco().getUf());
    }

}
