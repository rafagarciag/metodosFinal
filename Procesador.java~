public class Procesador{

	private boolean ocupado;
    
    private double nextArrival, nextDeparture;
    
	Queue<Tarea> q;
    
	public Procesador(){
		ocupado = false;
		q = new Queue<Tarea>();
		nextArrival = Double.POSITIVE_INFINITY;
		nextDeparture = Double.POSITIVE_INFINITY;
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
	
	
	
	public void setNextArrival(double nextArrival){      
		this.nextArrival = nextArrival;     
	}
	
	public double getNextArrival(){		
		return this.nextArrival;
	}
	
	
	public void setNextDeparture(double nextDeparture){
		this.nextDeparture = nextDeparture;
	}
	
	public double getNextDeparture(){
		return this.nextDeparture;
	}	
}
