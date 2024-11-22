import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToe {
    private static final JButton[][] buttons = new JButton[3][3];
    private static boolean xTurn = true;
    private static String playerX;
    private static String playerO;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));
         
        
        playerX = JOptionPane.showInputDialog("Favor ingresar Jugador X:");
        playerO = JOptionPane.showInputDialog("Favor ingresar Jugador 0:");
        
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener((ActionEvent e) -> {
                    JButton buttonClicked = (JButton) e.getSource();
                    if (buttonClicked.getText().equals("")) {
                        buttonClicked.setText(xTurn ? "X" : "O");
                        xTurn = !xTurn;
                        ganador();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Esta celda ya fue seleccionada");
                    }
                });
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private static void ganador() {
        for (int i = 0; i < 3; i++) {
            if (fila(i) || columna(i)) {
                mostrarganador(xTurn ? playerO : playerX);
                return;
            }
        }
        if (diagonal()) {
            mostrarganador(xTurn ? playerO : playerX);
        } else if (tablerolleno()) {
            JOptionPane.showMessageDialog(null, "Ronda Empatada");
        }
    }

    private static boolean fila(int row) {
        return buttons[row][0].getText().equals(buttons[row][1].getText()) &&
               buttons[row][0].getText().equals(buttons[row][2].getText()) &&
               !buttons[row][0].getText().equals("");
    }

    private static boolean columna(int col) {
        return buttons[0][col].getText().equals(buttons[1][col].getText()) &&
               buttons[0][col].getText().equals(buttons[2][col].getText()) &&
               !buttons[0][col].getText().equals("");
    }

    private static boolean diagonal() {
        return (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) ||
               (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals(""));
    }

    private static boolean tablerolleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void mostrarganador(String winner) {
        JOptionPane.showMessageDialog(null, "El ganador es " + winner);
        reinicio();
    }

    private static void reinicio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true;
    }
}
