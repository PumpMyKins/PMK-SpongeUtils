package fr.pmk_spongeutils.xpbonus;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.ChangeEntityExperienceEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent.Disconnect;

public class XpBonusListener {

	public static int bonus;
	
	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join e) {
		
		bonus += calculBonus(e.getTargetEntity());		
		
	}
	
	@Listener
	public void OnPlayerQuit(Disconnect e) {
		
		bonus -= calculBonus(e.getTargetEntity());
		
	}
	
	@Listener
	public void OnPlayerGetXP(ChangeEntityExperienceEvent e) {
		
		if(e.getTargetEntity() instanceof Player) {
			
			Player p = (Player) e.getTargetEntity();
			
			int exp = e.getOriginalExperience();
			int newExp = e.getExperience();
			
			int xp = newExp - exp;
			
			xp += xp * (bonus / 10);
			
			p.offer(Keys.TOTAL_EXPERIENCE, (newExp + xp));
			
		}
		
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
