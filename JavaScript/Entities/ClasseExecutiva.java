package Sistema_de_passagensAereas.Entities;

public final class ClasseExecutiva extends Passagem {
    private boolean servicoBordoVip = true;
    private boolean acessoSalaVip = true;

    public ClasseExecutiva(String nomePassageiro, String numeroVoo, double precoOriginal) {
        super(nomePassageiro, numeroVoo, precoOriginal, TipoClasse.EXECUTIVA);
    }

    @Override
    public double calcularPrecoFinal() {
        return getPrecoOriginal() * 1.5;
    }

    @Override
    public String toString() {
        return "\n=== PASSAGEM EXECUTIVA ===\n"
                + super.toString()
                + "\nServiço de Bordo VIP: " + (servicoBordoVip ? "Sim" : "Não")
                + "\nAcesso Sala VIP: " + (acessoSalaVip ? "Sim" : "Não")
                + String.format("\nPreço Final: R$ %.2f", calcularPrecoFinal());
    }
}