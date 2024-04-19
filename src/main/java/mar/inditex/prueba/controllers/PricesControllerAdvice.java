package mar.inditex.prueba.controllers;

import mar.inditex.prueba.exceptions.MensajeErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;


@ControllerAdvice
public class PricesControllerAdvice {
    private static final Logger log =  LoggerFactory.getLogger(PricesControllerAdvice.class);

    @ExceptionHandler(value = ParseException.class)
    public String runtimePricesException(ParseException e, Model model){
        model.addAttribute("info", "Formato de fecha incorrecta. (yyyy-MM-dd HH.mm.ss) ");
        log.error(e.getMessage());
        return "ErrorPrices";
    }

    @ExceptionHandler(value = MensajeErrorException.class)
    public String runtimePricesException(MensajeErrorException e, Model model){
        model.addAttribute("info", "No encontrado el producto: ");
        model.addAttribute("message", e.getMessage());
        return "ErrorPrices";
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public String runtimePricesException(MissingServletRequestParameterException e, Model model){
        model.addAttribute("info", "Parámetros obligatórios: ");
        model.addAttribute("message", "Fecha (yyyy-MM-dd HH.mm.ss)  productId  brandId ");
        log.error(e.getMessage());
        return "ErrorPrices";
    }


    @ExceptionHandler({Exception.class, RuntimeException.class})
    public String runtimePricesException(Throwable e, Model model){
        model.addAttribute("info", "Ha ocurrido un error contacte con el administrador");
        model.addAttribute("message",e.getMessage());
        log.error(e.getMessage());
        return "ErrorPrices";
    }
}
