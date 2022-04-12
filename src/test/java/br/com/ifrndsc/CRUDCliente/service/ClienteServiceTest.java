package br.com.ifrndsc.CRUDCliente.service;

import br.com.ifrndsc.CRUDCliente.model.Cliente;
import br.com.ifrndsc.CRUDCliente.model.Endereco;
import br.com.ifrndsc.CRUDCliente.repository.ClienteRepository;
import br.com.ifrndsc.CRUDCliente.util.ClienteCreator;
import br.com.ifrndsc.CRUDCliente.util.EnderecoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    EnderecoService enderecoService;

    @BeforeEach
    void setup(){

        List<Cliente> clienteList = new ArrayList<Cliente>();
        clienteList.add(ClienteCreator.clienteSalvoValido());

        // Quando for executado o findAll do repository
        BDDMockito.when(clienteRepository.findAll())
                .thenReturn(clienteList);

        // Quando for executado o findByName do repository
        BDDMockito.when(clienteRepository.findByName(ArgumentMatchers.any()))
                .thenReturn(clienteList);

        // Quando for executado o findByEmail do repository
        BDDMockito.when(clienteRepository.findByEmail(ArgumentMatchers.any()))
                .thenReturn(ClienteCreator.clienteSalvoValido());

        // Quando for executado o findById do repository
        BDDMockito.when(clienteRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(java.util.Optional.ofNullable(ClienteCreator.clienteSalvoValido()));

        // Quando for executado o save do repository
        BDDMockito.when(clienteRepository.save(ArgumentMatchers.any()))
                .thenReturn(ClienteCreator.clienteSalvoValido());

        // Quando for executado o deleteById do repository
        BDDMockito.doNothing().when(clienteRepository).deleteById(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName(value = "Salvar um cliente com sucesso")
    void salvarCliente_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Cliente clienteSalvo = clienteService.save(cliente);

        // Verificar se cliente não é nulo
        Assertions.assertThat(clienteSalvo).isNotNull();
        Assertions.assertThat(clienteSalvo.getId()).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(clienteSalvo.getNome()).isEqualTo(clienteEsperado.getNome());
        Assertions.assertThat(clienteSalvo.getEmail()).isEqualTo(clienteEsperado.getEmail());
        Assertions.assertThat(clienteSalvo.getTelefone()).isEqualTo(clienteEsperado.getTelefone());
        Assertions.assertThat(clienteSalvo.getEndereco().getRua()).isEqualTo(clienteEsperado.getEndereco().getRua());
        Assertions.assertThat(clienteSalvo.getEndereco().getBairro()).isEqualTo(clienteEsperado.getEndereco().getBairro());
        Assertions.assertThat(clienteSalvo.getEndereco().getCep()).isEqualTo(clienteEsperado.getEndereco().getCep());
        Assertions.assertThat(clienteSalvo.getEndereco().getNumero()).isEqualTo(clienteEsperado.getEndereco().getNumero());
        Assertions.assertThat(clienteSalvo.getEndereco().getCidade()).isEqualTo(clienteEsperado.getEndereco().getCidade());
        Assertions.assertThat(clienteSalvo.getEndereco().getUf()).isEqualTo(clienteEsperado.getEndereco().getUf());
    }

    @Test
    @DisplayName(value = "Atualizar um cliente com sucesso")
    void atualizarCliente_quandoSucesso(){
        Cliente cliente = ClienteCreator.clienteParaSalvar();
        Cliente clienteSalvo = clienteService.save(cliente);

        clienteSalvo.setNome("Fulano");
        clienteSalvo.setTelefone("84 22222-2222");
        Cliente clienteAtualizado = clienteService.update(clienteSalvo);

        Assertions.assertThat(clienteAtualizado).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(clienteSalvo.getNome());
        Assertions.assertThat(clienteAtualizado.getTelefone()).isEqualTo(clienteSalvo.getTelefone());

    }

    @Test
    @DisplayName(value = "Deletar um cliente com sucesso")
    void deletarCliente_quandoSucesso(){

        Assertions.assertThatCode(() -> clienteService.delete(1L))
                .doesNotThrowAnyException();


    }

    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientes_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();

        List<Cliente> clientes = clienteService.findAll();

        Assertions.assertThat(clientes).isNotEmpty();
        Assertions.assertThat(clientes.size()).isEqualTo(1);
        Assertions.assertThat(clientes).contains(clienteEsperado);

    }

    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientePeloId_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();

        Cliente cliente = clienteService.findById(1L);

        Assertions.assertThat(cliente.getNome()).isEqualTo(clienteEsperado.getNome());
        Assertions.assertThat(cliente.getId()).isEqualTo(clienteEsperado.getId());
        Assertions.assertThat(cliente.getTelefone()).isEqualTo(clienteEsperado.getTelefone());
        Assertions.assertThat(cliente.getEmail()).isEqualTo(clienteEsperado.getEmail());

    }

    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientesPeloNome_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();

        List<Cliente> clientes = clienteService.findByName(clienteEsperado.getNome());

        Assertions.assertThat(clientes).isNotEmpty();
        Assertions.assertThat(clientes.size()).isEqualTo(1);

        Assertions.assertThat(clientes.get(0).getNome()).isEqualTo(clienteEsperado.getNome());
    }

    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientePeloEmail_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();

        Cliente cliente = clienteService.clienteRepository.findByEmail(clienteEsperado.getEmail());

        Assertions.assertThat(cliente.getNome()).isEqualTo(clienteEsperado.getNome());
        Assertions.assertThat(cliente.getId()).isEqualTo(clienteEsperado.getId());
        Assertions.assertThat(cliente.getTelefone()).isEqualTo(clienteEsperado.getTelefone());
        Assertions.assertThat(cliente.getEmail()).isEqualTo(clienteEsperado.getEmail());

    }

}
