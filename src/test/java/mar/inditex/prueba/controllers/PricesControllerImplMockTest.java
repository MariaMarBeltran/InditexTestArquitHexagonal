package mar.inditex.prueba.controllers;

import mar.inditex.prueba.domain.DatosPruebas;
import mar.inditex.prueba.adapter.out.db.models.Prices;
import mar.inditex.prueba.adapter.out.db.repository.PricesRepositoryImpl;
import mar.inditex.prueba.application.PricesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PricesControllerImplMockTest {
    @Mock
    private PricesRepositoryImpl pricesRepositoryImpl;
    @InjectMocks
    private  PricesServiceImpl pricesService;

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH.mm.ss";
    private final DateFormat dateFormat  = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

    @Test
    void testListaPrecios() throws ParseException {
        List<Prices> listaEsperada = DatosPruebas.getPriceList();
        when(pricesRepositoryImpl.findAll()).thenReturn(listaEsperada);

        List<Prices> listaResultado = pricesService.getPriceList();
        assertEquals(listaEsperada.size(),listaResultado.size());

    }

    @Test
    void testPrecioById() throws ParseException  {
        Prices priceEsperado = DatosPruebas.getPrice3();
        when(pricesRepositoryImpl.findById(3L)).thenReturn(Optional.of(priceEsperado));

        Prices priceResultado = pricesService.getPriceById(3L);
        assertEquals(priceEsperado.getStartDate(), priceResultado.getStartDate());
        verify(pricesRepositoryImpl, Mockito.times(1)).findById(3L);
    }

    //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test1GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-14 10.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> listaEsperada = new ArrayList<>();
        listaEsperada.add(DatosPruebas.getPrice1());
        when(pricesRepositoryImpl
                .findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice))
                .thenReturn(listaEsperada);

        Prices priceResultado = pricesService.getPrice(datePrice,productId,brandId);
        verify(pricesRepositoryImpl, Mockito.times(1)).findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice);

        assertNotNull(priceResultado);
        assertEquals("EUR", priceResultado.getCurr());
        assertTrue(priceResultado.getPrice().toString().contains("35.50"));
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test2GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-14 16.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> listaEsperada = new ArrayList<>();
        listaEsperada.add(DatosPruebas.getPrice2());
        when(pricesRepositoryImpl
                .findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice))
                .thenReturn(listaEsperada);

        Prices priceResultado = pricesService.getPrice(datePrice,productId,brandId);
        verify(pricesRepositoryImpl, Mockito.times(1)).findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice);

        assertNotNull(priceResultado);
        assertEquals("EUR", priceResultado.getCurr());
        assertTrue(priceResultado.getPrice().toString().contains("25.45"));
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test3GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-14 21.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> listaEsperada = new ArrayList<>();
        listaEsperada.add(DatosPruebas.getPrice1());
        when(pricesRepositoryImpl
                .findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice))
                .thenReturn(listaEsperada);

        Prices priceResultado = pricesService.getPrice(datePrice,productId,brandId);
        verify(pricesRepositoryImpl, Mockito.times(1)).findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice);

        assertNotNull(priceResultado);
        assertEquals("EUR", priceResultado.getCurr());
        assertTrue(priceResultado.getPrice().toString().contains("35.50"));
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test4GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-15 10.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> listaEsperada = new ArrayList<>();
        listaEsperada.add(DatosPruebas.getPrice3());
        when(pricesRepositoryImpl
                .findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice))
                .thenReturn(listaEsperada);

        Prices priceResultado = pricesService.getPrice(datePrice,productId,brandId);
        verify(pricesRepositoryImpl, Mockito.times(1)).findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice);

        assertNotNull(priceResultado);
        assertEquals("EUR", priceResultado.getCurr());
        assertTrue(priceResultado.getPrice().toString().contains("30.50"));
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test5GetProducto() throws ParseException {
        Date datePrice = dateFormat.parse("2020-06-16 21.00.00");
        Integer productId = 35455;
        Integer brandId = 1;
        List<Prices> listaEsperada = new ArrayList<>();
        listaEsperada.add(DatosPruebas.getPrice4());
        when(pricesRepositoryImpl
                .findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice))
                .thenReturn(listaEsperada);

        Prices priceResultado = pricesService.getPrice(datePrice,productId,brandId);
        verify(pricesRepositoryImpl, Mockito.times(1)).findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId , datePrice, datePrice);

        assertNotNull(priceResultado);
        assertEquals("EUR", priceResultado.getCurr());
        assertTrue(priceResultado.getPrice().toString().contains("38.95"));
    }
}
