
/**
 * Habitación que no tiene escultura
 * 
 * @author (Castaño - Camargo) 
 * @version (2/04/2023)
 */
public class Unprotected extends Room
{
    /**
     * Constructor del cuarto standby
     * @param color, color con el cual se va a identificar el cuarto
     * @param polygon, vertices que componen el poligono del cuarto
     */
    public Unprotected(String color, int[][] polygon){
        super(color, polygon);
    }
    /**
     * Sobre escritura del metodo setAlarm de Room.
     * @param on, parametro que indica si la alarma se prende o se apaga. Sin embargo, como este tipo de habiatación no tiene alarma, siempre su 
     * valor va a ser falso.
     */
    public void setAlarm(boolean on){
        super.setAlarm(false);
    }
}
