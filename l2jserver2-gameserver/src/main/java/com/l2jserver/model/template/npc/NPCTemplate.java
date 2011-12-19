/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General public License for more details.
 *
 * You should have received a copy of the GNU General public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.template.npc;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.actor.ActorTemplate;
import com.l2jserver.model.template.npc.NPCTemplate.TalkMetadata.Chat;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.NPCController;
import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.NPCTemplateIDAdapter;
import com.l2jserver.util.jaxb.SkillTemplateIDAdapter;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "npc", namespace = "http://schemas.l2jserver2.com/npc")
@XmlType(namespace = "http://schemas.l2jserver2.com/npc", name = "NPCType")
@XmlAccessorType(XmlAccessType.FIELD)
public class NPCTemplate extends ActorTemplate<NPC> {
	@XmlAttribute(name = "id", required = true)
	@XmlJavaTypeAdapter(value = NPCTemplateIDAdapter.class)
	protected NPCTemplateID id = null;
	@XmlAttribute(name = "controller", required = true)
	protected Class<? extends NPCController> controller;

	@XmlElement(name = "info", required = true)
	protected NPCInformationMetadata info = null;

	@XmlType(name = "NPCInfoType")
	protected static class NPCInformationMetadata {
		@XmlElement(name = "name", required = false)
		public NPCNameMetadata nameMetadata = null;

		@XmlType(name = "NPCNameType")
		protected static class NPCNameMetadata {
			@XmlValue
			protected String name = null;
			@XmlAttribute(name = "send")
			protected Boolean send = false;
			@XmlAttribute(name = "display")
			protected Boolean display = false;
		}

		@XmlElement(name = "title", required = false)
		protected NPCTitleMetadata titleMetadata = null;

		@XmlType(name = "NPCTitleType")
		protected static class NPCTitleMetadata {
			@XmlValue
			protected String title = null;
			@XmlAttribute(name = "send")
			protected Boolean send = false;
		}

		@XmlElement(name = "level", required = true)
		protected int level = 0;
		@XmlElement(name = "race", required = false)
		protected NPCRace race = NPCRace.NONE;
		@XmlElement(name = "sex", required = false)
		protected ActorSex sex = null;

		@XmlAttribute(name = "attackable", required = false)
		protected boolean attackable = false;
		@XmlAttribute(name = "targetable", required = false)
		protected boolean targetable = false;
		@XmlAttribute(name = "aggressive", required = false)
		protected boolean aggressive = false;

		@XmlElement(name = "stats", required = true)
		protected NPCStatsMetadata stats = null;

		@XmlType(name = "NPCStatsType")
		protected static class NPCStatsMetadata {
			@XmlElement(name = "hp", required = true)
			protected Stat hp = null;
			@XmlElement(name = "mp", required = true)
			protected Stat mp = null;

			@XmlType(name = "")
			protected static class Stat {
				@XmlAttribute(name = "max", required = true)
				protected double max = 0;
				@XmlAttribute(name = "regen", required = true)
				protected double regen = 0;
			}

			@XmlElement(name = "attack", required = false)
			protected AttackMetadata attack = null;

			@XmlType(name = "NPCAttackType")
			protected static class AttackMetadata {
				@XmlAttribute(name = "range", required = true)
				protected int range = 0;
				@XmlAttribute(name = "evasion", required = true)
				protected int evasion = 0;
				@XmlAttribute(name = "critical", required = true)
				protected int critical = 0;

				@XmlElement(name = "physical", required = true)
				protected AttackValueMetadata physical = null;
				@XmlElement(name = "magical", required = true)
				protected AttackValueMetadata magical = null;

				@XmlType(name = "")
				protected static class AttackValueMetadata {
					@XmlAttribute(name = "damage", required = true)
					protected double damage = 0;
					@XmlAttribute(name = "speed", required = true)
					protected double speed = 0;
				}
			}

			@XmlElement(name = "defense", required = false)
			protected DefenseMetadata defense = null;

			@XmlType(name = "NPCDefenseType")
			protected static class DefenseMetadata {
				@XmlElement(name = "physical", required = true)
				protected DefenseValueMetadata physical = null;
				@XmlElement(name = "magical", required = true)
				protected DefenseValueMetadata magical = null;

				@XmlType(name = "")
				protected static class DefenseValueMetadata {
					@XmlAttribute(name = "value", required = true)
					protected double value = 0;
				}
			}

			@XmlElement(name = "move", required = false)
			protected MoveMetadata move = null;

			@XmlType(name = "NPCMovementType")
			protected static class MoveMetadata {
				@XmlAttribute(name = "run", required = true)
				protected double run = 0;
				@XmlAttribute(name = "walk", required = true)
				protected double walk = 0;
			}

			@XmlElement(name = "base", required = true)
			public BaseMetadata base = null;

