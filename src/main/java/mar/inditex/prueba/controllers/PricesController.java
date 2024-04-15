package mar.inditex.prueba.controllers;

import mar.inditex.prueba.models.Prices;
import mar.inditex.prueba.services.PricesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PricesController {
    private static final Logger log =  LoggerFactory.getLogger(PricesController.class);

    @Autowired
    private PricesService pricesService;

    @GetMapping("/listar")
    public ResponseEntity<List<Prices>> listar(){
        List<Prices> prices = pricesService.getPrecios();
        log.info(String.format("Encontrados %d ", prices.size()));
        return new ResponseEntity <List<Prices>>(prices,HttpStatus.OK);
    }


    @GetMapping("/{price_list}")
    public Prices detalle(@PathVariable Long price_list){
        return new ResponseEntity<>(pricesService.findById(price_list),HttpStatus.OK).getBody();
    }

    @GetMapping("buscaId/{price_list_id}")
    public Prices buscaIdentificadoTarifa(@PathVariable("price_list_id") Long price_list_id){
        List<Prices> prices = pricesService.getByPriceList(price_list_id);
        Prices price =  prices.get(0);
        return new ResponseEntity<>(price,HttpStatus.OK).getBody();
    }

    @GetMapping("buscaProducto/{productId}/{priceList}/{fechaProducto}")
    public  Prices buscaProducto(@PathVariable("productId") Integer productId,
                                 @PathVariable("priceList") Long priceList,
                                 @PathVariable("fechaProducto") String fechaProducto) {


        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        Date date ;
        try {
            date = dateFormat.parse(fechaProducto);

            List<Prices> prices = pricesService.getProducto(date, productId, priceList);
            Prices price =  prices.get(0);
            log.info("Fin buscaProducto");
            return new ResponseEntity<>(price,HttpStatus.OK).getBody();

        } catch (ParseException e) {
            // Por hacer
            log.info(e.getMessage());
            return null;
        }

    }

}
