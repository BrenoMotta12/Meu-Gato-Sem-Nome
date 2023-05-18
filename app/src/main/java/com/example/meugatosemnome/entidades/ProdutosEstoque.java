package com.example.meugatosemnome.entidades;

public abstract class ProdutosEstoque {

    private int id;
    private int idCategoria;
    private double quantidade;
    private double valor;

    public ProdutosEstoque() {
    }

    public ProdutosEstoque(int id, int idCategoria, double quantidade, double valor) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    // Inicio getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    // Fim getters and setters
}

