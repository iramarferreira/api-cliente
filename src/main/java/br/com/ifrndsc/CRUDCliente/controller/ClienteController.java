package br.com.ifrndsc.CRUDCliente.controller;

import br.com.ifrndsc.CRUDCliente.model.Cliente;
import br.com.ifrndsc.CRUDCliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody // Retorno json
@CrossOrigin(originPatterns = "${spring.application.originPatterns}", allowCredentials = "true") // Permitir requisições de qualquer origem
@RequestMapping(path = "/api")
@Tag(name = "Clientes", description = "Documentação da API REST de Clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;


    @Operation(summary = "Rota para buscar todos os clientes", security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    // Rota para listar clientes
    @GetMapping(path = "/clientes")
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @Operation(summary = "Rota para buscar todos os clientes por paginação",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    // Rota para listar clientes por paginação
    @GetMapping(path = "/clientes/paginacao")
    public ResponseEntity<Page<Cliente>> listarClientes(@Parameter(hidden = true) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllPaginacao(pageable));
    }

    @Operation(summary = "Rota para buscar clientes pelo nome",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    // Rotorna para listar clientes a partir de um nome
    @GetMapping(path = "/cliente")
    public ResponseEntity<List<Cliente>> findByName(@RequestParam(value = "nome") String nome){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByName(nome));
    }

    @Operation(summary = "Rota para buscar todos os clientes pelo nome",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    // Rotorna para listar clientes a partir de um nome
    @GetMapping(path = "/cliente/namePadrao")
    public ResponseEntity<List<Cliente>> findByName2(@RequestParam(value = "nome") String nome){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByName2(nome));
    }


    @Operation(summary = "Rota para buscar a quantidade de clientes",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(example = "0")))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    // Retorna o total de cliente
    @GetMapping(path = "clientes/quantidade")
    public ResponseEntity<?> totalCliente(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.totalCliente());
    }

    @Operation(summary = "Rota para encontrar um cliente pelo ID",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
            content = @Content)
    @GetMapping(path = "/cliente/{id}")
    public ResponseEntity<Cliente> findCliente(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        if(cliente != null){
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Rota para cadastrar um cliente",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    // Rota para salvar um cliente
    @PostMapping(path = "/cliente")
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @Operation(summary = "Rota para atualizar um cliente",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
            content = @Content)
    // Uma rota para atualizar um cliente
    @PutMapping(path = "/cliente")
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente){
        if(clienteService.findById(cliente.getId()) != null){
            // Atualizar o id de endereco só para garantir
            cliente.getEndereco().setId(cliente.getId());
            Cliente clienteAtualizado = clienteService.update(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(clienteAtualizado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Rota para deletar um cliente",
            security = @SecurityRequirement(name = "Basic Auth"))
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(example = "Cliente deletado.")))
    @ApiResponse(responseCode = "401", description = "Não autorizado",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
            content = @Content)
    // Rota para deletar um cliente
    @DeleteMapping(path = "/cliente/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id){
        if(clienteService.findById(id) != null){
            clienteService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
        }
    }

}
