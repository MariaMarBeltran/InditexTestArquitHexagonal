package mar.inditex.prueba.services;

import mar.inditex.prueba.models.Prices;

import java.util.Date;
import java.util.List;

public interface PricesService {
    List<Prices> findAll();
    List<Prices> getPrecios();
    Prices findById(Long id);

    List<Prices> getByPriceList(Long price_list);
    List<Prices> getProducto(Date datePrice, Integer productId, Long priceList );



}
