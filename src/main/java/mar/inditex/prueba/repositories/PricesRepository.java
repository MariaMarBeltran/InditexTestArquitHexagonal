package mar.inditex.prueba.repositories;

import mar.inditex.prueba.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long>  {

    List<Prices> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer productId, Integer brandId, Date datePriceToStrart, Date datePriceToEnd);

    List<Prices> findByPriceList(Long price_list);

}
