package mar.inditex.prueba.services;

import mar.inditex.prueba.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<Prices, Integer> {

}
