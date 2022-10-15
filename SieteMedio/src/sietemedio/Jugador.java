/******************************************************************************
 * Siete y Media
 * Raguex, Ordoñez y Quinilla
 * 
 * Es un juego en donde se sumara siete y media para ganar.
 ******************************************************************************/

package sietemedio;

import java.util.ArrayList;
import java.util.List;


//*********************************************************************************
// Esta clase guarda el nombre del jugador como un string y las cartas las guarda en una lista
//y un booleano en donde nos indica que ha terminado.

public class Jugador 
{
    
      private String nombre;
      private List<Carta> cartas;
      private boolean haTerminado;

    //******************************************************************************
   
    public Jugador(String nombre) 
    {
        
        // este constructor guarda los objetos carta en un Arraylist
        cartas = new ArrayList<>();
        haTerminado = false;
        this.nombre = nombre;
    }
    
    //******************************************************************************
    
    // Este método obtiene muestra la carta que eligio el jugador.
    
    public List<Carta> getCartas() 
    {
        return cartas;
    }

    //********************************************************************************
    
    // Este método retorna el valor de las cartas y si es mayor a 7.5 nos dice que el jugador ya termino
    // porque se ha pasado de 7 y medio.
    
    public boolean haTerminado() 
    {
        return getValorTotalDeCartas() > 7.5 || haTerminado;
    }
    
    //******************************************************************************
    
    // Este método establece que si el resultado es mayor a 7.5 el jugador ha terminado y no puede
    // seguir.
    
    public void setHaTerminado(boolean haTerminado) 
    {
        this.haTerminado = haTerminado;
    }
  
    //******************************************************************************
    
    // Este método obtiene el nombre del jugador y nos retorna el nombre del mismo.
    
    public String getNombre() 
    {
        return nombre;
    }// fin getNombre
    
    
    //******************************************************************************
    
    // Este método da un valor total de las cartas que eligio cada jugador.
    
    
    public double getValorTotalDeCartas()
    {
        return cartas.stream().mapToDouble(carta-> carta.getValor()).sum();
    }// fin getValorTotalDeCartas

}// fin de la clase Jugador