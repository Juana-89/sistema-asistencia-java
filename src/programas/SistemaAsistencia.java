package programas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SistemaAsistencia {

    // Arrays:)
    private static final String[] DNIS_REGISTRADOS = {"12345678", "87654321", "11223344", "44332211", "99999999"};
    private static final String[] CONTRASEÑAS_REGISTRADAS = {"password123", "contraseña321", "3344", "2211", "nueve"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.print("Ingrese su DNI: ");
            String dniIngresado = scanner.nextLine();

            // Si el DNI no corresponde:
            if (!validarDNI(dniIngresado)) {
                System.out.println("Marca un número de DNI válido");
                continue; // Volver a solicitar el DNI
            }

            System.out.print("Ingrese su contraseña: ");
            String contraseñaIngresada = scanner.nextLine();

            if (autenticarEmpleado(dniIngresado, contraseñaIngresada)) {
                System.out.println("Autenticación exitosa. Bienvenido.");

                System.out.println("¿Qué desea hacer? (1: Registrar Entrada, 2: Registrar Salida, 3: Salir)");
                int opcion = scanner.nextInt();

                // Obtener fecha actual
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE, dd-MM-yyyy HH:mm:ss");
                String fechaHoraFormateada = fechaHoraActual.format(formato);

                // Validar opción ingresada y si no es, marcar 1, 2 o 3
                if (opcion == 1) {
                    System.out.println("Entrada registrada para el DNI " + dniIngresado +". " + fechaHoraFormateada + ".");
                } else if (opcion == 2) {
                    System.out.println("Salida registrada para el DNI " + dniIngresado +". " + fechaHoraFormateada + ".");
                } else if (opcion == 3) {
                    salir = true;
                    System.out.println("Se cerró la sesión. ¡Hasta luego!");
                } else {
                    System.out.println("Opción incorrecta. Por favor, seleccione 1, 2 o 3.");
                }
                scanner.nextLine();
            } else {
                System.out.println("DNI o contraseña incorrectos. Inténtelo nuevamente.");
            }
        }

        scanner.close();
    }

    // Metodo para autenticar a un empleado (arrays)
    private static boolean autenticarEmpleado(String dni, String contraseña) {
        // Buscar el DNI y verificar que la contraseña coincida
        for (int i = 0; i < DNIS_REGISTRADOS.length; i++) {
            if (DNIS_REGISTRADOS[i].equals(dni) && CONTRASEÑAS_REGISTRADAS[i].equals(contraseña)) {
                return true;
            }
        }
        return false; // Si no se encuentra el DNI o la contraseña es incorrecta
    }

    // Metodo para validar el DNI
    private static boolean validarDNI(String dni) {
        return dni.matches("\\d{8}"); // Comprueba que el DNI contenga exactamente 8 dígitos
    }
}