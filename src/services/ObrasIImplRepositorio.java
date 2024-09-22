package services;

import entities.Obra;
import interfaces.IObrasRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ObrasIImplRepositorio implements IObrasRepositorio {
    private List<Obra> obras = new ArrayList<>();
    private Stack<Obra> pilhaObras = new Stack<>(); // Pilha para armazenar as obras

    @Override
    public void adicionarObra(Obra obra) {
        if (obra == null) {
            throw new IllegalArgumentException("A obra não pode ser nula.");
        }
        obras.add(obra);
        pilhaObras.push(obra); // Adiciona a obra à pilha
    }

    @Override
    public List<Obra> listarObras() {
        return new ArrayList<>(obras);
    }

    @Override
    public Obra buscarObra(String nomeObra) {
        if (nomeObra == null || nomeObra.isEmpty()) {
            throw new IllegalArgumentException("O nome da obra não pode ser nulo ou vazio.");
        }
        for (Obra obra : obras) {
            if (obra.getNome().equals(nomeObra)) {
                return obra;
            }
        }
        return null;
    }

    @Override
    public void removerObra(String nomeObra) {
        if (nomeObra == null || nomeObra.isEmpty()) {
            throw new IllegalArgumentException("O nome da obra não pode ser nulo ou vazio.");
        }
        obras.removeIf(obra -> obra.getNome().equals(nomeObra));
    }

    // Método para desempilhar a última obra adicionada
    public Obra desempilharObra() {
        if (!pilhaObras.isEmpty()) {
            return pilhaObras.pop();
        }
        return null; // Retorna null se a pilha estiver vazia
    }

    // Método para visualizar a última obra na pilha sem removê-la
    public Obra visualizarUltimaObra() {
        if (!pilhaObras.isEmpty()) {
            return pilhaObras.peek();
        }
        return null; // Retorna null se a pilha estiver vazia
    }
}
