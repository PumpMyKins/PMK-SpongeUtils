package fr.pmk_spongeutils.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import fr.pmk_spongeutils.MainSpongeUtils;

public class AntiLagsScheduler {

	public static int secondeTimer = 900;
	
	public void startClear() {
		// TODO Auto-generated method stub
		Task.Builder taskBuilder = Task.builder();
		
		List<EntityType> list = new ArrayList<>();
		completeEntityTypes(list);
		
		System.out.println("task start");
		
		taskBuilder.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("antilag clear");
		    	
		    	Game game = Sponge.getGame();
		    	
		    	game.getServer().getBroadcastChannel().send(Text.builder("[WARNING] Anti-lags : clear des entités").color(TextColors.RED).toText());
		    	
		    	/*Collection<World> worldList = Sponge.getServer().getWorlds();
		    	
		    	for (World world : worldList) {
					Collection<Entity> eList = world.getEntities();
					
					for (Entity entity : eList) {
						
						EntityType t = entity.getType();
						
						if(!list.contains(t)) {
							entity.remove();
						}
						
					}
				}*/
		    	
		    	//game.getCommandManager().process(game.getServer().getConsole(), "killall monsters");
		    	game.getCommandManager().process(game.getServer().getConsole(), "killall items");
			}
		}			
		).delay(secondeTimer, TimeUnit.SECONDS).interval(secondeTimer, TimeUnit.SECONDS).submit(MainSpongeUtils.getInstace());
		
	}
	
	private void completeEntityTypes(List<EntityType> list) {
		// TODO Auto-generated method stub
		// player
		list.add(EntityTypes.PLAYER);
		
		// boss
		list.add(EntityTypes.ENDER_DRAGON);
		list.add(EntityTypes.WITHER);
		
		// ignored
		list.add(EntityTypes.ARMOR_STAND);
		list.add(EntityTypes.BAT);
		list.add(EntityTypes.BOAT);
		list.add(EntityTypes.CHESTED_MINECART);
		list.add(EntityTypes.CHICKEN);
		list.add(EntityTypes.COMPLEX_PART);
		list.add(EntityTypes.COW);
		list.add(EntityTypes.DONKEY);
		list.add(EntityTypes.DRAGON_FIREBALL);
		list.add(EntityTypes.EGG);
		list.add(EntityTypes.ELDER_GUARDIAN);
		list.add(EntityTypes.ENDER_CRYSTAL);
		list.add(EntityTypes.ENDER_PEARL);
		list.add(EntityTypes.EYE_OF_ENDER);
		list.add(EntityTypes.FISHING_HOOK);
		list.add(EntityTypes.FURNACE_MINECART);
		list.add(EntityTypes.FIREWORK);
		list.add(EntityTypes.HOPPER_MINECART);
		list.add(EntityTypes.HORSE);
		list.add(EntityTypes.HUMAN);
		list.add(EntityTypes.HUSK);
		list.add(EntityTypes.ITEM_FRAME);
		list.add(EntityTypes.ILLUSION_ILLAGER);
		list.add(EntityTypes.IRON_GOLEM);
		list.add(EntityTypes.LLAMA);
		list.add(EntityTypes.LEASH_HITCH);
		list.add(EntityTypes.LLAMA_SPIT);
		list.add(EntityTypes.MULE);
		list.add(EntityTypes.MOB_SPAWNER_MINECART);
		list.add(EntityTypes.MUSHROOM_COW);
		list.add(EntityTypes.OCELOT);
		list.add(EntityTypes.PAINTING);
		list.add(EntityTypes.PARROT);
		list.add(EntityTypes.PIG);
		list.add(EntityTypes.POLAR_BEAR);
		list.add(EntityTypes.PRIMED_TNT);
		list.add(EntityTypes.RABBIT);
		list.add(EntityTypes.RIDEABLE_MINECART);
		list.add(EntityTypes.SHEEP);
		list.add(EntityTypes.SHULKER);
		list.add(EntityTypes.SHULKER_BULLET);
		list.add(EntityTypes.SNOWBALL);
		list.add(EntityTypes.SMALL_FIREBALL);
		list.add(EntityTypes.SNOWMAN);
		list.add(EntityTypes.SPECTRAL_ARROW);
		list.add(EntityTypes.SPLASH_POTION);
		list.add(EntityTypes.SQUID);
		list.add(EntityTypes.STRAY);
		list.add(EntityTypes.THROWN_EXP_BOTTLE);
		list.add(EntityTypes.TIPPED_ARROW);
		list.add(EntityTypes.TNT_MINECART);
		list.add(EntityTypes.UNKNOWN);
		list.add(EntityTypes.VEX);
		list.add(EntityTypes.VILLAGER);
		list.add(EntityTypes.VINDICATION_ILLAGER);
		list.add(EntityTypes.WEATHER);
		list.add(EntityTypes.WITHER);
		list.add(EntityTypes.WITHER_SKELETON);
		list.add(EntityTypes.WITHER_SKULL);
		
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
		).delay((secondeTimer-120), TimeUnit.SECONDS).interval(secondeTimer, TimeUnit.SECONDS).submit(MainSpongeUtils.getInstace());
		
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
		).delay((secondeTimer-30), TimeUnit.SECONDS).interval(secondeTimer, TimeUnit.SECONDS).submit(MainSpongeUtils.getInstace());
		
	}
	
}
