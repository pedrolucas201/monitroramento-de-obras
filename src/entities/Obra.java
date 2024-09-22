package entities;

public abstract class Obra {
    private String nome;
    private Engenheiro engenheiro;
    private String tipo;

    public Obra(String nome, Engenheiro engenheiro, String tipo) {
        setNome(nome);
        setEngenheiro(engenheiro);
        setTipo(tipo);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da obra não pode ser vazio.");
        }
        this.nome = nome;
    }

    public Engenheiro getEngenheiro() {
        return engenheiro;
    }

    public void setEngenheiro(Engenheiro engenheiro) {
        if (engenheiro == null) {
            throw new IllegalArgumentException("Engenheiro não pode ser nulo.");
        }
        this.engenheiro = engenheiro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de obra não pode ser vazio.");
        }
        this.tipo = tipo;
    }
}
