//Modelos de inventarios


import java.util.Scanner;

public class main{
    public static void main(String []args){
        Scanner sc= new Scanner (System.in);
        byte opcion=0;
        do{
            System.out.println("\n\n\t Modelos de inventarios (EOQ)");
            System.out.println("1.Modelo EOQ Clasico");
            System.out.println("2.Modelo EOQ con descuento");
            System.out.println("3.Salir");
        
            System.out.println("\n\n\tOpcion");
            opcion=sc.nextByte();

        switch (opcion){
            case 1-> {
                calcularEOQClasico(sc);
            }
            case 2-> {
                calcularEOQDescuento(sc);
            }
            default -> {
                System.out.println("Opcion no valida, intente de nuevo");
            }

        }
        
        }while(opcion!=3);
    }
    public static void calcularEOQClasico (Scanner sc){
        System.out.println("Ingresa la demanda por unidad de tiempo (D):");
        double D = sc.nextDouble();
        System.out.println("Ingresa el costo por pedido (K):");
        double K = sc.nextDouble();
        System.out.println("Ingresa costo de mantenimiento por unidad en el mismo  periodo de la demanda (h):");
        double h = sc.nextDouble();
        System.out.println("Ingresa el tiempo de entrega (L):");
        double L = sc.nextDouble();
        
        double y= Math.sqrt((2*K*D)/h);
        double t0=y/D;
        int n=(int) (L/t0);
        double Lc=L-n*t0;
        double ROP=D*Lc;
        double TC=K*(D/y)+h*(y/2);
        double TCU=TC/D;

        System.out.println("RESULTADOS DEL MODELO EOQ: ");
        System.out.println("Cantidad economica de pedido (EOQ): "+ y);
        System.out.println("Duracion del ciclo (t0): " + t0);
        System.out.println("Numero de pedidos dentro del tiempo de entrega (n): " + n);
        System.out.println("Tiempo restante (Lc): " + Lc);
        System.out.println("Punto de reorden (ROP): " + ROP);
        System.out.println("Costo total anual (TC): "+ TC);
        System.out.println("Costo total unitacio (TCU): " + TCU);
    }
    public static void calcularEOQDescuento (Scanner sc) {
        System.out.println("Ingresa el costo del pedido (K): ");
        double K=sc.nextDouble();
        System.out.println("Ingresa la demanda por unidad de tiempo (D): ");
        double D=sc.nextDouble();
        System.out.println("Ingresa porcentaje de descuento (En decimal): ");
        double i=sc.nextDouble();
        
        System.out.println("¿Cuantos niveles de descuento hay?");
        int niv = sc.nextInt();

        double[] precios = new double[niv];
        double[] CMin = new double [niv];
        double[] CMax = new double[niv];

        for(int j=0; j<niv; j++){
            System.out.println("Nivel" (j+1)+ ": ");
            System.out.println("Precio por unidad (C): " );
            precios[j]=sc.nextDouble();

            System.out.println("Ingresa la cantidad minima para aplicar el descuento: ");
            CMin[j]=sc.nextDouble();

            System.out.println("Ingresa cantidad maxima: ");
            CMax[j]=sc.nextDouble();
        }
        double MejorCT=Double.MAX_VALUE;
        double MejorY=0;
        double MejorC=0;

        for(int j=0;j<niv; j++){
            double C=precios[j];
            double h= i*precios[j];
            double Y = Math.sqrt((2*D*K)/h);
            
            double CC = D * C;             
            double CO = (D / Y) * K;       
            double CM = (Y / 2) * h;     
            double CTU = CC + CO + CM;   

            System.out.println("\nNivel " + (j + 1) + " (C = " + C + "):");
            System.out.println("EOQ : " + Y);
            System.out.println("Costo de compra: " + CC);
            System.out.println("Costo por ordenar: " +CO);
            System.out.println("Costo por mantenimiento:" + CM);
            System.out.println("Costo total anual:" + CTU);


        }
        if()
        
    }
}