package services;

import entities.Obra;
import interfaces.RepositorioObras;

import java.util.ArrayList;
import java.util.List;

public class ObrasRepositorioImpl implements RepositorioObras {
    private List<Obra> obras = new ArrayList<>();  // Lista mutável de obras

    @Override
    public void adicionarObra(Obra obra) {
        obras.add(obra);  // Adiciona a obra à lista
    }

    @Override
    public List<Obra> listarObras() {
        return obras;  // Retorna a lista de obras
    }
}
