package logic;

import java.util.ArrayList;

public class Movimento {
	
	
	
	
	public void movement(int l, int c) {
		
		Mapa mapaObject = Mapa.getInstance();		
		Hero mapaHero = mapaObject.getHeroObject();		
		ArrayList<Dragon> list = mapaObject.getList();
		
		mapaHero.MoveHeroi(l, c);
		
		
		if(mapaHero.isFoundIt()!=0 && mapaHero.getWin() !=-1) {
			
			for(int i=0; i<list.size(); i++) {

				
				list.get(i).MoveDragao();
				//mapaObject.getDragonObject().MoveDragao();
			}
			
		}
	}
}
