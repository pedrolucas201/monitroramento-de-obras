// ObraComercial.java
package entities;

public class ObraComercial extends Obra {
    private int numeroDeAndares;
    private boolean possuiEstacionamento;

    public ObraComercial(String nome, Engenheiro engenheiro, int numeroDeAndares, boolean possuiEstacionamento) {
        super(nome, engenheiro, "Comercial");
        setNumeroDeAndares(numeroDeAndares);
    }

    // Getters e Setters
    public int getNumeroDeAndares() {
        return numeroDeAndares;
    }

    public void setNumeroDeAndares(int numeroDeAndares) {
        if (numeroDeAndares <= 0) {
            throw new IllegalArgumentException("Número de andares deve ser maior que zero.");
        }
        this.numeroDeAndares = numeroDeAndares;
    }

    public boolean isPossuiEstacionamento() {
        return possuiEstacionamento;
    }

    public void setPossuiEstacionamento(boolean possuiEstacionamento) {
        this.possuiEstacionamento = possuiEstacionamento;
    }

    @Override
    public String toString() {
        return "Obra: " + getNome() +
                " | Engenheiro: " + getEngenheiro().getNome() +
                " | Tipo: " + getTipo() +
                " | Número de Andares: " + getNumeroDeAndares() +
                " | Possui Estacionamento? " + isPossuiEstacionamento();
    }
}
