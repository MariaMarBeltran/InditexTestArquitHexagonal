package mar.inditex.prueba.services;

import mar.inditex.prueba.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {
   @Query(value = "SELECT * FROM prices p WHERE p.product_id = :productId AND p.brand_id = :brandId AND :datePrice BETWEEN p.start_date AND p.end_date ", nativeQuery = true)
   List<Prices> findByStarDateAndProductIdAndPriceList(Date datePrice, Integer productId, Integer brandId);

   List<Prices> findByPriceList(Long price_list);

}
