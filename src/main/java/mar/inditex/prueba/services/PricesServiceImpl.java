package mar.inditex.prueba.services;


import mar.inditex.prueba.exceptions.MensajeErrorException;
import mar.inditex.prueba.models.Prices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static mar.inditex.prueba.utils.UtilsPrice.*;


@Service
public class PricesServiceImpl implements PricesService {
    private static final Logger log = LoggerFactory.getLogger(PricesServiceImpl.class);
    private PricesRepository pricesRepository;

    public PricesServiceImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prices> getListaPrecios() {
        return pricesRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Prices getPrecioById(Long id) {
        return pricesRepository.findById(id).orElseThrow(
                () -> new MensajeErrorException(id)
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<Prices> getByPriceList(Long price_list) {
        return pricesRepository.findByPriceList(price_list);
    }


    public List<Prices> getProducto1(Date datePrice, Integer productId, Integer brandId) {
        return pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice, productId, brandId);
    }

    @Override
    @Transactional(readOnly = true)
    public Prices getProducto(Date datePrice, Integer productId, Integer brandId) {
        List<Prices> lista = pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice, productId, brandId);
        return getPriority(lista);

    }


}
