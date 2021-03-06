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

import java.util.StringTokenizer;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.provider.ObjectIDResolver;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.NPCController.NPCControllerException;
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.service.game.npc.ActionServiceException;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;
import com.l2jserver.util.ArrayUtils;
import com.l2jserver.util.BufferUtils;

/**
 * Executes an bypass command
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_BYPASS extends AbstractClientPacket {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x21;

	/**
	 * The {@link ObjectID} resolver
	 */
	private final ObjectIDResolver idResolver;
	/**
	 * The {@link NPC} service
	 */
	private final NPCService npcService;

	/**
	 * The bypass command
	 */
	private String command;

	/**
	 * @param idResolver
	 *            the object id resolver
	 * @param npcService
	 *            the {@link NPC} service
	 */
	@Inject
	public CM_BYPASS(ObjectIDResolver idResolver, NPCService npcService) {
		this.idResolver = idResolver;
		this.npcService = npcService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		this.command = BufferUtils.readString(buffer);
	}

	@Override
	public void process(final Lineage2Client conn) {
		// parse command
		final StringTokenizer tokenizer = new StringTokenizer(command, "_ ");
		final String type = tokenizer.nextToken();
		switch (type) {
		case "npc":
			final int objectId = Integer.parseInt(tokenizer.nextToken());
			final ObjectID<NPC> id = idResolver.resolve(objectId);
			if (!(id instanceof NPCID)) {
				conn.sendActionFailed();
				return;
			}
			final NPC npc = id.getObject();
			try {
				npcService.action(npc, conn.getCharacter(),
						ArrayUtils.createArgumentArray(tokenizer));
			} catch (ActionServiceException e) {
				conn.sendActionFailed();
			} catch (CannotSetTargetServiceException e) {
				conn.sendActionFailed();
			} catch (NPCControllerException e) {
				if (e.getSystemMessage() != null)
					conn.sendSystemMessage(e.getSystemMessage());
				conn.sendActionFailed();
			}
			return;
		default:
			log.warn(
					"Client {} requested an bypass not supported by server: {}",
					conn, type);
			return;
		}
	}	
}
