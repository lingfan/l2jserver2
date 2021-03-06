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

import com.google.inject.Inject;
import com.l2jserver.service.ServiceException;
import com.l2jserver.service.game.admin.AdministratorService;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;
import com.l2jserver.util.ArrayUtils;
import com.l2jserver.util.BufferUtils;

/**
 * Executes an administrator action
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_ADMIN_COMMAND extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x5b;

	/**
	 * The admin service
	 */
	private final AdministratorService adminService;

	/**
	 * The command
	 */
	private String command;

	/**
	 * @param adminService
	 *            the administrator service
	 */
	@Inject
	public CM_ADMIN_COMMAND(AdministratorService adminService) {
		this.adminService = adminService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		command = BufferUtils.readString(buffer).trim();
	}

	@Override
	public void process(final Lineage2Client conn) {
		 final StringTokenizer tokenizer = new StringTokenizer(command, " ");
//		 final String cmd = ;
		// if (cmd.equals("tele")) {
		// Coordinate coord = Coordinate.fromXYZ(
		// Integer.parseInt(tokenizer.nextToken()),
		// Integer.parseInt(tokenizer.nextToken()),
		// Integer.parseInt(tokenizer.nextToken()));
		// try {
		// spawnService.teleport(conn.getCharacter(), coord);
		// } catch (NotSpawnedServiceException e) {
		// conn.sendActionFailed();
		// } catch (CharacterAlreadyTeleportingServiceException e) {
		// conn.sendActionFailed();
		// }
		// }
		try {
			adminService
					.command(conn, conn.getCharacter(), tokenizer.nextToken(), ArrayUtils.createArgumentArray(tokenizer));
		} catch (ServiceException e) {
			conn.sendMessage("Invalid administrator command or syntax");
		} finally {
			conn.sendActionFailed();
		}

		// TODO implement admin commands
	}
}
