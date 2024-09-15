package entities;

public class ObraComercial extends Obra {
    public ObraComercial(String nome, Engenheiro engenheiro) {
        super(nome, engenheiro);
    }

    @Override
    public String getTipo() {
        return "Comercial";
    }

    @Override
    public void setTipo(String tipo) {
        // Caso precise, você pode adicionar lógica para tratar o tipo.
    }
}
