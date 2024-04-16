package mar.inditex.prueba.controllers;

import mar.inditex.prueba.exceptions.MensajeErrorException;
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
        List<Prices> prices = pricesService.getListaPrecios();
        log.info(String.format("Encontrados %d ", prices.size()));
        return new ResponseEntity <List<Prices>>(prices,HttpStatus.OK);
    }

    @GetMapping("/{price_list}")
    public String detalle(@PathVariable Long price_list){
        String mensaje;
        try {
            Prices prices = pricesService.getPrecioById(price_list);
            mensaje = prices.toString();
        } catch ( MensajeErrorException m  ){
            mensaje ="No encontrado el producto " + price_list.toString();
        }
        return mensaje;
    }

    @GetMapping("buscaId/{price_list_id}")
    public String buscaIdentificadoTarifa2(@PathVariable("price_list_id") Long price_list_id){
        String mensaje ="No encontrado el producto " + price_list_id.toString();
        List<Prices> prices = pricesService.getByPriceList(price_list_id);
        if (!prices.isEmpty()){
            mensaje = prices.get(0).toString();
        }
        return mensaje;
    }


    @GetMapping("buscaProducto/{productId}/{brandId}/{fechaProducto}")
    public  String buscaProducto(@PathVariable("productId") Integer productId,
                                 @PathVariable("brandId") Integer brandId,
                                 @PathVariable("fechaProducto") String fechaProducto) {
        String mensaje ="No encontrado el producto con los siguientes parámetros: "
                        + " productId: " +  productId.toString()
                        + " priceList: " + brandId.toString()
                        + " fechaProducto: " + fechaProducto;

        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        Date date ;
        try {
            date = dateFormat.parse(fechaProducto);

            Prices prices = pricesService.getProducto(date, productId, brandId);
            if (prices != null){
                mensaje = prices.toString();
            }
            return mensaje;
        } catch (ParseException e) {
            mensaje = "Formato de fecha incorrecta. (yyyy-MM-dd HH.mm.ss)";
            return mensaje;
        }

    }

}
