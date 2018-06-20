package fr.pmk_spongeutils.commands;

import java.util.Optional;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class StewCommand implements CommandExecutor {	
	
	private ItemStack getIngotVariant(Game game) {
		
		ItemStack ingot = ItemStack.builder().itemType(game.getRegistry().getType(ItemType.class, "avaritia:resource").get()).build();
		
		DataContainer container = ingot.toContainer();
		container.set(DataQuery.of("UnsafeDamage"), 4);
		// If you want:
		DataView nbt = container.createView(DataQuery.of("UnsafeData"));
		// Stick the NBT data in there before the build() call.
		Optional<ItemStack> stack = ItemStack.builder().build(container);
		
		return stack.get();
		
	}
			
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// TODO Auto-generated method stub
		
		Game game = Sponge.getGame();
		
		ItemStack givestew = ItemStack.builder()
				.itemType(game.getRegistry().getType(ItemType.class, "avaritia:ultimate_stew").get())
				.quantity(9)
				.build();
		
		ItemStack ingot = getIngotVariant(game);
		
		if(src instanceof Player) {
			
		    Player player = (Player) src;
		    Inventory i = player.getInventory();
		    int count = 0;
		    
		    
		    
		    //if(i.contains(ingot)) {
		    	
		    	for (Inventory s : i.slots()) {
		    		
		    	    if (s.size() == 0) {
		    	    	
		    	        s.offer(givestew);
		    	        break;
		    	        
		    	    }
		    	    
		    	}
		    	
		    	//i.queryAny(ingot).poll();
		    	player.sendMessage(Text.builder("Vous venez de récupérer 9 Ultimate Stew").color(TextColors.GREEN).build());
		    	return CommandResult.success();
		    	
		   /* } else {
		    	
		    	player.sendMessage(Text.builder("Vous n'avez pas de lingot de neutronium !").color(TextColors.RED).build());
		    	return CommandResult.success();
		    	
		    }*/
		    
		}else {
			
		    src.sendMessage(Text.of("Commande impossible"));
		    return CommandResult.success();
		    
		}
	}

}
