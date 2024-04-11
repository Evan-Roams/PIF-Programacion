// Carlos Esteban Pinzón Montealegre
//
//

import javax.swing.text.Document;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner Teclado = new Scanner(System.in);
        Random rand = new Random();
        int Programa;

        //Buses y Asientos
        int Buses = 68;
        int Asientos = 50;
        byte Cupos;

        //Turnos y Horas  y Documentos
        byte Hora;
        byte Minutos;
        byte Turno;
        int Documento;

        //Reservas
        int Documentos[];
        int reservas[][] = new int[Buses][Asientos];
        int copia_reservas[][];
        int reservar_cupos[][] = new int[reservas.length][reservas[0].length];
        byte Reservas_Usuario[];
        copia_reservas = copiar_reservas(reservas);


        System.out.println("-----Programas / Tareas-----");
        System.out.println("1-Calculadora de Turno según hora");
        System.out.println("2-Copiadora de Matrices");
        System.out.println("3-Contadora de Cupos");
        System.out.println("4-Turnos de Buses de Usuario");
        System.out.println("5- Busqueda de asientos para reservas");
        System.out.print("Que desea hacer? (digite el numero del programa o tarea:");
        Programa = Teclado.nextInt();
        System.out.println("");


        if (Programa==1){
            // Main - Calculadora turnos por hora
            System.out.println("---CALCULADOR DE TURNO SEGÚN HORA---");
            System.out.print("Escriba su Hora (de 0 a 22):");
            Hora = Teclado.nextByte();
            if (Hora<23){
                System.out.println("Escriba sus Minutos (de 0 a 59): ");
                Minutos = Teclado.nextByte();
                if (Minutos<60){

                    Turno = determinar_turno(Hora, Minutos);
                    System.out.println("Su turno es el: "+(Turno));
                } else {
                    System.out.print("introduzca unos minutos válidos (de 0 a 59)");
                }
            } else {
                System.out.print("introduzca una hora válida (de 0 a 22)");
            }


        } else if (Programa==2){
            // Main - Calculadora turnos por hora
            System.out.println("---COPIADORA DE MATRICES---");

            System.out.println("Matriz a Copiar");

            for (int i = 0; i<reservas.length; i++){
                for (int x=0; x<reservas[0].length; x++){
                    System.out.print(" "+reservas[i][x]);
                }
                System.out.println(" ");
            }
            System.out.println(" ");

            System.out.println("Matriz Copiada");
            for (int i = 0; i<copia_reservas.length; i++){
                for (int x=0; x<copia_reservas[0].length; x++){
                    System.out.print(" "+copia_reservas[i][x]);
                }
                System.out.println(" ");
            }


        } else if (Programa==3){
            // Main - Contadora de Cupos
            System.out.println("---CONTADORA DE CUPOS---");

            System.out.print("Escriba su Hora (de 0 a 22):");
            Hora = Teclado.nextByte();
            if (Hora<23){
                System.out.println("Escriba sus Minutos (de 0 a 59): ");
                Minutos = Teclado.nextByte();
                if (Minutos<60){

                    Turno = determinar_turno(Hora, Minutos);
                    Cupos = contar_cupos_bus(Hora, Minutos, reservas);
                    System.out.println("Quedan "+Cupos+" Asientos disponibles para el turno "+Turno+".");
                } else {
                    System.out.print("Introduzca unos minutos válidos (de 0 a 59)");
                }
            } else {
                System.out.print("Introduzca una hora válida (de 0 a 22)");
            }

        } else if (Programa==4) {
            // Main - Busqueda de reservas
            System.out.println("---BÚSQUEDA DE RESERVAS---");

            System.out.print("Introduzca su Número de Documento:");
            Documento = Teclado.nextInt();
            System.out.println(" ");

            Reservas_Usuario = buscar_reservas(Documento, reservas );
            System.out.print("Los turnos en los que está el usuarios son: [|");
            for (byte i = 0; i< Reservas_Usuario.length; i++){
                System.out.print(Reservas_Usuario[i]+"|");
            }
            System.out.print("] ");


        } else if (Programa==5) {
            // Main - Reserva de Asientos
            System.out.println("---RESERVA DE ASIENTOS---");

            System.out.print("Escriba su Hora (de 0 a 22):");
            Hora = Teclado.nextByte();
            if (Hora<23){
                System.out.print("Escriba sus Minutos (de 0 a 59): ");
                Minutos = Teclado.nextByte();
                System.out.println("");

                System.out.print("Escriba cuántos documentos quiere inscribir: ");
                int Inscribir = Teclado.nextInt();
                Documentos = new int[Inscribir];

                for (int i = 0; i< Documentos.length; i++){
                    Documentos[i] = rand.nextInt(2147483647);
                }

                reservar_cupos = reservar_cupos(Hora, Minutos, reservas, Documentos);
            } else {
                System.out.print("Introduzca una hora válida (de 0 a 22)");
            }


            for (int i = 0; i < reservar_cupos.length; i++){
                for (int x = 0; x < reservar_cupos[0].length; x++){
                    System.out.print(" "+reservar_cupos[i][x]);
                }
                System.out.println(" ");
            }


        }


    }

    // Método para determinar el turno según la hora
    static byte determinar_turno (byte hora, byte minutos) {
        byte turno = 0;

        // el "hora-7" es porque a que el primer turno inicia a las 6:00 y como se cuenta
        // desde 0 en el for, pues se le añade una unidad más quedand 6+1=7
        for (byte i = 0; i<=(hora-7); i++){
            turno+=4;
                for (byte x = 15; i==(hora-7) && x<=minutos; x+=15){
                    turno++;
                }
        }
        return turno;
    }

    // Método para copiar una matriz
    static int[][] copiar_reservas (int[][] reservas) {
        int copiar_reservas[][] = new int[reservas.length][reservas[0].length];

        // Mediante dos ciclos se recorre la nueva matriz y se va reemplazando sus
        // filas y columnas con las filas y columnas de la matriz original.
        for (int i = 0; i<reservas.length; i++){
            for (int x = 0; x<reservas[0].length; x++){
                copiar_reservas[i][x] = reservas[i][x];
            }
        }

        return copiar_reservas;
    }

    // Método para cupos libres restantes en un bus
    static byte contar_cupos_bus(byte hora, byte minutos, int[][] reservas) {
        byte cupos = 0;
        byte turno = determinar_turno(hora, minutos);

        for (byte i = 0; i < reservas[0].length; i++){
            if (reservas[turno][i] == 0){
                cupos++;
            }
        }


        return cupos;
    }

    // Método para buscar las reservas de un Usuario
    static byte[] buscar_reservas(int documento, int[][] reservas) {

        byte reservas_usuario[] = new byte[reservas.length];

        for (byte i = 0; i<reservas.length; i++){
            for (byte x = 0; x<reservas[0].length; x++){
                if (reservas[i][x] == documento){
                    reservas_usuario[i] = i;

                }
            }
        }

        return reservas_usuario;
    }


    // Método para reservas cupos
    static int[][] reservar_cupos(byte hora, byte minutos, int[][]reservas, int[] documentos){

        int turno = determinar_turno(hora, minutos);
        byte cupos = contar_cupos_bus(hora, minutos, reservas);

        int [][] reservar_cupos = new int[reservas.length][reservas[0].length];

        if (cupos >= documentos.length){
                for (int x=0; x < documentos.length; x++){
                        reservar_cupos[turno][x] = documentos[x];

                }

        } else {

            for (byte i=0; i< reservas.length; i++){

                for (byte x=0; x<reservas[0].length; x++){
                reservar_cupos[i][x] = reservas[i][x];
            }
        }
    }



    return reservar_cupos;

    }





}