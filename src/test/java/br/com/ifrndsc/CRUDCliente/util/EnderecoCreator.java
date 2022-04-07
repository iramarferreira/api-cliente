package br.com.ifrndsc.CRUDCliente.util;

import br.com.ifrndsc.CRUDCliente.model.Endereco;

public class EnderecoCreator {

    public static Endereco enderecoParaSalvar(){
        Endereco endereco = Endereco.builder()
                .rua("José da Costa Cisne Neto")
                .bairro("Dinarte Mariz")
                .cidade("Parelhas")
                .cep("59360-000")
                .uf("RN")
                .numero(149)
                .build();

        return endereco;
    }

    public static Endereco enderecoSalvoValido(){
        Endereco endereco = Endereco.builder()
                .id(1L)
                .rua("José da Costa Cisne Neto")
                .bairro("Dinarte Mariz")
                .cidade("Parelhas")
                .cep("59360-000")
                .uf("RN")
                .numero(149)
                .build();

        return endereco;
    }

}
