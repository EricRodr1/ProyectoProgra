
package proyectop1;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

// Clase principal que extiende JFrame para el juego de memoria
public class JuegoMemoria extends JFrame implements ActionListener {
    // Componentes de la interfaz gráfica
    JPanel panel;
    JLabel matriz[][], lblJugadores, lblTurno, lblfecha;
    int mat[][] = new int[4][5]; // Matriz para almacenar los valores de las cartas
    int mat2[][] = new int[4][5]; // Matriz para controlar el estado de las cartas
    Random ran; // Generador de nnmeros aleatorios
    int contador, ban, ban1, annum, anposx, anposy, acnum, acposx, acposy; // Variables para la logica del juego
    Timer espera, espera2; // Temporizadores para controlar tiempos
    int consegund; // Contador de segundos
    JButton reiniciar, salir; // Botones de accion
    
    // Variables para los jugadores
    String jugador1, jugador2;
    int puntajeJ1 = 0;
    int puntajeJ2 = 0;
    boolean turnoJ1 = true; // Controla de quien es el turno

    ManejoUsuario BD; // Referencia para la conexión con la base de datos

    // Constructor del juego
    public JuegoMemoria() {
        this.setTitle("Juego de Memoria"); // Titulo de la ventana
        this.setSize(1010, 720); // Tamaño de la ventana
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Configuracion de cierre
        this.setResizable(false); // Cambiar tamanno de la ventana

        panel = new JPanel(); // Panel principal
        this.getContentPane().add(panel);
        panel.setLayout(null); // Layout absoluto

        ran = new Random(); // Inicializacion del generador aleatorio
        this.numaleatorios(); // Llama a la función para llenar la matriz con numeros aleatorios

        matriz = new JLabel[4][5]; // Inicialización de la matriz de etiquetas
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel(); // Crea cada etiqueta
                matriz[i][j].setSize(matriz[i][j].getWidth(), matriz[i][j].getHeight());
                matriz[i][j].setBounds(350+(j*125),30+(i*156), 125, 156); // Posicion y tamaño
                matriz[i][j].setIcon(new ImageIcon("src/imagenes/"+mat2[i][j]+".JPG")); // Imagen inicial
                matriz[i][j].setVisible(true); // Hacer visible la etiqueta
                panel.add(matriz[i][j],0); // Agregar al panel
            }
        }

        // Configuración inicial de variables
        consegund = 0;
        espera = new Timer (1000, new ActionListener() { // Temporizador principal
            public void actionPerformed(ActionEvent e) {
                consegund++;
            }
        });
        espera.start();
        espera.stop();
        consegund = 0;
        ban=0;
        ban1=0;
        contador = 0;

        // Agrega eventos de clic a las etiquetas de la matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                if(e.getSource() == matriz[k][l]){ // Verifica cual carta fue clicada 
                                   if(mat2[k][l] == 0 && contador !=2){
                                       mat2[k][l] = mat[k][l]; // Voltea la carta
                                       matriz[k][l].setIcon(new ImageIcon("src/imagenes/"+mat2[k][l]+".JPG"));
                                       contador++;
                                       acnum = mat[k][l];
                                       acposx = k;
                                       acposy = l;
                                       if(contador == 1){
                                            annum = mat[k][l]; // Guarda la primera carta seleccionada
                                            anposx = k;
                                            anposy = l;
                                       }

                                       // Temporizador para manejar el tiempo entre selecciones
                                       espera2 = new Timer (500, new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                           if(contador == 2 && ban1 == 0){
                                               espera.restart();
                                               ban1=1;
                                            }
                                            if(contador == 2 && consegund == 2){ // Despues de 2 segundos
                                                espera.stop();
                                                consegund = 0;
                                                if(mat2[acposx][acposy] == mat2[anposx][anposy]){
                                                    // Coincidencia de las cartas encontrada
                                                    mat2[acposx][acposy] = -1;
                                                    mat2[anposx][anposy] = -1;
                                                    matriz[acposx][acposy].setIcon(new ImageIcon("src/imagenes/"+mat2[acposx][acposy]+".JPG"));
                                                    matriz[anposx][anposy].setIcon(new ImageIcon("src/imagenes/"+mat2[anposx][anposy]+".JPG"));
                                                    contador=0;

                                                    // Incrementa el puntaje del jugador en turno
                                                    if(turnoJ1){
                                                        puntajeJ1++;
                                                    } else {
                                                        puntajeJ2++;
                                                    }

                                                    // Verifica si el juego termino
                                                    int acum = 0;
                                                    for (int m = 0; m < 4; m++) {
                                                       for (int n = 0; n < 5; n++) {
                                                          if (mat2[m][n] == -1)
                                                              acum++;
                                                       }
                                                    }

                                                    if(acum == 20){ // Todas las cartas emparejadas
                                                        String ganador;
                                                        if(puntajeJ1 > puntajeJ2){
                                                            ganador = "Gano " + jugador1;
                                                        } else if (puntajeJ2 > puntajeJ1){
                                                            ganador = "Gano " + jugador2;
                                                        } else {
                                                            ganador = "Empate";
                                                        }
                                                        JOptionPane.showMessageDialog(panel, ganador); // Muestra el ganador
                                                    }

                                                } else {
                                                    // Cambia de turno si no coinciden
                                                    turnoJ1 = !turnoJ1;
                                                    actualizarTurno(); 
                                                }

                                                // Voltea todas las cartas no emparejadas
                                                for (int m = 0; m < 4; m++) {
                                                    for (int n = 0; n < 5; n++) {
                                                        if(mat2[m][n]!=0 && mat2[m][n]!=-1){
                                                            mat2[m][n] = 0;
                                                            matriz[m][n].setIcon(new ImageIcon("src/imagenes/"+mat2[m][n]+".JPG"));
                                                            contador=0;
                                                        }
                                                    }
                                                }
                                                espera2.stop();
                                                ban1=0;
                                            }
                                        }});
                                       if(ban == 0)
                                           espera2.start();
                                       ban = 1;
                                       if(contador == 2)
                                               espera2.restart();
                                   }    
                                }
                            }
                        }
                    }
                });
            }
        }

        // Inicialización de componentes adicionales
        componentes();
        lblfecha.setText(fecha());
    }

    // Metodos
    
    // set para obtener usuarios 
    public void setBD(ManejoUsuario BD) {
        this.BD = BD;
    }

    // asignacion de jugador 1 y 2 
    public void setJugadores(String j1, String j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        if (lblJugadores != null) {
            lblJugadores.setText("Jugadores: " + jugador1 + " vs " + jugador2);
        }
        actualizarTurno();
    }

    // Metodo para fecha 
    private String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    // numeros aleatorios para cambiar las cartas 
    private void numaleatorios() {
        int acumulador = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 5; j++){
                mat[i][j] = 0;
            }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = ran.nextInt(10)+1;
                do {
                    acumulador = 0;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                            if(mat[i][j]== mat[k][l]){
                                acumulador +=1;
                            }
                        }
                    }
                    if(acumulador == 3){
                        mat[i][j] = ran.nextInt(10)+1;
                    }
                } while(acumulador == 3);
            }
        }
    }

   // creacion de interfaz grafica
    private void componentes() {
        lblJugadores = new JLabel("Jugadores: " + (jugador1==null?"":jugador1) + " vs " + (jugador2==null?"":jugador2));
        lblJugadores.setBounds(40,40,300,40);
        lblJugadores.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        panel.add(lblJugadores);

        JLabel etiquetaFecha = new JLabel("Fecha: ");
        etiquetaFecha.setBounds(40,80,150,40);
        etiquetaFecha.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        panel.add(etiquetaFecha);

        lblfecha = new JLabel();
        lblfecha.setBounds(135,80,150,40);
        lblfecha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(lblfecha);

        lblTurno = new JLabel();
        lblTurno.setBounds(40,120,300,40);
        lblTurno.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        panel.add(lblTurno);

        reiniciar = new JButton("Reiniciar");
        reiniciar.setBounds(115,560,120,40);
        reiniciar.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        reiniciar.addActionListener(this);
        panel.add(reiniciar);

        salir = new JButton("Salir");
        salir.setBounds(115,610,120,40);
        salir.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        salir.addActionListener(this);
        panel.add(salir);
    }
    
    
    // Metodo para intercambiar turnos
    private void actualizarTurno(){
        if(lblTurno != null){
            if(turnoJ1){
                lblTurno.setText("Turno de: " + jugador1);
            } else {
                lblTurno.setText("Turno de: " + jugador2);
            }
        }
    }

    @Override
    
    // Boton Reincio para reiniciar el juego
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reiniciar) {
            
            reiniciarJuego();
        }

        if (e.getSource() == salir) {
        Menu ventana = new Menu();
        ventana.setBD(BD); 
        ventana.setVisible(true);
        this.setVisible(false);
        }
    }
    
    
    // procceso para el reinicio del juego
    private void reiniciarJuego() {
    
        numaleatorios(); // Genera nuevas posiciones para las cartas
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat2[i][j] = 0; // Volteamos todas las cartas
                matriz[i][j].setIcon(new ImageIcon("src/imagenes/" + mat2[i][j] + ".JPG"));
            }
        }

        // Reiniciamos las variables del juego
        puntajeJ1 = 0;
        puntajeJ2 = 0;
        turnoJ1 = true; // Reinicia al turno del jugador 1
        contador = 0;
        ban = 0;
        ban1 = 0;

        // Actualizamos la etiqueta del turno
        actualizarTurno();

        // Mostramos mensaje opcional
        JOptionPane.showMessageDialog(this, "El juego ha sido reiniciado.");
    }
    
    
    
}

