package mar.inditex.prueba.services;


import mar.inditex.prueba.models.Prices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PricesServiceImpl implements PricesService {
    private static final Logger log =  LoggerFactory.getLogger(PricesServiceImpl.class);
    private PricesRepository pricesRepository;

    public PricesServiceImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    @Transactional(readOnly =  true)
    public List<Prices> findAll() {
        log.info("MAR-->PricesServiceImpl ok");
        return pricesRepository.findAll();
    }
}
