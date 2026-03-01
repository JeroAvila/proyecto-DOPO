import shapes.*;
import java.util.ArrayList;

/**
 * Tower - Simulador de torre de tazas y tapas
 * 
 * @author (Mabel Bernal . Jeronimo Avila) 
 * @version (1)
 */
public class Tower {
    // Variables para guardar las características de la torre
    private int width;                      // ancho de la torre
    private int maxHeight;                  // altura máxima permitida en cm
    private boolean isVisible;              // para saber si la torre se está mostrando o no
    private ArrayList<Cup> cups;            // aquí guardamos todas las tazas
    private ArrayList<Lid> lids;            // aquí guardamos todas las tapas
    private boolean lastOperationOk;        // para saber si la última cosa que hicimos funcionó
    private ArrayList<Rectangle> scale;     // para guardar las marcas de la escala de centímetros
    
    /**
     * Constructor - Aquí creamos la torre cuando decimos "new Tower"
     */
    public Tower(int width, int maxHeight) {
        this.width = width;
        this.maxHeight = maxHeight;
        this.isVisible = false;                     // al principio está invisible
        this.cups = new ArrayList<Cup>();           // empezamos sin tazas
        this.lids = new ArrayList<Lid>();           // empezamos sin tapas
        this.lastOperationOk = true;                // al principio todo está ok
        this.scale = new ArrayList<Rectangle>();    // inicializamos la escala vacía
    }
    
    /**
     * Hace visible la torre y todos sus elementos
     */
    public void makeVisible() {
        isVisible = true;
        
        // recorremos todas las tazas y las mostramos una por una
        for (Cup cup : cups) {
            cup.makeVisible();
        }
        
        // hacemos lo mismo con las tapas
        for (Lid lid : lids) {
            lid.makeVisible();
        }
        
        // dibujamos la escala de centímetros
        drawScale();
        
        System.out.println("Torre visible");
    }
    
    /**
     * Esconde la torre y todos sus elementos
     */
    public void makeInvisible() {
        isVisible = false;
        
        // escondemos todas las tazas
        for (Cup cup : cups) {
            cup.makeInvisible();
        }
        
        // escondemos todas las tapas
        for (Lid lid : lids) {
            lid.makeInvisible();
        }
        
        // escondemos la escala
        if (scale != null) {
            for (Rectangle line : scale) {
                line.makeInvisible();
            }
        }
        
        System.out.println("Torre invisible");
    }
    
    /**
     * Agrega una taza nueva encima de la torre
     */
    public void pushCup(int i) {
        // primero verificamos que el número de taza sea válido (no puede ser 0 o negativo)
        if (i < 1) {
            lastOperationOk = false;
            // si la torre está visible, le avisamos al usuario del error
            if (isVisible) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error: El índice de la taza debe ser mayor o igual a 1");
            }
            return;  // salimos del método sin hacer nada más
        }
        
        // definimos los colores que vamos a usar para las tazas
        // pusimos 8 colores diferentes para que se vean bonitas
        String[] colors = {"red", "blue", "green", "yellow", "magenta", 
                          "black", "white", "orange"};
        
        // elegimos un color según el número de taza
        // usamos (i-1) porque las tazas empiezan en 1 pero los arrays en 0
        // el % hace que cuando lleguemos a la taza 9, vuelva a empezar con rojo
        String color = colors[(i - 1) % colors.length];
        
        // ahora sí creamos la taza nueva con su número y color
        Cup newCup = new Cup(i, color);
        
        // calculamos dónde va a ir la taza en la pantalla
        int y = 400;  // empezamos desde abajo (400 píxeles)
        
        // por cada taza que ya tenemos, subimos la posición
        for (Cup cup : cups) {
            y -= cup.getHeight() * 10;  // multiplicamos por 10 para que se vea bien
                                        // porque 1cm solo sería 1 píxel (muy chiquito)
        }
        
        // movemos la taza a su posición
        newCup.moveTo(200, y);  // 200 es la posición horizontal (para centrarla)
        
        // agregamos la taza a nuestra lista
        cups.add(newCup);
        
        // si la torre está visible, mostramos la taza nueva
        if (isVisible) {
            newCup.makeVisible();
        }
        
