
import java.util.Scanner;

//METODO MAIN
public class Principal {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        // =========================================
        // DATOS DEL CLIENTE
        // =========================================

        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el saldo inicial: S/ ");
        double saldoInicial = sc.nextDouble();

        ClienteTelefonia cliente =
                new ClienteTelefonia(nombre, saldoInicial);

        // =========================================
        // MENU
        // =========================================

        char opcion;

        do {

            System.out.println("\n==============================");
            System.out.println("   SISTEMA DE TELEFONIA");
            System.out.println("==============================");
            System.out.println("R -> Recarga");
            System.out.println("C -> Consumo");
            System.out.println("F -> Finalizar");
            System.out.print("Seleccione una opción: ");

            opcion = Character.toUpperCase(
                    sc.next().charAt(0)
            );

            switch (opcion) {

                // =================================
                // RECARGA
                // =================================

                case 'R' -> {

                    System.out.println("\n--- RECARGA ---");
                    System.out.println("1 -> Tarjeta");
                    System.out.println("2 -> Efectivo");

                    System.out.print("Seleccione el tipo: ");
                    int tipo = sc.nextInt();

                    System.out.print(
                            "Ingrese el monto: S/ "
                    );

                    double monto = sc.nextDouble();

                    if (monto <= 0) {

                        System.out.println(
                                "Monto inválido."
                        );

                    } else {

                        cliente.realizarRecarga(
                                tipo,
                                monto
                        );

                        System.out.printf(
                                "Saldo actualizado: S/ %.2f%n",
                                cliente.getSaldoFinal()
                        );
                    }
                }

                // =================================
                // CONSUMO
                // =================================

                case 'C' -> {

                    System.out.println("\n--- CONSUMO ---");

                    System.out.print(
                            "Ingrese el monto: S/ "
                    );

                    double monto = sc.nextDouble();

                    if (monto <= 0) {

                        System.out.println(
                                "Monto inválido."
                        );

                    } else if (
                            cliente.realizarConsumo(monto)
                    ) {

                        System.out.println(
                                "Consumo realizado correctamente."
                        );

                        System.out.printf(
                                "Saldo disponible: S/ %.2f%n",
                                cliente.getSaldoFinal()
                        );

                    } else {

                        System.out.println(
                                "Saldo insuficiente."
                        );

                        System.out.printf(
                                "Saldo disponible: S/ %.2f%n",
                                cliente.getSaldoFinal()
                        );
                    }
                }

                // =================================
                // FINALIZAR
                // =================================

                case 'F' -> {

                    System.out.println(
                            "\nFinalizando el programa..."
                    );
                }

                default -> {

                    System.out.println(
                            "Opción inválida."
                    );
                }
            }

        } while (opcion != 'F');

        // =========================================
        // REPORTE FINAL
        // =========================================

        System.out.println(
                "\n================================="
        );
        System.out.println(
                "       REPORTE FINAL"
        );
        System.out.println(
                "================================="
        );

        System.out.println(
                "Nombre del cliente: "
                        + cliente.getNombre()
        );

        System.out.printf(
                "Saldo inicial: S/ %.2f%n",
                cliente.getSaldoInicial()
        );

        System.out.printf(
                "Saldo final: S/ %.2f%n",
                cliente.getSaldoFinal()
        );

        System.out.println(
                "Recargas con tarjeta: "
                        + cliente.getRecargasTarjeta()
        );

        System.out.println(
                "Recargas en efectivo: "
                        + cliente.getRecargasEfectivo()
        );

        System.out.println(
                "Total de consumos: "
                        + cliente.getTotalConsumos()
        );

        sc.close();
        }
}

