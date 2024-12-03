/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.veterinaria;
import java.util.Scanner;
/**
 *
 * @author  
 */
public class Veterinaria {
    static datosan [] animalesatendidos = new datosan[50];
    static int contador =0;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner (System.in)) {
            int opcion;
            
            do{
                System.out.println("BIENVENIDO A LA VETERINARIA!");
                System.out.println("1. Registrar atencion");
                System.out.println("2. Mostrar resumen del dia");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 1 -> registro(sc);
                    case 2 -> resumen();
                    case 3 -> System.out.println("Saliendo del sistema");
                    default -> System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } while (opcion != 3);
        }
    }

    static void registro(Scanner scanner) {
        if (contador >= animalesatendidos.length) {
            System.out.println("No se pueden registrar mas atenciones hoy.");
            return;
        }

        System.out.print("Ingrese el tipo de animal (gato o perro): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese el tamano (pequeno, mediano, grande): ");
        String razaTamaño = scanner.nextLine();

        System.out.print("Ingrese el tipo de atencion (medica o estetica): ");
        String tipoAtencion = scanner.nextLine();

        animalesatendidos[contador] = new datosan(tipo, razaTamaño, tipoAtencion);
        contador++;

        System.out.println("Atencion registrada!");
    }

    static void resumen() {
        int gatosmedica = 0, gatosestetica = 0, perrosmedica = 0, perrosestetica = 0;

        for (int i = 0; i < contador; i++) {
            datosan animal = animalesatendidos[i];
            if (animal.tipo.equalsIgnoreCase("gato")) {
                if (animal.servicio.equalsIgnoreCase("médica")) {
                    gatosmedica++;
                } else if (animal.servicio.equalsIgnoreCase("estética")) {
                    gatosestetica++;
                }
            } else if (animal.tipo.equalsIgnoreCase("perro")) {
                if (animal.servicio.equalsIgnoreCase("médica")) {
                    perrosmedica++;
                } else if (animal.servicio.equalsIgnoreCase("estética")) {
                    perrosestetica++;
                }
            }
        }

        System.out.println(  "Resumen del dia" );
        System.out.println("Gatos con atencion medica: " + gatosmedica);
        System.out.println("Gatos con atencion estetica: " + gatosestetica);
        System.out.println("Perros con atencio medica: " + perrosmedica);
        System.out.println("Perros con atencion estetica: " + perrosestetica);
        System.out.println("Total atendidos: " + contador);
    }
}
        
    

