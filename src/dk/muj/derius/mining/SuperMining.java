package dk.muj.derius.mining;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.ability.Ability;
import dk.muj.derius.ability.AbilityType;
import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.skill.Skill;

public class SuperMining extends Ability
{
    private static SuperMining i = new SuperMining();
	public static SuperMining get() { return i; }
	
	public SuperMining()
	{
		super.setDescription("Mines faster");
		
		super.setName("Super Mining");
		
		super.setType(AbilityType.ACTIVE);
		
	}
	
	@Override
	public Skill getSkill()
	{
		return MiningSkill.get();
	}
	
	@Override
	public int getId()
	{
		return MConf.get().getSuperMiningId();
	}

	@Override
	public String getLvlDescription(int lvl)
	{
		return "Lasts "+this.getTicksLast(lvl)/20 + " seconds";
	}
	
	@Override
	public int getCooldownTime(MPlayer p)
	{
		return super.getCooldownTime(p);
	}

	@Override
	public Optional<Object> onActivate(MPlayer p, Object other)
	{
		if( ! p.isPlayer()) return Optional.empty();
		Player player = p.getPlayer();
		ItemStack inHand = player.getItemInHand();
		if(inHand == null || inHand.getType() == Material.AIR) return Optional.empty();
		
		int lvlBefore = inHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
		if (lvlBefore < 0) lvlBefore = 0;
		int lvl = lvlBefore + MConf.get().eficciencyBuff;
		
		ItemMeta meta = inHand.getItemMeta();
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
		lore.add(Txt.parse("<lime>Derius Ability Tool"));

		meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
		
		meta.setLore(lore);
		inHand.setItemMeta(meta);
		return Optional.of(inHand);
	}

	@Override
	public void onDeactivate(MPlayer p, Optional<Object> other)
	{
		if( ! p.isPlayer()) return;
		if ( ! other.isPresent()) return;
		Object obj = other.get();
		if ( ! (obj instanceof ItemStack)) return;
		ItemStack inHand = (ItemStack) obj;
		
		int lvlBefore = inHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
		if (lvlBefore < 0) lvlBefore = 0;
		int lvl = lvlBefore - MConf.get().eficciencyBuff;
		if(lvl < 0) lvl = 0;
		
		ItemMeta meta = inHand.getItemMeta();
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
		lore.remove(Txt.parse("<lime>Derius Ability Tool"));

		if(lvl == 0)
		{
			meta.removeEnchant(Enchantment.DIG_SPEED);
		}
		else
		{
			meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
		}
			
		meta.setLore(lore);
		inHand.setItemMeta(meta);
	}



	

	

}
