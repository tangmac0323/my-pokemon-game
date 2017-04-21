package Map;

public class Map_TopRight extends Map{

	@Override
	public void mapGenerator() {
		for (int i = 0; i < 128; i++)
		{
			for (int j = 0; j < 128; j++)
			{
				map[i][j] = new MapBlock(GroundType.GRASSLAND);
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.AIR);
			}
		}
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 128; j++)
			{
				map[i][j].setObstacle(ObstacleType.ROCK);
			}
		}
		
		
		for (int i = 0; i < 31; i++)
		{
			for (int j = 97; j < 128; j++)
			{
				map[i][j].setObstacle(ObstacleType.ROCK);
			}
		}
		
		for (int i = 0; i < 50; i++)
		{
			for (int j = 120; j < 128; j++)
			{
				map[i][j].setObstacle(ObstacleType.ROCK);
			}
		}
		
		for (int i = 55; i < 128; i++)
		{
			for (int j = 124; j < 128; j++)
			{
				map[i][j].setObstacle(ObstacleType.ROCK);
			}
		}
		
		for (int i = 8; i < 30; i++)
		{
			for (int j = 15; j < 65; j++)
			{
				map[i][j].setObstacle(ObstacleType.WATER);
			}
		}
		
		for (int i = 44; i < 45; i++)
		{
			for (int j = 0; j < 75; j++)
			{
				map[i][j].setObstacle(ObstacleType.ROCK);
			}
		}
		
		
		for (int i = 69; i < 90; i++)
		{
			for (int j = 80; j < 120; j++)
			{
				map[i][j].setObstacle(ObstacleType.TREE);
			}
		}
		
		for (int i = 35; i < 69; i++)
		{
			for (int j = 85; j < 116; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.LONGGRASS);
			}
		}
		
		for (int i = 90; i < 126; i++)
		{
			for (int j = 85; j < 116; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.LONGGRASS);
			}
		}
		
		for (int i = 119; i < 126; i++)
		{
			for (int j = 70; j < 120; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.LONGGRASS);
			}
		}
		
		for (int i = 45; i < 58; i++)
		{
			for (int j = 0; j < 28; j++)
			{
				map[i][j].setObstacle(ObstacleType.TREE);
			}
		}
		
		
		for (int i = 100; i < 128; i++)
		{
			for (int j = 2; j < 48; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.LONGGRASS);
			}
		}
		
		for (int i = 60; i < 85; i++)
		{
			for (int j = 4; j < 35; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.LONGGRASS);
			}
		}
		
		for (int i = 70; i < 75; i++)
		{
			for (int j = 62; j < 72; j++)
			{
				map[i][j].setObstacle(ObstacleType.HOUSE);
			}
		}
		
		
		for (int i = 60; i < 68; i++)
		{
			for (int j = 32; j < 60; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.SHORTGRASS);
			}
		}
		
		
		for (int i = 18; i < 42; i++)
		{
			for (int j = 70; j < 77; j++)
			{
				map[i][j].setObstacle(ObstacleType.TREE);
			}
		}
		
		
		for (int i = 26; i < 42; i++)
		{
			for (int j = 4; j < 9; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.SHORTGRASS);
			}
		}
		
		for (int i = 36; i < 43; i++)
		{
			for (int j = 4; j < 27; j++)
			{
				map[i][j].setObstacle(ObstacleType.NONE);
				map[i][j].setPassable(PassableType.SHORTGRASS);
			}
		}
		
		for (int i = 0; i < 128; i++)
		{
				map[127][i].setObstacle(ObstacleType.ROCK);
		}
		
		
		for (int i = 50; i < 55; i++)
		{
			map[127][i].setPassable(PassableType.GATE);
		}
		
		for (int i = 40; i < 45; i++)
		{
			map[0][i].setPassable(PassableType.GATE);
		}
		
		for (int i = 115; i < 120; i++)
		{
			map[0][i].setPassable(PassableType.GATE);
		}
		
	}
	
	

}
