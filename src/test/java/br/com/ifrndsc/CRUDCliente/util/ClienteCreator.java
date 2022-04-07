package br.com.ifrndsc.CRUDCliente.util;


import br.com.ifrndsc.CRUDCliente.model.Cliente;
import br.com.ifrndsc.CRUDCliente.model.Endereco;

public class ClienteCreator {

    public static Cliente clienteParaSalvar(){

        return Cliente.builder()
                .nome("Iramar Ferreira dos Santos")
                .email("iramarbsi@gmail.com")
                .telefone("84 11111-1111")
                .endereco(EnderecoCreator.enderecoParaSalvar())
                .build();
    }

    public static Cliente clienteSalvoValido(){

        return Cliente.builder()
                .id(1L)
                .nome("Iramar Ferreira dos Santos")
                .email("iramarbsi@gmail.com")
                .telefone("84 11111-1111")
                .endereco(EnderecoCreator.enderecoSalvoValido())
                .build();
    }

}
