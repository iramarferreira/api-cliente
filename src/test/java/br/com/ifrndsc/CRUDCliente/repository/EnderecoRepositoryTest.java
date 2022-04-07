package br.com.ifrndsc.CRUDCliente.repository;

import br.com.ifrndsc.CRUDCliente.model.Endereco;
import br.com.ifrndsc.CRUDCliente.util.EnderecoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName(value = "Teste para EnderecoRepository")
public class EnderecoRepositoryTest {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Test
    @DisplayName(value = "Salvar um endereço com sucesso")
    void salvarEndereco_quandoSucesso(){
        Endereco endereco = EnderecoCreator.enderecoParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // Verificar se endereço não é nulo
        Assertions.assertThat(enderecoSalvo).isNotNull();
        Assertions.assertThat(enderecoSalvo.getId()).isNotNull();

        // Verificar se cada atributo foi realmente salvo
        Assertions.assertThat(enderecoSalvo.getRua()).isEqualTo(endereco.getRua());
        Assertions.assertThat(enderecoSalvo.getBairro()).isEqualTo(endereco.getBairro());
        Assertions.assertThat(enderecoSalvo.getCep()).isEqualTo(endereco.getCep());
        Assertions.assertThat(enderecoSalvo.getNumero()).isEqualTo(endereco.getNumero());
        Assertions.assertThat(enderecoSalvo.getCidade()).isEqualTo(endereco.getCidade());
        Assertions.assertThat(enderecoSalvo.getUf()).isEqualTo(endereco.getUf());
    }

    @Test
    @DisplayName(value = "Atualizar um endereço com sucesso")
    void atualizarEndereco_quandoSucesso(){
        Endereco endereco = EnderecoCreator.enderecoParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        enderecoSalvo.setRua("Rua Tenente Jonatas Luciano");
        enderecoSalvo.setBairro("Ivan Bezerra");

        // Atualizar endereço
        Endereco enderecoAtualizado = enderecoRepository.save(enderecoSalvo);

        // Verificar se endereço não é nulo
        Assertions.assertThat(enderecoAtualizado).isNotNull();
        Assertions.assertThat(enderecoAtualizado.getId()).isNotNull();

        // Verificar se realmente foi atualizado
        Assertions.assertThat(enderecoAtualizado.getRua()).isEqualTo(enderecoSalvo.getRua());
        Assertions.assertThat(enderecoAtualizado.getBairro()).isEqualTo(enderecoSalvo.getBairro());
        Assertions.assertThat(enderecoAtualizado).isEqualTo(enderecoSalvo);

    }

    @Test
    @DisplayName(value = "Deletar um endereço com sucesso")
    void deletarEndereco_quandoSucesso(){
        Endereco endereco = EnderecoCreator.enderecoParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // deletar o endereço salvo
        enderecoRepository.deleteById(enderecoSalvo.getId());

        // Buscar o endereço deletado
        Optional<Endereco> enderecoDeletado = enderecoRepository.findById(enderecoSalvo.getId());

        Assertions.assertThat(enderecoDeletado.isPresent()).isFalse();
    }

    @Test
    @DisplayName(value = "Lista de endereços com sucesso")
    void listarEnderecos_quandoSucesso(){
        Endereco endereco = EnderecoCreator.enderecoParaSalvar();
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        List<Endereco> enderecos = enderecoRepository.findAll();

        Assertions.assertThat(enderecos).isNotEmpty();
        Assertions.assertThat(enderecos.size()).isEqualTo(1);
        Assertions.assertThat(enderecos).contains(enderecoSalvo);
    }

}
