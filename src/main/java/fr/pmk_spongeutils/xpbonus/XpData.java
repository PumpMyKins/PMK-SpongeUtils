package fr.pmk_spongeutils.xpbonus;

import org.spongepowered.api.entity.living.player.Player;

public class XpData {

	// bonus en pourcentage
	private int bonus;

	public XpData(int b) {
		this.bonus = b;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	
	public static int calculBonus(Player p) {
		
		if(p.hasPermission("bonus.xp1")) {
			
			//vip
			return 5;
			
		}else if(p.hasPermission("bonus.xp2")) {
			
			// vip +
			return 7;
			
		}else {
			
			return 0;
			
		}
		
	}
	
	
}
