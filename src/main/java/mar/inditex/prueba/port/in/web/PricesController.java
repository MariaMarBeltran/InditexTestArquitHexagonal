package mar.inditex.prueba.port.in.web;

import mar.inditex.prueba.adapter.out.db.models.Prices;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

public interface PricesController {
    @GetMapping("/")
    public String priceList(Model model);

    @RequestMapping("/priceDetail")
    public String priceDetail(
            @RequestParam(value = "fechaProducto", required = true) String fechaProducto,
            @RequestParam(value ="productId", required = true) Long productId,
            @RequestParam(value = "brandId", required = true) Long brandId,
            Model model) throws ParseException;

    @GetMapping("/{price_list}")
    public String priceId(@PathVariable Long price_list, Model model);

    @RequestMapping("/priceDetailJson")
    public ResponseEntity<Prices> priceDetailJson(
            @RequestParam(value = "fechaProducto", required = true) String fechaProducto,
            @RequestParam(value ="productId", required = true) Long productId,
            @RequestParam(value = "brandId", required = true) Long brandId
    ) throws ParseException;


    @GetMapping("/priceIdJson/{price_list}")
    public ResponseEntity<Prices> priceIdJson(@PathVariable Long price_list);


}
