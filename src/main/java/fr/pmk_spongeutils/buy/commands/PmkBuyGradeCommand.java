package fr.pmk_spongeutils.buy.commands;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import fr.pmk_spongeutils.MainSpongeUtils;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;

public class PmkBuyGradeCommand implements CommandExecutor {

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// récupération des informations du jeu
		Game game = Sponge.getGame();
				
		// Récupération des arguments
		Player player = args.<Player>getOne("player").get();
		String gradeCode = args.<String>getOne("gradeCode").get();
		        
		// récupération du joueur
		LuckPermsApi api = MainSpongeUtils.getLuckPermsAPI();
		User user = api.getUserManager().getUser(player.getUniqueId());
		
		String chunkCode;
		
		if(player.hasPermission("pmkchunk.c3")) {
			chunkCode = "c3";
		}else if(player.hasPermission("pmkchunk.c2")) {
			chunkCode = "c2";
		}else if(player.hasPermission("pmkchunk.c1")) {
			chunkCode = "c1";
		}else {
			chunkCode = "";
		}
		
		if(gradeCode.equalsIgnoreCase("+vip+")) {
			
			game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " p" + chunkCode + "l2");			
			game.getCommandManager().process(game.getServer().getConsole(), "lp user " + player.getName() + " promote vip");
			game.getCommandManager().process(game.getServer().getConsole(), "lp user " + player.getName() + " promote vip");
			
			return CommandResult.success();
			
		}else if(gradeCode.equalsIgnoreCase("+vip")) {
			
			game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " p" + chunkCode + "l1");
			game.getCommandManager().process(game.getServer().getConsole(), "lp user " + player.getName() + " promote vip");
			
			return CommandResult.success();
			
		}else if(gradeCode.equalsIgnoreCase("-vip+")) {
			
			game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " p" + chunkCode);
			game.getCommandManager().process(game.getServer().getConsole(), "lp user " + player.getName() + " demote vip");
			game.getCommandManager().process(game.getServer().getConsole(), "lp user " + player.getName() + " demote vip");
			
			return CommandResult.success();
			
		}else if(gradeCode.equalsIgnoreCase("-vip")) {
			
			game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " p" + chunkCode);
			game.getCommandManager().process(game.getServer().getConsole(), "lp user " + player.getName() + " demote vip");
			
			return CommandResult.success();
			
		}else {
			return CommandResult.success();
		}
		
	}

}
