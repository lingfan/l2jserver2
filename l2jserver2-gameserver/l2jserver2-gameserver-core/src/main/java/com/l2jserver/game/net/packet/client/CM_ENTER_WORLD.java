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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;

/**
 * The client is requesting a logout. Currently, when this packet is received
 * the connection is immediately closed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_ENTER_WORLD extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x03;

	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(CM_ENTER_WORLD.class);

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService characterService;

	/**
	 * @param characterService
	 *            the character service
	 */
	@Inject
	public CM_ENTER_WORLD(CharacterService characterService) {
		this.characterService = characterService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.readBytes(new byte[32]); // Unknown Byte Array
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readBytes(new byte[32]); // Unknown Byte Array
		buffer.readInt(); // Unknown Value
		// TODO parse tracert
		// for (int i = 0; i < 5; i++)
		// for (int o = 0; o < 4; o++)
		// tracert[i][o] = readC();
	}

	@Override
	public void process(final Lineage2Client conn) {
		final CharacterID id = conn.getCharacterID();
		if (id == null) {
			log.warn(
					"Client {} is trying to enter world without select a character",
					conn);
			conn.close();
			return;
		}
		// TODO send fail message
		try {
			characterService.enterWorld(id.getObject());
		} catch (SpawnPointNotFoundServiceException e) {
			conn.sendActionFailed();
		} catch (AlreadySpawnedServiceException e) {
			// TODO send an error message here
		}
	}
}
