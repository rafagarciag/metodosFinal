public class Procesador{

	private boolean ocupado;
    
    private double nextArrival, nextDeparture
    
	Queue<Double> q;
    
	public Procesador(){
		ocupado = false;
		q = new Queue<Double>();
    }
     
    public void pushTarea(Double t){
    	q.enqueue(t);    
    }
     
    public double peekTarea(){    
    	return q.peek();    
    }
    
    public double popTarea(){
    	return q.dequeue();
    }
     
    public boolean isEmpty(){
    	return q.isEmpty();
    }
   
   
   
	public void setOcupado(boolean ocupado){
		this.ocupado = ocupado;
	}
	
	public boolean getOcupado(){	
		return this.ocupado;
	}
	
	
	
	public void setNextArrival(boolean nextArrival){      
		this.nextArrival = nextArrival;     
	}
	
	public boolean getNextArrival(){		
		return this.nextArrival;
	}
	
	
	public void setNextDeparture(boolean nextDeparture){
		this.nextDeparture = nextDeparture;
	}
	
	public boolean getNextDeparture(){
		return this.ocupado;
	}	
}
