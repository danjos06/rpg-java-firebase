package org.example;

public class Item {
    private String nome;
    private int quantidade;
    private String descricao;
    private String tipo; // "arma", "consumivel", "chave", "armadura", etc
    private int durabilidade; // -1 para itens sem durabilidade
    private int durabilidadeMaxima;

    public Item() {}

    // Construtor básico
    public Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.durabilidade = -1; // Sem durabilidade por padrão
        this.durabilidadeMaxima = -1;
    }

    // Construtor com descrição e tipo
    public Item(String nome, int quantidade, String descricao, String tipo) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.tipo = tipo;
        this.durabilidade = -1;
        this.durabilidadeMaxima = -1;
    }

    // Construtor completo com durabilidade
    public Item(String nome, int quantidade, String descricao, String tipo, int durabilidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.tipo = tipo;
        this.durabilidade = durabilidade;
        this.durabilidadeMaxima = durabilidade;
    }

    public boolean verificarSeConsumivel() {
        return "consumivel".equalsIgnoreCase(tipo);
    }

    public boolean verificarSeUsavel() {
        return tipo != null && (tipo.equalsIgnoreCase("consumivel") ||
                tipo.equalsIgnoreCase("escudo") ||
                tipo.equalsIgnoreCase("buff"));
    }

    // Métodos úteis para durabilidade
    public void reduzirDurabilidade(int valor) {
        if (durabilidade > 0) {
            durabilidade -= valor;
            if (durabilidade < 0) {
                durabilidade = 0;
            }
        }
    }

    public boolean estaDanificado() {
        return durabilidade > 0 && durabilidade < durabilidadeMaxima;
    }

    public boolean estaQuebrado() {
        return durabilidade == 0;
    }

    public boolean temDurabilidade() {
        return durabilidade != -1;
    }

    public void repararCompleto() {
        if (temDurabilidade()) {
            durabilidade = durabilidadeMaxima;
        }
    }

    public void reparar(int valor) {
        if (temDurabilidade()) {
            durabilidade += valor;
            if (durabilidade > durabilidadeMaxima) {
                durabilidade = durabilidadeMaxima;
            }
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public int getDurabilidadeMaxima() {
        return durabilidadeMaxima;
    }

    public void setDurabilidadeMaxima(int durabilidadeMaxima) {
        this.durabilidadeMaxima = durabilidadeMaxima;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(" x").append(quantidade);

        if (temDurabilidade()) {
            sb.append(" [").append(durabilidade).append("/").append(durabilidadeMaxima).append("]");
            if (estaQuebrado()) {
                sb.append(" (QUEBRADO)");
            }
        }

        return sb.toString();
    }
}