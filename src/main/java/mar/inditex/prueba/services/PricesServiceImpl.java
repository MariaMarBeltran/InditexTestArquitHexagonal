package mar.inditex.prueba.services;


import mar.inditex.prueba.exceptions.MensajeErrorException;
import mar.inditex.prueba.models.Prices;

import mar.inditex.prueba.repositories.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static mar.inditex.prueba.utils.UtilsPrice.*;


@Service
public class PricesServiceImpl implements PricesService {
    private final PricesRepository pricesRepository;

    public PricesServiceImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prices> getPriceList() {
        return pricesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Prices getPriceById(Long id) {
        return pricesRepository.findById(id).orElseThrow(
                () -> new MensajeErrorException(id)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Prices getPrice(Date datePrice, Integer productId, Integer brandId) {
        List<Prices> lista = pricesRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice);
        return getPriority(lista);
    }


}
