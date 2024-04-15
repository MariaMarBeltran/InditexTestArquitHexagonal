package mar.inditex.prueba;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaMarApplication {
	private static final Logger log =  LoggerFactory.getLogger(PruebaMarApplication.class);
	public static void main(String[] args) {


		SpringApplication.run(PruebaMarApplication.class, args);
		log.info("Prueba en marcha");
	}

}
