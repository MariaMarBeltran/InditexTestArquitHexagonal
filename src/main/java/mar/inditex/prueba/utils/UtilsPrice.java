package mar.inditex.prueba.utils;

import mar.inditex.prueba.models.Prices;

import java.util.Comparator;
import java.util.List;

public class UtilsPrice {

    public static Prices getPriority(List<Prices> lista){
        return lista.stream().max(Comparator.comparingInt(Prices::getPriority)).orElse(null);
    }
}
