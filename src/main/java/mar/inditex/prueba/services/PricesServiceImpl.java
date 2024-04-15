package mar.inditex.prueba.services;


import mar.inditex.prueba.models.Prices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public List<Prices> getPrecios() {
        return pricesRepository.findAll();
    }

    @Override
    @Transactional(readOnly =  true)
    public List<Prices> findAll() {
        return pricesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Prices findById(Long id) {
        return pricesRepository.findById(id).orElseThrow();
    }


    @Override
    @Transactional(readOnly =  true)
    public List<Prices> getByPriceList(Long price_list) {
        List<Prices> lista =  pricesRepository.findByPriceList(price_list);
        return lista  ;
    }

    @Override
    @Transactional(readOnly =  true)
    public List<Prices> getProducto(Date datePrice, Integer productId, Long priceList ){
        List<Prices> lista =  pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice,productId,priceList);
        return lista;
    }






}
