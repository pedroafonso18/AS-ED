import java.util.Scanner;

public class Casa {
    private TipoCasa tipoCasa;
    private Imovel imovel;
    private Casa proximaCasa;

    Casa(TipoCasa tipoCasa) {
        this.tipoCasa = tipoCasa;
        this.imovel = null;
        this.proximaCasa = null;
    }

    Casa(TipoCasa tipoCasa, Casa proximaCasa) {
        this.tipoCasa = tipoCasa;
        this.proximaCasa = proximaCasa;
        this.imovel = null;
    }

    Casa(TipoCasa tipoCasa, Imovel imovel, Casa proximaCasa) {
        this.tipoCasa = tipoCasa;
        this.imovel = imovel;
        this.proximaCasa = proximaCasa;
    }

    public Casa getProximaCasa() {
        return proximaCasa;
    }

    public void setProximaCasa(Casa casa) {
        this.proximaCasa = casa;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public TipoCasa getTipoCasa() {
        return tipoCasa;
    }

    public void setTipoCasa(TipoCasa tipoCasa) {
        this.tipoCasa = tipoCasa;
    }

    public void casaInicio(Jogador jogador) {
        // Regra: ao passar pelo início, recebe salário. Aqui tratamos quando cair na casa Início.
        double salario = jogador.getSalario();
        jogador.setSaldo(jogador.getSaldo() + salario);
        System.out.println("Parabéns, " + jogador.getNome() + "! Você recebeu seu salário de R$ " + String.format("%.2f", salario)
                + ". Saldo atual: R$ " + String.format("%.2f", jogador.getSaldo()));
    }

    public void casaImposto(Jogador jogador) {
        // Regra: 5% sobre o patrimônio total (saldo + valor dos imóveis)
        double imposto = jogador.getPatrimonioTotal() * 0.05;
        jogador.setSaldo(jogador.getSaldo() - imposto);
        System.out.println("Imposto! " + jogador.getNome() + " pagou R$ " + String.format("%.2f", imposto)
                + ". Saldo atual: R$ " + String.format("%.2f", jogador.getSaldo()));
    }

    public void casaRestituicao(Jogador jogador) {
        // Regra: 10% do salário
        double bonus = jogador.getSalario() * 0.10;
        jogador.setSaldo(jogador.getSaldo() + bonus);
        System.out.println("Restituição! " + jogador.getNome() + " recebeu R$ " + String.format("%.2f", bonus)
                + ". Saldo atual: R$ " + String.format("%.2f", jogador.getSaldo()));
    }

    public void casaImovel(Jogador jogador) {
        if (imovel == null) {
            System.out.println("[Aviso] Casa de IMÓVEL sem configuração de imóvel.");
            return;
        }
        if (imovel.getDono() != null) {
            if (imovel.getDono().equals(jogador)) {
                System.out.println("Jogador " + jogador.getNome() + " caiu na própria casa.");
            } else {
                jogador.setSaldo(jogador.getSaldo() - imovel.getAluguel());
                imovel.getDono().setSaldo(imovel.getDono().getSaldo() + imovel.getAluguel());
                System.out.println("Jogador " + jogador.getNome() + " pagou " + imovel.getAluguel() + " para " + imovel.getDono().getNome());
            }
        } else {
            System.out.println("Jogador " + jogador.getNome() + " caiu no imóvel '" + imovel.getNome() + "' (Preço: R$ " + imovel.getPreco() + ", Aluguel: R$ " + imovel.getAluguel() + ").\nDeseja comprar?\n 1 = SIM\n 2 = NÃO");
            Scanner sc = new Scanner(System.in);
            int opt = sc.nextInt();
            if (opt == 1) {
                if (jogador.getSaldo() >= imovel.getPreco()) {
                    jogador.setSaldo(jogador.getSaldo() - imovel.getPreco());
                    imovel.setDono(jogador);
                    jogador.adicionarPropriedade(imovel);
                    System.out.println("Parabéns! " + jogador.getNome() + " comprou o imóvel '" + imovel.getNome() + "'.");
                } else {
                    System.out.println("Saldo insuficiente para esta compra.");
                }
            }
        }
    }

    public void casaPrisao(Jogador jogador) {
        jogador.setNaPrisao(true);
        jogador.setTentativasPrisao(0);
        System.out.println(jogador.getNome() + " foi enviado para a prisão. Ficará até sair por dupla ou após 3 tentativas.");
    }

    public void casaSorteReves(Jogador jogador) {
        // Placeholder: Baralho/Pilha ainda não implementados.
        System.out.println("Sorte/Revés! (Baralho ainda não implementado nesta etapa).");
    }

    public void acionarAcao(Jogador jogador) {
        if (tipoCasa == null) return;
        switch (tipoCasa) {
            case INICIO:
                // Salário é tratado na detecção de passagem pelo início durante a movimentação.
                break;
            case IMOVEL:
                casaImovel(jogador);
                break;
            case IMPOSTO:
                casaImposto(jogador);
                break;
            case RESTITUICAO:
                casaRestituicao(jogador);
                break;
            case PRISAO:
                casaPrisao(jogador);
                break;
            case SORTE_REVES:
                casaSorteReves(jogador);
                break;
            default:
                break;
        }
    }
}
