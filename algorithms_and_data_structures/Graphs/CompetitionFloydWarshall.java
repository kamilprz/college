
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *  Each contestant walks at a given estimated speed.
 *  The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 * This class implements the competition using Dijkstra's algorithm
 */


public class CompetitionFloydWarshall {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */

    private static final double INFINITY = Integer.MAX_VALUE;   // to prevent overflow if you do INFINITY + INFINITY

    double grid[][];    // [from][to]

    int sA, sB, sC;
    int numberOfIntersections, numberOfStreets;     // intersections = vertices, streets = edges
    int slowest;

    String filename;

    boolean validFile = true;

    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        this.filename = filename;
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        this.initArray();
    }

    // initialise the array
    private void initArray(){
        slowest = Math.min(sA, sB);
        slowest = Math.min(slowest, sC);

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            numberOfIntersections = Integer.parseInt(br.readLine());
            numberOfStreets = Integer.parseInt(br.readLine());
            if(numberOfIntersections == 0 || numberOfStreets == 0 ){
                validFile = false;
            }
            else{
                grid = new double[numberOfIntersections][numberOfStreets];
                for (int i = 0; i < numberOfIntersections; i++){
                    for (int j = 0; j < numberOfIntersections; j++){
                        if(i == j ){
                            grid[i][j] = 0;
                        }
                        grid[i][j] = INFINITY;
                    }
                }
                String line = br.readLine();
                while((line != null)){
                    String[] lSplit = line.trim().split(" ");
                    grid[Integer.parseInt(lSplit[0])][Integer.parseInt(lSplit[1])] = Double.parseDouble(lSplit[2]);
                    line = br.readLine();
                }
                br.close();
            }
        }catch (Exception e){
            validFile = false;
            slowest = -1;
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        if((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)){
            return -1;
        }

        if(!validFile){
            return -1;
        }
        //floydWarshall
        for (int k = 0; k < numberOfIntersections; k++){
            for (int i = 0; i < numberOfIntersections; i++){
                for (int j = 0; j < numberOfIntersections; j++){
                    if(grid[i][k] + grid[k][j] < grid[i][j]){
                        grid[i][j] = grid[i][k] + grid[k][j];
                    }
                }
            }
        }
        double maxDist = getMax();
        if (maxDist == INFINITY) return -1;

        maxDist = maxDist * 1000;   //convert to meters

        // minimum number of minutes that will pass before the three contestants can meet
        // longest path with slowest speed
        return (int) Math.ceil(maxDist / slowest);
    }

    private double getMax(){
        double maxDist = -1;
        for (int i = 0; i < numberOfIntersections; i++){
            for (int j = 0; j < numberOfIntersections; j++){
                if(grid[i][j] > maxDist && i != j){
                    maxDist = grid[i][j];
                }
            }
        }
        return maxDist;
    }

}