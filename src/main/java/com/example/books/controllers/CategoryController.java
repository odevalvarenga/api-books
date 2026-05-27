package com.example.books.controllers;

import com.example.books.entities.Category;
import com.example.books.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor

@Tag(
        name = "Categorias",
        description = "Operações relacionadas às categorias dos livros"
)

public class CategoryController {

    private final CategoryService service;


    @Operation(summary = "Listar categorias")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categorias listadas"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @GetMapping
    public List<Category> listar() {

        return service.listar();
    }


    @Operation(summary = "Criar uma categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Categoria duplicada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PostMapping
    public Category criar(
            @RequestBody Category category
    ) {

        return service.salvar(category);
    }


    @Operation(summary = "Atualizar uma categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria atualizada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Category> atualizar(
            @PathVariable Long id,
            @RequestBody Category category
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, category)
        );
    }


    @Operation(summary = "Excluir uma categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria excluída"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
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