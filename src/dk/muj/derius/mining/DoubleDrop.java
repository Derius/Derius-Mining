package dk.muj.derius.mining;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.ability.Ability;
import dk.muj.derius.ability.AbilityType;
import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.skill.Skill;
import dk.muj.derius.util.SkillUtil;

public class DoubleDrop extends Ability
{
    private static DoubleDrop i = new DoubleDrop();
	public static DoubleDrop get() { return i; }

	public DoubleDrop()
	{
		super.setDescription("Gives double drop");
		
		super.setName("Double Drop");
		
		super.setType(AbilityType.PASSIVE);
	}
	
	@Override
	public Skill getSkill()
	{
		return MiningSkill.get();
	}
	
	@Override
	public int getId()
	{
		return MConf.get().getDoubleDropId();
	}

	@Override
	public String getLvlDescription(int lvl)
	{
		return "Chance to double drop" + lvl/10.0 + "%";
	}

	@Override
	public void onActivate(MPlayer mplayer, Object block)
	{
		if( ! (block instanceof Block))
			return;
		
		Skill skill = MiningSkill.get();
		
		Block b = (Block) block;
		int oreId = b.getTypeId();
		Player p = mplayer.getPlayer();
		ItemStack inHand = p.getItemInHand();
		Location loc = b.getLocation();
		
		

		if(inHand.getItemMeta().hasEnchant(Enchantment.getById(33)))
			return;
		
		if(MConf.get().expGain.containsKey(oreId) && 
				SkillUtil.shouldPlayerGetDoubleDrop(mplayer, skill, 10))
		{
			for(ItemStack is: b.getDrops(inHand))
				b.getWorld().dropItem(loc, is);
		}
		
	}

	@Override
	public void onDeactivate(MPlayer p)
	{
		//There is no deactivate for this thing
	}
}
