public class Tarea {

private int id;
private double num;
private double arrival, salidaFila;
private double duracion;

	public Tarea(int id, double num, double duracion,  double arrival){
		this.id=id;
		this.num=num;
		this.duracion=duracion;
		this.arrival=arrival;
		this.salidaFila = 0;
	}
	
	public int getId(){
		return id;
	}

	public double getNum(){
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
	
	public double getDuracion(){
		return duracion;
	}
}
