package fr.pmk_spongeutils.buy.commands;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

public class PmkClearCommand implements CommandExecutor {

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		Game game = Sponge.getGame();
		
		// Récupération des arguments
		Player player = args.<Player>getOne("player").get();
		
		game.getCommandManager().process(game.getServer().getConsole(), "ranks set " + player.getName() + " p");
		game.getCommandManager().process(game.getServer().getConsole(), "lp user " + player.getName() + " clear");
		game.getCommandManager().process(game.getServer().getConsole(), "ftb reload");
		
		return CommandResult.success();
		
	}

}
