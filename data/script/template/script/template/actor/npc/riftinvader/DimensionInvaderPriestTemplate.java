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
package script.template.actor.npc.riftinvader;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.RiftInvaderNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DimensionInvaderPriestTemplate extends RiftInvaderNPCTemplate {
	public static final int ID = 21687;

	@Inject
	protected DimensionInvaderPriestTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Dimension Invader Priest";
		this.serverSideName = false;
		this.title = "";
		this.serverSideTitle = false;
		this.collisionRadius = 15.00;
		this.collisionHeight = 38.00;
		this.level = 40;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 966.577920000000000;
		this.maxMP = 478.800000000000000;
		this.hpRegeneration = 4.500000000000000;
		this.mpRegeneration = 1.800000000000000;
		this.experience = 5374;
		this.sp = 347;
		this.aggressive = false;
		this.rightHand = null;
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
		attributes.physicalAttack = 176.36077;
		attributes.magicalAttack = 120.43155;
		attributes.physicalDefense = 156.55035;
		attributes.magicalDefense = 104.23299;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 60.00000;
		attributes.runSpeed = 165.00000;
	}
}