import java.lang.Math.*;

public class Procesador{

	private boolean ocupado;
    
    private double  nextDeparture;
    
    private Tarea tareaEnEjecucion;
    
	Queue<Tarea> q;
	
	private double tiempoFila;
	private boolean contando = false;
	
	//	===================================
	//	Parametros para pareto-fractal
	//	===================================
		//Tiempo de ejecucion de la tarea actual
		private double ta = 1.0;
		
		//Razon de salida
		private static double miu = Simulacion.mu;
		
		//Probabilidad de que el tiempo sea mayor que cierto valor
		private static double phi = 0.05;
		
		//Parametro de localizacion
		private static double paramA = 600.0;
		
		//Parametro de forma
		private static double a = 1.58;
		
		//Ache
		private static double paramH = 0.8;
		
		
	//	=======================================


	public Procesador(){
		ocupado = false;
		q = new Queue<Tarea>();
		nextDeparture = Double.POSITIVE_INFINITY;
		tareaEnEjecucion = null;
    }

    public double paretoParam(){
    	//Estimado del tiempo de ejecucion de las tareas en fila
		double tf = 0.0;

		//Estimado del tiempo de ejecución de la tarea en procesamiento
		double tp = 0.0;

		//Numero de tareas en procesador
		int n = q.size();

		tf = (n*miu) + (Math.pow((paramA*a), paramH) / Math.pow((phi), (1/a)));

		tp = ta * (Math.pow(phi, (-1/a)) - 1);

		return tf + tp;
    }
    
    public static void sortByPareto(){
    	int i, j=0;
    	int n = Simulacion.procesadores.length;
    	Procesador aux = new Procesador();
    	
    	for(i=0; i<n; i++){
			for(j=1; j<(n-1); j++){
				if(Simulacion.procesadores[j-1].paretoParam() > Simulacion.procesadores[j].paretoParam()){
					aux = Simulacion.procesadores[j-1];
					Simulacion.procesadores[j-1]=Simulacion.procesadores[j];
					Simulacion.procesadores[j]=aux;					
				}
			}
    	}
    }
    
    public static void sortByQueueSize(){
    	int i, j=0;
    	int n = Simulacion.procesadores.length;
    	Procesador aux = new Procesador();
    	
    	for(i=0; i<n; i++){
			for(j=1; j<(n-1); j++){
				if(Simulacion.procesadores[j-1].queueSize() > Simulacion.procesadores[j].queueSize()){
					aux = Simulacion.procesadores[j-1];
					Simulacion.procesadores[j-1]=Simulacion.procesadores[j];
					Simulacion.procesadores[j]=aux;					
				}
			}
    	}
    }
     
    public void pushTarea(Tarea t){
    	q.enqueue(t);
	}
     
    public Tarea peekTarea(){    
    	return q.peek();   
    }
    
    public Tarea popTarea(){
    	return q.dequeue();
    }
     
    public boolean isEmpty(){
    	return q.isEmpty();
    }
    
    public int queueSize(){
    	return q.size();
    }
   
	public void setOcupado(boolean ocupado){
		this.ocupado = ocupado;
	}
	
	public boolean getOcupado(){	
		return this.ocupado;
	}
	
	public void setNextDeparture(double nextDeparture){
		this.nextDeparture = nextDeparture;
	}
	
	public double getNextDeparture(){
		return this.nextDeparture;
	}
	
	public double getTa(){
		return ta;
	}
	
	public void setTa(double ta){
		this.ta = ta;
	}
		public Tarea getTareaEnEjecucion(){
		return tareaEnEjecucion;
	}
	
	public void setTareaEnEjecucion(Tarea tareaEnEjecucion){
		this.tareaEnEjecucion = tareaEnEjecucion;
	}
	
	public double getTiempoFila(){
		return tiempoFila;
	}
	
	public void setTiempoFila(double t){
		tiempoFila = t;
	}
	
	public boolean getContando(){
		return contando;
	}
	
	public void setContando(boolean contando){
		this.contando = contando;
	}
}
