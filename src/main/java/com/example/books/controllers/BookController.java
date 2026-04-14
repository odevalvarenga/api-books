package com.example.books.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

import com.example.books.entities.Book;
import com.example.books.service.BookService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(
        name = "Books",
        description = "Operações relacionadas ao gerenciamento de livros"
)
public class BookController {

    private final BookService service;

    //LISTAR
    @Operation(summary = "Listar livros com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @GetMapping
    public Page<Book> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    //BUSCAR POR ID
    @Operation(summary = "Buscar livro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    //BUSCAR POR TÍTULO
    @Operation(summary = "Buscar livros por título")
    @GetMapping("/search")
    public List<Book> buscarPorTitulo(@RequestParam String title) {
        return service.buscarPorTitulo(title);
    }

    //POR AUTOR
    @Operation(summary = "Buscar livros por autor")
    @GetMapping("/author/{authorId}")
    public List<Book> buscarPorAutor(@PathVariable Long authorId) {
        return service.buscarPorAutor(authorId);
    }

    @Operation(summary = "Criar um novo livro")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de criação de livro",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
            {
              "title": "Clean Code",
              "author": { "id": 1 },
              "category": { "id": 1 },
              "publisher": { "id": 1 }
            }
            """
                    )
            )
    )

    //CRIAR
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<Book> criar(@Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(book));
    }

    //ATUALIZAR
    @Operation(summary = "Atualizar um livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> atualizar(@PathVariable Long id, @Valid @RequestBody Book book) {
        return ResponseEntity.ok(service.atualizar(id, book));
    }

    //DELETAR
    @Operation(summary = "Deletar um livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito: livro possui registros vinculados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}