package dk.muj.derius.mining;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;
import dk.muj.derius.api.util.SkillUtil;

public class DoubleDrop extends AbilityAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static DoubleDrop i = new DoubleDrop();
	public static DoubleDrop get() { return i; }

	public DoubleDrop()
	{
		this.setDesc("Gives double drop");
		this.setName("Double Drop");
		
		this.setType(AbilityType.PASSIVE);
		this.setCooldownMillis(-1);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Skill getSkill()
	{
		return MiningSkill.get();
	}
	
	@Override
	public String getId()
	{
		return "derius:mining:doubledrop";
	}

	@Override
	public String getLvlDescriptionMsg(int lvl)
	{
		double percent = Math.min(100.0, (double) lvl/MiningSkill.getLevelsPerPercent());
		return "<i>Chance to double drop: <h>" + String.valueOf(percent) + "%";
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object obj)
	{
		if (dplayer == null) return null;
		if ( ! (obj instanceof Block)) return null;
		if ( ! dplayer.isPlayer()) return null;
		
		Skill skill = MiningSkill.get();
		
		Block block = (Block) obj;
		Material oreType = block.getType();
		Player player = dplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		Location blockLoc = block.getLocation();

		if (DeriusAPI.isBlockPlacedByPlayer(block)) return null;
		if ( ! MiningSkill.getExpGain().containsKey(oreType)) return null;
		if ( ! SkillUtil.shouldDoubleDropOccur(dplayer.getLvl(skill), MiningSkill.getLevelsPerPercent())) return null;
		
		for(ItemStack item: block.getDrops(inHand))
		{
			block.getWorld().dropItem(blockLoc, item);
		}
		
		return obj;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		//There is no deactivate for this thing
	}

}
