package services;

import entities.Obra;
import interfaces.IObrasRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ObrasIImplRepositorio implements IObrasRepositorio {
    private List<Obra> obras = new ArrayList<>();

    @Override
    public void adicionarObra(Obra obra) {
        if (obra == null) {
            throw new IllegalArgumentException("A obra não pode ser nula.");
        }
        obras.add(obra);
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
}
