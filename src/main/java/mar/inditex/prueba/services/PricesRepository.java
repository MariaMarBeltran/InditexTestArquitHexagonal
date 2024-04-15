package mar.inditex.prueba.services;

import mar.inditex.prueba.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {
   @Query(value = "select * from prices p where p.start_date >= ?1  and p.product_id = ?2 and p.price_list = ?3 ", nativeQuery = true)
   List<Prices> findByStarDateAndProductIdAndPriceList(Date datePrice, Integer productId, Long priceList);


   List<Prices> findByPriceList(Long price_list);

}
