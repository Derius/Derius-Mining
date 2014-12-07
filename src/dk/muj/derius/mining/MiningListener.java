package dk.muj.derius.mining;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.MassivePlugin;

import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.skill.Skill;

public class MiningListener implements Listener
{
	MassivePlugin plugin;
	public MiningListener(MassivePlugin plugin)
	{
		Bukkit.broadcastMessage("weird");
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onMine(BlockBreakEvent e)
	{
		Skill skill = DeriusMining.getMiningSkill();
		
		Block b = e.getBlock();
		int oreId = b.getTypeId();
		Player p = e.getPlayer();
		ItemStack inHand = p.getItemInHand();
		Location loc = b.getLocation();
		
		/*
		if(!MUtil.isPickaxe(inHand))
			return;*/
		if(inHand.getItemMeta().hasEnchant(Enchantment.getById(33)))
			return;
		
		Bukkit.broadcastMessage("be earned"+skill.CanSkillBeUsedInArea(loc));
		if(skill.CanSkillBeEarnedInArea(e.getBlock().getLocation()))
			this.PlayerEarnExp(oreId, p);
		
		Bukkit.broadcastMessage("be used"+skill.CanSkillBeUsedInArea(loc));
		if(skill.CanSkillBeUsedInArea(loc))
			if(this.PlayerGetDoubleDrop(p))
				for(ItemStack is: b.getDrops())
					b.getWorld().dropItemNaturally(loc, is);

	}
	
	private boolean PlayerGetDoubleDrop(Player p)
	{
		MPlayer mplayer = MPlayer.get(p.getUniqueId().toString());
		int level = mplayer.getLvl(Const.ID);
		double chance = level/10.0;
		double random = (int) ((Math.random()*100)+1);
		if(chance >= random)
		{
			return true;
		}
		return false;
	}

	private void PlayerEarnExp(int oreId, Player p)
	{
		if(!MConf.get().expGain.containsKey(oreId))
			return;
		int expGain = MConf.get().expGain.get(oreId);
		MPlayer mplayer = MPlayer.get(p.getUniqueId().toString());
		mplayer.AddExp(Const.ID, expGain);
	}
}
