package servidorchat;

import java.util.Observable;

/**
 * Objeto observable del patron observer.
 * 
 * @author Ivan Salas Corrales <http://programando-o-intentandolo.blogspot.com.es/>
 */
public class TratamientoEntradas extends Observable{

    private String entradas;
    
    public TratamientoEntradas(){
    }
    
    public String getMensaje(){
        return entradas;
    }
    
    public void setEntrada(String entradas){
        this.entradas = entradas;
        // Indica que el mensaje ha cambiado
        this.setChanged();
        // Notifica a los observadores que el mensaje ha cambiado y se lo pasa
        // (Internamente notifyObservers llama al metodo update del observador)
        this.notifyObservers(this.getMensaje());
    }
}
