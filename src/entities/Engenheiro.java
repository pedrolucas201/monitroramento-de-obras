package entities;

public class Engenheiro {
    private String nome;
    private String registro;

    public Engenheiro(String nome, String registro) {
        setNome(nome);
        setRegistro(registro);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        if (registro == null || registro.trim().isEmpty()) {
            throw new IllegalArgumentException("Registro não pode ser vazio.");
        }
        this.registro = registro;
    }
}
