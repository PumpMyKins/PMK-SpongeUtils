package fr.pmk_spongeutils.scheduler;

import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import fr.pmk_spongeutils.MainSpongeUtils;

public class AntiLagsScheduler {

	public static int secondeTimer = 600;
	
	public void startClear() {
		// TODO Auto-generated method stub
		Task.Builder taskBuilder = Task.builder();
		
		System.out.println("task start");
		
		taskBuilder.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("antilag clear");
		    	
		    	Game game = Sponge.getGame();
		    	
		    	game.getServer().getBroadcastChannel().send(Text.builder("[WARNING] Anti-lags : clear des entités").color(TextColors.RED).toText());
		    	
		    	game.getCommandManager().process(game.getServer().getConsole(), "killall monsters");
		    	game.getCommandManager().process(game.getServer().getConsole(), "killall items");
			}
		}			
		).delay(secondeTimer, TimeUnit.SECONDS).interval(secondeTimer, TimeUnit.SECONDS).submit(MainSpongeUtils.getInstace());
		
	}
	
	public void startPreventFirst() {
		// TODO Auto-generated method stub
		Task.Builder taskBuilder = Task.builder();
		
		System.out.println("task start");
		
		taskBuilder.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("antilag clear");
		    	
		    	Game game = Sponge.getGame();
		    	
		    	game.getServer().getBroadcastChannel().send(Text.builder("[WARNING] Anti-lags : clear des entités dans 60 secondes").color(TextColors.RED).toText());
		    	
			}
		}			
		).delay((secondeTimer-60), TimeUnit.SECONDS).interval(secondeTimer, TimeUnit.SECONDS).submit(MainSpongeUtils.getInstace());
		
	}
	
	public void startPreventSecond() {
		// TODO Auto-generated method stub
		Task.Builder taskBuilder = Task.builder();
		
		System.out.println("task start");
		
		taskBuilder.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("antilag clear");
		    	
		    	Game game = Sponge.getGame();
		    	
		    	game.getServer().getBroadcastChannel().send(Text.builder("[WARNING] Anti-lags : clear des entités dans 20 secondes").color(TextColors.RED).toText());
		    	
			}
		}			
		).delay((secondeTimer-20), TimeUnit.SECONDS).interval(secondeTimer, TimeUnit.SECONDS).submit(MainSpongeUtils.getInstace());
		
	}
	
}
