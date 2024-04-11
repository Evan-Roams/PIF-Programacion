// Carlos Esteban Pinzón Montealegre
//
//
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner Teclado = new Scanner(System.in);

        // Main - Calculadora turnos por hora
        System.out.println("---CALCULADOR DE TURNO SEGÚN HORA---");
            byte hora;
            byte minutos;
            byte turno;

            System.out.print("Escriba su Hora (de 0 a 22):");
            hora = Teclado.nextByte();
            if (hora<23){
                System.out.println("Escrba sus Minutos (de 0 a 59): ");
                minutos = Teclado.nextByte();
                if (minutos<60){

                    turno = determinar_turno(hora, minutos);
                    System.out.println("Su turno es el: "+(turno));
                } else {
                    System.out.print("introduzca unos minutos válidos (de 0 a 59)");
                }
            } else {
               System.out.print("introduzca una hora válida (de 0 a 22)");
            }


        // Main - Calculadora turnos por hora
        System.out.println("---CALCULADOR DE TURNO SEGÚN HORA---");

        int reservas[][] = new int[68][50];
        int copiar_reservas[][] = new int[68][50];


    }

    // Método para determinar el turno según la hora
    static byte determinar_turno (byte hora, byte minutos) {
        byte turno = 0;

        // el "hora-7" es debido a que el primer turno inicia a las 6:00 y como se cuenta
        // desde 0 en el for, pues se le añade una unidad más quedand 6+1=7
        for (byte i = 0; i<=(hora-7); i++){
            turno+=4;
            if (i==(hora-7)){
                for (byte x = 15; x<=minutos; x+=15){
                    turno++;
                }
            }
        }
        return turno;
    }

    // Método para copiar una matriz
    static int[][] copiar_reservas (int[][] reservas) {
        int copiar_reservas[][] = new int[68][50];

        for ()

        return copiar_reservas;
    }


}