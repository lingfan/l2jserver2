/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.world.npc.calculator.base;

import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.model.world.npc.calculator.NPCFormula;

/**
 * Calculates the character base physical attack speed
 * 
 * <pre>
 * ctx.result = c.getTemplate().getPhysicalAttackSpeed();
 * ctx.result *= BaseStats.DEX.calculateBonus(c.getStats().getDexterity());
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCBasePhysicalAttackSpeedCalculator extends NPCFormula {
	/**
	 * Creates a new instance of this formula
	 */
	public NPCBasePhysicalAttackSpeedCalculator() {
		super(0x000, StatType.POWER_ATTACK_SPEED);
	}

	@Override
	protected double calculate(NPC c, NPCTemplate t, double value) {
		return t.getPhysicalAttackSpeed();
	}
}