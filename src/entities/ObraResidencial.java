package entities;

public class ObraResidencial extends Obra {
    public ObraResidencial(String nome, Engenheiro engenheiro) {
        super(nome, engenheiro);
    }

    @Override
    public String getTipo() {
        return "Residencial";
    }

    @Override
    public void setTipo(String tipo) {
        // Caso precise, você pode adicionar lógica para tratar o tipo.
    }
}
