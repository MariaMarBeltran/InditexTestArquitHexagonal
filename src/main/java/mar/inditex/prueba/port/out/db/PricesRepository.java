package mar.inditex.prueba.port.out.db;

import mar.inditex.prueba.adapter.out.db.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PricesRepository {
    List<Prices> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer productId, Integer brandId, Date datePriceToStrart, Date datePriceToEnd);

    List<Prices> findByPriceList(Long price_list);
}

