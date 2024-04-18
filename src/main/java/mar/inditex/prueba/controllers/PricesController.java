package mar.inditex.prueba.controllers;

import mar.inditex.prueba.exceptions.MensajeErrorException;
import mar.inditex.prueba.models.Prices;
import mar.inditex.prueba.services.PricesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PricesController {
    private static final Logger log =  LoggerFactory.getLogger(PricesController.class);

    private final PricesServiceImpl pricesService;

    public PricesController(PricesServiceImpl pricesService) {
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
            Model model) throws ParseException {
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
}
