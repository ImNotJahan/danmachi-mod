package imnotjahan.mod.danmachi.world;

public class CaveFloor
{
    public static int width = 16;
    public static int height = width;

    static float chanceToStartAlive = 0.3f;
    static int numberOfSteps = 5;
    static int birthLimit = 3;
    static int deathLimit = 3;

    public static void main(String[] args)
    {
        boolean[][] map = initialiseMap(new boolean[width][height]);
        String dungeon = "";

        for(boolean[] walls : map)
        {
            for(boolean wall : walls)
            {
                dungeon += wall ? "\u25A0" : "\u25A1";
            }

            dungeon += "\n";
        }

        System.out.println(dungeon);
    }

    static public boolean[][] initialiseMap(boolean[][] map)
    {
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                if(Math.random() < chanceToStartAlive){
                    map[x][y] = true;
                }
            }
        }
        return map;
    }

    public boolean[][] generateMap(){
        //Create a new map
        boolean[][] cellmap = new boolean[width][height];
        //Set up the map with random values
        cellmap = initialiseMap(cellmap);
        //And now run the simulation for a set number of steps
        for(int i=0; i<numberOfSteps; i++){
            cellmap = doSimulationStep(cellmap);
        }

        return cellmap;
    }

    public int countAliveNeighbours(boolean[][] map, int x, int y)
    {
        int count = 0;
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                //If we're looking at the middle point
                if (i == 0 && j == 0)
                {
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length)
                {
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if (map[neighbour_x][neighbour_y])
                {
                    count = count + 1;
                }
            }
        }

        return count;
    }

    public boolean[][] doSimulationStep(boolean[][] oldMap)
    {
        boolean[][] newMap = new boolean[width][height];
        //Loop over each row and column of the map
        for (int x = 0; x < oldMap.length; x++)
        {
            for (int y = 0; y < oldMap[0].length; y++)
            {
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if (oldMap[x][y])
                {
                    if (nbs < deathLimit)
                    {
                        newMap[x][y] = false;
                    } else
                    {
                        newMap[x][y] = true;
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else
                {
                    if (nbs > birthLimit)
                    {
                        newMap[x][y] = true;
                    } else
                    {
                        newMap[x][y] = false;
                    }
                }
            }
        }

        return newMap;
    }
}