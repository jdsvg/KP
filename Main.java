import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner leerInt = new Scanner(System.in);
    Scanner leerStr = new Scanner(System.in);
    System.out.print("Ingrese la cantidad de articulos: ");
    int CantidaArticulos = leerInt.nextInt();
    String[] Articulos = new String[CantidaArticulos];
    int[] VolumenArticulos = new int[CantidaArticulos];
    int[] GananciaArticulos = new int[CantidaArticulos];
    // llenando arry por el usuario
    for (int i = 0; i < CantidaArticulos; i++) {
      System.out.println("----------------------------");
      System.out.println("Articulo [" + (i + 1) + "]");
      System.out.print("Ingrese el nombre: ");
      Articulos[i] = leerStr.nextLine();
      System.out.print("Ingrese el volumen: ");
      VolumenArticulos[i] = leerInt.nextInt();
      System.out.print("Ingrese el beneficio: ");
      GananciaArticulos[i] = leerInt.nextInt();
    }
    System.out.println("\n----Arrays Ingresados----");
    System.out.println("Array NombreArticulos : " + Arrays.toString(Articulos));
    System.out.println("Array VolumenArticulos : " + Arrays.toString(VolumenArticulos));
    System.out.println("Array GananciaArticulos : " + Arrays.toString(GananciaArticulos));
    // Calculando la capacidad max de la mochila en base al aticulo mas grande
    int CapacidadMaletaMax = 0;
    for (int volumenArticulo : VolumenArticulos) {
      CapacidadMaletaMax = Math.max(volumenArticulo, CapacidadMaletaMax);
    }
    // Ordenando en base al volumen de los articulos de menor a mayor
    int TemporalVolumen, TemporalGanancia;
    String TemporalArticulo;
    for (int i = 0; i < VolumenArticulos.length; i++) {
      for (int j = 1; j < (VolumenArticulos.length - i); j++) {
        if (VolumenArticulos[j - 1] > VolumenArticulos[j]) {
          TemporalVolumen = VolumenArticulos[j - 1];
          TemporalGanancia = GananciaArticulos[j - 1];
          TemporalArticulo = Articulos[j - 1];
          VolumenArticulos[j - 1] = VolumenArticulos[j];
          GananciaArticulos[j - 1] = GananciaArticulos[j];
          Articulos[j - 1] = Articulos[j];
          VolumenArticulos[j] = TemporalVolumen;
          GananciaArticulos[j] = TemporalGanancia;
          Articulos[j] = TemporalArticulo;
        }
      }
    }
    System.out.println("\n----Arrays Ingresados Ordenados----");
    System.out.println("Array NombreArticulos : " + Arrays.toString(Articulos));
    System.out.println("Array VolumenArticulos : " + Arrays.toString(VolumenArticulos));
    System.out.println("Array GananciaArticulos : " + Arrays.toString(GananciaArticulos));
    System.out.println("Capacidad de la maleta: " + CapacidadMaletaMax);
    // String[] Articulos = {"A","B", "C", "D", "E", "F"};
    // int[] VolumenArticulos = {1, 2, 4, 5, 7, 8};
    // int[] GananciaArticulos = {2, 5, 6, 10, 13, 16};
    // Arrays Solucion
    int[] SolucionGanancias = new int[CapacidadMaletaMax + 1];
    String[] SolucionArticulos = new String[CapacidadMaletaMax + 1];
    // Caso 0 inicial
    for (int i = 0; i < CapacidadMaletaMax + 1; i++) {
      SolucionGanancias[i] = 0;
    }
    // Caso "" inicial
    for (int i = 0; i < CapacidadMaletaMax + 1; i++) {
      SolucionArticulos[i] = "";
    }
    // logic
    int GananciaAnterior, VolumenAticuloI, GananciaArticuloI;
    int CapacidadRestante = 0;
    System.out.print("\n------Tabla------");
    for (int Articulo = 1; Articulo <= GananciaArticulos.length; Articulo++) {
      for (int CapacidadMaletaI = CapacidadMaletaMax; CapacidadMaletaI >= 1; CapacidadMaletaI--) {
        GananciaAnterior = SolucionGanancias[CapacidadMaletaI]; // Ganancia anterior
        VolumenAticuloI = VolumenArticulos[Articulo - 1]; // volumen del articulo en cuestion
        if (CapacidadMaletaI >= VolumenAticuloI) {
          GananciaArticuloI = GananciaArticulos[Articulo - 1]; // Ganancia del articulo en cuestion
          CapacidadRestante = CapacidadMaletaI - VolumenAticuloI; // Posicion de la sumatoria de ganancias anteriores
                                                                  // posibles
          GananciaArticuloI += SolucionGanancias[CapacidadRestante];
        } else {
          GananciaArticuloI = 0; // El articulo no cabe en la maleta
        }
        SolucionGanancias[CapacidadMaletaI] = Math.max(GananciaAnterior, GananciaArticuloI); // Ganancia anterior vs
                                                                                             // (Ganancia del articulo I
                                                                                             // + suma posible)
        if (GananciaArticuloI >= GananciaAnterior) {
          SolucionArticulos[CapacidadMaletaI] = Articulos[Articulo - 1] + ","; // Nomnre Articulo en cuestion
          SolucionArticulos[CapacidadMaletaI] += SolucionArticulos[CapacidadRestante];// le sumamos la solucion de
                                                                                      // articulo(s) anteriores posibles
        } else {
          SolucionArticulos[CapacidadMaletaI] = SolucionArticulos[CapacidadMaletaI]; // Agregamos la solucion de
                                                                                     // articulo(s) anterior
        }
      }
      // Visualuzacion de la tabla en cada fila
      System.out.print("\nArticulo [" + Articulo + "] Arry SolucionGanancias :");
      for (int solucionGanancia : SolucionGanancias) {
        System.out.print(" [" + solucionGanancia + "] ");
      }
      System.out.print("\nArticulo [" + Articulo + "] Arry SolucionArticulos : ");
      for (String solucionArticulo : SolucionArticulos) {
        System.out.print(" [" + solucionArticulo + "] ");
      }
    }
    // -----------Solucion_____________//
    System.out.println("\n\n -----------Solucion_____________");
    System.out.println("Ganancia Max: " + SolucionGanancias[CapacidadMaletaMax]);
    System.out.println("Con los articulo: " + SolucionArticulos[CapacidadMaletaMax]);
    System.out.print("\nArry SolucionGanancias : ");
    for (int solucionGanancia : SolucionGanancias) {
      System.out.print(" [" + solucionGanancia + "] ");
    }
    System.out.print("\nArry SolucionArticulos : ");
    for (String solucionArticulo : SolucionArticulos) {
      System.out.print(" [" + solucionArticulo + "] ");
    }
  }
}