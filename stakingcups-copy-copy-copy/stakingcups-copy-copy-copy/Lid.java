import shapes.*;
/**
 * Write a description of class Lid here.
 * lid la tapa de la taza
 * @author (Mabel Bernal,Jeronimo Avila ) 
 * @version (1)
 */
public class Lid {
    private int cupIndex;        // Índice de la taza a la que pertenece
    private String color;        // Color (mismo que su taza)
    private Rectangle body;      // Representación visual
    private int xPosition;       // Posición X
    private int yPosition;       // Posición Y
    private boolean isVisible;   // ¿Está visible?
    
    /**
     * Constructor - Crea una tapa
     * @param cupIndex índice de la taza a la que pertenece
     * @param color color de la tapa (debe ser igual al de su taza)
     */
    public Lid(int cupIndex, String color) {
        this.cupIndex = cupIndex;
        this.color = color;
        this.xPosition = 100;
        this.yPosition = 100;
        this.isVisible = false;
        
        // Crear representación visual
        body = new Rectangle();
        body.changeColor(color);
        // El ancho debe coincidir con el de la taza
        int width = 20 + (cupIndex * 10);
        body.changeSize(10, width); // Altura fija de 10 píxeles (representa 1 cm)
    }
    
    /**
     * Hace visible la tapa
     */
    public void makeVisible() {
        if (!isVisible) {
            body.makeVisible();
            isVisible = true;
        }
    }
    
    /**
     * Hace invisible la tapa
     */
    public void makeInvisible() {
        if (isVisible) {
            body.makeInvisible();
            isVisible = false;
        }
    }
    
    /**
     * Mueve la tapa a una posición específica
     */
    public void moveTo(int x, int y) {
        if (isVisible) {
            int deltaX = x - xPosition;
            int deltaY = y - yPosition;
            
            body.moveHorizontal(deltaX);
            body.moveVertical(deltaY);
        }
        
        this.xPosition = x;
        this.yPosition = y;
    }
    
    /**
     * Retorna la altura de la tapa (siempre 1 cm)
     */
    public int getHeight() {
        return 1;
    }
    
    /**
     * Retorna el índice de la taza a la que pertenece
     */
    public int getCupIndex() {
        return cupIndex;
    }
    
    /**
     * Retorna el color de la tapa
     */
    public String getColor() {
        return color;
    }
}