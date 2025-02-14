import java.util.ArrayList;
import java.util.Scanner;

public class lista {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //CREAR LISTA
        ArrayList<String>Lista = new ArrayList<>();
        
        Lista.add("Jupiter");
        Lista.add("Mercurio");
        Lista.add("Urano");
        Lista.add("Neptuno");
        Lista.add("Marte");
        Lista.add("Venus");
        Lista.add("Pluton");
        Lista.add("Saturno");

        //Desplegar planetas opcionados

        System.out.println("Escriba su planeta de destino");
        for (int i = 0; i < Lista.size(); i++) {
            System.out.println((i + 1) + ". " + Lista.get(i));
        }
         // Opciones

        System.out.println("Seleccione su planeta de destino");
        String planetaDestino = scanner.nextLine().trim().toLowerCase();

        //Verificar seleccion
        boolean valor = false;
        for(String seleccion : Lista){
            if (seleccion.toLowerCase().equals(planetaDestino)) {
                System.out.println("Tu planeta de destino es "  + seleccion);
                valor = true;
                break;
                
            }


        }

        if (!valor) {
            System.out.println("El planeta que selecciono para viajar con destino a "  + planetaDestino + "  no esta dentro de nuestros destinos actuales  ");
            
        }
        scanner.close();

        
    }
}

    