			@XmlType(name = "NPCBaseStatsType")
			protected static class BaseMetadata {
				@XmlAttribute(name = "int", required = true)
				protected int intelligence = 0;
				@XmlAttribute(name = "str", required = true)
				protected int strength = 0;
				@XmlAttribute(name = "con", required = true)
				protected int concentration = 0;
				@XmlAttribute(name = "men", required = true)
				protected int mentality = 0;
				@XmlAttribute(name = "dex", required = true)
				protected int dexterity = 0;
				@XmlAttribute(name = "wit", required = true)
				protected int witness = 0;
			}
		}

		@XmlElement(name = "experience", required = true)
		protected long experience = 0;
		@XmlElement(name = "sp", required = true)
		protected int sp = 0;

		@XmlElement(name = "item", required = false)
		protected ItemMetadata item = null;

		@XmlType(name = "NPCItemsType")
		protected static class ItemMetadata {
			@XmlAttribute(name = "righthand", required = false)
			@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
			protected ItemTemplateID rightHand = null;
			@XmlAttribute(name = "lefthand", required = false)
			@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
			protected ItemTemplateID leftHand = null;
		}

		@XmlElement(name = "collision", required = false)
		protected CollisionMetadata collision = null;

		@XmlType(name = "NPCCollisionType")
		protected static class CollisionMetadata {
			@XmlAttribute(name = "radius", required = true)
			protected double radius = 0;
			@XmlAttribute(name = "heigth", required = true)
			protected double height = 0;
		}
	}

	@XmlElement(name = "ai", required = false)
	protected AIMetadata ai = null;

	@XmlType(name = "NPCAIType")
	protected static class AIMetadata {
		@XmlAttribute(name = "script", required = true)
		protected String script = null;
	}

	@XmlElement(name = "talk", required = false)
	protected TalkMetadata talk = null;

	@XmlType(name = "NPCTalkType")
	protected static class TalkMetadata {
		@XmlAttribute(name = "default", required = true)
		protected String defaultChat = null;

		@XmlElement(name = "chat", required = true)
		protected List<Chat> chats = null;

		@XmlType(name = "")
		public static class Chat {
			@XmlAttribute(name = "id", required = true)
			protected String id = null;
			@XmlValue
			protected String html = null;
		}
	}

	@XmlElementWrapper(name = "droplist", required = false)
	@XmlElement(name = "item", required = true)
	protected List<DropItemMetadata> droplist = null;

	@XmlType(name = "NPCDropType")
	protected static class DropItemMetadata {
		@XmlAttribute(name = "id", required = true)
		@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
		protected ItemTemplateID item = null;
		@XmlAttribute(name = "min", required = true)
		protected int min = 0;
		@XmlAttribute(name = "max", required = true)
		protected int max = 0;

		@XmlAttribute(name = "category", required = true)
		protected DropCategory category = null;

		@XmlType(name = "NPCDropCategoryType")
		public enum DropCategory {
			DROP, SPOIL, UNK_1, UNK_2, UNK_3, UNK_4, UNK_5, UNK_6, UNK_7, UNK_8, UNK_9, UNK_10, UNK_11, UNK_12, UNK_13, UNK_14, UNK_15, UNK_16, UNK_17, UNK_18, UNK_19, UNK_20, UNK_21, UNK_22, UNK_23, UNK_24, UNK_25, UNK_26, UNK_27, UNK_28, UNK_29, UNK_30, UNK_31, UNK_32, UNK_33, UNK_34, UNK_35, UNK_36, UNK_100, UNK_101, UNK_102, UNK_200;
		}

		@XmlAttribute(name = "chance", required = true)
		protected int chance = 0;
	}

	@XmlElementWrapper(name = "skills", required = false)
	@XmlElement(name = "skill", required = true)
	protected List<SkillMetadata> skills = null;

	@XmlType(name = "NPCSkillType")
	protected static class SkillMetadata {
		@XmlAttribute(name = "id", required = true)
		@XmlJavaTypeAdapter(value = SkillTemplateIDAdapter.class)
		protected SkillTemplateID skill = null;
		@XmlAttribute(name = "level", required = true)
		protected int level = 0;
	}

	@Override
	protected NPC createInstance() {
		final NPC npc = new NPC(this.id);

		// new npcs are full hp/mp
		npc.setHP(getMaximumHP());
		npc.setMP(getMaximumMP());

		// load skills
		final Collection<Skill> skills = CollectionFactory.newSet();
		for (final SkillMetadata metadata : this.skills) {
			final SkillTemplate template = metadata.skill.getTemplate();
			if (template == null)
				// FIXME this should thrown an exception!
				continue;
			final Skill skill = template.create(npc.getID());
			skill.setLevel(metadata.level);
			skills.add(skill);
		}
		npc.getSkills().load(skills);

		return npc;
	}

	/**
	 * @return the controller class
	 */
	public Class<? extends NPCController> getControllerClass() {
		return controller;
	}

	public String getName() {
		if (info == null)
			return null;
		if (info.nameMetadata == null)
			return null;
		return info.nameMetadata.name;
	}

	public boolean getSendName() {
		if (info == null)
			return false;
		if (info.nameMetadata == null)
			return false;
		return info.nameMetadata.send;
	}

