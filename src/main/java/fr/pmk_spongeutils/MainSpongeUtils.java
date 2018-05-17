package fr.pmk_spongeutils;

import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

import fr.pmk_spongeutils.buy.commands.PmkBuyChunkCommand;
import fr.pmk_spongeutils.buy.commands.PmkBuyGradeCommand;
import fr.pmk_spongeutils.buy.commands.PmkClearCommand;
import fr.pmk_spongeutils.scheduler.AntiLagsScheduler;
import fr.pmk_spongeutils.scheduler.BuyCraftScheduler;
import fr.pmk_spongeutils.scheduler.ScheduleFTB;
import fr.pmk_spongeutils.xpbonus.XpBonusListener;
import me.lucko.luckperms.api.LuckPermsApi;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "pmk-spongeutils", name = "PMK-SpongeUtils", version = "1.0")
public class MainSpongeUtils {

	private static LuckPermsApi luckAPI;
	
	public static LuckPermsApi getLuckPermsAPI() {
		return luckAPI;
	}
	
	@Inject
	private Logger logger;
	
	@Inject
	@DefaultConfig(sharedRoot = true)
	private Path defaultConfig;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private ConfigurationLoader<CommentedConfigurationNode> configManager;
	
	public Logger getLogger() {
		return this.logger;
	}
	
	public Path getDefaultPathConfig() {
		return defaultConfig;
	}
	
	private static MainSpongeUtils instance;
	
	@Listener
    public void onPreInit(GamePreInitializationEvent  event) {
        instance = this;
		/*L��v�nement GamePreInitializationEvent est lev�. Durant cet �tat, le plugin se pr�pare � l�initialisation. 
		 * Les acc�s � l�instance du logger par d�faut et aux informations concernant les localisations de fichiers de configurations pr�f�r�es 
		 * sont disponibles.
		 */
		
    }
	
	@Listener
	public void onInit(GameInitializationEvent event) {
		
		/*L��v�nement GameInitializationEvent est lev�. Durant cet �tat, le plugin devrait avoir finit tout ce qu�il avait � faire afin de fonctionner. 
		 * Les gestionnaires d��v�nements sont trait�s � ce moment l�.
		 */
		
		// ajout de la class de listener XP
		Sponge.getEventManager().registerListeners(this, new XpBonusListener());
		
	}
	
	@Listener
	public void onPostInit(GamePostInitializationEvent event) {
		
		/* L��v�nement GamePostInitializationEvent est lev�. Par cet �tat, les communications inter-plugin devraient �tre pr�tes � se produire. 
		 * Les plugins fournissant une API devraient �tre pr�ts � accepter des requ�tes de base.
		 */
		
	}
	
	@Listener
	public void onStartServer(GameStartingServerEvent event) {
		
		// R�cup�ration de l'api luckperms
		Optional<ProviderRegistration<LuckPermsApi>> provider = Sponge.getServiceManager().getRegistration(LuckPermsApi.class);
		if (provider.isPresent()) {
		    luckAPI = provider.get().getProvider();
		    
		}
		
		CommandSpec buyClearCommand = CommandSpec.builder()
			    .permission("pmkbuy.command")
			    .executor(new PmkClearCommand())
			    .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))))
			    .build();
		
		Sponge.getCommandManager().register(this, buyClearCommand, "pmkbuyclear");	// ajout de la commande au serveur
		
		// commande de boutique achat de chunk
		CommandSpec buyChunkCommand = CommandSpec.builder()
			    .permission("pmkbuy.command")
			    .executor(new PmkBuyChunkCommand())
			    .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))),
			    			GenericArguments.onlyOne(GenericArguments.integer(Text.of("chunk_number"))))
			    .build();
		
		Sponge.getCommandManager().register(this, buyChunkCommand, "pmkbuychunk");	// ajout de la commande au serveur
		
		CommandSpec buyGradeCommand = CommandSpec.builder()
			    .permission("pmkbuy.command")
			    .executor(new PmkBuyGradeCommand())
			    .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))),
			    			GenericArguments.onlyOne(GenericArguments.string(Text.of("gradeCode"))))
			    .build();
		
		Sponge.getCommandManager().register(this, buyGradeCommand, "pmkbuygrade");	// ajout de la commande au serveur
		
		new ScheduleFTB().start();	// auto sync ftb
		
		// init scheduler anti lag
		new AntiLagsScheduler().startPreventFirst();
		new AntiLagsScheduler().startPreventSecond();
		new AntiLagsScheduler().startClear();
		
		// start scheduler buycraft
		new BuyCraftScheduler().start();
		
	}

	public static MainSpongeUtils getInstace() {
		// TODO Auto-generated method stub
		return instance;
	}

}
