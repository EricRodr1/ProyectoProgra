package trabajo8;

import javax.swing.*;

public class Trabajo8 {
    private static final int MAX_JUGADORES = 10; 
    private static final int DATA_COLUMNS = 4;  
    private static final String[][] jugadores = new String[MAX_JUGADORES][DATA_COLUMNS];
    private static String USUARIO = null; 

    public static void main(String[] args) {
        while (true) {
            String[] options = {"Iniciar Sesión", "Registro", "Jugar X-0", "Ranking", "Cerrar Sesión", "Salir"};
            int choice = JOptionPane.showOptionDialog(null, "Menú Inicio", "Menú",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            switch (choice) {
                case 0 -> iniciosesion();
                case 1 -> registro();
                case 2 -> TicTacToe();
                case 3 -> Ranking();
                case 4 -> salir();
                case 5 -> {
                    JOptionPane.showMessageDialog(null, "Gracias por usar el programa");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void iniciosesion() {
        String username = JOptionPane.showInputDialog("Ingrese su username:");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña:");

        for (String[] player : jugadores) {
            if (player[1] != null && player[1].equals(username) && player[2].equals(password)) {
                USUARIO = username;
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenido " + player[0] + "!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, " Usuario Incorrecto");
    }

    private static void registro() {
        String username = JOptionPane.showInputDialog("Ingrese un username:");

         
        for (String[] player : jugadores) {
            if (player[1] != null && player[1].equals(username)) {
                JOptionPane.showMessageDialog(null, "El username ya existe.");
                return;
            }
        }

        String password = JOptionPane.showInputDialog("Ingrese una contraseña (5 caracteres):");
        if (password.length() != 5) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres.");
            return;
        }

        String name = JOptionPane.showInputDialog("Ingrese su nombre:");

        
        for (String[] jugadore : jugadores) {
            if (jugadore[1] == null) {
                jugadore[0] = name;
                jugadore[1] = username;
                jugadore[2] = password;
                jugadore[3] = "0";  
                JOptionPane.showMessageDialog(null, "Registro exitoso. ¡Bienvenido ");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No se pueden registrar más jugadores.");
    }

    private static void TicTacToe() {
        if (USUARIO == null) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión primero.");
            return;
        }

        String opponentUsername = JOptionPane.showInputDialog("Ingrese el username del jugador 2 (o SALIR para cancelar):");
        if ("SALIR".equalsIgnoreCase(opponentUsername)) {
            JOptionPane.showMessageDialog(null, "Juego cancelado. Regresando al menú principal.");
            return;
        }

        String[] opponent = usuariojugadores(opponentUsername);
        if (opponent == null) {
            JOptionPane.showMessageDialog(null, "Jugador no registrado. Inténtelo de nuevo.");
            return;
        }

        TicTacToe game = new TicTacToe(USUARIO, opponent[1], jugadores);
        game.jugar();
    }

    private static void Ranking() {
        
        String[][] arreglarjug = puntosjugadores(jugadores);

        StringBuilder ranking = new StringBuilder("Ranking de Jugadores:\n");
        for (String[] juador : arreglarjug) {
            if (juador[1] != null) {  
                ranking.append(juador[0]).append(" - ").append(juador[3]).append(" puntos\n");
            }
        }
        JOptionPane.showMessageDialog(null, ranking.toString());
    }

    private static void salir() {
        if (USUARIO != null) {
            JOptionPane.showMessageDialog(null, "Cerrando sesión de " + USUARIO);
            USUARIO = null;
        } else {
            JOptionPane.showMessageDialog(null, "No hay sesión iniciada.");
        }
    }

    private static String[] usuariojugadores(String username) {
        for (String[] player : jugadores) {
            if (player[1] != null && player[1].equals(username)) {
                return player;
            }
        }
        return null;
    }

    private static String[][] puntosjugadores(String[][] players) {
        String[][] sorted = players.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[i][1] != null && sorted[j][1] != null &&
                        Integer.parseInt(sorted[i][3]) < Integer.parseInt(sorted[j][3])) {
                    String[] temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }
        return sorted;
    }
}
