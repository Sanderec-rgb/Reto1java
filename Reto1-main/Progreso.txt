import java.util.Scanner;

public class Progreso {

    //DISTANCIA MAXIMA ENTRE LA TIERRA Y LOS PLANETAS

    static final int maximaDis = 5000;

    public static void main(String[] args) {

        //ARREGLO DE PLANETAS
        String [] planet = {
           "Júpiter", //1
            "Neptuno", //2
            "Venus", // 3
            "Marte", //4  
            "Saturno", //5 
            "Urano", //6
            "Mercurio", //7
        };

        //CREAR ENTRADA PARA RECIBIR LOS DATOS DEL USUARIO
        
        Scanner scanner = new Scanner(System.in);

        //ITERAR LISTA DE PLANETAS
        System.out.println("PLANETAS DESTINO");
        for(int u=0; u < planet.length; u++){
            System.out.println((u+1) + "." + planet[u]);
        }


        //USUARIO DEBE SELECCIONAR EL NUMERO DEL PLANETA QUE DESEA VISITAR
        System.out.println("INTRODUZCA EL NUMERO DEL PLANETA QUE DESEA VISITAR");
        int selection = scanner.nextInt();


        //VALIDAR SI LA ELECCION SELECCIONADA ES CORRECTA
        if (selection < 1 || selection > planet.length ) {
            System.out.println("OPCION NO VALIDA, VUELVALO A INTENTAR");
            scanner.close();
            return;
            
        }


        String selectPlanet = planet[selection-1];
        System.out.println("EL PLANETA SELECCIONADO FUE " +   selectPlanet);

        //DEFINIR TIEMPOS Y RECURSOS
        int initialRec= 100; //RECURSOS EN PORCENTAJE
        int restTime= 200; //TIEMPO NECESARIO PARA LLEGAR
        int progress= 0; //PORCENTAJE DE PROGRESO
        int rec= initialRec;

        //AVANCE DEL VIAJE
        while (progress<100 && rec>0 && restTime>0) {

            progress +=20; //AUMENTAR PROGRESO
            rec -=12; //RESTAR RECURSOS
            restTime -=17; //RESTAR TIEMPO

            
        }


        //ESTADO DEL VIAJE

        System.out.println("\nEstado del viaje  ");
        System.out.println(" Progreso "  +  progress + "%");
        System.out.println(" Recursos faltantes  "  +  rec + "%");
        System.out.println(" Tiempo restante  "  +  restTime  +  " minutos " );



        //RESULTADO
        if (progress>=100) {
            System.out.println("\n¡Has llegado " + selectPlanet );
            
        }else if (rec <=0) {
            System.out.println("\nTe quedaste sin recursos antes de llegar al planeta " +  selectPlanet);
                               
            
        }else if (restTime<=0 ) {
            System.out.println("\nEl tiempo se agoto y no has llegado a " + selectPlanet);
            
        }

        scanner.close();



       








        
    }
    
}
