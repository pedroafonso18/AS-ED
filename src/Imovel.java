public class Imovel {
    private String nome;
    private double preco;
    private Jogador dono;
    private double aluguel;

    public double getAluguel() {
        return aluguel;
    }

    public void setAluguel(double aluguel) {
        this.aluguel = aluguel;
    }

    public Imovel(String nome, double preco, Casa local) {
        this.nome = nome;
        this.preco = preco;
        this.dono = null;
    }

    // Construtor conveniente sem Casa e já com aluguel
    public Imovel(String nome, double preco, double aluguel) {
        this.nome = nome;
        this.preco = preco;
        this.aluguel = aluguel;
        this.dono = null;
    }

    @Override
    public String toString() {
        return nome + " - Preço: R$ " + String.format("%.2f", preco) + " - Aluguel: R$ " + String.format("%.2f", aluguel)
                + (dono != null ? " (Dono: " + dono.getNome() + ")" : "");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }
}
