package com.example.books.controllers;

import com.example.books.entities.Author;
import com.example.books.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor

@Tag(
        name = "Autores",
        description = "Operações relacionadas ao gerenciamento de autores"
)

public class AuthorController {

    private final AuthorService service;


    @Operation(summary = "Listar autores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autores listados"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @GetMapping
    public List<Author> listar() {

        return service.listar();
    }


    @Operation(summary = "Criar autor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Autor criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Autor duplicado"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PostMapping
    public Author criar(@RequestBody Author author) {

        return service.salvar(author);
    }


    @Operation(summary = "Atualizar autor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Author> atualizar(
            @PathVariable Long id,
            @RequestBody Author author
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, author)
        );
    }


    @Operation(summary = "Excluir autor")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Autor excluído"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
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