package com.example.books.controllers;

import com.example.books.entities.Publisher;
import com.example.books.service.PublisherService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor

@Tag(
        name = "Editoras",
        description = "Operações relacionadas às editoras"
)

public class PublisherController {

    private final PublisherService service;


    @Operation(summary = "Listar editoras")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Editoras listadas"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @GetMapping
    public List<Publisher> listar() {

        return service.listar();
    }


    @Operation(summary = "Criar uma editora")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Editora criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Editora duplicada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PostMapping
    public Publisher criar(
            @RequestBody Publisher publisher
    ) {

        return service.salvar(publisher);
    }


    @Operation(summary = "Atualizar uma editora")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Editora atualizada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> atualizar(
            @PathVariable Long id,
            @RequestBody Publisher publisher
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, publisher)
        );
    }


    @Operation(summary = "Excluir uma editora")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Editora excluída"),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}