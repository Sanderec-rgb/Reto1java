
import java.util.Scanner;
public class distancia {

    public static void main(String[] args) {
        /*PARA ESTA SEGUNDA PARTE DEL TRABAJO QUE HAY QUE CALCULAR DISTANCIA HAY QUE TENER EN CUENTA QUE DISTANCIA= VELOCIDAD*TIEMPO
         * LOS DATOS DISTANCIA SE DARAN EN MILLONES DE KM
         */

         Scanner scanner = new Scanner(System.in);

         String [] Plan = {

            "Mercurio",  "Marte", "Venus", "Júpiter", "Urano", "Neptuno", "Saturno",

        };
        double [] distancia = {

            4351e6, //Neptuno
            91.7e6, //Mercurio
            78.3e6, //Marte
            628.7e6, //Jupiter
            41.4e6, //Venus
            1275e6, //Saturno
            2723e6 //Urano

        };

        //Recorrer los planetas
        System.out.println("Planetas de destino");
        for(int i= 0; i<Plan.length; i++){
        System.out.println((i+1) + "." + Plan[i]);

    }

        //Recibir eleccion usuario
        System.out.println("Cual es tu planeta de destino");
        String planetaSel= scanner.nextLine().trim().toLowerCase();



        //Variable distancia
        double distancia2=-1;

        //Recorrer distancias
        for (int i = 0; i < Plan.length; i++) {
            if (Plan[i].toLowerCase().equals(planetaSel)) {
                distancia2 = distancia[i];
                break;

            }
        }

         // Verificar si el planeta es valido
         if (distancia2 == -1) {
            System.out.println("El planeta '" + planetaSel + "' no es válido.");
        } else {
            // Pedir al usuario la velocidad
            System.out.print("Introduce la velocidad de viaje (en km/h): ");
            double velocidad = scanner.nextDouble();

            // Calcular el tiempo de viaje en horas
            double tiempoViaje = distancia2 / velocidad;

            // Convertir el tiempo de horas a días (1 día = 24 horas)
            double tiempoDias = tiempoViaje / 24;

            // Imprimir el resultado
            System.out.println("Distancia entre la Tierra y " + planetaSel + ": " + distancia2 / 1e6 + " millones de km");
            System.out.println("Tiempo estimado de viaje a " + planetaSel + " a " + velocidad + " km/h: " + tiempoViaje + " horas");
            System.out.println("Lo que equivale a: " + tiempoDias + " días.");
        }

        scanner.close();
    }
    
}
