package mar.inditex.prueba.controllers;

import mar.inditex.prueba.adapter.out.db.models.Prices;
import mar.inditex.prueba.adapter.out.db.repository.PricesRepositoryImpl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static mar.inditex.prueba.domain.UtilsPrice.getPriority;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PricesControllerPricesRepositoryImplTest {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH.mm.ss";
    private final DateFormat dateFormat  = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

    @Autowired
    private PricesRepositoryImpl pricesRepositoryImpl;

    @Test
    void testListaPrecios() {
        List<Prices> lista = pricesRepositoryImpl.findAll();
        assertFalse(lista.isEmpty());
        assertEquals(4, lista.size());
    }

    @Test
    void testPrecioById() {
        Optional<Prices> prices = pricesRepositoryImpl.findById(3L);
        assertTrue(prices.isPresent());
        assertNotNull(prices);
        assertEquals("EUR",prices.get().getCurr());

        prices = pricesRepositoryImpl.findById(14L);
        assertFalse(prices.isPresent());
    }

    @Test
    void testPriceList() {
        List<Prices> lista = pricesRepositoryImpl.findByPriceList(2L);
        assertFalse(lista.isEmpty());
        assertEquals("EUR", lista.get(0).getCurr());
    }


    //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test1GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-14 10.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> lista = pricesRepositoryImpl.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice,datePrice);
        assertFalse(lista.isEmpty());
        Prices price = getPriority(lista);
        assertNotNull(price);
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("35.50"));
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test2GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-14 16.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> lista = pricesRepositoryImpl.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice,datePrice);
        assertFalse(lista.isEmpty());
        Prices price = getPriority(lista);
        assertNotNull(price);
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("25.45"));
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test3GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-14 21.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> lista = pricesRepositoryImpl.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice,datePrice);
        assertFalse(lista.isEmpty());
        Prices price = getPriority(lista);
        assertNotNull(price);
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("35.50"));
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test4GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-15 10.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> lista = pricesRepositoryImpl.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice,datePrice);
        assertFalse(lista.isEmpty());
        Prices price = getPriority(lista);
        assertNotNull(price);
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("30.50"));
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test5GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-16 21.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> lista = pricesRepositoryImpl.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice,datePrice);
        assertFalse(lista.isEmpty());
        Prices price = getPriority(lista);
        assertNotNull(price);
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("38.95"));
    }

}