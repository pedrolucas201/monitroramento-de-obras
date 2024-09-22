package interfaces;

import entities.Obra;
import java.util.List;

public interface IObrasRepositorio {
    void adicionarObra(Obra obra);
    List<Obra> listarObras();
    Obra buscarObra(String nomeObra);
    void removerObra(String nomeObra);
}
