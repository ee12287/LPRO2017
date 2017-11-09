package logic;

import java.util.ArrayList;

public class Hero extends Elements {

	private String heroState = Variables.PIN_HERO;
	private int foundIt = 0;

	private boolean armed = false;
	private int win = 0;
	private boolean continueAux = false;

	public void ColocaHeroi() {
		Mapa mapaObject = Mapa.getInstance();

		String[][] mapaVector = mapaObject.getMapa();

		int hero = 0;

		while (hero != 1) {
			int random_l = 1 + (int) (Math.random() * 8);
			int random_c = 1 + (int) (Math.random() * 8);

			if (mapaVector[random_l][random_c] == Variables.PIN_HALL) {

				mapaVector[random_l][random_c] = Variables.PIN_HERO;
				mapaObject.setMapa(mapaVector);

				Vector[0] = random_l;
				Vector[1] = random_c;
				hero = 1;
			}
		}
	}

	public void MoveHeroi(int l, int c) {
		Mapa mapaObject = Mapa.getInstance();
		String[][] mapaVector = mapaObject.getMapa();
		ArrayList<Dragon> list = mapaObject.getList();

		continueAux = false;

		if (mapaVector[Vector[0] + l][Vector[1] + c] != Variables.PIN_WALL) {

			/************************************
			 * HEROI
			 ************************************************/

			if (mapaVector[Vector[0] + l][Vector[1] + c] == Variables.PIN_SWRD) {
				armed = true;
				heroState = Variables.PIN_HERO_ARM;
			}
			if (mapaVector[Vector[0] + l][Vector[1] + c] == Variables.PIN_EXIT && foundIt == 0) {
				win = 1;
			}

			else if ((mapaVector[Vector[0] + l][Vector[1] + c] == Variables.PIN_EXIT)
					|| (mapaVector[Vector[0] + l][Vector[1] + c] == Variables.PIN_DRAG_SLEEP)) {
				continueAux = true;
			}

			if (!continueAux && win != 1) {
				mapaVector[Vector[0]][Vector[1]] = Variables.PIN_HALL;

				Vector[0] = Vector[0] + l;
				Vector[1] = Vector[1] + c;

				/************************************
				 * DRAG√O
				 ************************************************/

				if (heroState != Variables.PIN_HERO_ARM && (mapaVector[Vector[0] + 1][Vector[1]] == Variables.PIN_DRAG
						|| mapaVector[Vector[0] - 1][Vector[1]] == Variables.PIN_DRAG
						|| mapaVector[Vector[0]][Vector[1] + 1] == Variables.PIN_DRAG
						|| mapaVector[Vector[0]][Vector[1] - 1] == Variables.PIN_DRAG
						|| mapaVector[Vector[0] + 1][Vector[1]] == Variables.PIN_DRAG_SWRD
						|| mapaVector[Vector[0] - 1][Vector[1]] == Variables.PIN_DRAG_SWRD
						|| mapaVector[Vector[0]][Vector[1] + 1] == Variables.PIN_DRAG_SWRD
						|| mapaVector[Vector[0]][Vector[1] - 1] == Variables.PIN_DRAG_SWRD)) {
					win = -1;
					heroState = Variables.PIN_HERO_DEAD;

				}

				// Verificar qualquer frag√£o, a dormir ou acordado. Usado o .toUpperCase() para
				// por sempre o pin do drag√£o a "D"
				else if (heroState == Variables.PIN_HERO_ARM
						&& (mapaVector[Vector[0] + 1][Vector[1]] == Variables.PIN_DRAG
								|| mapaVector[Vector[0] - 1][Vector[1]] == Variables.PIN_DRAG
								|| mapaVector[Vector[0]][Vector[1] + 1] == Variables.PIN_DRAG
								|| mapaVector[Vector[0]][Vector[1] - 1] == Variables.PIN_DRAG
								|| mapaVector[Vector[0] + 1][Vector[1]] == Variables.PIN_DRAG_SLEEP
								|| mapaVector[Vector[0] - 1][Vector[1]] == Variables.PIN_DRAG_SLEEP
								|| mapaVector[Vector[0]][Vector[1] + 1] == Variables.PIN_DRAG_SLEEP
								|| mapaVector[Vector[0]][Vector[1] - 1] == Variables.PIN_DRAG_SLEEP

						)) {
					foundIt--;

					// Dragaoo √© morto
					/*
					 * faz todas as hipoteses de periferia do heroi e verifica se coincide com
					 * qualquer um dos dragıes, coordenadas sao comparadas individualmente mas tem
					 * de ser simultaneas &&
					 */
					for (int i = 0; i < list.size(); i++) {
						if ((((Vector[0] + 1) == list.get(i).getDragonVector()[0])
								&& ((Vector[1]) == list.get(i).getDragonVector()[1]))

								|| (((Vector[0] - 1) == list.get(i).getDragonVector()[0])
										&& ((Vector[1]) == list.get(i).getDragonVector()[1]))

								|| (((Vector[0]) == list.get(i).getDragonVector()[0])
										&& ((Vector[1] + 1) == list.get(i).getDragonVector()[1]))

								|| (((Vector[0]) == list.get(i).getDragonVector()[0])
										&& ((Vector[1] - 1) == list.get(i).getDragonVector()[1]))) {

							/*
							 * se se verificar a condiÁ„o no mapa mete " " na posiÁ„o do drag„o 'i' e remove
							 * esse drag„o da lista (supostamente)
							 */
							mapaVector[list.get(i).getDragonVector()[0]][list.get(i)
									.getDragonVector()[1]] = Variables.PIN_HALL;
							list.remove(i);
						}
					}

				}

			}
			mapaVector[Vector[0]][Vector[1]] = heroState;
		}

		mapaObject.setMapa(mapaVector);

	}
	
	public void remove() {
		heroState = Variables.PIN_HERO;
		foundIt = 0;
		armed = false;
		win = 0;
		continueAux = false;
		
		Mapa mapaObject = Mapa.getInstance();
		String[][] mapaVector = mapaObject.getMapa();
		
		mapaVector[Vector[0]][Vector[1]] = Variables.PIN_HALL;
		
		
	}

	public int getWin() {
		return win;
	}

	public int[] getHeroVector() {
		return Vector;
	}

	public void setHeroVector(int[] heroVector) {
		this.Vector = heroVector;
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int isFoundIt() {
		return foundIt;
	}

	public void setFoundIt(int foundIt) {
		this.foundIt = this.foundIt - foundIt;
	}

}