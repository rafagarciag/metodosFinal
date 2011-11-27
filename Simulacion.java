import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class Simulacion{


	//Numero de iteraciones para la simulacion
	final static int ITERACIONES = 10000;
	final static double lambda = 0.027383;
    final static double mu = 0.05;
	
	//Variable que contendra el tipo de broker
	static Broker broker = null;
	
	//Arreglo de procesadores a utilizar
	public static Procesador [] procesadores;
	
	public static double NextArrival = Double.POSITIVE_INFINITY;
    public static double NextDeparture = Double.POSITIVE_INFINITY;
	
	
	static Scanner scan = new Scanner(System.in);
	//Para seleccionar el broker a utilizar
	private static void menuBroker(){
		System.out.println("===============================================================");
		System.out.println("Seleccione el número de opción para utilizar como corredor");
		System.out.println("de tareas ('broker')");
		System.out.println("1. ROUNDROBIN");
		System.out.println("2. LIST");
		System.out.println("3. PARETOFRACTAL");
		System.out.println("===============================================================");
		
		
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
	    
        int numProcesadores = procesadores.length;
        
        for(int i = 0; i<numProcesadores; i++){
        	double ta = NextArrival - procesadores[i].getNextDeparture();
        	procesadores[i].setTa(ta);
        }
		NextArrival=StdRandom.exp(lambda);
        //Inicia simulacion 
        for (int contador=0; contador < ITERACIONES; contador++) {       
           	System.out.println("Step: "+ contador +"//////////////////////////////////////////////");
            //scan.nextInt();
            
            
            NextDeparture = Double.POSITIVE_INFINITY;
            for(int i=0; i<numProcesadores; i++){
            	System.out.println("nextDep del xcpu: "+procesadores[i].getNextDeparture());
                if (procesadores[i].getNextDeparture() < NextDeparture){
                    NextDeparture = procesadores[i].getNextDeparture();
					procMenorND = i;
				}
            }
			System.out.println("NextArrival: "+ NextArrival);
			System.out.println("NextDeparture: "+ NextDeparture);
			
            // Llegada al sistema
            if (NextArrival <= NextDeparture) {
                Tarea t = new Tarea(contador, random.nextInt(numProcesadores)+1, NextArrival);    
                //System.out.println("Tarea asignada con ID " + t.getId());
                //Verificar que se cuenta con el numero de procesadores necesarios
                //para atender la tarea
                if(t.getNum()<=numProcesadores){
                	//Asignar tarea a un procesador
                	broker.asignaTarea(t);
                }
                else{

                	System.out.println("Tarea excede num de proc.");
                		
                }
                NextArrival=StdRandom.exp(lambda);
            }			

			
            // Salida del sistema

            else {
            	double wait=9999999;
            	if(procesadores[procMenorND].peekTarea().getNum()>1){
            		int id=procesadores[procMenorND].peekTarea().getId();
            		int tareasListas=0;
            		for(int i=0;i<procesadores.length;i++){
            			if(!procesadores[i].isEmpty()){
            				if(procesadores[i].peekTarea().getId()==id)
            				tareasListas++;
            			}   			
            		}
            		if(tareasListas>=procesadores[procMenorND].peekTarea().getNum()){
            			for(int i=0;i<procesadores.length;i++){
            				if(!procesadores[i].isEmpty()){
            					if(procesadores[i].peekTarea().getId()==id){
            						procesadores[i].popTarea();
            						if(procesadores[i].isEmpty()){
            							procesadores[i].setNextDeparture(Double.POSITIVE_INFINITY);
            						}
            						else{
            							procesadores[i].setNextDeparture(StdRandom.exp(mu));
            						}
            					}
            				}
            			}
            			System.out.println("Trono muchas tareas");
            		}
            		else{
            			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Esperando a mis compañeritos :3"+id);
            			procesadores[procMenorND].setNextDeparture(procesadores[procMenorND].getNextDeparture()+StdRandom.exp(mu));
            		}
            	}else{
            		
            		wait = NextDeparture - procesadores[procMenorND].popTarea().getArrival();
            		System.out.println("Trono una sola tarea");
            	}
            
                //double wait = NextDeparture - procesadores[procMenorND].popTarea().getArrival();
                //StdOut.printf("Wait = %6.2f, queue size = %d\n", wait, q.size());
                System.out.println("#############################Wait y la verga y asi: "+wait);
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
