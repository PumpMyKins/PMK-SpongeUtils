package fr.pmk_spongeutils.scheduler;

import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;

import fr.pmk_spongeutils.MainSpongeUtils;

public class ScheduleFTB {

	public void start() {
		// TODO Auto-generated method stub
		Task.Builder taskBuilder = Task.builder();
		
		System.out.println("task start");
		
		taskBuilder.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Actualisation FTB Utilities");
		    	
		    	Game game = Sponge.getGame();
		    	game.getCommandManager().process(game.getServer().getConsole(), "ftb reload");
			}
		}			
		).delay(10, TimeUnit.SECONDS).interval(10, TimeUnit.MINUTES).submit(MainSpongeUtils.getInstace());
		
	}

}
