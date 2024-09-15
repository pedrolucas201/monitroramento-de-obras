package interfaces;

import entities.Obra;
import java.util.List;

public interface RepositorioObras {
    void adicionarObra(Obra obra); // Método para adicionar uma obra
    List<Obra> listarObras();      // Método que retorna uma lista de obras
}
