package com.quartz.x.map;


public class MapTwo extends Map {

	public MapTwo()
	{
		mapPoints[0] = new MapPoint(580, 520, new int[] {1, 2, 4}, false);
		mapPoints[0].setVisited(true);
		mapPoints[1] = new MapPoint(720, 510, new int[] {0, 4, 7}, false);
		mapPoints[2] = new MapPoint(590, 420, new int[] {0, 3, 4}, false);
		mapPoints[3] = new MapPoint(640, 330, new int[] {2, 4, 5, 8}, false);
		mapPoints[4] = new MapPoint(705, 425, new int[] {1, 2, 3, 6, 7, 0}, false);
		mapPoints[5] = new MapPoint(760, 270, new int[] {3, 6, 9}, false);
		mapPoints[6] = new MapPoint(800, 360, new int[] {4, 5, 7, 10}, false);
		mapPoints[7] = new MapPoint(820, 450, new int[] {1, 4, 6, 10}, false);
		mapPoints[8] = new MapPoint(650, 225, new int[] {3, 5}, false);
		mapPoints[9] = new MapPoint(880, 240, new int[] {5, 11}, false);
		mapPoints[10] = new MapPoint(930, 400, new int[] {6, 7, 11}, false);
		mapPoints[11] = new MapPoint(980, 325, new int[] {9, 10}, true);
		
		travelable = mapPoints[0].getTravelable();
	}
}
