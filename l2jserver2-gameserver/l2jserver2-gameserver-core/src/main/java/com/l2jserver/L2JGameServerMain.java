/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver;

import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.BasicConfigurator;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.google.inject.Guice;
import com.l2jserver.model.id.provider.IDProviderModule;
import com.l2jserver.service.Service;
import com.l2jserver.service.ServiceException;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.character.ShortcutService;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.item.ItemService;
import com.l2jserver.service.game.map.pathing.PathingService;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.game.world.WorldIDService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.service.network.gameguard.GameGuardService;
import com.l2jserver.service.network.keygen.BlowfishKeygenService;

/**
 * Main class
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class L2JGameServerMain {
	/**
	 * List of start services
	 */
	private static final Class<?>[][] SERVICES = {
			// core services
			{ CacheService.class, ConfigurationService.class,
					DatabaseService.class, WorldIDService.class,
					ScriptingService.class, TemplateService.class },
			// game services
			{ ChatService.class, NPCService.class, ItemService.class,
					CharacterService.class, ShortcutService.class,
					PathingService.class },
			// network services - should be started at last!
			{ BlowfishKeygenService.class, GameGuardService.class,
					NetworkService.class } };

	/**
	 * Main method
	 * 
	 * @param args
	 *            no arguments are used
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BasicConfigurator.configure();

		final ServiceManager serviceManager = new ServiceManager();
		try {
			serviceManager.load(Paths.get("services.xml"));
		} catch (ClassNotFoundException e) {
			System.out.println("Service class not found: " + e.getMessage());
			e.printStackTrace();
			return;
		} catch (SAXException | DOMException | IOException
				| ParserConfigurationException e) {
			System.out.println("Error parsing XML service descriptor");
			e.printStackTrace();
			return;
		} catch (ServiceException e) {
			System.out.println("Error loading XML service descriptor");
			e.printStackTrace();
			return;
		}
		try {
			serviceManager.init(Guice.createInjector(new IDProviderModule(),
					serviceManager.newGuiceModule()));
		} catch (ServiceStartException e) {
			System.out.println("Error stating basic services");
			e.printStackTrace();
			return;
		}

		try {
			for (final Class<?>[] category : SERVICES) {
				for (final Class<?> service : category) {
					serviceManager.start((Class<? extends Service>) service);
				}
			}
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					for (final Class<?>[] category : SERVICES) {
						for (final Class<?> service : category) {
							try {
								serviceManager
										.stop((Class<? extends Service>) service);
							} catch (ServiceStopException e) {
							}
						}
					}
				}
			}));

		} catch (Exception e) {
			System.out.println("GameServer could not be started!");
			e.printStackTrace();
			System.exit(0);
		}
	}
}
