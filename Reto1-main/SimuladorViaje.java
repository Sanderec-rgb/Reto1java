import java.util.Scanner;
import java.util.Random;

public class SimuladorViaje {
    private static final Planeta[] planetas = {
        new Planeta("Mercurio", 91.7, "El planeta más cercano al sol."),
        new Planeta("Venus", 41.4, "El planeta más parecido a la Tierra."),
        new Planeta("Marte", 225, "El planeta rojo."),
        new Planeta("Júpiter", 778, "El gigante gaseoso."),
        new Planeta("Saturno", 1427, "Famoso por sus anillos."),
        new Planeta("Urano", 2723, "Un gigante helado."),
        new Planeta("Neptuno", 4351, "El planeta más lejano del sol.")
    };

    private static final NaveEspacial[] naves = {
        new NaveEspacial("Nave A", 100000, 5),
        new NaveEspacial("Nave B", 150000, 10)
    };

    private static final double FACTOR_COMBUSTIBLE = 10; // Litros por 100 km
    private static final double FACTOR_OXIGENO = 5; // Litros por hora por pasajero

    private static double combustibleNecesario;
    private static double oxigenoNecesario;
    private static int pasajeros;
    private static NaveEspacial naveSeleccionada;
    private static double distanciaPlanetaSeleccionado;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mostrarMenu(scanner);
        scanner.close();
    }

    private static void mostrarMenu(Scanner scanner) {
        int opcion;

        do {
            System.out.println("\n--- Menú del Simulador de Viaje ---");
            System.out.println("1. Seleccionar un planeta de destino");
            System.out.println("2. Seleccionar una nave espacial");
            System.out.println("3. Revisar y ajustar recursos");
            System.out.println("4. Iniciar la simulación del viaje");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar la entrada no válida
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    seleccionarPlaneta(scanner);
                    break;
                case 2:
                    seleccionarNave(scanner);
                    break;
                case 3:
                    ajustarRecursos(scanner);
                    break;
                case 4:
                    if (naveSeleccionada == null || distanciaPlanetaSeleccionado == 0) {
                        System.out.println("Por favor, seleccione un planeta y una nave antes de iniciar la simulación.");
                    } else {
                        calcularRecursos(distanciaPlanetaSeleccionado);
                        simularViaje(scanner);
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void seleccionarPlaneta(Scanner scanner) {
        System.out.println("Seleccione un planeta:");
        for (int i = 0; i < planetas.length; i++) {
            System.out.println((i + 1) + ". " + planetas[i].nombre + " - Distancia: " + planetas[i].distancia + " millones de km");
        }

        int seleccion = -1;
        while (seleccion < 0 || seleccion >= planetas.length) {
            System.out.print("Ingrese el número del planeta: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
            seleccion = scanner.nextInt() - 1;

            if (seleccion >= 0 && seleccion < planetas.length) {
                System.out.println("Has seleccionado: " + planetas[seleccion].nombre);
                System.out.println("Descripción: " + planetas[seleccion].descripcion);
                distanciaPlanetaSeleccionado = planetas[seleccion].distancia;
            } else {
                System.out.println("Selección no válida. Intente de nuevo.");
            }
        }
    }

    private static void seleccionarNave(Scanner scanner) {
        System.out.println("Seleccione una nave:");
        for (int i = 0; i < naves.length; i++) {
            System.out.println((i + 1) + ". " + naves[i].nombre + " - Velocidad: " + naves[i].velocidad + " km/h");
        }
    
        int seleccion = -1;
        while (seleccion < 0 || seleccion >= naves.length) {
            System.out.print("Ingrese el número de la nave: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar la entrada no válida
            }
            seleccion = scanner.nextInt() - 1;
    
            if (seleccion >= 0 && seleccion < naves.length) {
                naveSeleccionada = naves[seleccion];
                System.out.println("Has seleccionado: " + naveSeleccionada.nombre);
                System.out.print("Ingrese la cantidad de pasajeros: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número válido.");
                    scanner.next(); // Limpiar la entrada no válida
                }
                pasajeros = scanner.nextInt();
    
                if (pasajeros > naveSeleccionada.capacidadPasajeros) {
                    System.out.println("La cantidad de pasajeros excede la capacidad de la nave. Intente de nuevo.");
                    seleccion = -1; // Reiniciar la selección de la nave
                }
            } else {
                System.out.println("Selección no válida. Intente de nuevo.");
            }
        }
    }

    private static void calcularRecursos(double distancia) {
        double distanciaKm = distancia * 1_000_000;
        combustibleNecesario = (distanciaKm / 100) * FACTOR_COMBUSTIBLE;
        double tiempoViajeHoras = distanciaKm / naveSeleccionada.velocidad;
        oxigenoNecesario = tiempoViajeHoras * pasajeros * FACTOR_OXIGENO;
        System.out.println("Combustible necesario: " + combustibleNecesario + " litros");
        System.out.println("Oxígeno necesario: " + oxigenoNecesario + " litros");
    }

    private static void ajustarRecursos(Scanner scanner) {
        System.out.println("Recursos actuales:");
        System.out.println("Combustible necesario: " + combustibleNecesario + " litros");
        System.out.println("Oxígeno necesario: " + oxigenoNecesario + " litros");

        int ajusteCombustible;
        do {
            System.out.print("¿Desea ajustar el combustible? (1: Sí, 2: No): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido (1 o 2).");
                scanner.next();
            }
            ajusteCombustible = scanner.nextInt();
            if (ajusteCombustible != 1 && ajusteCombustible != 2) {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (ajusteCombustible != 1 && ajusteCombustible != 2);

        if (ajusteCombustible == 1) {
            System.out.print("Ingrese la nueva cantidad de combustible: ");
            combustibleNecesario = scanner.nextDouble();
        }

        int ajusteOxigeno;
        do {
            System.out.print("¿Desea ajustar el oxígeno? (1: Sí, 2: No): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido (1 o 2).");
                scanner.next();
            }
            ajusteOxigeno = scanner.nextInt();
            if (ajusteOxigeno != 1 && ajusteOxigeno != 2) {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (ajusteOxigeno != 1 && ajusteOxigeno != 2);

        if (ajusteOxigeno == 1) {
            System.out.print("Ingrese la nueva cantidad de oxígeno: ");
            oxigenoNecesario = scanner.nextDouble();
        }
    }

    private static void simularViaje(Scanner scanner) {
        System.out.println("Iniciando la simulación del viaje...");
        Random random = new Random();
        double distanciaRecorrida = 0;
        double distanciaTotal = distanciaPlanetaSeleccionado * 100; // Convertir a kilómetros
        int hora = 0;
        while (distanciaRecorrida < distanciaTotal) { // Ciclo principal
            hora++;
            double distanciaRestante = distanciaTotal - distanciaRecorrida;
            double distanciaAvance = Math.min(naveSeleccionada.velocidad, distanciaRestante);
            distanciaRecorrida += distanciaAvance;
    
            System.out.println("Hora " + hora + ": Distancia recorrida: " + distanciaRecorrida + " km");
    
            if (random.nextInt(10) < 2) { // 20% de probabilidad de un evento
                int evento = random.nextInt(2);
                if (evento == 0) {
                    System.out.println("¡Fallo en el sistema! Realizando reparaciones...");
                    try {
                        Thread.sleep(2000); // Simula el tiempo de reparación
                    } catch (InterruptedException e) {
                        System.out.println("Reparaciones interrumpidas.");
                    }
                    System.out.println("Reparaciones completadas.");
                } else {
                    System.out.println("Desvío en el camino. Ajustando rumbo...");
                    try {
                        Thread.sleep(2000); // Simula el tiempo de ajuste de rumbo
                    } catch (InterruptedException e) {
                        System.out.println("Ajuste de rumbo interrumpido.");
                    }
                    System.out.println("Rumbo ajustado.");
                }
            }
        }
    
        System.out.println("¡Llegaste a tu destino!");
        System.out.println("Presione Enter para volver al menú principal...");
        scanner.nextLine(); // Limpiar el buffer
        scanner.nextLine(); // Esperar a que el usuario presione Enter
    }
}

class Planeta {
    String nombre;
    double distancia; // en millones de km
    String descripcion;

    Planeta(String nombre, double distancia, String descripcion) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.descripcion = descripcion;
    }
}

class NaveEspacial {
    String nombre;
    double velocidad; // en km/h
    int capacidadPasajeros;

    NaveEspacial(String nombre, double velocidad, int capacidadPasajeros) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.capacidadPasajeros = capacidadPasajeros;
    }
}