        lastOperationOk = true;  // todo salió bien
    }
    
    /**
     * Quita la taza de arriba de la torre
     */
    public void popCup() {
        // revisamos si hay tazas para quitar
        if (cups.isEmpty()) {
            lastOperationOk = false;
            if (isVisible) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error: No hay tazas en la torre");
            }
            return;
        }
        
        // quitamos la última taza (la de arriba)
        // cups.size()-1 nos da el índice de la última taza
        Cup removedCup = cups.remove(cups.size() - 1);
        
        // la escondemos de la pantalla
        removedCup.makeInvisible();
        
        lastOperationOk = true;
    }
    
    /**
     * Agrega una tapa encima de la torre
     */
    public void pushLid(int i) {
        // verificamos que el número sea válido
        if (i < 1) {
            lastOperationOk = false;
            if (isVisible) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error: El índice debe ser mayor o igual a 1");
            }
            return;
        }
        
        // usamos los mismos colores que las tazas
        // así la tapa tiene el mismo color que su taza
        String[] colors = {"red", "blue", "green", "yellow", "magenta", 
                          "black", "white", "orange"};
        String color = colors[(i - 1) % colors.length];
        
        // creamos la tapa
        Lid newLid = new Lid(i, color);
        
        // calculamos dónde va la tapa
        int y = 400;
        
        // primero subimos por todas las tazas
        for (Cup cup : cups) {
            y -= cup.getHeight() * 10;
        }
        
        // luego subimos por todas las tapas que ya están
        for (Lid lid : lids) {
            y -= 10;  // cada tapa ocupa 10 píxeles (representa 1 cm)
        }
        
        // movemos la tapa a su lugar
        newLid.moveTo(200, y);
        
        // la agregamos a la lista
        lids.add(newLid);
        
        // si está visible, la mostramos
        if (isVisible) {
            newLid.makeVisible();
        }
        
        lastOperationOk = true;
    }
    
    /**
     * Quita la tapa de arriba
     */
    public void popLid() {
        // revisamos que haya tapas
        if (lids.isEmpty()) {
            lastOperationOk = false;
            if (isVisible) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error: No hay tapas en la torre");
            }
            return;
        }
        
        // quitamos la última tapa
        Lid removedLid = lids.remove(lids.size() - 1);
        removedLid.makeInvisible();
        
        lastOperationOk = true;
    }
    
    /**
     * Quita una taza específica (no solo la de arriba)
     */
    public void removeCup(int i) {
        // verificamos que el índice exista en nuestra lista
        // i tiene que ser entre 0 y el número de tazas-1
        if (i < 0 || i >= cups.size()) {
            lastOperationOk = false;
            if (isVisible) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error: Índice inválido. Debe estar entre 0 y " + (cups.size() - 1));
            }
            return;
        }
        
        // quitamos la taza en esa posición
        Cup removedCup = cups.remove(i);
        removedCup.makeInvisible();
        
        // como quitamos una taza del medio, hay que reacomodar todas las demás
        repositionAllItems();
        
        lastOperationOk = true;
    }
    
    /**
     * Quita una tapa específica
     */
    public void removeLid(int i) {
        // verificamos que el índice sea válido
        if (i < 0 || i >= lids.size()) {
            lastOperationOk = false;
            if (isVisible) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error: Índice inválido. Debe estar entre 0 y " + (lids.size() - 1));
            }
            return;
        }
        
        // quitamos la tapa
        Lid removedLid = lids.remove(i);
        removedLid.makeInvisible();
        
        // reacomodamos todo
        repositionAllItems();
        
        lastOperationOk = true;
    }
    
    /**
     * Ordena las tazas y tapas de mayor a menor
     */
    public void orderTower() {
        // ordenamos las tazas de la más alta a la más baja
        // usamos una función especial de Java que compara las alturas
        cups.sort((cup1, cup2) -> cup2.getHeight() - cup1.getHeight());
        
        // ordenamos las tapas por el número de taza (de mayor a menor)
        lids.sort((lid1, lid2) -> lid2.getCupIndex() - lid1.getCupIndex());
        
        // como cambiamos el orden, tenemos que redibujar todo
        repositionAllItems();
        
        lastOperationOk = true;
    }
    
    /**
     * Invierte el orden de todos los elementos
     */
    public void reverseTower() {
        // usamos una función de Java que voltea listas
        // lo que estaba primero queda último y viceversa
        java.util.Collections.reverse(cups);
        java.util.Collections.reverse(lids);
        
        // redibujamos todo en el nuevo orden
        repositionAllItems();
        
        lastOperationOk = true;
    }
    
    /**
     * Método auxiliar que reacomoda todos los elementos después de cambiar algo
     * Lo usamos cuando quitamos algo del medio o cambiamos el orden
     */
    private void repositionAllItems() {
        int y = 400;  // empezamos desde la base
        
        // reposicionamos cada taza
        for (Cup cup : cups) {
            cup.moveTo(200, y);
            y -= cup.getHeight() * 10;  // vamos subiendo
        }
        
        // reposicionamos cada tapa
        for (Lid lid : lids) {
            lid.moveTo(200, y);
            y -= 10;  // seguimos subiendo
        }
    }
    
    /**
     * Calcula cuántos centímetros mide la torre completa
     */
    public int height() {
        int totalHeight = 0;
        
        // sumamos la altura de cada taza
        for (Cup cup : cups) {
            totalHeight += cup.getHeight();
        }
        
        // sumamos las tapas (cada una mide 1 cm)
        // como todas miden lo mismo, solo contamos cuántas hay
        totalHeight += lids.size();
        
        return totalHeight;
    }
    
    /**
     * Nos dice qué tazas tienen tapa puesta
     */
    public int[] lidedCups() {
        // creamos una lista temporal para ir guardando los números
        ArrayList<Integer> lided = new ArrayList<Integer>();
        
        // por cada tapa, guardamos el número de taza a la que pertenece
        for (Lid lid : lids) {
            lided.add(lid.getCupIndex());
        }
        
        // convertimos el ArrayList a un array normal (que es lo que pide el ejercicio)
        int[] result = new int[lided.size()];
        for (int i = 0; i < lided.size(); i++) {
            result[i] = lided.get(i);
        }
        
        return result;
    }
    
    /**
     * Retorna toda la información de lo que hay en la torre
     * Cada elemento tiene [tipo, número]
     */
    public String[][] stackingItems() {
        // calculamos cuántos elementos hay en total
        int totalItems = cups.size() + lids.size();
        
        // creamos un array de 2 columnas: [tipo, número]
        String[][] items = new String[totalItems][2];
        
        int index = 0;  // para ir llenando el array
        
        // agregamos la info de cada taza
        for (Cup cup : cups) {
            items[index][0] = "cup";                           // tipo
            items[index][1] = String.valueOf(cup.getIndex());  // número de taza
            index++;
        }
        
        // agregamos la info de cada tapa
        for (Lid lid : lids) {
            items[index][0] = "lid";                              // tipo
            items[index][1] = String.valueOf(lid.getCupIndex());  // número de taza a la que pertenece
            index++;
        }
        
        return items;
    }
    
    /**
     * Cierra el simulador y limpia todo
     */
    public void exit() {
        // primero escondemos todo
        makeInvisible();
        
        // borramos todas las tazas y tapas de las listas
        cups.clear();
        lids.clear();
        
        System.out.println("Simulador terminado");
        lastOperationOk = true;
    }
    
    /**
     * Nos dice si la última operación que hicimos funcionó o no
     */
    public boolean ok() {
        return lastOperationOk;
    }
    
    /**
     * Dibuja la escala de centímetros al lado de la torre
     * Son solo marcas sin números, como una regla
     */
    private void drawScale() {
        // limpiamos la escala anterior si existía
        for (Rectangle line : scale) {
            line.makeInvisible();
        }
        scale.clear();
        
        // solo dibujamos si está visible
        if (!isVisible) {
            return;
        }
        
        // dibujamos una marca por cada centímetro hasta 50 cm
        // (o hasta maxHeight si es menor)
        int maxCm = Math.min(maxHeight, 50);
        
        for (int cm = 0; cm <= maxCm; cm++) {
            Rectangle mark = new Rectangle();
            mark.changeColor("black");
            
            // las marcas de cada 5 cm son más largas para que sea más fácil contar
            if (cm % 5 == 0) {
                mark.changeSize(2, 15);  // marca larga
            } else {
                mark.changeSize(2, 8);   // marca corta
            }
            
            // posicionamos la marca al lado izquierdo de la torre
            // 150 es la posición horizontal (un poco a la izquierda de las tazas)
            // 400 es la base, restamos cm*10 para ir subiendo
            mark.moveHorizontal(150);
            mark.moveVertical(400 - (cm * 10));
            mark.makeVisible();
            
            // guardamos la marca en la lista
            scale.add(mark);
        }
    }
}