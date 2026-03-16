import shapes.*;
/**
 * Write a description of class Cup here.
 * 
 * @author (Mabel Bernal , Jeronimo Avila  
 * @version (1)
 */
public class Cup {
    private int index;           // Número de taza (1, 2, 3, 4...)
    private int height;          // Altura = 2^(index-1) cm
    private String color;        // Color de la taza
    private Rectangle body;      // Cuerpo de la taza 
    private int xPosition;       // Posición X en pantalla
    private int yPosition;       // Posición Y en pantalla
    private boolean isVisible;   // Si Está visible
    
    /**
     * Constructor - Crea una taza
     * @ index número de la taza (1, 2, 3, 4...)
     * @ color color de la taza ("red", "blue", "green", etc.)
     */
    public Cup(int index, String color) {
        this.index = index;
        // Calcular altura: 2^(index-1)
        // Ejemplo: index=1 → 2^0=1, index=2 → 2^1=2, index=3 → 2^2=4
        this.height = (int)Math.pow(2, index - 1);
        this.color = color;
        this.xPosition = 100;
        this.yPosition = 100;
        this.isVisible = false;
        
        // Crear representación visual con Rectangle de shapes
        body = new Rectangle();
        body.changeColor(color);
        // El ancho será proporcional al índice para simular diámetros diferentes
        int width = 20 + (index * 10);
        body.changeSize(height * 10, width); // Multiplicamos por 10 para ver mejor
    }
    
    /**
     * Hace visible la taza
     */
    public void makeVisible() {
        if (!isVisible) {
            body.makeVisible();
            isVisible = true;
        }
    }
    
    /**
     * Hace invisible la taza
     */
    public void makeInvisible() {
        if (isVisible) {
            body.makeInvisible();
            isVisible = false;
        }
    }
    
    /**
     * Mueve la taza a una posición específica
     */
    public void moveTo(int x, int y) {
        if (isVisible) {
            // Calcular cuánto mover desde la posición actual
            int deltaX = x - xPosition;
            int deltaY = y - yPosition;
            
            body.moveHorizontal(deltaX);
            body.moveVertical(deltaY);
        }
        
        // Actualizar posición guardada
        this.xPosition = x;
        this.yPosition = y;
    }
    
    /**
     * Retorna la altura de la taza en cm
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Retorna el índice de la taza
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Retorna el color de la taza
     */
    public String getColor() {
        return color;
    }
}