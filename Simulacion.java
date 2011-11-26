import java.io.PrintWriter;
import java.util.Scanner;

public class Simulacion{

    public static double lambda
    public static double 

	//Numero de iteraciones para la simulacion
	final static int ITERACIONES = 1000000;
	
	//Variable que contendra el tipo de broker
	static String broker = "";
	
	//Arreglo de procesadores a utilizar
	public static Procesador [] procesadores; 
	
	//Para seleccionar el broker a utilizar
	private static void menuBroker(){
		System.out.println("===============================================================");
		System.out.println("Seleccione el número de opción para utilizar como corredor");
		System.out.println("de tareas ('broker')");
		System.out.println("1. ROUNDROBIN");
		System.out.println("2. LIST");
		System.out.println("3. PARETOFRACTAL");
		System.out.println("===============================================================");
		
		Scanner scan = new Scanner(System.in);
		int opcion = scan.nextInt();
		
		switch(opcion){
			case 1:
				broker="roundrobin";
				break;
			case 2:
				broker="list";
				break;
			case 3:
				broker="pareto";
				break;
			default:
				System.out.println("Opcion invlálida, seleccione un número del 1 al 3");
				menuBroker();
		}
		
		System.out.println("\n===============================================================");
		System.out.println("Ingrese el número de procesadores a utilizar (minimo 1)");
		System.out.println("===============================================================");
		
		opcion = scan.nextInt();
		if(opcion>=1){
			procesadores = new Procesador[opcion];
			for(int i=0; i<opcion;i++);{
			    procesadores[i] = new Procesador();
			}
		}
		else{
			System.out.println("Ingrese  un número de procesadores mayor o igual a 1");
			menuBroker();
		}
	}
	
	//Algorito corredor de tareas
	private static void listBroker(Tarea tarea){
		
	}
	
	public static void main(String[] args) {
	
	    int contador = 0;
	    
        lambda = Double.parseDouble(args[0]);  // arrival rate
        mu     = Double.parseDouble(args[1]);  // service rate

        double menorNextArrival = Double.POSITIVE_INFINITY;
        double menorNextDeparture = Double.POSITIVE_INFINITY;

        menuBroker();

        //Aqui se debe crear el broker

        // simulate an M/M/1 queue
        while (contador < ITERACIONES) {             
            
            
            for(int i=0; i<procesadores.length; i++){
                if (procesadores[i].getNextArrival < menorNextArrival)
                    menorNextArrival = procesadores[i].getNextArrival;
                if (procesadores[i].getNextDeparture < menorNextDeparture)
                    menorNextDeparture = procesadores[i].getNextDeparture;
            }
            
            // it's an arrival
            if (menorNextArrival <= menorNextDeparture) {
                Tarea t = new Tarea(cont, Math.Random.nextInt(4));
                //Llama broker enviandole tarea
                
            }
            
            // it's a departure
            else {
                double wait = nextDeparture - q.dequeue();
                StdOut.printf("Wait = %6.2f, queue size = %d\n", wait, q.size());
                if (q.isEmpty()) nextDeparture = Double.POSITIVE_INFINITY;
                else             nextDeparture += StdRandom.exp(mu);
                
            }
            
            contador++;
        }

    }
}
