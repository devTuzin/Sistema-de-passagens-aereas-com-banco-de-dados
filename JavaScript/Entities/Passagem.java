package Sistema_de_passagensAereas.Entities;

public abstract class Passagem {
    private String nomePassageiro;
    private String numeroVoo;
    private double precoOriginal;
    private TipoClasse tipoClasse;

    public Passagem(String nomePassageiro, String numeroVoo, double precoOriginal, TipoClasse tipoClasse) {
        this.nomePassageiro = nomePassageiro;
        this.numeroVoo = numeroVoo;
        this.precoOriginal = precoOriginal;
        this.tipoClasse = tipoClasse;
    }

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public void setNumeroVoo(String numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    public double getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(double precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    public TipoClasse getTipoClasse() {
        return tipoClasse;
    }

    public abstract double calcularPrecoFinal();

    @Override
    public String toString() {
        return "Passageiro: " + nomePassageiro + "\nNúmero do voo: " + numeroVoo + "\nClasse: " + tipoClasse;
    }
}