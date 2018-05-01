package fr.pmk_spongeutils;

import java.util.logging.Logger;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "pmk-spongeutils", name = "PMK-SpongeUtils", version = "1.0")
public class MainSpongeUtils {

	@Inject
	private Logger logger;
	
	public Logger getLogger() {
		return this.logger;
	}
	
	@Listener
    public void onPreInit(GamePreInitializationEvent  event) {
        
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
		
	}
	
	@Listener
	public void onPostInit(GamePostInitializationEvent event) {
		
		/* L��v�nement GamePostInitializationEvent est lev�. Par cet �tat, les communications inter-plugin devraient �tre pr�tes � se produire. 
		 * Les plugins fournissant une API devraient �tre pr�ts � accepter des requ�tes de base.
		 */
		
	}
	
	@Listener
	public void onStartServer(GameStartingServerEvent event) {
		
		
		
	}

}
