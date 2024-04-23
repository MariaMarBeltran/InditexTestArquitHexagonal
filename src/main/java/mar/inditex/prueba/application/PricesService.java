package mar.inditex.prueba.application;

import mar.inditex.prueba.adapter.out.db.models.Prices;

import java.util.Date;
import java.util.List;

public interface PricesService {

    List<Prices> getPriceList();

    Prices getPriceById(Long id);

    Prices getPrice(Date datePrice, Integer productId, Integer brandId );

}
