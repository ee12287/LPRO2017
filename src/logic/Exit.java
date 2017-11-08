package logic;



public class Exit extends Elements {
	
	
	public void ColocaSaida() {
		Mapa mapaObject = Mapa.getInstance();
		String[][] mapaVector = mapaObject.getMapa();
		
		int exit=0;
		while (exit != 1) {
			int random_l = 0 + (int) (Math.random() * 9);
			int random_c = 0 + (int) (Math.random() * 9);
			if ((random_l - random_c) == 0 || (random_l - random_c) == 9 || (random_l - random_c) == -9)
				continue;
			// ficar nos cantos

			if (random_c == 0 || random_c == 9) {
				mapaVector[random_l][random_c] = Variables.PIN_EXIT ;
				exit = 1;
			} else if (random_l == 0 || (random_l == 9 && (random_c != 2 && random_c != 3))) {
				mapaVector[random_l][random_c] = Variables.PIN_EXIT;
				exit = 1;
			}
			// ficar so nas arestas
			
			Vector[0] = random_l;
			Vector[1] = random_c;
		}
		
		mapaObject.setMapa(mapaVector);
		
	}
	
	public void remove() {
		Mapa mapaObject = Mapa.getInstance();
		String[][] mapaVector = mapaObject.getMapa();
		
		mapaVector[Vector[0]][Vector[1]] = Variables.PIN_WALL;
		
		
	}
}