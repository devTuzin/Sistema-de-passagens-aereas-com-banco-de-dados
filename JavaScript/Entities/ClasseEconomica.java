package Sistema_de_passagensAereas.Entities;

public final class ClasseEconomica extends Passagem {
    private boolean despacharMala;
    private double taxaBagagem = 120.0;

    public ClasseEconomica(String nomePassageiro, String numeroVoo, double precoOriginal, boolean despacharMala) {
        super(nomePassageiro, numeroVoo, precoOriginal, TipoClasse.ECONOMICA);
        this.despacharMala = despacharMala;
    }

    public boolean isDespacharMala() {
        return despacharMala;
    }

    public void setDespacharMala(boolean despacharMala) {
        this.despacharMala = despacharMala;
    }

    @Override
    public double calcularPrecoFinal() {
        if (despacharMala) {
            return getPrecoOriginal() + taxaBagagem;
        }
        return getPrecoOriginal();
    }

    @Override
    public String toString() {
        return "\n=== PASSAGEM ECONÔMICA ===\n"
                + super.toString()
                + "\nMala Despachada: " + (despacharMala ? "Sim" : "Não")
                + String.format("\nPreço Final: R$ %.2f", calcularPrecoFinal());
    }

}