public class RoundRobin extends Broker{
	public static int actual = 0;
	int proc = 0;
	
	public void asignaTarea(Tarea t){
	
		for(int i=t.getNum();i>0;i--){
			proc= (actual++)%Simulacion.procesadores.length;
			
			if(Simulacion.procesadores[proc].isEmpty()){
				//Hacer set de departure
			}
			
			Simulacion.procesadores[proc].pushTarea(t);
			System.out.println("Se metio la tarea al Proc: "+proc);
		}
		
	}
}
