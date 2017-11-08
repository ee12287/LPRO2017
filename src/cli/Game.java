package cli;

import java.util.Scanner;

import logic.Movimento;

public class Game {
	private static int l = 0, c = 0;
	static Scanner comand = new Scanner(System.in);

	private static final String KEY_UP = "w";
	private static final String KEY_DOWN = "s";
	private static final String KEY_RIGHT = "d";
	private static final String KEY_LEFT = "a";
	private static final String KEY_STILL = "p";
	private static final String INVALID = "invalido";

	private int NumDragons = 10;

	Movimento movimentoObject = new Movimento();

	/*
	 * Questiona o numero de dragões ao utilizador e retorna o Int com o valor
	 * System.out.print("\n"); é usado pois na iteração feita a seguir o
	 * comand.nextLine faz skip pois aqui é feito um nextInt
	 */
	public int Dificulty() {
		System.out.print("Quantos Dragões quer? (max 5) ");
		while (NumDragons > 5) {
			NumDragons = comand.nextInt();
			if (NumDragons > 5){
				System.out.print("Numero maximo de dragões permitidos é 5\n\nInsira um numero válido: ");
			}
		}
		System.out.print("\n");
		comand.nextLine();
		return NumDragons;

	}

	/*
	 * tenho de alterar isto para nao imprimir duas x, mais acrescentear arrayList
	 * para multiplos dragões
	 */
	public void Iteracao() {

		while (true) {

			l = 0;
			c = 0;

			String comando = comand.nextLine();

			switch (comando) {
			case KEY_UP:
				l = -1;
				break;

			case KEY_DOWN:
				l = 1;
				break;

			case KEY_RIGHT:
				c = 1;
				break;

			case KEY_LEFT:
				c = -1;
				break;

			case KEY_STILL:
				break;

			default:
				comando = INVALID;
			}

			if (comando != INVALID) {
				break;
			} else {
				System.out.print("Insira um comando válido (w,a,s,d,p)");
			}

		}

		movimentoObject.movement(l, c);

	}
}
