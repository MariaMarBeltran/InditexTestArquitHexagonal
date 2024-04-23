package mar.inditex.prueba.adapter.out.db;

import mar.inditex.prueba.adapter.out.db.models.Prices;
import mar.inditex.prueba.adapter.out.db.repository.PricesRepositoryImpl;
import mar.inditex.prueba.port.out.db.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.Date;
import java.util.List;

@Service
public class DbAdapter implements PricesRepository {

    @Autowired
    private PricesRepositoryImpl pricesRepositoryImpl;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Prices> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer productId, Integer brandId, Date datePriceToStrart, Date datePriceToEnd) {
        return List.of();
    }

    @Override
    public List<Prices> findByPriceList(Long price_list) {
        return List.of();
    }
}
