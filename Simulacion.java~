import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Simulacion{


	//Numero de iteraciones para la simulacion
	final static int ITERACIONES = 65535;
	final static double lambda = 0.027383;
    final static double mu = 0.05;
	
	static boolean filasVacias = true;
	
	//para calcuular makespan
	static double makespan = 0.0;
	
	
	//Variable que contendra el tipo de broker
	static Broker broker = null;
	
	//Arreglo de procesadores a utilizar
	public static Procesador [] procesadores;
	
	public static double nextArrival = 0.0;
    public static double nextDeparture = Double.POSITIVE_INFINITY;
	
	public static double datosTareas[][] = new double[65535][2];
	
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

	
	public static void main(String[] args) throws IOException{

		FileReader fr1 = new FileReader("runtime.txt"); 
		BufferedReader br = new BufferedReader(fr1); 
		int runtime; 
		int numTarea = 0;
		while((runtime = Integer.parseInt(br.readLine())) != -1) { 
			datosTareas[numTarea][0] =  runtime;
			numTarea++;
		} 
		fr1.close(); 
	    
	    FileReader fr2 = new FileReader("nproc.txt"); 
		BufferedReader br2 = new BufferedReader(fr2); 
		int nproc; 
		numTarea = 0;
		while((nproc = Integer.parseInt(br2.readLine())) != -1) { 
			datosTareas[nproc][1] =  runtime;
			numTarea++;
		} 
		fr2.close(); 
	    
	    menuBroker();
	    
	    Random random = new Random();
	    int procMenorNA = 0;
	    int procMenorND = 0;
	    
        int numProcesadores = procesadores.length;
        
        //	Estadísticos y acumulados
        double promTareasEnFila = 0.0;
        double promTiempoSistema = 0.0;
        int numTareas = 0;
        double makespan = 0.0;
        
		nextArrival+=StdRandom.exp(lambda);
        //Inicia simulacion 
        int contador=0;
        while ((contador < ITERACIONES)||!(filasVacias)) {
        	filasVacias = lasFilasVacias();
           	//System.out.println("Step: "+ contador +"//////////////////////////////////////////////");
            //scan.nextInt();
            
            
            nextDeparture = Double.POSITIVE_INFINITY;
            for(int i=0; i<numProcesadores; i++){
            	//System.out.println("nextDep del xcpu: "+procesadores[i].getNextDeparture());
                if (procesadores[i].getNextDeparture() < nextDeparture){
                    nextDeparture = procesadores[i].getNextDeparture();
					procMenorND = i;
				}
            }
			//System.out.println("nextArrival: "+ nextArrival);
			//System.out.println("nextDeparture: "+ nextDeparture);
			
            // Llegada al sistema
            if (nextArrival <= nextDeparture) {
	            contador++;
                Tarea t = new Tarea(contador, random.nextInt(numProcesadores)+1, nextArrival);    
                //System.out.println("Tarea asignada con ID " + t.getId());
                //Verificar que se cuenta con el numero de procesadores necesarios
                //para atender la tarea
                
                for(int i = 0; i<numProcesadores; i++){
        			double ta;
        			if(procesadores[i].getTareaEnEjecucion() == null){
        				ta = 0;
        			}
        			else{
        				ta = nextArrival - procesadores[i].getTareaEnEjecucion().getSalidaFila();
        			}
        			procesadores[i].setTa(ta);
				}
                
                if(t.getNum()<=numProcesadores){
                	//Asignar tarea a un procesador
                	broker.asignaTarea(t);
                }
                else{

                	//System.out.println("Tarea excede num de proc.");
                		
                }
                
                //para calcular makespan
                
                
                nextArrival+=StdRandom.exp(lambda);
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
            						procesadores[i].setTareaEnEjecucion(procesadores[i].popTarea());
            						procesadores[i].setContando(false);
            						if (procesadores[i].getTiempoFila()>makespan){
            							makespan = procesadores[i].getTiempoFila();
            						}
            						procesadores[i].getTareaEnEjecucion().setSalidaFila(nextDeparture);
            						numTareas++;
            						promTiempoSistema += procesadores[i].getTareaEnEjecucion().getTiempoEnFila();
            						//System.out.println("Tiempo en fila = " + promTiempoSistema);
            						
            						if(procesadores[i].isEmpty()){
            							procesadores[i].setNextDeparture(Double.POSITIVE_INFINITY);
            						}
            						else{
            							procesadores[i].setNextDeparture(nextArrival + StdRandom.exp(mu));
            						}
            					}
            				}
            				else if (procesadores[i].getContando()){
            					
            				}
            			}
            			//System.out.println("Trono muchas tareas");
            		}
            		else{
            			procesadores[procMenorND].setNextDeparture(procesadores[procMenorND].getNextDeparture()+StdRandom.exp(mu));
            		}
            	}
            	else{
            		procesadores[procMenorND].setTareaEnEjecucion(procesadores[procMenorND].popTarea());
            		procesadores[procMenorND].setContando(false);
            		if (procesadores[procMenorND].getTiempoFila()>makespan){
            			makespan = procesadores[procMenorND].getTiempoFila();
            		}
            		procesadores[procMenorND].getTareaEnEjecucion().setSalidaFila(nextDeparture);
            		numTareas++;
            		promTiempoSistema += procesadores[procMenorND].getTareaEnEjecucion().getTiempoEnFila();
            		//System.out.println("Tiempo en fila del ELSE = " + promTiempoSistema);
            		if (procesadores[procMenorND].isEmpty()){
		            	procesadores[procMenorND].setNextDeparture(Double.POSITIVE_INFINITY);
		            }
		            else{
		            	procesadores[procMenorND].setNextDeparture(procesadores[procMenorND].getNextDeparture() + StdRandom.exp(mu) );
		            }
         
            	}
            
                //double wait = nextDeparture - procesadores[procMenorND].popTarea().getArrival();
                //StdOut.printf("Wait = %6.2f, queue size = %d\n", wait, q.size());

                
                
            }
            
            
            for(int i = 0; i<numProcesadores; i++){
				//System.out.println("Procesador " + i);
				//System.out.println("\tTamaño de fila " + procesadores[i].queueSize());
				promTareasEnFila+=procesadores[i].queueSize();
			}

        }
        /*
        for(int i = 0; i<numProcesadores; i++){
			System.out.println("Procesador " + i);
			System.out.println("\tTamaño de fila " + procesadores[i].queueSize());
				
		}
		*/
        System.out.println("==========================================");
        System.out.println("Desempeño de la estrategia de scheduling ");
       	System.out.println("==========================================");
       	System.out.println("Promedio de tareas en fila: " + (promTareasEnFila/ITERACIONES) / numProcesadores);
       	System.out.println("Promedio de tiempo en sistema: " + (promTiempoSistema/numTareas));
		System.out.println("Makespan: " + makespan/3600 + " horas");
    }
    
    public static boolean lasFilasVacias(){
    	for(int i = 0; i<procesadores.length; i++){
    		if(!(procesadores[i].isEmpty()))
    			return false;
    	}
    	return true;
    }
}
