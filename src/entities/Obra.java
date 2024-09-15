package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Obra {
    private String nome;
    private Engenheiro engenheiro;
    private List<Tarefa> tarefas;

    public Obra(String nome, Engenheiro engenheiro) {
        this.nome = nome;
        this.engenheiro = engenheiro;
        this.tarefas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Engenheiro getEngenheiro() {
        return engenheiro;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public abstract String getTipo();

    public abstract void setTipo(String tipo);  // Adiciona um método abstrato para setar o tipo

    public void setNome(String nome) {
        this.nome = nome;
    }

}
