package dk.muj.derius.mining;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.ability.DeriusAbility;

public class SuperMining extends DeriusAbility
{
	private static SuperMining i = new SuperMining();
	public static SuperMining get() { return i; }
	
	public SuperMining()
	{
		super.setDesc("Mines faster");
		
		super.setName("Super Mining");
		
		super.setType(AbilityType.ACTIVE);
		
	}
	
	@Override
	public Skill getSkill()
	{
		return MiningSkill.get();
	}
	
	@Override
	public String getId()
	{
		return "derius:mining:supermining";
	}

	@Override
	public String getLvlDescriptionMsg(int lvl)
	{
		int ticks = this.getDuration(lvl);
		int millis = ticks*50;
		
		LinkedHashMap<TimeUnit, Long> durUnitcounts = TimeDiffUtil.limit(TimeDiffUtil.unitcounts(millis, TimeUnit.getAllButMillis()), 3);
		String durDesc = TimeDiffUtil.formatedVerboose(durUnitcounts, "<i>");
		
		return "<g>Lasts " + durDesc;
	}

	@Override
	public Object onActivate(DPlayer p, Object other)
	{
		if ( ! p.isPlayer()) return Optional.empty();
		Player player = p.getPlayer();
		ItemStack inHand = player.getItemInHand();
		if (inHand == null || inHand.getType() == Material.AIR) return Optional.empty();
		
		int lvlBefore = inHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
		if (lvlBefore < 0) lvlBefore = 0;
		int lvl = lvlBefore + MiningSkill.getEfficiencyBuff();
		
		ItemMeta meta = inHand.getItemMeta();
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
		lore.add(Txt.parse("<lime>Derius Ability Tool"));

		meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
		
		meta.setLore(lore);
		inHand.setItemMeta(meta);
		return Optional.of(inHand);
	}

	@Override
	public void onDeactivate(DPlayer p, Object other)
	{
		if ( ! p.isPlayer()) return;
		if ( ! (other instanceof ItemStack)) return;
		ItemStack inHand = (ItemStack) other;
		
		int lvlBefore = inHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
		int lvl = lvlBefore - MiningSkill.getEfficiencyBuff();
		if (lvl < 0) lvl = 0;
		
		ItemMeta meta = inHand.getItemMeta();
		
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
		lore.remove(Txt.parse("<lime>Derius Ability Tool"));
		meta.setLore(lore);
		
		if (lvl == 0)	meta.removeEnchant(Enchantment.DIG_SPEED);
		else			meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
			
		inHand.setItemMeta(meta);
	}

}
