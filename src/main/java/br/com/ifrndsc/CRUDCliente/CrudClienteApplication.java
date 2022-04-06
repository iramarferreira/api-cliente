package br.com.ifrndsc.CRUDCliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CrudClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudClienteApplication.class, args);
	}

}
