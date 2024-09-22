// ObraResidencial.java
package entities;

public class ObraResidencial extends Obra {
    private int numeroDeQuartos;
    private boolean possuiJardim;

    public ObraResidencial(String nome, Engenheiro engenheiro, int numeroDeQuartos, boolean possuiJardim) {
        super(nome, engenheiro, "Residencial");
        setNumeroDeQuartos(numeroDeQuartos);
    }

    // Getters e Setters
    public int getNumeroDeQuartos() {
        return numeroDeQuartos;
    }

    public void setNumeroDeQuartos(int numeroDeQuartos) {
        if (numeroDeQuartos < 0) {
            throw new IllegalArgumentException("Número de quartos não pode ser negativo.");
        }
        this.numeroDeQuartos = numeroDeQuartos;
    }

    public boolean isPossuiJardim() {
        return possuiJardim;
    }

    public void setPossuiJardim(boolean possuiJardim) {
        this.possuiJardim = possuiJardim;
    }

    @Override
    public String toString() {
        return "Obra: " + getNome() +
                " | Engenheiro: " + getEngenheiro().getNome() +
                " | Tipo: " + getTipo() +
                " | Número de Quartos: " + getNumeroDeQuartos() +
                " | Possui Jardim? " + isPossuiJardim();
    }
}
