package fr.pmk_spongeutils.commands;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class StewCommand implements CommandExecutor {

	
	public Game game = Sponge.getGame();
	public ItemStack stew = ItemStack.builder()
			.itemType(game.getRegistry().getType(ItemType.class, "avaritia:ultimate_stew").get())
			.build();
	
	public ItemStack givestew = ItemStack.builder()
			.itemType(game.getRegistry().getType(ItemType.class, "avaritia:ultimate_stew").get())
			.quantity(9)
			.build();
	
	public ItemStack ingot = ItemStack.builder()
			.itemType(game.getRegistry().getType(ItemType.class, "avaritia:resource").get())
			.build();
	
	public DataView dataView = ingot.toContainer();
	
			
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// TODO Auto-generated method stub
		dataView.set(DataQuery.of("UnsafeDamage"), 4);
		ingot.setRawData(dataView);
		
		if(src instanceof Player) {
			
		    Player player = (Player) src;
		    int count = 0;
		    
		    if(player.getInventory().contains(ingot)) {
		    	for (Inventory i : player.getInventory().slots()) {
		    	    if (i.size() == 0 || i.contains(stew)) {
		    	        i.offer(givestew);
		    	        break;
		    	    }
		    	}
		    	player.getInventory().queryAny(ingot).poll();
		    }
		    else {
		    	player.sendMessage(Text.builder("Vous n'avez pas de lingot de neutronium").color(TextColors.RED).build());
		    }
		    
		}
		else{
		    src.sendMessage(Text.of("Commande impossible"));
		}
		
		return null;
	}

}
