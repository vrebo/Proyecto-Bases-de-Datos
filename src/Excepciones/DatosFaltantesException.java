package Excepciones;

/**
 *
 * @author VREBO
 */
public class DatosFaltantesException extends Exception {

    /**
     * Creates a new instance of <code>DatosFaltantesException</code> without
     * detail message.
     */
    public DatosFaltantesException() {
    
    }
    
    
    @Override
    public String getMessage(){
        return "Datos insuficientes";
    }

    /**
     * Constructs an instance of <code>DatosFaltantesException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DatosFaltantesException(String msg) {
        super(msg);
    }
}
