package services;

import entities.Obra;
import interfaces.RepositorioObras;
import interfaces.ServicoPlanejamento;

import java.util.List;

public class PlanejamentoService implements ServicoPlanejamento {
    private RepositorioObras repositorio;

    public PlanejamentoService(RepositorioObras repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void adicionarObra(Obra obra) {
        repositorio.adicionarObra(obra);  // Adiciona obra ao repositório
    }

    @Override
    public List<Obra> listarObras() {
        return repositorio.listarObras();  // Retorna a lista de obras do repositório
    }
}
