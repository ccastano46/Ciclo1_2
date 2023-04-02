
/**
 * Clase que maneja ciertas exceṕciones que se pueden dar dentro de la clase Room
 * 
 * @author (Castaño - Camargo) 
 * @version (2/04/2023)
 */
public class RoomException extends Exception
{
    public static final String SIN_ESCULTURA = "La habitación no tiene escultura";
    public static final String SIN_GUARDIA = "La habitación no tiene guardia";
    public static final String ESCULTURA_PESADA = "La escultura esta muy pesada para robar"; 
    public RoomException(String message){
        super(message);
    }
}
