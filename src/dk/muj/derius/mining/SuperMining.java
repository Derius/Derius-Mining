package dk.muj.derius.mining;

import java.util.LinkedHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.ability.DeriusAbility;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;
import dk.muj.derius.req.ReqCooldownIsExpired;

public class SuperMining extends DeriusAbility
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static SuperMining i = new SuperMining();
	public static SuperMining get() { return i; }
	
	public SuperMining()
	{
		this.setDesc("Mines faster");
		
		this.setName("Super Mining");
		
		this.setType(AbilityType.ACTIVE);
		
		this.addActivateRequirements(ReqCooldownIsExpired.get());
	}
	
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static final String ACTIVATED_LORE_TAG = Txt.parse("<lime>SuperMining Pickaxe");
	
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
		
		SuperMiningItemManager.get().toSpecial(inHand);
		
		player.updateInventory();
		return player.getItemInHand();
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		if ( ! dplayer.isPlayer()) return;
		
		SuperMiningItemManager.get().clearInventory(dplayer.getPlayer().getInventory());
		
		dplayer.getPlayer().updateInventory();
		
		return;
	}

}
