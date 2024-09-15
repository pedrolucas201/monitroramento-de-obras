package entities;

public class Engenheiro {
    private String nome;
    private String registro;

    public Engenheiro(String nome, String registro) {
        this.nome = nome;
        this.registro = registro;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;  // Permite atualizar o nome do engenheiro
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;  // Permite atualizar o registro do engenheiro
    }
}
