public class RoundRobin extends Broker{
	public static int actual = 0;
	int proc = 0;
	
	public void asignaTarea(Tarea t){
	
		for(int i=t.getNum();i>0;i--){
			proc= (actual++)%Simulacion.procesadores.length;
			
			if(Simulacion.procesadores[proc].isEmpty()){
				Simulacion.procesadores[proc].setNextDeparture(Simulacion.nextArrival + StdRandom.exp(Simulacion.mu));
			}
			else if(!Simulacion.procesadores[proc].isEmpty() && !Simulacion.procesadores[proc].getContando()){
				Simulacion.procesadores[proc].setTiempoFila(0.0);
				Simulacion.procesadores[proc].setContando(true);
			}
			else if(Simulacion.procesadores[proc].getContando()){
				Simulacion.procesadores[proc].setTiempoFila(Simulacion.procesadores[proc].getTiempoFila() + Simulacion.nextArrival);
			}
			
			Simulacion.procesadores[proc].pushTarea(t);
			//System.out.println("Se metio la tarea al Proc: "+proc);
		}
		
	}
}
