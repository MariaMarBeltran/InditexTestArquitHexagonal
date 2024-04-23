package mar.inditex.prueba.adapter.in.web;

import mar.inditex.prueba.domain.MensajeErrorException;
import mar.inditex.prueba.adapter.out.db.models.Prices;
import mar.inditex.prueba.port.in.web.PricesController;
import mar.inditex.prueba.application.PricesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PricesControllerImpl implements PricesController {
    private static final Logger log =  LoggerFactory.getLogger(PricesControllerImpl.class);

    private final PricesServiceImpl pricesService;

    public PricesControllerImpl(PricesServiceImpl pricesService) {
        this.pricesService = pricesService;
    }

    // Pruebas desde url con capa vista http://localhost:8090/
    @GetMapping("/")
    public String priceList(Model model){
        List<Prices> lista = pricesService.getPriceList();
        model.addAttribute("pricesList", lista);
        return "list-prices";
    }

    @RequestMapping("/priceDetail")
    public String priceDetail(
            @RequestParam(value = "fechaProducto", required = true) String fechaProducto,
            @RequestParam(value ="productId", required = true) Long productId,
            @RequestParam(value = "brandId", required = true) Long brandId,
            Model model)throws ParseException  {
        Prices prices = null;

        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        Date date ;
        date = dateFormat.parse(fechaProducto);
        prices = pricesService.getPrice(date, productId.intValue(), brandId.intValue());
        if (prices == null){
            String mensaje ="Parámetros: "
                    + " productId: " +  productId.toString()
                    + " priceList: " + brandId.toString()
                    + " fechaProducto: " + fechaProducto;
            throw new MensajeErrorException(mensaje);
        } else {
            List<Prices> lista =  new ArrayList<>();
            lista.add(prices);
            model.addAttribute("pricesList", lista);
            return "list-prices";
        }
    }

    @GetMapping("/{price_list}")
    public String priceId(@PathVariable Long price_list, Model model){
        String mensaje;
        Prices prices = null;
            prices = pricesService.getPriceById(price_list);
            if (prices == null){
                throw new MensajeErrorException(price_list);
            } else {
                List<Prices> lista =  new ArrayList<>();
                lista.add(prices);
                model.addAttribute("pricesList", lista);
                return "list-prices";
            }
    }

    // Pruebas con formato Json para Postman
    @RequestMapping("/priceDetailJson")
    public ResponseEntity<Prices>  priceDetailJson(
            @RequestParam(value = "fechaProducto", required = true) String fechaProducto,
            @RequestParam(value ="productId", required = true) Long productId,
            @RequestParam(value = "brandId", required = true) Long brandId
            ) throws ParseException {
        Prices prices = null;

        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        Date date ;
        date = dateFormat.parse(fechaProducto);
        prices = pricesService.getPrice(date, productId.intValue(), brandId.intValue());
        return new ResponseEntity <>(prices , HttpStatus.OK);
    }


    @GetMapping("/priceIdJson/{price_list}")
    public ResponseEntity<Prices> priceIdJson(@PathVariable Long price_list){
        Prices prices = pricesService.getPriceById(price_list);
        return new ResponseEntity <>(prices , HttpStatus.OK);
    }
}
