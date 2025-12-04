public class Tabuleiro {
    private Casa ultimaCasa;
    private int tamanho;

    Tabuleiro(){
        this.ultimaCasa = null;
        this.tamanho = 0;
    }

    Tabuleiro(Casa ultimaCasa, int tamanho){
        this.ultimaCasa = ultimaCasa;
        this.tamanho = tamanho;
    }

    public int getTamanho(){
        return tamanho;
    }

    public Casa getUltimaCasa(){
        return ultimaCasa;
    }

    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }

    public void setUltimaCasa(Casa ultimaCasa){
        this.ultimaCasa = ultimaCasa;
    }

    public boolean isEmpty() {
        return (tamanho == 0);
    }

    public Casa getPrimeiraCasa(){
        if (isEmpty()) return null;
        return ultimaCasa.getProximaCasa();
    }

    public void adicionaPrimeiro(Casa casa, TipoCasa tipoCasa) {
        Casa nova = new Casa(tipoCasa);

        if (isEmpty()) {
            nova.setProximaCasa(nova);
            ultimaCasa = nova;
            tamanho = 1;
            return;
        }

        nova.setProximaCasa(ultimaCasa.getProximaCasa());
        ultimaCasa.setProximaCasa(nova);
        tamanho++;
    }

    public void adicionaUltimo(TipoCasa tipoCasa) {
        Casa nova = new Casa(tipoCasa);

        if (isEmpty()) {
            nova.setProximaCasa(nova);
            ultimaCasa = nova;
            tamanho = 1;
            return;
        }
        nova.setProximaCasa(ultimaCasa.getProximaCasa());
        ultimaCasa.setProximaCasa(nova);
        ultimaCasa = nova;
        tamanho++;
    }

    public void adicionaUltimo(TipoCasa tipoCasa, Imovel imovel) {
        Casa nova = new Casa(tipoCasa);
        nova.setImovel(imovel);

        if (isEmpty()) {
            nova.setProximaCasa(nova);
            ultimaCasa = nova;
            tamanho = 1;
            return;
        }
        nova.setProximaCasa(ultimaCasa.getProximaCasa());
        ultimaCasa.setProximaCasa(nova);
        ultimaCasa = nova;
        tamanho++;
    }
}
