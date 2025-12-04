import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    // Limites do jogo
    private static final int LIMITE_JOGADORES = 6;
    private static final int LIMITE_IMOVEIS = 40;

    // Estado de configuração
    private static List<Jogador> jogadores = new ArrayList<>();
    private static List<Imovel> imoveis = new ArrayList<>();
    private static double saldoInicial = 25000.0;
    private static double salarioPorVolta = 2000.0;
    private static int maxRodadas = 20;

    public static void main(String[] args) {
        menuPrincipalConfiguracao();
    }

    private static void menuPrincipalConfiguracao() {
        while (true) {
            System.out.println("=========================================");
            System.out.println("=== SIMULADOR DE JOGO DE TABULEIRO    ===");
            System.out.println("=========================================");
            System.out.println("Seja bem-vindo! Antes de começar, vamos configurar a partida.");
            System.out.println("--- MENU DE CONFIGURAÇÃO ---");
            System.out.println("1. Gerenciar Jogadores");
            System.out.println("2. Gerenciar Imóveis");
            System.out.println("3. Definir Configurações da Partida");
            System.out.println("4. Iniciar Jogo");
            System.out.println("0. Sair do Programa");
            System.out.print(">> Escolha uma opção: ");
            int op = lerInteiro();
            switch (op) {
                case 1: submenuJogadores(); break;
                case 2: submenuImoveis(); break;
                case 3: submenuConfiguracoes(); break;
                case 4:
                    if (validarInicioJogo()) {
                        iniciarJogo();
                    } else {
                        System.out.println("ERRO: O jogo não pode ser iniciado.");
                        if (jogadores.size() < 2) {
                            System.out.println("Motivo: Mínimo de 2 jogadores não alcançado (apenas " + jogadores.size() + ")");
                        }
                        if (imoveis.size() < 10) {
                            System.out.println("Motivo: Mínimo de 10 imóveis não alcançado (apenas " + imoveis.size() + ")");
                        }
                        pausar();
                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void submenuJogadores() {
        while (true) {
            System.out.println("--- Menu de Jogadores ---");
            System.out.println("(Atualmente: " + jogadores.size() + "/" + LIMITE_JOGADORES + " jogadores cadastrados)\n");
            System.out.println("1. Cadastrar Novo Jogador");
            System.out.println("2. Listar Jogadores Cadastrados");
            System.out.println("3. Remover Jogador");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");
            int op = lerInteiro();
            switch (op) {
                case 1:
                    if (jogadores.size() >= LIMITE_JOGADORES) {
                        System.out.println("Limite de jogadores atingido.");
                        break;
                    }
                    System.out.print(">> Digite o nome do novo jogador: ");
                    String nome = sc.nextLine().trim();
                    if (nome.isEmpty()) {
                        System.out.println("Nome inválido.");
                        break;
                    }
                    Jogador j = new Jogador();
                    j.setNome(nome);
                    j.setSaldo(0);
                    j.setSalario(0);
                    jogadores.add(j);
                    System.out.println("Jogador '" + nome + "' cadastrado com sucesso!");
                    break;
                case 2:
                    listarJogadores();
                    break;
                case 3:
                    listarJogadores();
                    if (jogadores.isEmpty()) break;
                    System.out.print(">> Informe o índice do jogador para remover (1.." + jogadores.size() + "): ");
                    int idx = lerInteiro();
                    if (idx < 1 || idx > jogadores.size()) {
                        System.out.println("Índice inválido.");
                    } else {
                        System.out.println("Jogador '" + jogadores.get(idx - 1).getNome() + "' removido.");
                        jogadores.remove(idx - 1);
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarJogadores() {
        System.out.println("--- Jogadores Cadastrados ---");
        if (jogadores.isEmpty()) {
            System.out.println("(nenhum)");
            return;
        }
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println((i + 1) + ". " + jogadores.get(i).getNome());
        }
    }

    private static void submenuImoveis() {
        while (true) {
            System.out.println("--- Menu de Imóveis ---");
            System.out.println("(Atualmente: " + imoveis.size() + "/" + LIMITE_IMOVEIS + " imóveis cadastrados)\n");
            System.out.println("1. Cadastrar Novo Imóvel");
            System.out.println("2. Listar Imóveis Cadastrados");
            System.out.println("3. Remover Imóvel");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");
            int op = lerInteiro();
            switch (op) {
                case 1:
                    if (imoveis.size() >= LIMITE_IMOVEIS) {
                        System.out.println("Limite de imóveis atingido.");
                        break;
                    }
                    System.out.print("Nome do imóvel: ");
                    String nome = sc.nextLine().trim();
                    System.out.print("Preço de compra: ");
                    double preco = lerDouble();
                    System.out.print("Aluguel: ");
                    double aluguel = lerDouble();
                    Imovel im = new Imovel(nome, preco, aluguel);
                    imoveis.add(im);
                    System.out.println("Imóvel cadastrado com sucesso!");
                    break;
                case 2:
                    listarImoveis();
                    break;
                case 3:
                    listarImoveis();
                    if (imoveis.isEmpty()) break;
                    System.out.print(">> Informe o índice do imóvel para remover (1.." + imoveis.size() + "): ");
                    int idx = lerInteiro();
                    if (idx < 1 || idx > imoveis.size()) {
                        System.out.println("Índice inválido.");
                    } else {
                        System.out.println("Imóvel '" + imoveis.get(idx - 1).getNome() + "' removido.");
                        imoveis.remove(idx - 1);
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarImoveis() {
        System.out.println("--- Imóveis Cadastrados ---");
        if (imoveis.isEmpty()) {
            System.out.println("(nenhum)");
            return;
        }
        for (int i = 0; i < imoveis.size(); i++) {
            System.out.println((i + 1) + ". " + imoveis.get(i));
        }
    }

    private static void submenuConfiguracoes() {
        while (true) {
            System.out.println("--- Configurações da Partida ---\n");
            System.out.println("1. Definir Saldo Inicial (Atual: R$ " + String.format("%.2f", saldoInicial) + ")");
            System.out.println("2. Definir Salário por volta (Atual: R$ " + String.format("%.2f", salarioPorVolta) + ")");
            System.out.println("3. Definir Nº Máximo de Rodadas (Atual: " + maxRodadas + ")");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");
            int op = lerInteiro();
            switch (op) {
                case 1:
                    System.out.print(">> Digite o novo saldo inicial: ");
                    saldoInicial = lerDouble();
                    System.out.println("Saldo inicial definido para R$ " + String.format("%.2f", saldoInicial) + ".");
                    break;
                case 2:
                    System.out.print(">> Digite o novo salário por volta: ");
                    salarioPorVolta = lerDouble();
                    System.out.println("Salário por volta definido para R$ " + String.format("%.2f", salarioPorVolta) + ".");
                    break;
                case 3:
                    System.out.print(">> Digite o número máximo de rodadas: ");
                    maxRodadas = lerInteiro();
                    System.out.println("Número máximo de rodadas definido para " + maxRodadas + ".");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static boolean validarInicioJogo() {
        return jogadores.size() >= 2 && imoveis.size() >= 10;
    }

    private static void iniciarJogo() {
        // Construir tabuleiro como lista ligada circular: INICIO + sequência de casas
        Tabuleiro tabuleiro = new Tabuleiro();
        // Casa de INICIO
        tabuleiro.adicionaUltimo(TipoCasa.INICIO);

        // Distribuição simples: adicionar imóveis; a cada 5 imóveis, uma casa de IMPOSTO; a cada 3, uma RESTITUICAO
        int contadorImoveis = 0;
        int contadorGeral = 0;
        for (Imovel im : imoveis) {
            tabuleiro.adicionaUltimo(TipoCasa.IMOVEL, im);
            contadorImoveis++;
            contadorGeral++;
            if (contadorImoveis % 5 == 0) {
                tabuleiro.adicionaUltimo(TipoCasa.IMPOSTO);
                contadorGeral++;
            } else if (contadorImoveis % 3 == 0) {
                tabuleiro.adicionaUltimo(TipoCasa.RESTITUICAO);
                contadorGeral++;
            }
            if (contadorGeral >= 39) break; // evita exceder um tabuleiro muito grande
        }

        // Inicializar jogadores
        for (Jogador j : jogadores) {
            j.setSaldo(saldoInicial);
            j.setSalario(salarioPorVolta);
            j.setPosicao(tabuleiro.getPrimeiraCasa()); // INICIO
        }

        System.out.println("Tudo pronto! Iniciando o jogo…");
        pausar();

        executarPartida(tabuleiro);
    }

    private static void executarPartida(Tabuleiro tabuleiro) {
        Random rng = new Random();
        int rodada = 1;
        // Lista mutável de jogadores ativos para desistência
        List<Jogador> ativos = new ArrayList<>(jogadores);

        while (rodada <= maxRodadas && ativos.size() > 1) {
            for (int i = 0; i < ativos.size(); i++) {
                Jogador atual = ativos.get(i);
                System.out.println("=========================================");
                System.out.println("=== RODADA " + rodada + " / " + maxRodadas + " - VEZ DE: " + atual.getNome() + "         ===");
                System.out.println("=========================================");
                System.out.println("Posição Atual: " + descreverCasa(atual.getPosicao()));
                System.out.println("Saldo: R$ " + String.format("%.2f", atual.getSaldo()));

                System.out.println("--- O que você deseja fazer? ---");
                System.out.println("1. Lançar Dados e Mover");
                System.out.println("2. Ver Meu Status Completo (Saldo e Propriedades)");
                System.out.println("0. Desistir do Jogo");
                System.out.print(">> Escolha uma opção: ");
                int op = lerInteiro();
                if (op == 0) {
                    System.out.println(atual.getNome() + " desistiu do jogo.");
                    ativos.remove(i);
                    i--; // ajustar índice após remoção
                    if (ativos.size() <= 1) break;
                    continue;
                } else if (op == 2) {
                    mostrarStatusCompleto(atual);
                    pausar();
                    i--; // repetir o mesmo jogador após visualização
                    continue;
                }

                // Jogar dados
                int d1 = rng.nextInt(6) + 1;
                int d2 = rng.nextInt(6) + 1;
                int total = d1 + d2;
                System.out.println("Você tirou " + d1 + " e " + d2 + ". Total: " + total + ".");
                System.out.println("Avançando " + total + " casas...");

                // Movimentação passo a passo para detectar passagem pelo INICIO
                Casa pos = atual.getPosicao();
                for (int passos = 0; passos < total; passos++) {
                    pos = pos.getProximaCasa();
                    if (pos == tabuleiro.getPrimeiraCasa()) { // passou pelo início
                        atual.setSaldo(atual.getSaldo() + atual.getSalario());
                        System.out.println("Você passou pelo INÍCIO e recebeu R$ " + String.format("%.2f", atual.getSalario()) + ".");
                    }
                }
                atual.setPosicao(pos);

                // Aplicar ação da casa (imóvel/imposto/restituição)
                System.out.println();
                System.out.println("Você parou em: " + descreverCasa(pos));
                pos.acionarAcao(atual);

                System.out.println("Pressione Enter para finalizar o turno…");
                sc.nextLine();
            }
            rodada++;
        }

        encerrarPartida(ativos);
    }

    private static String descreverCasa(Casa c) {
        if (c == null) return "(desconhecida)";
        if (c.getTipoCasa() == TipoCasa.INICIO) return "Início";
        if (c.getTipoCasa() == TipoCasa.IMOVEL) {
            Imovel im = c.getImovel();
            if (im == null) return "Imóvel (não configurado)";
            return im.getNome();
        }
        if (c.getTipoCasa() == TipoCasa.IMPOSTO) return "Imposto";
        if (c.getTipoCasa() == TipoCasa.RESTITUICAO) return "Restituição";
        return c.getTipoCasa().name();
    }

    private static void mostrarStatusCompleto(Jogador j) {
        System.out.println("--- STATUS DE " + j.getNome() + " ---");
        System.out.println("Saldo: R$ " + String.format("%.2f", j.getSaldo()));
        System.out.println("Salário por volta: R$ " + String.format("%.2f", j.getSalario()));
        System.out.println("Propriedades:");
        if (j.getPropriedades().isEmpty()) {
            System.out.println("(nenhuma)");
        } else {
            for (Imovel im : j.getPropriedades()) {
                System.out.println("- " + im);
            }
        }
    }

    private static void encerrarPartida(List<Jogador> ativos) {
        System.out.println("=========================================");
        System.out.println("======          FIM DE JOGO!         ======");
        System.out.println("=========================================");

        if (ativos.size() <= 1) {
            System.out.println("Motivo do término: Restou apenas um jogador.");
        } else {
            System.out.println("Motivo do término: Todas as " + maxRodadas + " rodadas foram concluídas!");
        }

        // Determinar vencedor por maior patrimônio
        List<Jogador> ranking = new ArrayList<>(ativos);
        ranking.sort((a, b) -> Double.compare(b.getPatrimonioTotal(), a.getPatrimonioTotal()));
        System.out.println("\n--- RANKING FINAL ---");
        for (int i = 0; i < ranking.size(); i++) {
            Jogador j = ranking.get(i);
            String prefixo = (i == 0 ? "1. VENCEDOR(A): " : (i + 1) + ". ");
            System.out.println(prefixo + j.getNome() + "  - Patrimônio: R$ " + String.format("%.2f", j.getPatrimonioTotal()));
        }

        System.out.println("\n--- O que deseja fazer? ---");
        System.out.println("1. Jogar Novamente (com as mesmas configurações)");
        System.out.println("2. Voltar ao Menu Principal (para novas configurações)");
        System.out.println("0. Encerrar Programa");
        System.out.print(">> Escolha uma opção: ");
        int op = lerInteiro();
        if (op == 1) {
            iniciarJogo();
        } else if (op == 2) {
            menuPrincipalConfiguracao();
        } else {
            System.out.println("Encerrando...");
        }
    }

    private static int lerInteiro() {
        while (true) {
            String s = sc.nextLine();
            try {
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            String s = sc.nextLine();
            try {
                return Double.parseDouble(s.trim());
            } catch (Exception e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }

    private static void pausar() {
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }
}