	public boolean getDisplayName() {
		if (info == null)
			return false;
		if (info.nameMetadata == null)
			return false;
		return info.nameMetadata.display;
	}

	public String getTitle() {
		if (info == null)
			return null;
		if (info.titleMetadata == null)
			return null;
		return info.titleMetadata.title;
	}

	public boolean getSendTitle() {
		if (info == null)
			return false;
		if (info.titleMetadata == null)
			return false;
		return info.titleMetadata.send;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		if (info == null)
			return -1;
		return info.level;
	}

	/**
	 * @return the race
	 */
	public NPCRace getRace() {
		if (info == null)
			return NPCRace.NONE;
		return info.race;
	}

	/**
	 * @return the sex
	 */
	public ActorSex getSex() {
		if (info == null)
			return null;
		return info.sex;
	}

	/**
	 * @return the attackable
	 */
	public boolean isAttackable() {
		if (info == null)
			return false;
		return info.attackable;
	}

	/**
	 * @return the targetable
	 */
	public boolean isTargetable() {
		if (info == null)
			return false;
		return info.targetable;
	}

	/**
	 * @return the aggressive
	 */
	public boolean isAggressive() {
		if (info == null)
			return false;
		return info.aggressive;
	}

	/**
	 * @return the experience
	 */
	public long getExperience() {
		if (info == null)
			return 0;
		return info.experience;
	}

	/**
	 * @return the sp
	 */
	public int getSP() {
		if (info == null)
			return 0;
		return info.sp;
	}

	public double getMaximumHP() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.hp == null)
			return 0;
		return info.stats.hp.max;
	}

	public double getHPRegeneration() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.hp == null)
			return 0;
		return info.stats.hp.regen;
	}

	public double getMaximumMP() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.mp == null)
			return 0;
		return info.stats.mp.max;
	}

	public double getMPRegeneration() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.mp == null)
			return 0;
		return info.stats.mp.regen;
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		return info.stats.attack.range;
	}

	/**
	 * @return the evasion
	 */
	public int getEvasion() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		return info.stats.attack.evasion;
	}

	/**
	 * @return the critical
	 */
	public int getCritical() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		return info.stats.attack.critical;
	}

	/**
	 * @return the physical attack
	 */
	public double getPhysicalAttack() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.physical == null)
			return 0;
		return info.stats.attack.physical.damage;
	}

	/**
	 * @return the physical defense
	 */
	public double getPhysicalDefense() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.defense == null)
			return 0;
		if (info.stats.defense.physical == null)
			return 0;
		return info.stats.defense.physical.value;
	}

	/**
	 * @return the physical attack speed
	 */
	public double getPhysicalAttackSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.physical == null)
			return 0;
		return info.stats.attack.physical.speed;
	}

	/**
	 * @return the magical attack
	 */
	public double getMagicalAttack() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.magical == null)
			return 0;
		return info.stats.attack.magical.damage;
	}

	/**
	 * @return the magical attack
	 */
	public double getMagicalDefense() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.defense == null)
			return 0;
		if (info.stats.defense.magical == null)
			return 0;
		return info.stats.defense.magical.value;
	}

	/**
	 * @return the magical attack speed
	 */
	public double getMagicalAttackSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.magical == null)
			return 0;
		return info.stats.attack.magical.speed;
	}

	public double getRunSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.move == null)
			return 0;
		return info.stats.move.run;
	}

	public double getWalkSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.move == null)
			return 0;
		return info.stats.move.walk;
	}

	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.intelligence;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.strength;
	}

	/**
	 * @return the concentration
	 */
	public int getConcentration() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.concentration;
	}

	/**
	 * @return the mentality
	 */
	public int getMentality() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.mentality;
	}

	/**
	 * @return the dexterity
	 */
	public int getDexterity() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.dexterity;
	}

	/**
	 * @return the witness
	 */
	public int getWitness() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.witness;
	}

	public ItemTemplateID getRightHand() {
		if (info == null)
			return null;
		if (info.item == null)
			return null;
		return info.item.rightHand;
	}

	public ItemTemplateID getLeftHand() {
		if (info == null)
			return null;
		if (info.item == null)
			return null;
		return info.item.leftHand;
	}

	public double getCollisionRadius() {
		if (info == null)
			return 0;
		if (info.collision == null)
			return 0;
		return info.collision.radius;
	}

	public double getCollisionHeight() {
		if (info == null)
			return 0;
		if (info.collision == null)
			return 0;
		return info.collision.height;
	}

	public String getAIScriptName() {
		if (ai == null)
			return null;
		return ai.script;
	}

	public String getHTML(String id) {
		if (talk == null)
			return null;
		if (id == null)
			id = talk.defaultChat;
		for (final Chat chat : this.talk.chats) {
			if (chat.id.equals(id))
				return chat.html;
		}
		return null;
	}

	public String getDefaultHTML() {
		if (talk == null)
			return null;
		return getHTML(talk.defaultChat);
	}

	@Override
	public NPCTemplateID getID() {
		return id;
	}
}
