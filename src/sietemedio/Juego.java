/**************************
 * Siete y Media
 * Raguex, Ordoñez y Quinilla
 * 
 * Es un juego en donde se sumara siete y media para ganar.
 **************************/
package sietemedio;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


//***************************
// Esta clase  contiene una lista privada de las cartas y los jugadores, tambien el turno de cada uno.

public class Juego 
{
    private List<Carta> cartas;
    private List<Jugador> jugadores;
    private int jugadorEnTurno;
    
    //**************************

    public Juego() 
    {
        
        // Este constructor guarda las cartas y jugadores en un Arraylist 
        
        cartas = new ArrayList<>();
        jugadores = new ArrayList<>();
        iniciarJuego();

    }

// Este método inicia el juego, con una carpeta que contiene las cartas con diferentes imagenes.

    private void iniciarJuego() 
    {
        // Carpetas con las imagenes de las diferentes cartas
        File[] carpetas = {new File("cartas/corazon"), new File("cartas/picas"), new File("cartas/diamante"), new File("cartas/trebol")};

        for (File carpeta : carpetas) 
        {

            File[] ficherosImagen = carpeta.listFiles();

            //Por cada fichero de imagen agregar a la lista de cartas la carta con su valor
            for (File file : ficherosImagen) 
            {
                if (file.isFile()) 
                {
                    String nombreImagen = file.getName();
                    String ruta = carpeta + "/" + nombreImagen;
                    cartas.add(new Carta(ruta, getValorCarta(nombreImagen)));
                }
            }
        }

        jugadorEnTurno = 0;

    } // fin de iniciar juego
    
    //**********************
    
    // Este metodo el jugador obtiene otra carta.
    
    public Carta getProximaCarta() 
    {
        if (cartas.size() > 0) 
        {
            //tomar una carta de manera aleatoria
            Carta carta = cartas.get(ThreadLocalRandom.current().nextInt(0, cartas.size()));
            cartas.remove(carta);
            return carta;
        }
        return null;
    } // fin de getProximaCarta
    
    //**********************
    
    // Con este metodo el jugador obtiene su turno para jugar.
    
    public Jugador getJugadorEnTurno() 
    {
        return jugadores.get(jugadorEnTurno);
    }// fin de getJugadorEnTurno
    
    //**********************
    
    // Este método le dara el turno al proximo jugador, brinda un orden para jugar.
    
    public Jugador getProximoJugador() 
    {
        if (jugadorEnTurno < jugadores.size() - 1) 
        {
            jugadorEnTurno++;
        } 
        else 
        {
            jugadorEnTurno = 0;
        }

        return jugadores.get(jugadorEnTurno);
    }// fin de getJugadorEnTurno
    
     //***********************
    
    // Este método busca en la lista a los jugadores que han terminado de elegir carta
    // y ya no pueden elegir y la retorna.
    
    public boolean hanTerminadoTodosLosJugadores() 
    {
        return jugadores.stream().filter(jugador -> jugador.haTerminado()).count() == jugadores.size();
    }

    
    //**********************
    
    // Este método nos dice que el jugador en turno ya eligio carta y termino su turno.
    
    public boolean haTerminadoJugador() 
    {
        return jugadores.get(jugadorEnTurno).haTerminado();
    }

     //**************************
    public Jugador getGanador() {
         
        List<Jugador> lista = jugadores.stream().filter(jugador-> jugador.getValorTotalDeCartas()<= 7.5).collect(Collectors.toList());
        
        if(!lista.isEmpty()){
            Jugador ganador = lista.get(0);
            
            for(int i = 1; i < lista.size(); i++){
                //buscar el que este mas cerca de 7.5 y en caso que tengan los mismos puntos que tenga menos cartas
                if(lista.get(i).getValorTotalDeCartas()-7.5 < ganador.getValorTotalDeCartas()-7.5 && lista.get(i).getCartas().size() < ganador.getCartas().size()){
                    ganador = lista.get(i);
                }
                
            }
            return ganador;
        }
        
        return null;
       
    }
    
    // Este método nos retornara la lista de jugadores.
    
    public List<Jugador> getJugadores() 
    {
        return jugadores;
    }
    
    //***************************
    
    // Este método privado guarda el nombre de la carta y determina el valor  a cada diferente carta.

    private double getValorCarta(String nombreImagen) 
    {

        String nombre = nombreImagen.split(".png")[0];
        switch (nombre) 
        {
            case "A":                                         //caso 1
                return 1;
            case "J":                                         //caso 2
                return 0.5;
            case "Q":                                        //caso 3
                return 0.5;                                 
            case "K":                                         //caso 4
                return 0.5;
            default:                                           //caso 5
                return Double.parseDouble(nombre);
        }

    }// fin de getValorCarta

}// fin Class Juego