package dk.muj.derius.mining;

import java.util.Map;
import java.util.Set;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.xlib.gson.reflect.TypeToken;

import dk.muj.derius.entity.skill.DeriusSkill;

public class MiningSkill extends DeriusSkill
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MiningSkill i = new MiningSkill();
	public static MiningSkill get() { return i; }
	
	
	public MiningSkill()
	{		
		this.setName("Mining");
		this.setDesc("Makes you better at mining");
		this.setIcon(Material.DIAMOND_PICKAXE);
		this.setEarnExpDescs(MUtil.list("Mine ores"));
		
		Map<Material, Integer> expMap = MUtil.map(
			Material.STONE, 10,
			Material.GOLD_ORE, 50,
			Material.IRON_ORE, 50,
			Material.COAL_ORE, 50,
			Material.LAPIS_ORE, 50,
			Material.DIAMOND_ORE, 100,
			Material.REDSTONE_ORE, 50,
			Material.GLOWING_REDSTONE_ORE, 50,
			Material.EMERALD_ORE, 50,
			Material.QUARTZ_ORE, 50
			);
		
		this.writeConfig(Const.JSON_EFFICIENCY_BUFF, 5);
		this.writeConfig(Const.JSON_LEVELS_PER_PERCENT, 10);
		this.writeConfig(Const.JSON_EXP_GAIN, expMap, new TypeToken<Map<Material, Integer>>(){});
		this.writeConfig(Const.JSON_DOUBLE_DROP_BLOCKS, expMap.keySet(), new TypeToken<Set<Material>>(){});
		this.writeConfig(Const.JSON_SUPER_MINING_BLOCKS, expMap.keySet(), new TypeToken<Set<Material>>(){});
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public String getId() 
	{
		return "derius:mining";
	}
	
	// -------------------------------------------- //
	// CONFIG
	// -------------------------------------------- //
	
	public static int getEfficiencyBuff()
	{
		return get().readConfig(Const.JSON_EFFICIENCY_BUFF, Integer.TYPE);
	}
	
	public static int getLevelsPerPercent()
	{
		return get().readConfig(Const.JSON_LEVELS_PER_PERCENT, Integer.TYPE);
	}
	
	public static Map<Material, Integer> getExpGain()
	{
		return get().readConfig(Const.JSON_EXP_GAIN, new TypeToken<Map<Material, Integer>>(){});
	}
	
	public static Set<Material>getDoubleDropBlocks()
	{
		return get().readConfig(Const.JSON_DOUBLE_DROP_BLOCKS, new TypeToken<Set<Material>>(){});
	}
	
	public static Set<Material>getSuperMiningBlocks()
	{
		return get().readConfig(Const.JSON_SUPER_MINING_BLOCKS, new TypeToken<Set<Material>>(){});
	}

}
