package mar.inditex.prueba.services;

import mar.inditex.prueba.models.Prices;

import java.util.Date;
import java.util.List;

public interface PricesService {
    List<Prices> getListaPrecios();

    Prices getPrecioById(Long id);

    List<Prices> getByPriceList(Long price_list);

    Prices getProducto(Date datePrice, Integer productId, Integer brandId );

}
