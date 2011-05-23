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
package script.template.actor.npc.monster;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.MonsterNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DragonSteedTroopCommander4Template extends MonsterNPCTemplate {
	public static final int ID = 22594;

	@Inject
	protected DragonSteedTroopCommander4Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Dragon Steed Troop Commander";
		this.serverSideName = false;
		this.title = "";
		this.serverSideTitle = false;
		this.collisionRadius = 28.00;
		this.collisionHeight = 46.50;
		this.level = 81;
		this.sex = ActorSex.MALE;
		this.attackRange = 80;
		this.maxHP = 32829.283194771200000;
		this.maxMP = 1777.400000000000000;
		this.hpRegeneration = 111.507051242950000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 359407;
		this.sp = 37057;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(13978);
		this.leftHand = null;
		this.enchantLevel = 0;
		this.targetable = true;
		this.showName = true;
		this.dropHerbGroup = 0;
		this.baseAttributes = true;
		
		attributes.intelligence = 21;
		attributes.strength = 40;
		attributes.concentration = 43;
		attributes.mentality = 20;
		attributes.dexterity = 30;
		attributes.witness = 20;
		attributes.physicalAttack = 5883.70412;
		attributes.magicalAttack = 4065.07552;
		attributes.physicalDefense = 572.83923;
		attributes.magicalDefense = 628.76834;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 55.00000;
		attributes.runSpeed = 210.00000;
	}
}