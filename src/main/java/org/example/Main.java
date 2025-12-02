package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner e = new Scanner(System.in);
        Random r = new Random();
        FirebaseManager firebase = new FirebaseManager();

        System.out.println("=== O PROTOCOLO ÉDEN ===");
        System.out.println("Bem vindo ao RPG Protocolo Éden! O que deseja fazer?");
        int escolha;
        do {
            System.out.println("1 - Continuar aventura a partir de um player existente (digitar TOKEN)");
            System.out.println("2 - Começar uma nova aventura (criar novo player)");
            System.out.println("3 - Sair");
            escolha = e.nextInt();
            e.nextLine();

            if(escolha < 1 || escolha > 3){
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while(escolha < 1 || escolha > 3);

        Player jogador;

        if (escolha == 1) {
            System.out.print("Digite o TOKEN do jogador: ");
            String token = e.nextLine();

            jogador = firebase.buscarPlayer(token);

            while (jogador == null) {
                System.out.println("Token inválido...");
                System.out.println("1 - Tentar novamente");
                System.out.println("2 - Criar novo jogador");
                int op = e.nextInt();
                e.nextLine();

                while (op < 1 || op > 2) {
                    System.out.print("Opção inválida. Escolha 1 ou 2: ");
                    op = e.nextInt();
                    e.nextLine();
                }
                if (op == 1) {
                    System.out.print("Digite o TOKEN do jogador: ");
                    token = e.nextLine();
                    jogador = firebase.buscarPlayer(token);
                } else {
                    jogador = criarNovoPlayer(firebase, e);
                }
            }
        } else if (escolha == 2) {
            jogador = criarNovoPlayer(firebase, e);
        } else {
            System.out.println("Saindo do jogo...");
            return;
        }

        int hpJogador = jogador.getHp();
        int lvl = jogador.getLevel();
        int xp = jogador.getXp();

        // Carrega ato
        int atoAtual = jogador.getAto();
        System.out.println("\nCarregando Ato " + atoAtual + "...\n");

        // Direciona para o ato certo
        switch (atoAtual) {
            case 1:
                ato1(jogador, firebase, hpJogador, xp, lvl);
                break;

            case 2:
                ato2(jogador, firebase, hpJogador, xp, lvl);
                break;

            case 3:
                ato3(jogador, firebase, hpJogador, xp, lvl);
                break;

            case 4:
                ato4(jogador, firebase, hpJogador, xp, lvl);
                break;

            case 5:
                ato5(jogador, firebase, hpJogador, xp, lvl);
                break;

            default:
                System.out.println("Ato inválido, resetando para Ato 1.");
                jogador.setAto(1);
                ato1(jogador, firebase, hpJogador, xp, lvl);
        }
    }

    // ====================== ATO 1 =========================

    public static void ato1(Player jogador, FirebaseManager firebase, int hpJogador, int xp, int lvl) throws Exception {
        Scanner e = new Scanner(System.in);
        Random r = new Random();

        int maxHpJogador = jogador.getMaxHp();

        jogador.setAto(1);

        // Gera número aleatório da cobaia
        int idCobaia = r.nextInt(9000) + 1000;

        // Introdução

        System.out.println("\nAno 2040. O planeta está em colapso...");
        System.out.println("Você é identificado como Unidade " + idCobaia + ".");
        System.out.println("Alarmes tocam, luzes piscam. O escape começou.\n");

        System.out.println("==== ATO 1: DESPERTAR ====");

        System.out.println("[???]: Rápido, cobaia! O vírus saiu do controle!");
        System.out.println("1 - 'Quem é você?'");
        System.out.println("2 - 'O que aconteceu?'");
        System.out.println("3 - 'Onde estou?'");
        int fala;
        do {
            fala = e.nextInt();
            e.nextLine();

            if (fala < 1 || fala > 3) {
                System.out.print("Opção inválida. Escolha 1, 2 ou 3: ");
            }
        } while (fala < 1 || fala > 3);

        switch (fala) {
            case 1:
                System.out.println("[VOCÊ]: Quem é você?");
                System.out.println("[MASON]: Eu sou o Dr. Mason, responsável pela busca da cura do Vírus-P, porém as coisas saíram do controle...");

                System.out.println("1 - 'O que aconteceu?'");
                System.out.println("2 - 'Onde estou?'");
                do {
                    fala = e.nextInt();
                    e.nextLine();

                    if (fala < 1 || fala > 2) {
                        System.out.print("Opção inválida. Escolha 1 ou 2: ");
                    }
                } while (fala < 1 || fala > 2);

                if(fala == 1){
                    System.out.println("[VOCÊ]: O que aconteceu?");
                    System.out.println("[MASON]: O mundo está em colapso e você é a nossa única esperança, seu sangue é imune ao Vírus-P.");
                } else {
                    System.out.println("[VOCÊ]: Onde estou?");
                    System.out.println("[MASON]: Você está em um laboratório de pesquisas, seu sangue é imune ao vírus P que devastou metade do planeta e agora você tem uma missão de sobreviver e acabar com quem está por trás do controle desse caos.");
                }

                System.out.println("\n[MASON]: Não há mais tempo para perguntas, agora você deve fugir.");
                break;

            case 2:
                System.out.println("[VOCÊ]: O que aconteceu?");
                System.out.println("[MASON]: O mundo está em colapso e você é a nossa única esperança, seu sangue é imune ao Vírus-P.");

                System.out.println("1 - 'Quem é você?'");
                System.out.println("2 - 'Onde estou?'");
                do {
                    fala = e.nextInt();
                    e.nextLine();

                    if (fala < 1 || fala > 2) {
                        System.out.print("Opção inválida. Escolha 1 ou 2: ");
                    }
                } while (fala < 1 || fala > 2);

                if (fala == 1) {
                    System.out.println("[VOCÊ]: Quem é você?");
                    System.out.println("[MASON]: Eu sou o Dr. Mason, responsável pela busca da cura do Vírus-P, porém as coisas saíram do controle...");
                    System.out.println("\n[MASON]: Não há mais tempo para perguntas, agora você deve fugir.");
                } else {
                    System.out.println("[VOCÊ]: Onde estou?");
                    System.out.println("[???]: Você está em um laboratório de pesquisas, seu sangue é imune ao vírus P que devastou metade do planeta e agora você tem uma missão de sobreviver e acabar com quem está por trás do controle desse caos.");
                    System.out.println("\n[???]: Não há mais tempo para perguntas, agora você deve fugir.");
                }
                break;
            case 3:
                System.out.println("[VOCÊ]: Onde estou?");
                System.out.println("[???]: Você está em um laboratório de pesquisas, seu sangue é imune ao vírus P que devastou metade do planeta e agora você tem uma missão de sobreviver e acabar com quem está por trás do controle desse caos.");

                System.out.println("1 - 'Quem é você?'");
                System.out.println("2 - 'O que aconteceu?'");
                do {
                    fala = e.nextInt();
                    e.nextLine();

                    if (fala < 1 || fala > 2) {
                        System.out.print("Opção inválida. Escolha 1 ou 2: ");
                    }
                } while (fala < 1 || fala > 2);

                if (fala == 1) {
                    System.out.println("[VOCÊ]: Quem é você?");
                    System.out.println("[MASON]: Eu sou o Dr. Mason, responsável pela busca da cura do Vírus-P, porém as coisas saíram do controle...");
                    System.out.println("\n[MASON]: Não há mais tempo para perguntas, agora você deve fugir.");
                } else {
                    System.out.println("[VOCÊ]: O que aconteceu?");
                    System.out.println("[???]: O mundo está em colapso e você é a nossa única esperança, seu sangue é imune ao Vírus-P.");
                    System.out.println("\n[???]: Não há mais tempo para perguntas, agora você deve fugir.");
                }
                break;
        }

        // Atualiza player
        Map<String, Object> updates = new HashMap<>();
        updates.put("hp", jogador.getHp());
        updates.put("maxHp", jogador.getMaxHp());
        updates.put("xp", jogador.getXp());
        updates.put("level", jogador.getLevel());
        updates.put("danoBase", jogador.getDanoBase());
        updates.put("defesa", jogador.getDefesa());
        updates.put("ato", 2);

        firebase.atualizarCamposPlayer(jogador.getId(), updates);

        System.out.println("\n--- Fim do Ato 1 ---");
        System.out.println("Seu progresso foi salvo.");

        ato2(jogador, firebase, hpJogador, xp, lvl);

    }

    // ====================== ATO 2 =========================

    public static void ato2(Player jogador, FirebaseManager firebase, int hpJogador, int xp, int lvl) throws Exception {
        Scanner e = new Scanner(System.in);

        jogador.setAto(2);

        System.out.println("==== ATO 2: FUGA ====");

        jogador.adicionarItem("Bandagem", 3, "Restaura 20 HP", "consumivel");
        jogador.adicionarItem("Kit Médico", 1, "Restaura 50 HP", "consumivel");
        Map<String, Object> inventarioInicial = new HashMap<>();
        inventarioInicial.put("inventario", jogador.getInventario());
        firebase.atualizarCamposPlayer(jogador.getId(), inventarioInicial);

        System.out.println("\nNo caminho você encontra suprimentos médicos!");
        jogador.mostrarInventario();

        // Escolha da rota de fuga
        System.out.println("\nVocê percebe dois corredores de fuga:");
        System.out.println("1 - Túnel técnico (Mais curto, mas perigoso)");
        System.out.println("2 - Escada de emergência (Mais longo, mas seguro)");

        int caminho;
        do {
            caminho = e.nextInt();
            e.nextLine();

            if (caminho < 1 || caminho > 2) {
                System.out.print("Opção inválida. Escolha 1 ou 2: ");
            }
        } while (caminho < 1 || caminho > 2);

        if (caminho == 1) {
            System.out.println("\n=== TÚNEL TÉCNICO ===");
            System.out.println("Você entra no túnel... Um dróide aparece!");
            hpJogador = batalha(hpJogador, 90, 11, "Dróide de Manutenção", jogador, firebase);
            jogador.adicionarXp(100);

            System.out.println("\nVocê avança, mas outro inimigo surge!");
            hpJogador = batalha(hpJogador, 100, 12, "Dróide de Segurança", jogador, firebase);
            jogador.adicionarXp(120);

            System.out.println("\n*Ao sair do túnel, você encontra um armário de equipamentos militares aberto.*");
            System.out.println("1 - Pegar a armadura leve (+3 defesa)");
            System.out.println("2 - Pegar a espada longa (+7 dano)");

            int opc;
            do {
                opc = e.nextInt();
                e.nextLine();
                if (opc < 1 || opc > 2) {
                    System.out.print("Opção inválida. Escolha 1 ou 2: ");
                }
            } while (opc < 1 || opc > 2);

            if(opc == 1){
                jogador.adicionarItem("Colete Tático", 1, "Armadura leve que fornece +3 de defesa", "armadura");
                jogador.setDefesa(jogador.getDefesa() + 3);
                System.out.println("✓ Você veste o Colete Tático! Defesa aumentada!");
            } else {
                jogador.adicionarItem("Espada Longa", 1, "Uma espada de combate que fornece +7 de dano", "arma");
                jogador.setDanoBase(jogador.getDanoBase() + 7);
                System.out.println("✓ Você empunha a Espada Longa! Dano aumentado!");
            }

            inventarioInicial.put("inventario", jogador.getInventario());
            firebase.atualizarCamposPlayer(jogador.getId(), inventarioInicial);
            jogador.mostrarInventario();

        } else {
            System.out.println("\n=== ESCADA DE EMERGÊNCIA ===");
            System.out.println("Você começa a descer com cautela...");
            hpJogador = batalha(hpJogador, 85, 10, "Drone de Patrulha", jogador, firebase);
            jogador.adicionarXp(100);

            System.out.println("\n*Ao chegar no térreo, você encontra uma sala de armamentos.*");
            System.out.println("Você tem tempo para escolher apenas um equipamento:");
            System.out.println("1 - Colete de Kevlar (+5 defesa)");
            System.out.println("2 - Adaga Afiada (+5 dano) + Poção");

            int opc;
            do {
                opc = e.nextInt();
                e.nextLine();
                if (opc < 1 || opc > 2) {
                    System.out.print("Opção inválida. Escolha 1 ou 2: ");
                }
            } while (opc < 1 || opc > 2);

            if(opc == 1){
                jogador.adicionarItem("Colete de Kevlar", 1, "Armadura resistente que fornece +5 de defesa", "armadura");
                jogador.setDefesa(jogador.getDefesa() + 5);
                System.out.println("✓ Você veste o Colete de Kevlar! Defesa significativamente aumentada!");
            } else {
                jogador.adicionarItem("Adaga Afiada", 1, "Uma adaga mortal que fornece +5 de dano", "arma");
                jogador.adicionarItem("Poção Média", 1, "Restaura 60 HP", "consumivel");
                jogador.setDanoBase(jogador.getDanoBase() + 5);
                System.out.println("✓ Você pega a Adaga e uma Poção!");
            }

            inventarioInicial.put("inventario", jogador.getInventario());
            firebase.atualizarCamposPlayer(jogador.getId(), inventarioInicial);
            jogador.mostrarInventario();
        }

        System.out.println("\n*Você finalmente chega ao exterior... o ar livre após tanto tempo!*");
        System.out.println("*Mas algo se move nas sombras...*");

        Map<String, Object> updates = new HashMap<>();
        updates.put("hp", jogador.getHp());
        updates.put("maxHp", jogador.getMaxHp());
        updates.put("xp", jogador.getXp());
        updates.put("level", jogador.getLevel());
        updates.put("danoBase", jogador.getDanoBase());
        updates.put("defesa", jogador.getDefesa());
        updates.put("ato", 3);

        firebase.atualizarCamposPlayer(jogador.getId(), updates);

        System.out.println("\n--- Fim do Ato 2 ---");
        System.out.println("Seu progresso foi salvo.");

        ato3(jogador, firebase, hpJogador, xp, lvl);
    }

// ====================== ATO 3 =========================

    public static void ato3(Player jogador, FirebaseManager firebase, int hpJogador, int xp, int lvl) throws Exception {
        Scanner e = new Scanner(System.in);

        jogador.setAto(3);

        System.out.println("==== ATO 3: ENCONTRO ====");

        System.out.println("\n*Uma criatura mutante aparece do nada!*");
        System.out.println("*Ela parece ter detectado sua presença...*");
        hpJogador = batalha(hpJogador, 120, 13, "Mutante Caçador", jogador, firebase);
        jogador.adicionarXp(150);

        System.out.println("\n*Após a batalha, duas figuras aparecem das sombras*");
        System.out.println("[???]: Impressionante... você derrotou aquilo sozinho.");

        System.out.println("\n*Ao abrir os olhos você se depara com duas pessoas te observando*");
        System.out.println("[COMANDANTE HERRERA]: Calma, não somos inimigos. Sou Comandante Herrera, ex-militar, e esta é Sophia, minha parceira de confiança.");
        System.out.println("[SOPHIA]: Vimos sua luta. Você tem potencial... e sabemos quem está por trás de tudo isso.");
        System.out.println("[HERRERA]: Eve. A diretora do laboratório. Ela não está desenvolvendo uma cura, está criando um exército de mutantes para dominar o que resta do mundo.");
        System.out.println("[SOPHIA]: Precisamos de alguém como você. Alguém que sobreviveu ao experimento dela.");

        System.out.println("\n1 - 'Conte-me mais sobre Eve.'");
        System.out.println("2 - 'Como posso confiar em vocês?'");
        System.out.println("3 - 'Estou dentro. Vamos acabar com isso.'");

        int opc;
        do {
            opc = e.nextInt();
            e.nextLine();

            if (opc < 1 || opc > 3) {
                System.out.print("Opção inválida. Escolha 1, 2 ou 3: ");
            }
        } while (opc < 1 || opc > 3);

        Map<String, Object> inventarioInicial = new HashMap<>();

        switch (opc){
            case 1:
                System.out.println("[VOCÊ]: Conte-me mais sobre Eve.");
                System.out.println("[HERRERA]: Ela era brilhante... mas enlouqueceu com poder. O Vírus-P foi apenas o começo.");
                System.out.println("[SOPHIA]: Aqui, tome isso. Vai precisar.");
                jogador.adicionarItem("Estimulante de Combate", 2, "Aumenta força por 3 turnos (+5 dano)", "consumivel");
                jogador.adicionarItem("Poção Grande", 1, "Restaura 80 HP", "consumivel");
                break;

            case 2:
                System.out.println("[VOCÊ]: Como posso confiar em vocês?");
                System.out.println("[HERRERA]: Não pode. Mas isso aqui deveria ajudar.");

                System.out.println("\n*Herrera entrega equipamentos avançados*");

                if(!jogador.temItemTipo("armadura")){
                    jogador.adicionarItem("Armadura Reforçada", 1, "Armadura militar que fornece +7 de defesa", "armadura");
                    jogador.setDefesa(jogador.getDefesa() + 7);
                    System.out.println("✓ Você recebe uma Armadura Reforçada!");
                } else {
                    jogador.adicionarItem("Lâmina de Combate", 1, "Arma letal que fornece +10 de dano", "arma");
                    if(jogador.temItem("Adaga Afiada") || jogador.temItem("Espada Longa")){
                        jogador.setDanoBase(jogador.getDanoBase() - 5);
                    }
                    jogador.setDanoBase(jogador.getDanoBase() + 10);
                    System.out.println("✓ Você recebe uma Lâmina de Combate superior!");
                }

                jogador.adicionarItem("Kit Médico Avançado", 2, "Restaura 70 HP", "consumivel");
                break;

            case 3:
                System.out.println("[VOCÊ]: Estou dentro. Vamos acabar com isso.");
                System.out.println("[SOPHIA]: Decisão rápida. Gosto disso.");
                System.out.println("[HERRERA]: Então prepare-se bem. Pegue estes suprimentos.");
                jogador.adicionarItem("Poção Média", 3, "Restaura 60 HP", "consumivel");
                jogador.adicionarItem("Granada Atordoante", 1, "Reduz defesa do inimigo temporariamente", "consumivel");
                break;
        }

        inventarioInicial.put("inventario", jogador.getInventario());
        firebase.atualizarCamposPlayer(jogador.getId(), inventarioInicial);
        jogador.mostrarInventario();

        System.out.println("\n[HERRERA]: Agora vamos. Temos um longo caminho até o laboratório.");

        System.out.println("\n--- FIM DO ATO 3 ---");
        Map<String, Object> updates = new HashMap<>();
        updates.put("hp", jogador.getHp());
        updates.put("maxHp", jogador.getMaxHp());
        updates.put("xp", jogador.getXp());
        updates.put("level", jogador.getLevel());
        updates.put("danoBase", jogador.getDanoBase());
        updates.put("defesa", jogador.getDefesa());
        updates.put("ato", 4);
        firebase.atualizarCamposPlayer(jogador.getId(), updates);

        ato4(jogador, firebase, hpJogador, xp, lvl);
    }

// ====================== ATO 4 =========================

    public static void ato4(Player jogador, FirebaseManager firebase, int hpJogador, int xp, int lvl) throws Exception {
        Scanner e = new Scanner(System.in);

        jogador.setAto(4);

        System.out.println("\n=== ATO 4: INFILTRAÇÃO ===");

        System.out.println("\n*Durante a jornada até o laboratório...*");
        System.out.println("*Um humanoide modificado bloqueia o caminho!*");
        hpJogador = batalha(hpJogador, 140, 13, "Humanoide Modificado", jogador, firebase);
        jogador.adicionarXp(180);

        System.out.println("\n[SOPHIA]: Eles estão ficando mais fortes...");
        System.out.println("[HERRERA]: Precisamos nos apressar antes que Eve complete seus experimentos.");

        System.out.println("\n*Vocês chegam aos portões do laboratório Éden Corp.*");
        System.out.println("[HERRERA]: Temos duas opções de entrada:");
        System.out.println("1 - Entrada frontal - Rápida mas com mais guardas");
        System.out.println("2 - Entrada dos fundos - Mais demorada mas sigilosa");

        int opc;
        do {
            opc = e.nextInt();
            e.nextLine();
            if (opc < 1 || opc > 2) {
                System.out.print("Opção inválida. Escolha 1 ou 2: ");
            }
        } while (opc < 1 || opc > 2);

        if(opc == 1) {
            System.out.println("\n=== ASSALTO FRONTAL ===");
            System.out.println("*Vocês invadem pela entrada principal!*");
            System.out.println("*Guardas de elite aparecem!*");

            hpJogador = batalha(hpJogador, 110, 14, "Guarda de Elite", jogador, firebase);
            jogador.adicionarXp(150);

            System.out.println("\n*Mais reforços chegam!*");
            hpJogador = batalha(hpJogador, 130, 14, "Sentinela Armado", jogador, firebase);
            jogador.adicionarXp(170);

            System.out.println("\n[HERRERA]: Bom trabalho! Abriram caminho para Sophia hackear o sistema.");
        } else {
            System.out.println("\n=== INFILTRAÇÃO SILENCIOSA ===");
            System.out.println("*Vocês se esgueiram pelos fundos...*");
            System.out.println("*Mas um cão de guarda modificado detecta vocês!*");

            hpJogador = batalha(hpJogador, 100, 15, "Cão de Guarda Mutante", jogador, firebase);
            jogador.adicionarXp(140);

            System.out.println("\n[SOPHIA]: Por pouco... mas consegui acessar o sistema daqui.");
        }

        System.out.println("\n[SOPHIA]: Encontrei! Eve está no subsolo, no laboratório principal.");
        System.out.println("[DR. MASON surge das sombras]: Eu sabia que vocês viriam.");
        System.out.println("[VOCÊ]: Dr. Mason?!");
        System.out.println("[MASON]: Não há tempo. Eve sabe que estão aqui. Sigam-me!");

        System.out.println("\n*Dr. Mason os guia até um elevador de carga*");
        System.out.println("*As portas se abrem revelando um corredor escuro...*");

        Map<String, Object> updates = new HashMap<>();
        updates.put("hp", jogador.getHp());
        updates.put("maxHp", jogador.getMaxHp());
        updates.put("xp", jogador.getXp());
        updates.put("level", jogador.getLevel());
        updates.put("danoBase", jogador.getDanoBase());
        updates.put("defesa", jogador.getDefesa());
        updates.put("ato", 5);
        firebase.atualizarCamposPlayer(jogador.getId(), updates);

        System.out.println("\n--- FIM DO ATO 4 ---");
        ato5(jogador, firebase, hpJogador, xp, lvl);
    }

// ====================== ATO 5 (BOSS FINAL) =========================

    public static void ato5(Player jogador, FirebaseManager firebase, int hpJogador, int xp, int lvl) throws Exception {
        Scanner e = new Scanner(System.in);

        jogador.setAto(5);

        System.out.println("\n=== ATO 5: CONFRONTO FINAL ===");

        System.out.println("\n*Vocês entram no laboratório principal*");
        System.out.println("*Uma figura está de costas, observando telas com dados genéticos*");

        System.out.println("\n[EVE]: Finalmente... minha maior criação retorna.");
        System.out.println("*Ela se vira, revelando veias brilhantes em seu corpo*");
        System.out.println("[EVE]: Você deveria estar ao meu lado, não contra mim.");
        System.out.println("[EVE]: Mas não importa... vou mostrar o verdadeiro poder da evolução!");

        System.out.println("\n*Eve injeta um soro brilhante e seu corpo começa a se transformar*");
        System.out.println("*Sua pele fica metálica e seus olhos brilham em vermelho*");

        System.out.println("\n[HERRERA]: Preparem-se! Ela se transformou!");
        System.out.println("[SOPHIA]: Vamos te dar cobertura! Você consegue!");

        System.out.println("⚠️BATALHA FINAL: EVE MUTANTE");

        hpJogador = batalha(hpJogador, 280, 16, "Eve - Forma Mutante", jogador, firebase);
        jogador.adicionarXp(500);

        System.out.println("\n*Eve cai de joelhos, seu corpo voltando ao normal*");
        System.out.println("[EVE]: Im...impossível... eu era... perfeita...");
        System.out.println("*Ela desmorona, imóvel*");

        Map<String, Object> updates = new HashMap<>();
        updates.put("hp", jogador.getHp());
        updates.put("maxHp", jogador.getMaxHp());
        updates.put("xp", jogador.getXp());
        updates.put("level", jogador.getLevel());
        updates.put("danoBase", jogador.getDanoBase());
        updates.put("defesa", jogador.getDefesa());
        updates.put("ato", 6);
        firebase.atualizarCamposPlayer(jogador.getId(), updates);

        System.out.println("\n--- FIM DO ATO 5 ---");
        ato6(jogador, firebase, hpJogador, xp, lvl);
    }

// ====================== ATO 6 (EPÍLOGO) =========================

    public static void ato6(Player jogador, FirebaseManager firebase, int hpJogador, int xp, int lvl) throws Exception {
        Scanner e = new Scanner(System.in);

        jogador.setAto(6);

        System.out.println("\n=== ATO 6: RECOMEÇO ===");

        System.out.println("\n*Alguns dias depois...*");

        System.out.println("\n[COMANDANTE HERRERA]: Conseguimos. O laboratório foi destruído.");
        System.out.println("[SOPHIA]: E encontramos os dados de Eve. Com seu sangue, podemos desenvolver a cura de verdade.");
        System.out.println("[DR. MASON]: A humanidade tem uma segunda chance... graças a você.");
        System.out.println("\n[VOCÊ]: Não fiz sozinho. Fomos uma equipe.");

        System.out.println("\n*Vocês observam o pôr do sol de uma nova era*");
        System.out.println("*O mundo ainda está em ruínas, mas agora há esperança*");

        System.out.println("O PROTOCOLO ÉDEN");
        System.out.println("FIM");
        System.out.println("Obrigado por jogar!");


        jogador.mostrarStatus();
        jogador.mostrarInventario();

        Map<String, Object> updates = new HashMap<>();
        updates.put("hp", jogador.getHp());
        updates.put("maxHp", jogador.getMaxHp());
        updates.put("xp", jogador.getXp());
        updates.put("level", jogador.getLevel());
        updates.put("danoBase", jogador.getDanoBase());
        updates.put("defesa", jogador.getDefesa());
        updates.put("ato", 7);
        firebase.atualizarCamposPlayer(jogador.getId(), updates);

        System.out.println("\n--- JOGO CONCLUÍDO ---");
    }

    // ====================== BATALHA =======================

    public static int batalha(int hpJogador, int hpInimigoMax, int dtDefInimigo, String nomeInimigo, Player jogador, FirebaseManager firebase) {
        Scanner e = new Scanner(System.in);
        Random dado = new Random();

        int hpInimigo = hpInimigoMax;
        int dtDefJogador = jogador.getDefesa();
        boolean caido = false;

        String estimativaHp = "Não analisado (Observe o inimigo para obter informações)";

        System.out.println("\n=== INÍCIO DA BATALHA ===");
        System.out.println("Você enfrentará " + nomeInimigo + "!");

        jogador.setHp(hpJogador);

        while (jogador.getHp() > 0 && hpInimigo > 0) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("❤️ Sua vida: " + jogador.getHp() + "/" + jogador.getMaxHp()); // MUDANÇA AQUI
            System.out.println("⚔️ Dano: " + jogador.getDanoBase() + " | 🛡️ Defesa: " + jogador.getDefesa());

            // MOSTRA OS EFEITOS ATIVOS
            if (jogador.getEscudoAtivo() > 0) {
                System.out.println("🛡️ Escudo ativo: " + jogador.getEscudoAtivo() + " turnos restantes");
            }
            if (jogador.getBuffAtaque() > 0) {
                System.out.println("💪 Força aumentada: " + jogador.getBuffAtaque() + " turnos restantes (+5 de dano)");
            }

            System.out.println("💀 Status do inimigo: " + estimativaHp);
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");

            int opc;

            if(caido){
                opc = 6;
            } else {
                System.out.println("\n1 - Atacar");
                System.out.println("2 - Defender");
                System.out.println("3 - Usar Item");
                System.out.println("4 - Observar");
                System.out.println("5 - Ver Status");

                opc = e.nextInt();
                while (opc < 1 || opc > 5) {
                    System.out.print("Opção inválida. Escolha de 1 a 5: ");
                    opc = e.nextInt();
                }
            }

            int dadoAtk;
            boolean usouTurno = true;

            switch (opc) {
                case 1: // Atacar
                    System.out.println("\n⚔️ Você ataca!");
                    dadoAtk = dado.nextInt(1, 21);

                    if (dadoAtk > dtDefInimigo) {
                        int dano = dado.nextInt(1, 11) + jogador.getDanoBase();

                        if (dadoAtk == 20){
                            dano += 10;
                            System.out.println("💥 ACERTO CRÍTICO! (+10 de dano)");
                        }
                        if (jogador.getBuffAtaque() > 0) {
                            dano += 5;
                            System.out.println("✦ Buff de ataque ativo! (+5 de dano)");
                        }

                        hpInimigo -= dano;
                        System.out.println("💥 Acertou! Dano total: " + dano);
                    } else if(dadoAtk == 1) {
                        System.out.println("🤦 Falha crítica!");
                        System.out.println("Você tropeça e perde o equilíbrio, necessitando de um turno para se levantar!");
                        caido = true;
                    } else {
                        System.out.println("✗⛔ Errou o ataque!");
                    }
                    break;

                case 2:
                    System.out.println("🛡️ Você assume posição defensiva!");
                    dtDefJogador += 5;
                    System.out.println("Defesa aumentada temporariamente para " + dtDefJogador + "!");
                    break;

                case 3:
                    usarItemBatalha(jogador, e);
                    usouTurno = false;
                    break;

                case 4:
                    System.out.println("Como deseja observar?");
                    System.out.println("1 - Analisar superficialmente (Não gasta turno)");
                    System.out.println("2 - Analisar profundamente (Gasta turno)");
                    System.out.println("3 - Voltar");

                    int opcObservar;
                    do {
                        System.out.print("Escolha uma opção: ");
                        opcObservar = e.nextInt();

                        if (opcObservar < 1 || opcObservar > 3) {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (opcObservar < 1 || opcObservar > 3);

                    if (opcObservar == 1) {
                        System.out.println("\n🔍 Análise superficial realizada.");
                        if (hpInimigo == hpInimigoMax) {
                            estimativaHp = "Normal";
                        } else if (hpInimigo >= (hpInimigoMax * 0.7)) {
                            estimativaHp = "Levemente ferido";
                        } else if (hpInimigo >= (hpInimigoMax * 0.4)) {
                            estimativaHp = "Moderadamente ferido";
                        } else {
                            estimativaHp = "Gravemente ferido";
                        }
                        usouTurno = false;
                    } else if (opcObservar == 2) {
                        System.out.println("\n🔎 Análise profunda realizada.");
                        estimativaHp = hpInimigo + " HP";
                    } else {
                        usouTurno = false;
                    }
                    break;

                case 5:
                    jogador.mostrarStatus();
                    usouTurno = false;
                    break;

                case 6:
                    System.out.println("Você se levanta do chão, pronto para continuar a luta!");
                    caido = false;
                    usouTurno = true;
                    break;
            }

            if (usouTurno) {
                jogador.reduzirBuffs();
            }

            if (hpInimigo > 0 && usouTurno) {
                System.out.println("\n--- Turno do " + nomeInimigo + " ---");
                dadoAtk = dado.nextInt(1, 21);

                if (dadoAtk > dtDefJogador) {
                    int dano = dado.nextInt(1, 11) + 8;

                    if (jogador.getEscudoAtivo() > 0) {
                        int danoOriginal = dano;
                        dano = dano / 2;
                        System.out.println("🛡️ Escudo absorveu " + (danoOriginal - dano) + " de dano!");
                    }

                    jogador.setHp(jogador.getHp() - dano);
                    System.out.println("💥 " + nomeInimigo + " causou " + dano + " de dano!");
                } else {
                    System.out.println("✓ " + nomeInimigo + " errou o ataque!");
                }

                dtDefJogador = jogador.getDefesa();
            }
        }

        if (jogador.getHp() <= 0) {
            System.out.println("\n☠️🪦 Você morreu...");
            System.out.println("\n💀 Seu save será deletado...");

            try {
                firebase.deletarPlayer(jogador.getId());
                System.out.println("✓ Save deletado com sucesso.");
            } catch (Exception ex) {
                System.out.println("⚠️ Erro ao deletar save: " + ex.getMessage());
            }
            System.exit(0);
        } else {
            System.out.println("\n⚔️☠️ Você derrotou " + nomeInimigo + "!");
        }

        return jogador.getHp();
    }

    public static void usarItemBatalha(Player jogador, Scanner e) {
        // Filtra apenas itens consumíveis/usáveis
        List<Item> itensUsaveis = new ArrayList<>();
        for (Item item : jogador.getInventario()) {
            if (item.verificarSeUsavel() && item.getQuantidade() > 0) {
                itensUsaveis.add(item);
            }
        }

        if (itensUsaveis.isEmpty()) {
            System.out.println("\nVocê não tem itens para usar!");
            return;
        }

        System.out.println("\n=== USAR ITEM ===");
        int escolha;
        do {
            for (int i = 0; i < itensUsaveis.size(); i++) {
                Item item = itensUsaveis.get(i);
                System.out.println((i + 1) + " - " + item.getNome() + " x" + item.getQuantidade() + " - " + item.getDescricao());
            }
            System.out.println("0 - Cancelar");

            System.out.print("\nEscolha um item: ");
            escolha = e.nextInt();

            if (escolha == 0) {
                System.out.println("Voltando...");
                return;
            }

            if (escolha < 1 || escolha > itensUsaveis.size()) {
                System.out.println("Opção inválida! Tente novamente.");
            }
        } while (escolha < 1 || escolha > itensUsaveis.size());

        Item itemEscolhido = itensUsaveis.get(escolha - 1);
        aplicarEfeitoItem(jogador, itemEscolhido);

        jogador.removerItem(itemEscolhido.getNome(), 1);
    }

    public static int[] aplicarEfeitoItemBatalha(Player jogador, Item item, int dtDefInimigo, int dtDefInimigoOriginal, int turnosDefesaReduzida) {
        String nome = item.getNome().toLowerCase();

        if (nome.contains("granada atordoante")) {
            dtDefInimigo = Math.max(5, dtDefInimigo - 5); // Reduz defesa do inimigo em 5 (mínimo 5)
            turnosDefesaReduzida = 3; // Dura 3 turnos
            System.out.println("\n💥 Granada explode! O inimigo está atordoado!");
            System.out.println("🎯 Defesa do inimigo reduzida para " + dtDefInimigo + " pelos próximos 3 turnos!");
            return new int[]{dtDefInimigo, turnosDefesaReduzida};
        } else {
            // Itens normais de cura/buff
            aplicarEfeitoItem(jogador, item);
            return new int[]{dtDefInimigo, turnosDefesaReduzida};
        }
    }

    public static void aplicarEfeitoItem(Player jogador, Item item) {
        String nome = item.getNome().toLowerCase();

        if (nome.contains("bandagem")) {
            int cura = 20;
            int hpAntes = jogador.getHp();
            int hpDepois = Math.min(jogador.getHp() + cura, jogador.getMaxHp());
            jogador.setHp(hpDepois);
            int curaReal = hpDepois - hpAntes;
            System.out.println("\n❤️‍🩹 Você usou " + item.getNome() + " e recuperou " + curaReal + " HP!");

        } else if (nome.contains("kit médico") && !nome.contains("avançado")) {
            int cura = 50;
            int hpAntes = jogador.getHp();
            int hpDepois = Math.min(jogador.getHp() + cura, jogador.getMaxHp());
            jogador.setHp(hpDepois);
            int curaReal = hpDepois - hpAntes;
            System.out.println("\n❤️‍🩹 Você usou " + item.getNome() + " e recuperou " + curaReal + " HP!");

        } else if (nome.contains("kit médico avançado")) {
            int cura = 70;
            int hpAntes = jogador.getHp();
            int hpDepois = Math.min(jogador.getHp() + cura, jogador.getMaxHp());
            jogador.setHp(hpDepois);
            int curaReal = hpDepois - hpAntes;
            System.out.println("\n❤️‍🩹 Você usou " + item.getNome() + " e recuperou " + curaReal + " HP!");

        } else if (nome.contains("poção média")) {
            int cura = 60;
            int hpAntes = jogador.getHp();
            int hpDepois = Math.min(jogador.getHp() + cura, jogador.getMaxHp());
            jogador.setHp(hpDepois);
            int curaReal = hpDepois - hpAntes;
            System.out.println("\n❤️‍🩹 Você usou " + item.getNome() + " e recuperou " + curaReal + " HP!");

        } else if (nome.contains("poção grande")) {
            int cura = 80;
            int hpAntes = jogador.getHp();
            int hpDepois = Math.min(jogador.getHp() + cura, jogador.getMaxHp());
            jogador.setHp(hpDepois);
            int curaReal = hpDepois - hpAntes;
            System.out.println("\n❤️‍🩹 Você usou " + item.getNome() + " e recuperou " + curaReal + " HP!");

        } else if (nome.contains("poção") || nome.contains("pocao")) {
            // Poção genérica (40 HP)
            int cura = 40;
            int hpAntes = jogador.getHp();
            int hpDepois = Math.min(jogador.getHp() + cura, jogador.getMaxHp());
            jogador.setHp(hpDepois);
            int curaReal = hpDepois - hpAntes;
            System.out.println("\n❤️‍🩹 Você usou " + item.getNome() + " e recuperou " + curaReal + " HP!");

        } else if (nome.contains("estimulante de combate") || nome.contains("elixir") || nome.contains("força")) {
            // Buff de ataque
            if (jogador.getBuffAtaque() > 0) {
                jogador.setBuffAtaque(jogador.getBuffAtaque() + 3);
                System.out.println("\n💪 Força renovada! +" + jogador.getBuffAtaque() + " turnos de poder!");
            } else {
                jogador.setBuffAtaque(3);
                System.out.println("\n💪 Força aumentada! +5 de dano pelos próximos 3 turnos!");
            }

        } else if (nome.contains("escudo")) {
            if (jogador.getEscudoAtivo() > 0) {
                jogador.setEscudoAtivo(jogador.getEscudoAtivo() + 3);
                System.out.println("\n🛡️ Escudo renovado! +" + jogador.getEscudoAtivo() + " turnos de proteção!");
            } else {
                jogador.setEscudoAtivo(3);
                System.out.println("\n🛡️ Escudo ativado! Você receberá metade do dano pelos próximos 3 turnos!");
            }

        } else if (nome.contains("granada atordoante")) {
            System.out.println("\n⚠️ Este item só pode ser usado durante batalhas!");

        } else {
            // Item desconhecido - cura genérica pequena
            int cura = 30;
            int hpAntes = jogador.getHp();
            int hpDepois = Math.min(jogador.getHp() + cura, jogador.getMaxHp());
            jogador.setHp(hpDepois);
            int curaReal = hpDepois - hpAntes;
            System.out.println("\n✦ Você usou " + item.getNome() + " e recuperou " + curaReal + " HP!");
        }
    }

    // ===== NOVO PLAYER =====

    public static Player criarNovoPlayer(FirebaseManager firebase, Scanner e) throws Exception {
        String nome;
        do {
            System.out.print("Digite o nome do player: ");
            nome = e.nextLine().trim();

            if(nome.isEmpty()){
                System.out.println("O nome não pode ser vazio. Tente novamente.");
            }
        } while(nome.isEmpty());

        Player novo = new Player(nome);
        firebase.salvarPlayer(novo);

        System.out.println("Novo player criado! Token: " + novo.getId());
        System.out.println("Guarde esse token para continuar o jogo depois.\n");

        return novo;
    }
}