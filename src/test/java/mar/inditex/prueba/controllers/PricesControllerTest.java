package mar.inditex.prueba.controllers;

import mar.inditex.prueba.models.Prices;
import mar.inditex.prueba.repositories.PricesRepository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static mar.inditex.prueba.utils.UtilsPrice.getPriority;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PricesControllerTest {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH.mm.ss";
    private final DateFormat dateFormat  = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    private Logger log = LoggerFactory.getLogger(PricesControllerTest.class);

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    @Order(1)
    void testListaPrecios() {
        log.info("Inicio: testListaPrecios");
        List<Prices> lista = pricesRepository.findAll();
        assertFalse(lista.isEmpty());
        assertEquals(4, lista.size());
        log.info("Fin: testListaPrecios");
    }

    @Test
    @Order(2)
    void testPrecioById() {
        log.info("Inicio: testPrecioById");
        Optional<Prices> prices = pricesRepository.findById(3L);
        assertTrue(prices.isPresent());
        assertNotNull(prices);
        assertEquals(prices.get().getCurr(),"EUR");

        prices = pricesRepository.findById(14L);
        assertFalse(prices.isPresent());
        log.info("Fin: testPrecioById");
    }

    @Test
    @Order(3)
    void testPriceList() {
        log.info("Inicio: testPriceList");
        List<Prices> lista = pricesRepository.findByPriceList(2L);
        assertFalse(lista.isEmpty());
        assertEquals(lista.get(0).getCurr(),"EUR");
        log.info("Fin: testPriceList");
    }


    //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    @Order(4)
    void test1GetProducto() {
        log.info("Inicio: test1GetProducto");
        try {
            Date datePrice = dateFormat.parse("2020-06-14 10.00.00");
            Integer productId = 35455;
            Integer brandId = 1;
            List<Prices> lista = pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice, productId, brandId);
            assertFalse(lista.isEmpty());
            Prices price = getPriority(lista);
            assertNotNull(price);
            assertEquals(price.getCurr(),"EUR");
            assertTrue(price.getPrice().toString().contains("35.50"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        log.info("Fin: test1GetProducto");
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    @Order(5)
    void test2GetProducto() {
        log.info("Inicio: test2GetProducto");
        try {
            Date datePrice = dateFormat.parse("2020-06-14 16.00.00");
            Integer productId = 35455;
            Integer brandId = 1;
            List<Prices> lista = pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice, productId, brandId);
            assertFalse(lista.isEmpty());
            Prices price = getPriority(lista);
            assertNotNull(price);
            assertEquals(price.getCurr(),"EUR");
            assertTrue(price.getPrice().toString().contains("25.45"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        log.info("Fin: test2GetProducto");
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    @Order(6)
    void test3GetProducto() {
        log.info("Inicio: test3GetProducto");
        try {
            Date datePrice = dateFormat.parse("2020-06-14 21.00.00");
            Integer productId = 35455;
            Integer brandId = 1;
            List<Prices> lista = pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice, productId, brandId);
            assertFalse(lista.isEmpty());
            Prices price = getPriority(lista);
            assertNotNull(price);
            assertEquals(price.getCurr(),"EUR");
            assertTrue(price.getPrice().toString().contains("35.50"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        log.info("Fin: test3GetProducto");
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    @Order(7)
    void test4GetProducto() {
        log.info("Inicio: test4GetProducto");
        try {
            Date datePrice = dateFormat.parse("2020-06-15 10.00.00");
            Integer productId = 35455;
            Integer brandId = 1;
            List<Prices> lista = pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice, productId, brandId);
            assertFalse(lista.isEmpty());
            Prices price = getPriority(lista);
            assertNotNull(price);
            assertEquals(price.getCurr(),"EUR");;
            assertTrue(price.getPrice().toString().contains("30.50"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        log.info("Fin: test4GetProducto");
    }
    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    @Order(8)
    void test5GetProducto() {
        log.info("Inicio: test5GetProducto");
        try {
            Date datePrice = dateFormat.parse("2020-06-16 21.00.00");
            Integer productId = 35455;
            Integer brandId = 1;
            List<Prices> lista = pricesRepository.findByStarDateAndProductIdAndPriceList(datePrice, productId, brandId);
            assertFalse(lista.isEmpty());
            Prices price = getPriority(lista);
            assertNotNull(price);
            assertEquals(price.getCurr(),"EUR");;
            assertTrue(price.getPrice().toString().contains("38.95"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        log.info("Fin: test5GetProducto");
    }

}