package logic;

import java.util.ArrayList;

public class Dragon extends Elements {

	
	private String[][] mapaVector;

	public void ColocaDragao() {
		Mapa mapaObject = Mapa.getInstance();

		
		boolean dragonPositionAux = false;
		mapaVector = mapaObject.getMapa();

		while (!dragonPositionAux) {
			int random_l = 1 + (int) (Math.random() * 8);
			int random_c = 1 + (int) (Math.random() * 8);

			if (mapaVector[random_l][random_c] == Variables.PIN_HALL
					&& mapaVector[random_l + 1][random_c] != Variables.PIN_HERO
					&& mapaVector[random_l - 1][random_c] != Variables.PIN_HERO
					&& mapaVector[random_l][random_c + 1] != Variables.PIN_HERO
					&& mapaVector[random_l][random_c - 1] != Variables.PIN_HERO) {

				mapaVector[random_l][random_c] = Variables.PIN_DRAG;

				mapaObject.setMapa(mapaVector);

				// dragonVector[0] = random_l;
				Vector[0] = random_l;
				Vector[1] = random_c;
				// dragonVector[1] = random_c;

				dragonPositionAux = true;
			}
		}
	}

	public int[] getDragonVector() {
		return Vector;
	}

	public void setDragonVector(int[] dragonVector) {
		this.Vector = dragonVector;

	}
	
	public void RemoveDragon() {
		
		Mapa mapaObject = Mapa.getInstance();
		String[][] mapaVector = mapaObject.getMapa();

		mapaVector[Vector[0]][Vector[1]] = Variables.PIN_HALL;
		         
		
	}

	public void MoveDragao() {

		Mapa mapaObject = Mapa.getInstance();
		mapaVector = mapaObject.getMapa();
		Hero mapaHero = mapaObject.getHeroObject();
		ArrayList<Dragon> list = mapaObject.getList();

		// variável que indica se o dragão está a dormir 0 -> acordado, 1 -> a dormir
		int randonSleep = (1 + (int) (Math.random() * 2) - 1);
		// int randonSleep = 1;

		// Caso o esteja a dormir, atualizamos o mapa e termina-se a execução do
		// método com return
		if (randonSleep == 1 && mapaVector[Vector[0]][Vector[1]] != Variables.PIN_DRAG_SWRD) {

			mapaVector[Vector[0]][Vector[1]] = Variables.PIN_DRAG_SLEEP;
			mapaObject.setMapa(mapaVector);
			return;

		}

		int dragon_moved = 0;
		while (dragon_moved != 1) {
			int random_l = (1 + (int) (Math.random() * 3)) - 2;
			int random_c = (1 + (int) (Math.random() * 3)) - 2;
			//////////////////////////////////////////////////////////////////////////////////////////////////////

			if (random_l - random_c != 1 && random_l - random_c != -1)
				continue;

			if (mapaVector[Vector[0] + random_l][Vector[1] + random_c] == Variables.PIN_HALL) {

				if (mapaVector[Vector[0]][Vector[1]] == Variables.PIN_DRAG_SWRD)
					mapaVector[Vector[0]][Vector[1]] = Variables.PIN_SWRD;
				else
					mapaVector[Vector[0]][Vector[1]] = Variables.PIN_HALL;

				Vector[0] = Vector[0] + random_l;
				Vector[1] = Vector[1] + random_c;

				mapaVector[Vector[0]][Vector[1]] = Variables.PIN_DRAG;
				mapaObject.setMapa(mapaVector);

				dragon_moved = 1;

			} else if (mapaVector[Vector[0] + random_l][Vector[1] + random_c] == Variables.PIN_SWRD) {

				mapaVector[Vector[0]][Vector[1]] = Variables.PIN_HALL;
				Vector[0] = Vector[0] + random_l;
				Vector[1] = Vector[1] + random_c;

				mapaVector[Vector[0]][Vector[1]] = Variables.PIN_DRAG_SWRD;
				mapaObject.setMapa(mapaVector);

				dragon_moved = 1;
			}
		}

		mapaVector = mapaObject.getMapa();

		if ((mapaVector[Vector[0] + 1][Vector[1]] == Variables.PIN_HERO
				|| mapaVector[Vector[0] - 1][Vector[1]] == Variables.PIN_HERO
				|| mapaVector[Vector[0]][Vector[1] + 1] == Variables.PIN_HERO
				|| mapaVector[Vector[0]][Vector[1] - 1] == Variables.PIN_HERO)) {
			mapaHero.setWin(-1);
			mapaVector[mapaHero.getHeroVector()[0]][mapaHero.getHeroVector()[1]] = Variables.PIN_HERO_DEAD;
			mapaObject.setMapa(mapaVector);
		}

		else if ((mapaVector[Vector[0] + 1][Vector[1]] == Variables.PIN_HERO_ARM
				|| mapaVector[Vector[0] - 1][Vector[1]] == Variables.PIN_HERO_ARM
				|| mapaVector[Vector[0]][Vector[1] + 1] == Variables.PIN_HERO_ARM
				|| mapaVector[Vector[0]][Vector[1] - 1] == Variables.PIN_HERO_ARM)) {

			mapaHero.setFoundIt(1);

			mapaVector[Vector[0]][Vector[1]] = Variables.PIN_HALL;
			mapaObject.setMapa(mapaVector);
			list.remove(this);
		}

	}
}