package mar.inditex.prueba.domain;

import mar.inditex.prueba.adapter.out.db.models.Prices;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatosPruebas {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH.mm.ss";
    private static final DateFormat dateFormat  = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

    public static List<Prices> getPriceList()  throws ParseException {
        List<Prices> lista = new ArrayList<>();
        lista.add(getPrice1());
        lista.add(getPrice2());
        lista.add(getPrice3());
        lista.add(getPrice4());
        return lista;
    }

    public static Prices getPrice1() throws ParseException {
        Prices price = new Prices();
        price.setPriceList(1L);
        price.setBrandId(1);
        price.setProductId(35455);
        price.setPriority(0);
        price.setPrice(new BigDecimal("35.50"));
        price.setCurr("EUR");
        price.setStartDate(dateFormat.parse("2020-06-14 00.00.00"));
        price.setEndDate(dateFormat.parse("2020-12-31 23.59.59"));
        return price;
    }
    public static Prices getPrice2() throws ParseException {
        Prices price = new Prices();
        price.setPriceList(2L);
        price.setBrandId(1);
        price.setProductId(35455);
        price.setPriority(1);
        price.setPrice(new BigDecimal("25.45"));
        price.setCurr("EUR");
        price.setStartDate(dateFormat.parse("2020-06-14 15.00.00"));
        price.setEndDate(dateFormat.parse("2020-06-14 18.30.00"));
        return price;
    }

    public static  Prices getPrice3() throws ParseException {
        Prices price = new Prices();
        price.setPriceList(3L);
        price.setBrandId(1);
        price.setProductId(35455);
        price.setPriority(1);
        price.setPrice(new BigDecimal("30.50"));
        price.setCurr("EUR");
        price.setStartDate(dateFormat.parse("2020-06-15 00.00.00"));
        price.setEndDate(dateFormat.parse("2020-06-15 11.00.00"));
        return price;
    }


    public static Prices getPrice4() throws ParseException {
        Prices price = new Prices();
        price.setPriceList(4L);
        price.setBrandId(1);
        price.setProductId(35455);
        price.setPriority(1);
        price.setPrice(new BigDecimal("38.95"));
        price.setCurr("EUR");
        price.setStartDate(dateFormat.parse("2020-06-15 16.00.00"));
        price.setEndDate(dateFormat.parse("2020-12-31 23.59.59"));
        return price;
    }
}
