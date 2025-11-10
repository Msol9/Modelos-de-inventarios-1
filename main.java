//Modelos de inventarios


import java.util.Scanner;

public class Main{
    public static void main(String []args){
        Scanner escaner = new Scanner(System.in);
        byte opcion = 0;
        do{
            System.out.println("\n\n\t Modelos de inventarios (EOQ)");
            System.out.println("1. Modelo EOQ Clasico");
            System.out.println("2. Modelo EOQ con descuento");
            System.out.println("3. Salir");
        
            System.out.println("\n\n\tOpcion");
            opcion = escaner.nextByte();

        switch (opcion){
            case 1-> {
                double t0, Lc, ROP, TC, TCU,c, y, D, K, h, L;
                int n;
                System.out.println("Ingresa la demanda por unidad de tiempo (D):");
                D = escaner.nextDouble();
                System.out.println("Ingresa el costo por pedido (K):");
                K = escaner.nextDouble();
                System.out.println("Ingresa costo de mantenimiento por unidad en el mismo  periodo de la demanda (h):");
                h = escaner.nextDouble();
                System.out.println("Ingresa el tiempo de entrega (L):");
                L = escaner.nextDouble();
        
                y= Math.sqrt((2 * K * D) / h);
                t0= y / D;
                n = (int) (L / t0);
                Lc = L - n * t0;
                ROP = D * Lc;
                TC=K * (D / y ) + h * (y / 2);
                TCU = TC / D;

                System.out.println("\n\n  RESULTADOS DEL MODELO EOQ: ");
                System.out.println("Cantidad economica de pedido (EOQ): "+ y);
                System.out.println("Duracion del ciclo (t0): " + t0);
                System.out.println("Numero de pedidos dentro del tiempo de entrega (n): " + n);
                System.out.println("Tiempo restante (Lc): " + Lc);
                System.out.println("Punto de reorden (ROP): " + ROP);
                System.out.println("Costo total anual (TC): "+ TC);
                System.out.println("Costo total unitacio (TCU): " + TCU);

                System.out.println("\nConclusiones finales del modelo EOQ: " + 
                "\nDebe comprar " + (int) (y+1)+ " unidades cada " + (int) n + " dias, o cuando el nivel de inventario descienda a " + (int)ROP + " unidades");
            }
            case 2-> {
                double K, D, i, mejorCTU, mejorY, mejorC, C, h, Y, CO, CC, CM, CTU, Lc, L, n, t0, ROP;
                int niv;
                System.out.println("Ingresa el costo del pedido (K): ");
                K = escaner.nextDouble();
                System.out.println("Ingresa la demanda por unidad de tiempo (D): ");
                D = escaner.nextDouble();
                System.out.println("Ingresa porcentaje de descuento de mantenimiento (En decimal): ");
                i = escaner.nextDouble();
                System.out.println("Ingresa tiempo de entrega: ");
                L = escaner.nextDouble();
        
                System.out.println("¿Cuantos niveles de descuento hay?: ");
                niv = escaner.nextInt();

                double[] precios = new double[niv];
                double[] CMin = new double [niv];
                double[] CMax = new double[niv];

                for(int j=0; j<niv; j++){
                    System.out.println("\nNivel " + (j+1)+ ": ");
                    System.out.println("    Precio por unidad (C): " );
                    precios[j]=escaner.nextDouble();

                    System.out.println("    Ingresa la cantidad minima para aplicar el descuento: ");
                    CMin[j]=escaner.nextDouble();

                    System.out.println("    Ingresa cantidad maxima: ");
                    CMax[j]=escaner.nextDouble();
                }

                mejorCTU = Double.MAX_VALUE;
                mejorY = 0;
                mejorC = 0;

                for(int j=0;j<niv; j++){
                    C = precios[j];
                    h = i * C;
                    Y = Math.sqrt((2 * K * D) / h);

                    if (Y < CMin[j]){
                        Y = CMin[j];
                    }else if (Y > CMax[j]) {
                        Y = CMax[j];
                    }
                    CC = D * C;             
                    CO = (D / Y) * K;       
                    CM = (Y / 2) * h;     
                    CTU = CC + CO + CM;   

                    System.out.println("\n\n      Nivel " + (j + 1) + " (C = " + C + "):");
                    System.out.println("EOQ : " + (int)Y);
                    System.out.println("Costo de compra: " + (int)CC);
                    System.out.println("Costo por ordenar: " +(int)CO);
                    System.out.println("Costo por mantenimiento:" + (int)CM);
                    System.out.println("Costo total anual:" + (int)CTU);

                    if(CTU < mejorCTU) {
                        mejorCTU = CTU;
                        mejorY = Y;
                        mejorC = C;
                    }
                }
                System.out.println("\n\n      Mejor opcion: ");
                System.out.println("Cantidad optima (y*): " + (int)mejorY);
                System.out.println("Costo unitario (C): " + (int)mejorC);
                System.out.println("Costo total minimo (CTU): " + (int)mejorCTU);

                ROP= L*D;

                System.out.println("\nPunto de reorden: " + (int)ROP);
                }

            case 3-> {
                System.out.println("Saliendo...");
            }
            default -> {
                System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
        }while(opcion!=3);
    }
}