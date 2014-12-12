package dk.muj.derius.mining;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.ability.Ability;
import dk.muj.derius.ability.AbilityType;
import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.skill.Skill;
import dk.muj.derius.skill.SkillUtil;

public class DoubleDrop extends Ability
{
    private static DoubleDrop i = new DoubleDrop();
	public static DoubleDrop get() { return i; }

	public DoubleDrop()
	{
		super.setDescription("Gives double drop");
		
		super.setName("Double Drop");
		
		List<Material> blockBreakKeys = new ArrayList<Material>();
		for(int i : MConf.get().expGain.keySet())
			blockBreakKeys.add(Material.getMaterial(i));
		super.addBlockBreakKeys(blockBreakKeys);
		
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
		return "chance to double drop" + lvl/10.0 + "%";
	}

	@Override
	public boolean CanPlayerActivateAbility(MPlayer p)
	{
		return true;
	}

	@Override
	public void onActivate(MPlayer mplayer, Object block)
	{
		if(!(block instanceof Block))
			return;
		if(!mplayer.isPlayer())
			return;
		
		Skill skill = MiningSkill.get();
		
		Block b = (Block) block;
		int oreId = b.getTypeId();
		Player p = mplayer.getPlayer();
		ItemStack inHand = p.getItemInHand();
		Location loc = b.getLocation();
		
		
		if(!MUtil.isPickaxe(inHand))
			return;
		if(inHand.getItemMeta().hasEnchant(Enchantment.getById(33)))
			return;
		
		if(skill.CanSkillBeEarnedInArea(p.getLocation()))
			this.PlayerEarnExp(oreId, mplayer);
		
		if(this.CanAbilityBeUsedInArea(loc) && MConf.get().expGain.containsKey(oreId) && 
				SkillUtil.PlayerGetDoubleDrop(mplayer, skill, 10))
		{
			for(ItemStack is: b.getDrops(inHand))
				b.getWorld().dropItem(loc, is);
		}
		
	}
	
	private void PlayerEarnExp(int oreId, MPlayer mplayer)
	{
		if(!MConf.get().expGain.containsKey(oreId))
			return;
		int expGain = MConf.get().expGain.get(oreId);
		mplayer.AddExp(MiningSkill.get(), expGain);
	}

	@Override
	public void onDeactivate(MPlayer p)
	{
		//There is no deactivate for this thing
	}
}
