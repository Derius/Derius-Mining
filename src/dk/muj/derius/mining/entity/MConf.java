package dk.muj.derius.mining.entity;

import java.util.List;
import java.util.Map;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

public class MConf extends Entity<MConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MConf i;
	public static MConf get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private int skillId = 10;
	public int getSkillId() { return skillId; }
	
	// -------------------------------------------- //
	// EXP GAIN
	// -------------------------------------------- //
	
	//The first int is the id of the block
	//The second is the amount of exp gained.
	public Map<Integer, Integer> expGain = MUtil.map(
			1,	10,
			14,	50,
			15,	50,
			16,	50,
			21,	50,
			56,	100,
			73,	50,
			74,	50,
			129,50,
			153,50
			);
	
	public List<Integer> doubleDropList = MUtil.list(
			1, 14, 15, 16, 21, 56, 73, 74, 129, 153);
	
	
	public double pickAxeDurability0 = 1;
	public double pickAxeDurability1000 = 1;
	public double pickAxeDurability2000 = 5;
}
