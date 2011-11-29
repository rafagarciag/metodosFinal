public class Pareto extends Broker{
	
	public void asignaTarea(Tarea t){
		Procesador.sortByPareto();
		
		for(int i=0; i<t.getNum(); i++){
			
			if(Simulacion.procesadores[i].isEmpty()){
				//Hacer set de departure
				Simulacion.procesadores[i].setNextDeparture(Simulacion.nextArrival + Simulacion.procesadores[i].getDuracionTE());
			}
				Simulacion.procesadores[i].pushTarea(t);
				//System.out.println("Se metio la tarea al Proc: "+i);
		}
		
	}
}
