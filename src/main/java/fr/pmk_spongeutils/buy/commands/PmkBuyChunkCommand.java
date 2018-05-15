package fr.pmk_spongeutils.buy.commands;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import fr.pmk_spongeutils.MainSpongeUtils;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;

public class PmkBuyChunkCommand implements CommandExecutor {

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		// récupération des informations du jeu
		Game game = Sponge.getGame();
		
		// Récupération des arguments
		Player player = args.<Player>getOne("player").get();
        Integer chunkNum = args.<Integer>getOne("chunk_number").get();
        
        // récupération du joueur
        LuckPermsApi api = MainSpongeUtils.getLuckPermsAPI();
        User user = api.getUserManager().getUser(player.getUniqueId());
        
        Node node = api.getNodeFactory().newBuilder("pmkchunk.c" + chunkNum).build();
        
        user.setPermission(node);
        
        api.getUserManager().saveUser(user);
      
        // vérification des permissions
        if(player.hasPermission("group.vip+")) {
        	
        	// run de la commande 
        	//System.out.println("ranks set " + player.getName() + "pc" + chunkNum + "l2");
        	game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " pc" + chunkNum + "l2");
        	return CommandResult.success();
        	
        }else if(player.hasPermission("group.vip")) {

        	// run de la commande 
        	//System.out.println("ranks set " + player.getName() + "pc" + chunkNum + "l1");
        	game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " pc" + chunkNum + "l1");
        	return CommandResult.success();
        	
        }else {
        	
        	// run de la commande 
        	System.out.println("ranks set " + player.getName() + "pc" + chunkNum);
        	game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " pc" + chunkNum);
        	return CommandResult.success();
        	
        }
        
	}

}
