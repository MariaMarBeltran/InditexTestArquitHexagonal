package mar.inditex.prueba.domain;

public class MensajeErrorException extends RuntimeException{
    public MensajeErrorException(String message) {
        super(message);
    }

    public MensajeErrorException(Long id) {
        super("El producto: " + id + " no existe");
    }


}
