package br.com.ifrndsc.CRUDCliente.service;

import br.com.ifrndsc.CRUDCliente.model.Endereco;
import br.com.ifrndsc.CRUDCliente.repository.EnderecoRepository;
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
public class EnderecoServiceTest {

    //utiliza quando queremos testar a classe em si
    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    void setup(){
        // Criando uma lista
        List<Endereco> enderecoList = new ArrayList<>();
        enderecoList.add(EnderecoCreator.enderecoSalvoValido());

        // Quando for executado o findAll do repository
        BDDMockito.when(enderecoRepository.findAll())
                .thenReturn(enderecoList);

        // Quando for executado o findById do repository
        BDDMockito.when(enderecoRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(java.util.Optional.ofNullable(EnderecoCreator.enderecoSalvoValido()));

        // Quando for executado o save do repository
        BDDMockito.when(enderecoRepository.save(ArgumentMatchers.any()))
                .thenReturn(EnderecoCreator.enderecoSalvoValido());

        // Quando for executado o deleteById do repository
        BDDMockito.doNothing().when(enderecoRepository).deleteById(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName(value = "Salvar um endereço com sucesso")
    void salvarEndereco_quandoSucesso(){
        Endereco enderecoEsperado = EnderecoCreator.enderecoSalvoValido();
        Endereco endereco = EnderecoCreator.enderecoParaSalvar();
        Endereco enderecoSalvo = enderecoService.save(endereco);

        // Verificar se endereço não é nulo
        Assertions.assertThat(enderecoSalvo).isNotNull();
        Assertions.assertThat(enderecoSalvo.getId()).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(enderecoSalvo.getRua()).isEqualTo(enderecoEsperado.getRua());
        Assertions.assertThat(enderecoSalvo.getBairro()).isEqualTo(enderecoEsperado.getBairro());
        Assertions.assertThat(enderecoSalvo.getCep()).isEqualTo(enderecoEsperado.getCep());
        Assertions.assertThat(enderecoSalvo.getNumero()).isEqualTo(enderecoEsperado.getNumero());
        Assertions.assertThat(enderecoSalvo.getCidade()).isEqualTo(enderecoEsperado.getCidade());
        Assertions.assertThat(enderecoSalvo.getUf()).isEqualTo(enderecoEsperado.getUf());
    }

    @Test
    @DisplayName(value = "Deletar um endereço com sucesso")
    void deletarEndereco_quandoSucesso(){

        Assertions.assertThatCode(() -> enderecoService.delete(1L))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName(value = "Lista de endereços com sucesso")
    void listarEnderecos_quandoSucesso(){

        List<Endereco> enderecos = enderecoService.findAll();

        Assertions.assertThat(enderecos).isNotEmpty();
        Assertions.assertThat(enderecos.size()).isEqualTo(1);
    }

    @Test
    @DisplayName(value = "Atualizar um endereço com sucesso")
    void atualizarEndereco_quandoSucesso(){
        Endereco enderecoEsperado = EnderecoCreator.enderecoSalvoValido();
        Endereco endereco = EnderecoCreator.enderecoParaSalvar();
        Endereco enderecoAtualizado = enderecoService.update(endereco);

        // Verificar se endereço não é nulo
        Assertions.assertThat(enderecoAtualizado).isNotNull();
        Assertions.assertThat(enderecoAtualizado.getId()).isNotNull();

        // Verificar se realmente foi atualizado
        Assertions.assertThat(enderecoAtualizado.getRua()).isEqualTo(enderecoEsperado.getRua());
        Assertions.assertThat(enderecoAtualizado.getBairro()).isEqualTo(enderecoEsperado.getBairro());
        Assertions.assertThat(enderecoAtualizado.getUf()).isEqualTo(enderecoEsperado.getUf());

    }

}
