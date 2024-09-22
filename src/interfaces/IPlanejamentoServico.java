package interfaces;

import entities.Obra;
import java.util.List;

public interface IPlanejamentoServico {
    void adicionarObra(Obra obra);
    List<Obra> listarObras();
    Obra buscarObra(String nomeObra);
    void removerObra(String nomeObra);

    // Novos métodos
    Obra visualizarUltimaObra();
    Obra desempilharObra();
}

