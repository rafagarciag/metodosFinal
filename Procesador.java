public class Procesador{

	private boolean ocupado;
    
	Queue<Double> q;
    
	public Procesador(){
    
		ocupado = false;
		q = new Queue<Double>();
		
    }
    
	public void setOcupado(boolean ocupado){
            
		this.ocupado = ocupado;
           
	}
	
	public boolean getOcupado(){
		
		return this.ocupado;
		
	}
	
}
