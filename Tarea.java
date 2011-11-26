public class Tarea {

private int id;
private int num;
private double arrival;

	public Tarea(int id, int num, double arrival){
		this.id=id;
		this.num=num;
		this.arrival=arrival;
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
}
