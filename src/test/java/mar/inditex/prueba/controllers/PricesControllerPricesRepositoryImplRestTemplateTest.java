package mar.inditex.prueba.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import mar.inditex.prueba.adapter.out.db.models.Prices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesControllerPricesRepositoryImplRestTemplateTest {

    @LocalServerPort
    private int puerto;

    @Autowired
    private TestRestTemplate client;

    @Test
    void testGetProductoById() throws JsonProcessingException {
        System.out.println("Inicio: testGetProductoById");

        ResponseEntity<Prices> response = client.getForEntity(crearUri("/priceIdJson/1"), Prices.class);
        assertNotNull(response);
        assertEquals( HttpStatus.OK, response.getStatusCode());
        Prices price = response.getBody();
        assertNotNull( price );
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("35.50"));

        System.out.println("Fin: testGetProductoById");
    }


    //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test1GetProducto() throws JsonProcessingException {
        ResponseEntity<Prices> response = client.getForEntity(
                crearUri("/priceDetailJson?fechaProducto=2020-06-14 10.00.00&productId=35455&brandId=1"), Prices.class);
        assertNotNull(response);
        assertEquals( HttpStatus.OK, response.getStatusCode());
        Prices price = response.getBody();
        assertNotNull( price );
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("35.50"));
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test2GetProducto() throws JsonProcessingException {
        ResponseEntity<Prices> response = client.getForEntity(
                crearUri("/priceDetailJson?fechaProducto=2020-06-14 16.00.00&productId=35455&brandId=1"), Prices.class);
        assertNotNull(response);
        assertEquals( HttpStatus.OK, response.getStatusCode());
        Prices price = response.getBody();
        assertNotNull( price );
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("25.45"));
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test3GetProducto() throws JsonProcessingException {
        ResponseEntity<Prices> response = client.getForEntity(
                crearUri("/priceDetailJson?fechaProducto=2020-06-14 21.00.00&productId=35455&brandId=1"), Prices.class);
        assertNotNull(response);
        assertEquals( HttpStatus.OK, response.getStatusCode());
        Prices price = response.getBody();
        assertNotNull( price );
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("35.50"));
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test4GetProducto() throws JsonProcessingException {
        ResponseEntity<Prices> response = client.getForEntity(
                crearUri("/priceDetailJson?fechaProducto=2020-06-15 10.00.00&productId=35455&brandId=1"), Prices.class);
        assertNotNull(response);
        assertEquals( HttpStatus.OK, response.getStatusCode());
        Prices price = response.getBody();
        assertNotNull( price );
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("30.50"));
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test5GetProducto() throws JsonProcessingException {
        ResponseEntity<Prices> response = client.getForEntity(
                crearUri("/priceDetailJson?fechaProducto=2020-06-16 21.00.00&productId=35455&brandId=1"), Prices.class);
        assertNotNull(response);
        assertEquals( HttpStatus.OK, response.getStatusCode());
        Prices price = response.getBody();
        assertNotNull( price );
        assertEquals("EUR", price.getCurr());
        assertTrue(price.getPrice().toString().contains("38.95"));
    }


    private String crearUri(String uri) {
        return "http://localhost:" + puerto + uri;
    }

}
