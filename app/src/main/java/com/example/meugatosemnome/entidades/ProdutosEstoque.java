package com.example.meugatosemnome.entidades;

public class ProdutosEstoque {

    private Integer id;
    private Double quantidade;
    private String categoria, nome, tipoProduto;

    public ProdutosEstoque() {
    }

    public ProdutosEstoque(Integer id, String categoria, Double quantidade, String nome, String tipoProduto) {
        this.id = id;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
    }

    // Inicio getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
// Fim getters and setters
}

