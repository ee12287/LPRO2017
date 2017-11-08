package cli;


import logic.Mapa;

public class Main {

	public static void main(String[] args) {

		Mapa mapaObject = Mapa.getInstance();
			
			Game gameObject = new Game();
			
			mapaObject.setNumDragons(gameObject.Dificulty());
			mapaObject.InsereDragoes();
			mapaObject.ColocaElementos();
			

			while (true) {
				mapaObject.printMap();
				
				if (mapaObject.getHeroObject().getWin() == 1) {
					System.out.print("\nGanhou sabe?\n");
					break;
				}
				
				gameObject.Iteracao();
				
				if (mapaObject.getHeroObject().getWin() == -1) {
					mapaObject.printMap();
					System.out.print("\nPerdeu sabe?\n");
					break;
				}
			}

	}

}
