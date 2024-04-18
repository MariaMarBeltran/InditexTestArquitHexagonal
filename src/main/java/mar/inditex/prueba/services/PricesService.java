package mar.inditex.prueba.services;

import mar.inditex.prueba.models.Prices;

import java.util.Date;
import java.util.List;

public interface PricesService {

    List<Prices> getPriceList();

    Prices getPriceById(Long id);

    Prices getPrice(Date datePrice, Integer productId, Integer brandId );

}
