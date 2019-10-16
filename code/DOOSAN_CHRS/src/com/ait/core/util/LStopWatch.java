package com.ait.core.util;


/**
 * @(#) LStopWatch.java
 * 
 * All rights reserved.
 *
 * Do Not Erase This Comment!!! (捞 林籍巩阑 瘤快瘤 富巴)
 *
 * LAF/J狼 努贰胶甫 角力 橇肺璃飘俊 荤侩窍绰 版快 LAF/J 俺惯淬寸磊俊霸
 * 橇肺璃飘 沥侥疙莫, 淬寸磊 楷遏贸(Email)殿阑 mail肺 舅妨具 茄促.
 *
 * 家胶甫 函版窍咯 荤侩窍绰 版快 LAF/J 俺惯淬寸磊俊霸
 * 函版等 家胶 傈眉客 函版等 荤亲阑 舅妨具 茄促.
 * 历累磊绰 力傍等 家胶啊 蜡侩窍促绊 魄窜登绰 版快 秦寸 荤亲阑 馆康且 荐 乐促.
 * 吝夸茄 Idea甫 力傍窍看促绊 魄窜登绰 版快 蛆狼窍俊 历磊 List俊 馆康且 荐 乐促.
 *
 * (林狼!) 盔历磊狼 倾遏绝捞 犁硅器 且 荐 绝栏哥
 */

/**
 * 埃窜茄 器欺刚胶抛胶飘侩栏肺 Elapsed Time, Total Time, Average Time阑 备且荐 乐绰 
 * 扁瓷捞 备泅登绢乐促.
 *
 * @since LAF/J II 2004
 * @version LAF/J II 2004
 * @author 捞沥籍, <br>
 *          累己 : 2002/08/25<br>
 */
public class LStopWatch {

//	private static final long UNSET = -999999;
	int count = 0;
	long start = 0;
	long current = 0;
	long max = 0;
	long min = 0;
	
	/**
	 * Default Constructor
	 */
	public LStopWatch() {
		reset();
	}

	/**
	 * 付瘤阜 Lap 鸥烙俊辑 泅犁 Lap鳖瘤狼 矫埃阑 备茄促.
	 * @param N/A
	 * @return long elapsed 矫埃
	 */
	public long getElapsed() {

		count++;
		long now = System.currentTimeMillis();
		long elapsed = (now - current);
		current = now;

//		if (max == UNSET || elapsed > max) {
		if (elapsed > max) {
				max = elapsed;
		}
//		if (min == UNSET || elapsed < min) {
		if (elapsed < min) {
			min = elapsed;
		}

		return elapsed;
	}

	/**
	 * 乏鸥烙 眉农 冉荐甫 馆券茄促.
	 * @param N/A
	 * @return int 傈眉 眉农 墨款飘 
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 眉农等 乏鸥烙 吝 弥窜矫埃阑 馆券茄促.
	 * @param N/A
	 * @return long 弥家矫埃
	 */
	public long getMinTime() {
		return min;
	}

	/**
	 * 眉农等 乏鸥烙 吝 弥厘矫埃阑 馆券茄促.
	 * @param N/A
	 * @return long 弥措矫埃 
	 */
	public long getMaxTime() {
		return max;
	}

	/**
	 * 乞闭 乏鸥烙阑 馆券茄促.
	 * @param N/A
	 * @return long 乞闭矫埃 
	 */
	public long getAvgTime() {
		if (count > 0) {
			return Math.round((double) (current - start) / (double) count);
		} else {
			return 0;
		}
	}

	/**
	 * 胶鸥飘矫痢俊辑 弥辆 乏鸥烙鳖瘤狼 醚 矫埃阑 馆券茄促.
	 * @param N/A
	 * @return long 傈眉矫埃
	 */
	public long getTotalElapsed() {
		current = System.currentTimeMillis();
		return (current - start);
	}

	/**
	 * StopWatch狼 檬扁拳
	 * @param N/A
	 * @return N/A 
	 */
	public void reset() {
		start = System.currentTimeMillis();
		current = start;
		count = 0;
		max = 0;
		min = 0;
	}

	/**
	 * 泅犁狼 乏沥焊甫 能贾俊 橇赴飘茄促.
	 * @param N/A
	 * @return String 乏沥焊 
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		float taskCount = getCount();
		float taskTime = getTotalElapsed() / 1000F;
		float taskMin = getMinTime() / 1000F;
		float taskMax = getMaxTime() / 1000F;
		float taskAvg = getAvgTime() / 1000F;
		buffer.append(
			"\nCount : "
				+ taskCount
				+ "\nTotal : "
				+ taskTime
				+ "\nMax : "
				+ taskMax
				+ "\nMin : "
				+ taskMin
				+ "\nAvg : "
				+ taskAvg
				+ "\n");

		return buffer.toString();
	}
}