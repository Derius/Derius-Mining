package dk.muj.derius.mining;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
		int millis = this.getDurationMillis(lvl);
		
		LinkedHashMap<TimeUnit, Long> unitcounts = TimeDiffUtil.limit(TimeDiffUtil.unitcounts(millis, TimeUnit.getAllButMillis()), 3);
		
		String entry = Txt.parse("<v>%1$d <k>%3$s");
		String comma = TimeDiffUtil.FORMAT_COMMA_VERBOOSE;
		String and = TimeDiffUtil.FORMAT_AND_VERBOOSE;
		String durationDesc = TimeDiffUtil.formated(unitcounts, entry, comma, and, "<yellow>");
		
		return "<i>Lasts " + durationDesc;
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		if ( ! dplayer.isPlayer()) return null;
		Player player = dplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		if (inHand == null || inHand.getType() == Material.AIR) return null;
		
		int lvlBefore = inHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
		if (lvlBefore < 0) lvlBefore = 0;
		int lvl = lvlBefore + MiningSkill.getEfficiencyBuff();
		
		ItemMeta meta = inHand.getItemMeta();
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
		lore.add(Txt.parse("<lime>Derius Ability Tool"));

		meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
		
		meta.setLore(lore);
		inHand.setItemMeta(meta);
		player.updateInventory();
		return player.getItemInHand();
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		dplayer.msg("deactivate start");
		if ( ! dplayer.isPlayer()) throw new RuntimeException("isn't player");
		if ( ! (other instanceof ItemStack)) throw new RuntimeException("isn't itemstack");
		ItemStack inHand = (ItemStack) other;
		
		dplayer.msg(String.valueOf(inHand == dplayer.getPlayer().getItemInHand()));
		
		int lvlBefore = inHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
		dplayer.msg("lvlBefore:" + String.valueOf(lvlBefore));
		dplayer.msg("buff:" + String.valueOf(MiningSkill.getEfficiencyBuff()));
		int lvl = lvlBefore - MiningSkill.getEfficiencyBuff();
		dplayer.msg("lvl:" + String.valueOf(lvl));
		if (lvl < 0) lvl = 0;
		dplayer.msg("lvl:" + String.valueOf(lvl));
		
		
		ItemMeta meta = inHand.getItemMeta();
		dplayer.msg("meta:" + String.valueOf(meta));
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
		lore.remove(Txt.parse("<lime>Derius Ability Tool"));
		meta.setLore(lore);
		
		meta.removeEnchant(Enchantment.DIG_SPEED);
		if (lvl > 0) meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
		
		
		dplayer.msg("meta:" + String.valueOf(meta));
		inHand.setItemMeta(meta);
		dplayer.getPlayer().updateInventory();
		dplayer.msg("deactivate end");
	}

}
