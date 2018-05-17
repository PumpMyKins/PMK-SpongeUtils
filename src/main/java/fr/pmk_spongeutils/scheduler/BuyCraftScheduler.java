package fr.pmk_spongeutils.scheduler;

import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;

import fr.pmk_spongeutils.MainSpongeUtils;

public class BuyCraftScheduler {

	public void start() {
		Task.Builder taskBuilder = Task.builder();
		
		taskBuilder.execute(new Runnable() {
			
			@Override
			public void run() {
		    	
		    	Game game = Sponge.getGame();
		    	game.getCommandManager().process(game.getServer().getConsole(), "buycraft forcecheck");
			}
		}			
		).delay(5, TimeUnit.MINUTES).interval(5, TimeUnit.MINUTES).submit(MainSpongeUtils.getInstace());
		
	}
	
}
