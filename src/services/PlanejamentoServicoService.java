package services;

import entities.Obra;
import interfaces.IObrasRepositorio;
import interfaces.IPlanejamentoServico;

import java.util.List;

public class PlanejamentoServicoService implements IPlanejamentoServico {
    private IObrasRepositorio repositorio;

    public PlanejamentoServicoService(IObrasRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void adicionarObra(Obra obra) {
        if (obra == null) {
            throw new IllegalArgumentException("A obra não pode ser nula.");
        }
        repositorio.adicionarObra(obra);
    }

    @Override
    public List<Obra> listarObras() {
        return repositorio.listarObras();
    }

    @Override
    public Obra buscarObra(String nomeObra) {
        if (nomeObra == null || nomeObra.isEmpty()) {
            throw new IllegalArgumentException("O nome da obra não pode ser nulo ou vazio.");
        }
        return repositorio.buscarObra(nomeObra);
    }

    @Override
    public void removerObra(String nomeObra) {
        if (nomeObra == null || nomeObra.isEmpty()) {
            throw new IllegalArgumentException("O nome da obra não pode ser nulo ou vazio.");
        }
        repositorio.removerObra(nomeObra);
    }
}
