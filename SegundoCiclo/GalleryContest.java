
/**
 * Clase creada para solucionar el problema D
 * 
 * Camargo - Castaño
 * @version (25 de marzo del 2023)
 */
public class GalleryContest
{
    /**
     * Función que indica la distancia más corta para que el guardia vea a la escultura.
     * @param polygon, matriz n x 2, que contiene las cordenadas de los vertices del poligono que conforma el cuarto.
     * @param guard, arreglo de dos dimensiones que contiene las posiciones x y y del guardia.
     * @param sculpture, arreglo de dos dimensiones que contiene las posiciones x y y de la escultura para la habitación.
     */
    public float solve(int[][] polygon, int[] guard, int[] sculpture){
        Gallery galeria = new Gallery(polygon, guard, sculpture);
        return galeria.shortestDistance("black");
    }
}
