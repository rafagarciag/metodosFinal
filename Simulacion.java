import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class Simulacion{


	//Numero de iteraciones para la simulacion
	final static int ITERACIONES = 1000;
	
	//Variable que contendra el tipo de broker
	static Broker broker = null;
	
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
				broker=new RoundRobin();
				break;
			case 2:
				broker=new List();
				break;
			case 3:
				broker=new Pareto();
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
			for(int i=0; i<opcion; i++){
			    procesadores[i] = new Procesador();
			}
		}
		else{
			System.out.println("Ingrese  un número de procesadores mayor o igual a 1");
			menuBroker();
		}
	}

	
	public static void main(String[] args) {
	    
	    menuBroker();
	    
	    Random random = new Random();
	    int procMenorNA = 0;
	    int procMenorND = 0;
	    
		double lambda = 0.027383;
        double mu = 0.05;
        int numProcesadores = procesadores.length;

        double menorNextArrival = Double.POSITIVE_INFINITY;
        double menorNextDeparture = Double.POSITIVE_INFINITY;

        //Inicia simulacion 
        for (int contador=0; contador < ITERACIONES; contador++) {       
            
            System.out.println("Iteracion "+ contador);
            for(int i=0; i<numProcesadores; i++){
                if (procesadores[i].getNextArrival() < menorNextArrival){
                    menorNextArrival = procesadores[i].getNextArrival();
                    procMenorNA = i;
				}
                if (procesadores[i].getNextDeparture() < menorNextDeparture){
                    menorNextDeparture = procesadores[i].getNextDeparture();
					procMenorND = i;
				}
            }

            // Llegada al sistema
            if (menorNextArrival <= menorNextDeparture) {
                Tarea t = new Tarea(contador, random.nextInt(numProcesadores), StdRandom.exp(lambda));
                
                
                //System.out.println("Tarea asignada con ID " + t.getId());
                
                //Verificar que se cuenta con el numero de procesadores necesarios
                //para atender la tarea
                if(t.getNum()<=numProcesadores){
                	//Asignar tarea a un procesador
                	broker.asignaTarea(t);
                }
                else{
                	
                }
                
                
                
            }			
			
            // Salida del sistema

            else {
                double wait = menorNextDeparture - procesadores[procMenorND].popTarea().getArrival();
                //StdOut.printf("Wait = %6.2f, queue size = %d\n", wait, q.size());
                if (procesadores[procMenorND].isEmpty()){
                	procesadores[procMenorND].setNextDeparture(Double.POSITIVE_INFINITY);
                }
                else{
                	procesadores[procMenorND].setNextDeparture(procesadores[procMenorND].getNextDeparture() + StdRandom.exp(mu) );
                }
                
            }
            
            for(int i = 0; i<numProcesadores; i++){
				System.out.println("Procesador " + i);
				System.out.println("\tTamaño de fila " + procesadores[i].queueSize());
			}

        }

    }
}
