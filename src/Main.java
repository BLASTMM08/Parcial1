import java.util.Scanner;
import java.util.HashMap;
import java.util.Map; // Importa Map para mayor claridad en las declaraciones de HashMap

/**
 * Una aplicación de consola simple para la gestión de inventario de zapatos.
 * Permite agregar zapatos, ver el stock disponible, realizar ventas, agregar stock,
 * y ver la información del inventario. Incluye una funcionalidad para duplicar el
 * inventario inicial de un zapato si su stock llega a cero.
 */
public class Main {
    /**
     * Método principal para ejecutar la aplicación de gestión de inventario de zapatos.
     * Presenta un menú al usuario y realiza acciones basadas en su elección,
     * gestionando el stock de zapatos utilizando HashMaps.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Inicializa un objeto Scanner para leer la entrada del usuario desde la consola.
        Scanner scanner = new Scanner(System.in);
        // HashMap para almacenar el inventario actual de zapatos (nombre del zapato -> stock actual).
        Map<String, Short> shoesInformation = new HashMap<>();
        // HashMap para almacenar la cantidad de inventario inicial para cada zapato (nombre del zapato -> stock inicial).
        // Esto se utiliza para la lógica de duplicación de inventario cuando el stock se agota.
        Map<String, Short> shoesStartingInventory = new HashMap<>();

        // Declara variables utilizadas para la entrada del usuario y las operaciones.
        String shoeName;
        short shoeAmount;
        short shoeAmountToAdd;
        short sellAmount;
        byte choice;

        // Bucle principal de la aplicación: continúa hasta que el usuario elige salir (opción 6).
        do {
            // Muestra las opciones del menú principal al usuario.
            System.out.println("-------------- MENÚ --------------");
            System.out.println("1. Agregar zapato");
            System.out.println("2. Ver stock disponible de X producto");
            System.out.println("3. Realizar una venta");
            System.out.println("4. Agregar stock a X producto");
            System.out.println("5. Información de inventario");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");

            // Lee la elección del menú del usuario.
            choice = scanner.nextByte();
            System.out.println("\n----------------------------------");
            System.out.println("Opción Elegida: " + choice);

            // Utiliza un switch statement para realizar acciones basadas en la elección del usuario.
            switch (choice) {
                case 1:
                    // Caso 1: Agregar un nuevo zapato al inventario.
                    System.out.println("-------------- Agregar zapato --------------");
                    // Consume el carácter de nueva línea restante después de leer la elección byte.
                    scanner.nextLine();
                    System.out.print("Digite el nombre del zapato: ");
                    // Lee el nombre del zapato y lo convierte a minúsculas para manejo sin distinción de mayúsculas/minúsculas.
                    shoeName = scanner.nextLine().toLowerCase();
                    System.out.print("Cantidad disponible en stock (Solo números): ");
                    // Lee la cantidad de stock inicial.
                    shoeAmount = scanner.nextShort();
                    // Agrega el zapato y su stock inicial a ambos mapas de inventario.
                    shoesInformation.put(shoeName, shoeAmount);
                    shoesStartingInventory.put(shoeName, shoeAmount);
                    System.out.println("Zapato '" + shoeName + "' agregado con éxito al inventario " +
                            "con un stock de: " + shoeAmount);
                    break;

                case 2:
                    // Caso 2: Ver el stock actual de un zapato específico.
                    System.out.println("-------------- Ver stock disponible de X producto --------------");
                    // Consume el carácter de nueva línea restante.
                    scanner.nextLine();
                    System.out.print("Digite el nombre del zapato: ");
                    // Lee el nombre del zapato y lo convierte a minúsculas.
                    shoeName = scanner.nextLine().toLowerCase();
                    // Verifica si el zapato existe en el inventario.
                    if (shoesInformation.containsKey(shoeName)) {
                        System.out.println("La cantidad disponible en stock del zapato: '" + shoeName + "' es de: " +
                                shoesInformation.get(shoeName));
                    } else {
                        // Informa al usuario si el zapato no se encuentra.
                        System.out.println("Ese zapato no existe en inventario. Vuelve al menú y selecciona la " +
                                "opción 1 para agregar un nuevo zapato.");
                    }
                    break;

                case 3:
                    // Caso 3: Realizar una venta.
                    System.out.println("-------------- Menú de Ventas --------------");
                    // Consume el carácter de nueva línea restante.
                    scanner.nextLine();
                    System.out.print("Digite el nombre del zapato que vas vender: ");
                    // Lee el nombre del zapato y lo convierte a minúsculas.
                    shoeName = scanner.nextLine().toLowerCase();
                    System.out.print("Cuanto que planeas vender: ");
                    // Lee la cantidad a vender.
                    sellAmount = scanner.nextShort();

                    // Verifica si el zapato existe, tiene un stock no nulo y stock suficiente para la venta.
                    if (shoesInformation.containsKey(shoeName) && shoesInformation.get(shoeName) != null
                            && shoesInformation.get(shoeName) >= sellAmount) {

                        System.out.println("Hay stock para hacer la venta. Calculando nuevo stock de: '" + shoeName + "'");
                        // Calcula el nuevo stock después de la venta y actualiza el inventario.
                        shoesInformation.put(shoeName, (short) (shoesInformation.get(shoeName) - sellAmount));

                        System.out.println("Nuevo stock calculado. Nuevo stock para '" + shoeName + "' es de: " +
                                shoesInformation.get(shoeName));
                    } else {
                        // Informa al usuario por qué no se puede realizar la venta.
                        System.out.println("""
                                No es posible hacer la venta en ese momento por una de las razones:
                                1. El zapato no existe en el inventario.
                                2. No hay zapatos suficiente en stock.

                                3. La cantidad de venta es mayor que la cantidad de zapatos en stock.""");
                    }
                    break;

                case 4:
                    // Caso 4: Agregar stock a un zapato existente.
                    System.out.println("-------------- Agregar Stock a un Zapato --------------");
                    // Consume el carácter de nueva línea restante.
                    scanner.nextLine();
                    System.out.print("Digite el nombre del zapato que vas agregar stock: ");
                    // Lee el nombre del zapato y lo convierte a minúsculas.
                    shoeName = scanner.nextLine().toLowerCase();
                    System.out.print("Cuanto de stock desea adicionar al inventario de '" + shoeName + "': ");
                    // Lee la cantidad de stock a agregar.
                    shoeAmountToAdd = scanner.nextShort();

                    // Verifica si el zapato existe en el inventario.
                    if (shoesInformation.containsKey(shoeName)) {
                        // Agrega el nuevo stock al stock actual y actualiza el inventario.
                        shoesInformation.put(shoeName, (short) (shoesInformation.get(shoeName) + shoeAmountToAdd));
                        System.out.println("Stock agregado! Nueva cantidad de '" + shoeName + "' en stock es de: " +
                                shoesInformation.get(shoeName));
                    } else {
                        // Informa al usuario si el zapato no se encuentra.
                        System.out.println("No es posible agregar stock a ese zapato porque no existe en inventario.");
                    }
                    break;

                case 5:
                    // Caso 5: Ver información sobre el inventario actual.
                    System.out.println("-------------- Información del Stock --------------");
                    // Verifica si el inventario está vacío.
                    if (shoesInformation.isEmpty()) {
                        System.out.println("Ahem. No hay zapatos en inventario....");
                    } else {
                        System.out.println("Zapatos en inventario (Nombre - Stock Actual): ");
                        // Itera a través de las claves (nombres de zapatos) en el mapa de inventario inicial
                        // e imprime el stock actual del mapa shoesInformation.
                        for (String key : shoesStartingInventory.keySet()) {
                            System.out.println(key + " - " + shoesInformation.get(key));
                        }
                    }
                    break;

                case 6:
                    // Caso 6: Salir de la aplicación.
                    System.out.println("-------------- Saliendo --------------");
                    break;

                default:
                    // Caso por defecto para opciones de menú inválidas.
                    System.out.println("La opción seleccionada no es valida. Intente de nuevo.");
            } // Fin del switch statement.

            // Verifica los niveles de inventario después de cada operación (excepto salir).
            if (choice != 6) {
                // Itera a través del inventario actual para verificar si hay artículos con stock cero o menos.
                for (String key : shoesInformation.keySet()) {
                    // Verifica si el stock actual es menor o igual a cero.
                    if (shoesInformation.get(key) != null && shoesInformation.get(key) <= 0) {
                        System.out.println("El zapato '" + key + "' no tiene ningun stock mas en el inventario.\n" +
                                "Intentando reponer el stock...");
                        // Obtiene el stock inicial para el zapato y lo duplica.
                        short initialStock = shoesStartingInventory.get(key);
                        // Realiza un casting del resultado de la multiplicación a short antes de volver a ponerlo en el mapa.
                        // Esto es necesario porque initialStock * 2 resulta en un int.
                        shoesInformation.put(key, (short) (initialStock * 2));
                        System.out.println("La reposición fue un éxito, la nueva cantidad de '" + key + "' en inventario" +
                                " es de: " + shoesInformation.get(key));
                    }
                }
            }

        } while (choice != 6); // Continúa el bucle hasta que el usuario elige la opción 6.

        // Cierra el recurso scanner cuando la aplicación sale.
        scanner.close();

    } // Fin del método main.
} // Fin de la clase Main.