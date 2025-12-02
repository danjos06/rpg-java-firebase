package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Player {
    private String id;
    private String nome;
    private int level;
    private int xp;
    private int hp;
    private int maxHp;
    private int ato = 1;

    // Novos atributos de combate
    private int danoBase;
    private int defesa;

    private List<Item> inventario;

    // Buffs temporários
    private int escudoAtivo = 0;
    private int buffAtaque = 0;

    public Player() {
        this.inventario = new ArrayList<>();
    }

    public Player(String nome) {
        this.nome = nome;
        this.level = 1;
        this.xp = 0;
        this.hp = 100;
        this.maxHp = 100;
        this.danoBase = 5;  // Dano inicial
        this.defesa = 10;   // Defesa inicial
        this.inventario = new ArrayList<>();
    }

    // Método para subir de nível
    public void subirNivel() {
        level++;

        // Aumenta atributos ao subir de nível
        maxHp += 20;           // +20 HP máximo por nível
        hp = maxHp;            // Cura completamente ao subir de nível
        danoBase += 2;         // +2 de dano base por nível
        defesa += 1;           // +1 de defesa por nível

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🎉 SUBIU PARA NÍVEL " + level + "! 🎉");
        System.out.println("║ ❤️  HP Máximo: " + maxHp);
        System.out.println("║ ⚔️  Dano Base: " + danoBase);
        System.out.println("║ 🛡️  Defesa: " + defesa);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }

    // Verifica se pode subir de nível
    public boolean podeSubirNivel() {
        return xp >= calcularXpNecessario();
    }

    // Calcula XP necessário para o próximo nível
    public int calcularXpNecessario() {
        return level * 100; // 100 XP por nível
    }

    // Adiciona XP e verifica se subiu de nível
    public void adicionarXp(int quantidade) {
        xp += quantidade;
        System.out.println("✨ +" + quantidade + " XP! (" + xp + "/" + calcularXpNecessario() + ")");

        while (podeSubirNivel()) {
            xp -= calcularXpNecessario();
            subirNivel();
        }
    }

    // Métodos de inventário (mantém os mesmos)
    public void adicionarItem(String nomeItem, int quantidade) {
        Item itemExistente = buscarItem(nomeItem);

        if (itemExistente != null && !itemExistente.temDurabilidade()) {
            itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
        } else {
            inventario.add(new Item(nomeItem, quantidade));
        }
    }

    public void adicionarItem(String nomeItem, int quantidade, String descricao, String tipo) {
        Item itemExistente = buscarItemPorTipo(nomeItem, tipo);

        if (itemExistente != null && !itemExistente.temDurabilidade()) {
            itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
        } else {
            inventario.add(new Item(nomeItem, quantidade, descricao, tipo));
        }
    }

    public void adicionarItem(String nomeItem, int quantidade, String descricao, String tipo, int durabilidade) {
        inventario.add(new Item(nomeItem, quantidade, descricao, tipo, durabilidade));
    }

    public void removerItem(String nomeItem, int quantidade) {
        Item item = buscarItem(nomeItem);

        if (item != null) {
            int novaQuantidade = item.getQuantidade() - quantidade;
            if (novaQuantidade <= 0) {
                inventario.remove(item);
            } else {
                item.setQuantidade(novaQuantidade);
            }
        }
    }

    public void removerItemEspecifico(Item item) {
        inventario.remove(item);
    }

    public boolean temItem(String nomeItem) {
        return buscarItem(nomeItem) != null;
    }

    public boolean temItemTipo(String tipo) {
        for (Item item : inventario) {
            if (tipo.equalsIgnoreCase(item.getTipo())) {
                return true;
            }
        }
        return false;
    }

    public int getQuantidadeItem(String nomeItem) {
        int total = 0;
        for (Item item : inventario) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                total += item.getQuantidade();
            }
        }
        return total;
    }

    private Item buscarItem(String nomeItem) {
        for (Item item : inventario) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                return item;
            }
        }
        return null;
    }

    private Item buscarItemPorTipo(String nomeItem, String tipo) {
        for (Item item : inventario) {
            if (item.getNome().equalsIgnoreCase(nomeItem) &&
                    item.getTipo() != null &&
                    item.getTipo().equalsIgnoreCase(tipo)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> buscarItensPorTipo(String tipo) {
        List<Item> itensEncontrados = new ArrayList<>();
        for (Item item : inventario) {
            if (tipo.equalsIgnoreCase(item.getTipo())) {
                itensEncontrados.add(item);
            }
        }
        return itensEncontrados;
    }

    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }

        System.out.println("\n=== INVENTÁRIO ===");

        List<String> tiposExibidos = new ArrayList<>();

        for (Item item : inventario) {
            String tipo = item.getTipo() != null ? item.getTipo() : "Diversos";

            if (!tiposExibidos.contains(tipo)) {
                tiposExibidos.add(tipo);
                System.out.println("\n[" + tipo.toUpperCase() + "]");

                for (Item i : inventario) {
                    String tipoItem = i.getTipo() != null ? i.getTipo() : "Diversos";
                    if (tipoItem.equals(tipo)) {
                        System.out.print("- " + i.toString());

                        if (i.getDescricao() != null && !i.getDescricao().isEmpty()) {
                            System.out.print(" - " + i.getDescricao());
                        }

                        if (i.estaQuebrado()) {
                            System.out.print(" [INUTILIZÁVEL]");
                        }

                        System.out.println();
                    }
                }
            }
        }
        System.out.println();
    }

    public void mostrarItensTipo(String tipo) {
        List<Item> itens = buscarItensPorTipo(tipo);

        if (itens.isEmpty()) {
            System.out.println("Você não possui itens do tipo: " + tipo);
            return;
        }

        System.out.println("\n=== " + tipo.toUpperCase() + " ===");
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println((i + 1) + ". " + item.toString() +
                    (item.getDescricao() != null ? " - " + item.getDescricao() : ""));
        }
        System.out.println();
    }

    // Mostra status completo do jogador
    public void mostrarStatus() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("STATUS DO JOGADOR");
        System.out.println("║ Nome: " + nome);
        System.out.println("║ Nível: " + level);
        System.out.println("║ XP: " + xp + "/" + calcularXpNecessario());
        System.out.println("║ ❤️  HP: " + hp + "/" + maxHp);
        System.out.println("║ ⚔️  Dano Base: " + danoBase);
        System.out.println("║ 🛡️  Defesa: " + defesa);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }

    public void reduzirBuffs() {
        if (escudoAtivo > 0) escudoAtivo--;
        if (buffAtaque > 0) buffAtaque--;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public void setDanoBase(int danoBase) {
        this.danoBase = danoBase;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getAto() {
        return ato;
    }

    public void setAto(int ato) {
        this.ato = ato;
    }

    public List<Item> getInventario() {
        return inventario;
    }

    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }

    public int getEscudoAtivo() {
        return escudoAtivo;
    }

    public void setEscudoAtivo(int escudoAtivo) {
        this.escudoAtivo = escudoAtivo;
    }

    public int getBuffAtaque() {
        return buffAtaque;
    }

    public void setBuffAtaque(int buffAtaque) {
        this.buffAtaque = buffAtaque;
    }
}