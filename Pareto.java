public class Pareto extends Broker{
	
	public void asignaTarea(Tarea t){
		Procesador.sortByPareto();
		
		for(int i=0; i<t.getNum(); i++){
			
			if(Simulacion.procesadores[i].isEmpty()){
				//Hacer set de departure
				Simulacion.procesadores[i].setNextDeparture(Simulacion.nextArrival + StdRandom.exp(Simulacion.mu));
			}
			else if(!Simulacion.procesadores[i].isEmpty() && !Simulacion.procesadores[i].getContando()){
				Simulacion.procesadores[i].setTiempoFila(0.0);
				Simulacion.procesadores[i].setContando(true);
			}
			else if(Simulacion.procesadores[i].getContando()){
				Simulacion.procesadores[i].setTiempoFila(Simulacion.procesadores[i].getTiempoFila() + Simulacion.nextArrival);
			}
				Simulacion.procesadores[i].pushTarea(t);
				//System.out.println("Se metio la tarea al Proc: "+i);
		}
		
	}
}
