package services;

import entities.Obra;
import interfaces.IObrasRepositorio;
import interfaces.IPlanejamentoServico;

import java.util.List;
import java.util.Stack;

public class PlanejamentoServicoService implements IPlanejamentoServico {
    private IObrasRepositorio repositorio;
    private Stack<Obra> pilhaObras = new Stack<>(); // Pilha para armazenar as obras

    public PlanejamentoServicoService(IObrasRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void adicionarObra(Obra obra) {
        repositorio.adicionarObra(obra);
        pilhaObras.push(obra); // Adiciona a obra à pilha
    }

    @Override
    public List<Obra> listarObras() {
        return repositorio.listarObras();
    }

    @Override
    public Obra buscarObra(String nomeObra) {
        return repositorio.buscarObra(nomeObra);
    }

    @Override
    public void removerObra(String nomeObra) {
        repositorio.removerObra(nomeObra);
    }

    @Override
    public Obra visualizarUltimaObra() {
        return pilhaObras.isEmpty() ? null : pilhaObras.peek(); // Retorna a última obra sem remover
    }

    @Override
    public Obra desempilharObra() {
        return pilhaObras.isEmpty() ? null : pilhaObras.pop(); // Remove e retorna a última obra
    }
}

