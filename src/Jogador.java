public class Jogador {
    private String nome;
    private double saldo;
    private Casa posicao;
    private double salario;
    private java.util.List<Imovel> propriedades;
    private boolean naPrisao;
    private int tentativasPrisao;

    Jogador(String nome, double saldo, Casa posicao) {
        this.nome = nome;
        this.saldo = saldo;
        this.posicao = posicao;
        this.salario = 0;
        this.propriedades = new java.util.ArrayList<>();
        this.naPrisao = false;
        this.tentativasPrisao = 0;
    }

    Jogador() {
        nome = "";
        saldo = 0;
        posicao = null;
        salario = 0;
        propriedades = new java.util.ArrayList<>();
        naPrisao = false;
        tentativasPrisao = 0;
    }

    public String getNome() {
        return nome;
    }
    public double getSaldo() {
        return saldo;
    }
    public Casa getPosicao() {
        return posicao;
    }

    public void setPosicao(Casa posicao) {
        this.posicao = posicao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public java.util.List<Imovel> getPropriedades() {
        return propriedades;
    }

    public void adicionarPropriedade(Imovel imovel) {
        if (imovel != null) {
            propriedades.add(imovel);
        }
    }

    public boolean isNaPrisao() {
        return naPrisao;
    }

    public void setNaPrisao(boolean naPrisao) {
        this.naPrisao = naPrisao;
    }

    public int getTentativasPrisao() {
        return tentativasPrisao;
    }

    public void setTentativasPrisao(int tentativasPrisao) {
        this.tentativasPrisao = tentativasPrisao;
    }

    public double getPatrimonioTotal() {
        double totalImoveis = 0;
        if (propriedades != null) {
            for (Imovel im : propriedades) {
                totalImoveis += (im != null ? im.getPreco() : 0);
            }
        }
        return saldo + totalImoveis;
    }

}
