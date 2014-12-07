package dk.muj.derius.mining.entity;

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
	
	// -------------------------------------------- //
	// EXP GAIN
	// -------------------------------------------- //
	
	public Map<Integer, Integer> expGain = MUtil.map(
			14,50,
			15,50,
			16,50,
			21,50,
			56,100,
			73,50,
			74,50,
			129,50,
			153,50
			);
}