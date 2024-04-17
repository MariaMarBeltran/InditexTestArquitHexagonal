package mar.inditex.prueba.controllers;

import mar.inditex.prueba.exceptions.MensajeErrorException;
import mar.inditex.prueba.models.Prices;
import mar.inditex.prueba.services.PricesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PricesController {
    private static final Logger log =  LoggerFactory.getLogger(PricesController.class);

    @Autowired
    private PricesService pricesService;

    // Pruebas desde url con capa vista http://localhost:8090/
    @GetMapping("/")
    public String listarHtml(Model model){
        List<Prices> lista = pricesService.getListaPreciosTodos();
        model.addAttribute("pricesList", lista);
        return "list-prices";
    }

    @RequestMapping("/buscaProductoHtml")
    public String detalleHtml(
            @RequestParam(value = "fechaProducto", required = true) String fechaProducto,
            @RequestParam(value ="productId", required = true) Long productId,
            @RequestParam(value = "brandId", required = true) Long brandId,
            Model model){
        Prices prices = null;
        String mensaje ="con los siguientes parámetros: "
                + " productId: " +  productId.toString()
                + " priceList: " + brandId.toString()
                + " fechaProducto: " + fechaProducto;
        log.info(mensaje);
        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        Date date ;

        try {
            date = dateFormat.parse(fechaProducto);
            prices = pricesService.getProducto(date, productId.intValue(), brandId.intValue());
            if (prices == null){
                model.addAttribute("info", "No encontrado el producto " + mensaje);
                model.addAttribute("message", " ");
                return "ErrorPrices";
            } else {
                List<Prices> lista =  new ArrayList<>();
                lista.add(prices);
                model.addAttribute("pricesList", lista);
                return "list-prices";
            }
        } catch ( MensajeErrorException m  ){
            model.addAttribute("info", "No encontrado el producto " + mensaje);
            model.addAttribute("message", m.getMessage());
            return "ErrorPrices";
        }  catch (ParseException e) {
            model.addAttribute("info", "Formato de fecha incorrecta. (yyyy-MM-dd HH.mm.ss) " + fechaProducto);
            model.addAttribute("message", e.getMessage());
            return "ErrorPrices";
        }

    }

    // Pruebas realizadas desde Postman
    @GetMapping("/listar")
    public ResponseEntity<List<Prices>> listar(){
        List<Prices> prices = pricesService.getListaPrecios();
        return new ResponseEntity <List<Prices>>(prices,HttpStatus.OK);
    }

    @GetMapping("/{price_list}")
    public ResponseEntity<Prices>  detalle(@PathVariable Long price_list){
        String mensaje;
        Prices prices = null;
        try {
            prices = pricesService.getPrecioById(price_list);
        } catch ( MensajeErrorException m  ){
            log.info("No encontrado el producto " + price_list.toString());
        }
        return new ResponseEntity <>(prices ,HttpStatus.OK);
    }

    @GetMapping("buscaId/{price_list_id}")
    public ResponseEntity<List<Prices>>  buscaIdentificadoTarifa2(@PathVariable("price_list_id") Long price_list_id){
        List<Prices> prices = pricesService.getByPriceList(price_list_id);
        if (prices.isEmpty()){
            log.info("No encontrado el producto " + price_list_id.toString());
        }
        return new ResponseEntity <List<Prices>>(prices ,HttpStatus.OK);
    }


    @GetMapping("buscaProducto/{productId}/{brandId}/{fechaProducto}")
    public ResponseEntity<Prices>  buscaProducto(
            @PathVariable("productId") Integer productId,
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
            if (prices == null){
                log.info(mensaje);
            }
            return new ResponseEntity <>(prices ,HttpStatus.OK);
        } catch (ParseException e) {
            log.info("Formato de fecha incorrecta. (yyyy-MM-dd HH.mm.ss)");
            return null;
        }

    }

}
