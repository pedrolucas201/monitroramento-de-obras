package interfaces;

import entities.Obra;
import java.util.List;

public interface ServicoPlanejamento {
    void adicionarObra(Obra obra);  // Adiciona uma obra
    List<Obra> listarObras();       // Retorna uma lista de obras
}
