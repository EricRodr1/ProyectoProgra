/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo8;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 *  
 */
public class TicTacToe {
    private final char[][] tablero;
    private final String jugador1 ;
    private final String jugador2 ;
    private final String[][] jugadores;

    public TicTacToe(String jugador1Username, String jugador2Username, String[][] jugadores) {
        this.tablero = new char[3][3];
        this.jugador1  = jugador1Username;
        this.jugador2  = jugador2Username;
        this.jugadores = jugadores;
    }

    public void jugar() {
        char jugadorActual = 'X';
        String nombreJugadorActual = jugador1 ;

        while (true) {
            String estadoTablero = imprimirTablero();
            String entrada = JOptionPane.showInputDialog(null, estadoTablero + "\n" +
                    "Turno de " + nombreJugadorActual + " (" + jugadorActual + ")\n" +
                    "Ingrese la fila y columna (ejemplo: 1,1):");

            if (entrada == null) return;  
            String[] partes = entrada.split(",");
            try {
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);

                if (fila < 1 || fila > 3 || columna < 1 || columna > 3 || tablero[fila - 1][columna - 1] != '\u0000') {
                    JOptionPane.showMessageDialog(null, "Movimiento inválido. Inténtelo nuevamente.");
                    continue;
                }

                tablero[fila - 1][columna - 1] = jugadorActual;

                if (verificarVictoria(jugadorActual)) {
                    JOptionPane.showMessageDialog(null, imprimirTablero() + "\n¡" + nombreJugadorActual + " ha ganado!");
                    actualizarPuntaje(nombreJugadorActual);
                    return;
                }

                if (tableroLleno()) {
                    JOptionPane.showMessageDialog(null, imprimirTablero() + "\n¡Empate!");
                    return;
                }

                 
                jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
                nombreJugadorActual = (nombreJugadorActual.equals(jugador1 )) ? jugador2  : jugador1 ;
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Asegúrese de ingresar en el formato correcto (ejemplo: 1,1).");
            }
        }
    }

    private String imprimirTablero() {
        StringBuilder sb = new StringBuilder("Tablero actual:\n");
        for (char[] fila : tablero) {
            for (char celda : fila) {
                sb.append(celda == '\u0000' ? '-' : celda).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private boolean verificarVictoria(char jugador) {
         
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) return true;
            if (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador) return true;
        }
        
        return (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) ||
               (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador);
    }

    private boolean tableroLleno() {
        for (char[] fila : tablero) {
            for (char celda : fila) {
                if (celda == '\u0000') return false;
            }
        }
        return true;
    }

    private void actualizarPuntaje(String username) {
        for (String[] jugador : jugadores) {
            if (jugador[1] != null && jugador[1].equals(username)) {
                jugador[3] = String.valueOf(Integer.parseInt(jugador[3]) + 1);
                break;
            }
        }
    }
}

