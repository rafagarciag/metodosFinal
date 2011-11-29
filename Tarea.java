public class Tarea {

private int id;
private int num;
private double arrival, salidaFila;

	public Tarea(int id, int num, double arrival){
		this.id=id;
		this.num=num;
		this.arrival=arrival;
		this.salidaFila = 0;
	}
	
	public int getId(){
		return id;
	}

	public int getNum(){
		return num;
	}
	
	public double getArrival(){
		return arrival;
	}
	
	public double getSalidaFila(){
		return salidaFila;
	}
	
	public void setSalidaFila(Double salidaFila){
		this.salidaFila = salidaFila;
	}
	
	public double getTiempoEnFila(){
		return salidaFila-arrival;
	}
}
