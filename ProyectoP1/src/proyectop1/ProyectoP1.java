/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectop1;

/**
 *
 * @author enigm
 */
public class ProyectoP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ManejoUsuario BD = new ManejoUsuario();
        Login login = new Login();
        login.setBD(BD); 
        login.setVisible(true);
    }
    
}
