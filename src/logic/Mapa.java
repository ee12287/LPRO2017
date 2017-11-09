package logic;
import java.util.ArrayList;

public final class Mapa {

	private String[][] mapa = { 
			{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
			{ "X", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "X" }, 
			{ "X", "  ", "X", "X", "  ", "X", "  ", "X", "  ", "X" },
			{ "X", "  ", "X", "X", "  ", "X", "  ", "X", "  ", "X" }, 
			{ "X", "  ", "X", "X", "  ", "X", "  ", "X", "  ", "X" },
			{ "X", "  ", "  ", "  ", "  ", "  ", "  ", "X", "  ", "X" }, 
			{ "X", "  ", "X", "X", "  ", "X", "  ", "X", "  ", "X" },
			{ "X", "  ", "X", "X", "  ", "X", "  ", "X", "  ", "X" }, 
			{ "X", "  ", "X", "X", "  ", "  ", "  ", "  ", "  ", "X" },
			{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" } };

	private Hero heroObject = new Hero();
	
	private Exit exitObject = new Exit();
	private Sord sordObject = new Sord();
	private int NumDragons;
	private ArrayList<Dragon> list = new ArrayList<Dragon>();
	//private Dragon dragonObject = new Dragon();
	
	/*cria objetos tipo dragão e coloca na lista*/
	public void InsereDragoes() {

		for (int i = 0; i < NumDragons; i++) {
			// String DragonObjectString = ("DragonObject" + i).toString();
			// Dragon DragonObject = new Dragon();
			Dragon aux = new Dragon();
			list.add(aux);

		}
	}
	
		/**
	 * Singleton para garantir que chamamos sempre o mesmo objeto Mapa
	 */
	private static final Mapa INSTANCE = new Mapa();

	private Mapa() {

	}

	public static Mapa getInstance() {
		return INSTANCE;
	}

	public String[][] getMapa() {
		return this.mapa;
	}

	public void ColocaElementos() {

		heroObject.ColocaHeroi();
		
		
			/*percorre a lista de objetos tipo dragao e chama a função coloca para cada um deles (supostamente)*/
		for(int i=0; i<list.size(); i++) {

			list.get(i).ColocaDragao();
		}
			
		exitObject.ColocaSaida();

		sordObject.ColocaEspada();
	}

	public void printMap() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(mapa[i][j] + " ");
			}
			System.out.println();

		}

	}
	

	public Hero getHeroObject() {
		return this.heroObject;
	}

	public void setMapa(String[][] mapa) {
		this.mapa = mapa;
	}

	public void setHeroObject(Hero heroObject) {
		this.heroObject = heroObject;
	}

	public void setNumDragons(int numDragons) {
		NumDragons = numDragons;
		heroObject.setFoundIt(-numDragons);
	}
	
	public void removeElements() {
		
		exitObject.remove();
		sordObject.remove();
		heroObject.remove();
		
		for(int i=0; i<list.size(); i++) {
			//mapaHero.setFoundIt(1);
			list.get(i).RemoveDragon();
			
			
		}		
			list.clear();
	}

	/*retorna a lista*/
	public ArrayList<Dragon> getList() {
		return list;
	}

}
