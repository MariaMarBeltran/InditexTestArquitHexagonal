package mar.inditex.prueba.domain;

import mar.inditex.prueba.adapter.out.db.models.Prices;

import java.util.Comparator;
import java.util.List;

public class UtilsPrice {

    public static Prices getPriority(List<Prices> lista){
        return lista.stream().max(Comparator.comparingInt(Prices::getPriority)).orElse(null);
    }
}
