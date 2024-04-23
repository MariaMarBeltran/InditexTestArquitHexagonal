package mar.inditex.prueba.application;


import mar.inditex.prueba.domain.MensajeErrorException;
import mar.inditex.prueba.adapter.out.db.models.Prices;
import mar.inditex.prueba.adapter.out.db.repository.PricesRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static mar.inditex.prueba.domain.UtilsPrice.*;


@Service
public class    PricesServiceImpl implements PricesService {
    private final PricesRepositoryImpl pricesRepositoryImpl;

    public PricesServiceImpl(PricesRepositoryImpl pricesRepositoryImpl) {
        this.pricesRepositoryImpl = pricesRepositoryImpl;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prices> getPriceList() {
        return pricesRepositoryImpl.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Prices getPriceById(Long id) {
        return pricesRepositoryImpl.findById(id).orElseThrow(
                () -> new MensajeErrorException(id)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Prices getPrice(Date datePrice, Integer productId, Integer brandId) {
        List<Prices> lista = pricesRepositoryImpl.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice);
        return getPriority(lista);
    }


}
