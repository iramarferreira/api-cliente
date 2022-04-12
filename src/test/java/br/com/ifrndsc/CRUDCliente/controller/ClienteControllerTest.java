package br.com.ifrndsc.CRUDCliente.controller;

import br.com.ifrndsc.CRUDCliente.model.Cliente;
import br.com.ifrndsc.CRUDCliente.service.ClienteService;
import br.com.ifrndsc.CRUDCliente.util.ClienteCreator;
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

@ExtendWith(SpringExtension.class)
public class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteService clienteService;

    @BeforeEach
    void setup(){

        List<Cliente> clienteList = new ArrayList<Cliente>();
        clienteList.add(ClienteCreator.clienteSalvoValido());

        // Quando for executado o findAll do service
        BDDMockito.when(clienteService.findAll())
                .thenReturn(clienteList);

        // Quando for executado o findByName do service
        BDDMockito.when(clienteService.findByName(ArgumentMatchers.any()))
                .thenReturn(clienteList);


        // Quando for executado o findById do service
        BDDMockito.when(clienteService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteCreator.clienteSalvoValido());

        // Quando for executado o save do service
        BDDMockito.when(clienteService.save(ArgumentMatchers.any()))
                .thenReturn(ClienteCreator.clienteSalvoValido());

        // Quando for executado o update do service
        BDDMockito.when(clienteService.update(ArgumentMatchers.any()))
                .thenReturn(ClienteCreator.clienteSalvoValido());

        // Quando for executado o deleteById do service
        BDDMockito.doNothing().when(clienteService).delete(ArgumentMatchers.anyLong());

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
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();
        Cliente clienteAtualizado = clienteController.atualizar(clienteEsperado).getBody();

        Assertions.assertThat(clienteAtualizado).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(clienteEsperado.getNome());
        Assertions.assertThat(clienteAtualizado.getTelefone()).isEqualTo(clienteEsperado.getTelefone());

    }

    @Test
    @DisplayName(value = "Deletar um cliente com sucesso")
    void deletarCliente_quandoSucesso(){

        Assertions.assertThatCode(() -> clienteController.deletarCliente(1L))
                .doesNotThrowAnyException();


    }

    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientes_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();

        List<Cliente> clientes = clienteController.listarClientes().getBody();

        Assertions.assertThat(clientes).isNotEmpty();
        Assertions.assertThat(clientes.size()).isEqualTo(1);

    }

    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientePeloId_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();

        Cliente cliente = clienteController.findCliente(1L).getBody();

        Assertions.assertThat(cliente.getNome()).isEqualTo(clienteEsperado.getNome());
        Assertions.assertThat(cliente.getId()).isEqualTo(clienteEsperado.getId());
        Assertions.assertThat(cliente.getTelefone()).isEqualTo(clienteEsperado.getTelefone());
        Assertions.assertThat(cliente.getEmail()).isEqualTo(clienteEsperado.getEmail());

    }

    @Test
    @DisplayName(value = "Listar clientes com sucesso")
    void listarClientesPeloNome_quandoSucesso(){
        Cliente clienteEsperado = ClienteCreator.clienteSalvoValido();

        List<Cliente> clientes = clienteController.findByName(clienteEsperado.getNome()).getBody();

        Assertions.assertThat(clientes).isNotEmpty();
        Assertions.assertThat(clientes.size()).isEqualTo(1);

        Assertions.assertThat(clientes.get(0).getNome()).isEqualTo(clienteEsperado.getNome());
    }


}
