package mar.inditex.prueba.controllers;

import mar.inditex.prueba.models.Prices;
import mar.inditex.prueba.services.PricesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PricesController {
    private static final Logger log =  LoggerFactory.getLogger(PricesController.class);

    @Autowired
    private PricesService pricesService;

    @GetMapping("/listar")
    public ResponseEntity<List<Prices>> listar(){
        List<Prices> prices = pricesService.findAll();
        log.info(String.format("Encontrados %d ", prices.size()));
        return new ResponseEntity <List<Prices>>(prices,HttpStatus.OK);
    }



}
