package com.example.books.enums;

public enum Rating {

    RUIM(1),
    BOM(3),
    OTIMO(5);

    private final int valor;

    Rating(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}