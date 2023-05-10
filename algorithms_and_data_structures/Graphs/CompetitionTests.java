
/**
 * Competition Tests
 *
 * Performance Discussion
 *
 * 1. Justify the choice of the data structures used in CompetitionDijkstra and CompetitionFloydWarshall
 * A: For CompetitionFloydWarshall the graph was represented through the use of a 2D array. This is because the algorithm works based of an adjacency matrix
 *    and a 2D array is equivalent to a matrix. The 2D array is of size VxV ( V = number of vertices/nodes ) and is of the form [from][to]. For sparse graphs
 *    where the adjacency matrix would be mostly empty (infinity), this is potentially a waste of a lot of space.
 *
 *    For CompetitionDijkstra I chose to represent the graph through a TreeMap of Nodes. Each Node has it's own ArrayList of Paths which go out from it.
 *    This is a much better design for sparse graphs as you don't have to store unnecessary data, only edges which actually exist. It is also a far more
 *    extensible approach and can easily be expanded if more Nodes are added etc.
 *
 *
 * 2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms in the
 * given problem. Also explain how would their relative performance be affected by the density of the graph.
 * Which would you choose in which set of circumstances and why?
 *
 *  In this case, Dijkstra's performance is O(V^2 E). Normally Dijkstra is better for a single source, however in this assignment we need to find the lowest
 *  cost from all sources to all nodes, and then take the maximum. This means that every Node has to be source at some point.
 *
 *  Floyd Warshall performance is O(V^3). Floyd Warshall generates the lowest cost from all sources to all destinations but it costs O(V^3) because of the nested for loops.
 *
 *  Dijkstra would perform better in a sparse graph, since O(V^2 E) is dependent on E, the number of edges. This would also mean that memory wise it would be better than
 *  Floyd Warshall as Dijkstra doesn't waste memory on edges which don't exist, whereas Floyd Warshall does because of the adjacency matrix ( 2D array ) it uses.
 *  On the other hand, Floyd Warshall's O(V^3) isn't dependent on the number of edges and would perform better in a dense graph, especially if E is large. Floyd Warshall in this case
 *  wouldn't be wasting as much (if any) space in the 2D array as there is a high probability that an edge exists between two vertices.
 *
 *  Overall, I would choose Dijkstra for a sparse graph and Floyd Warshall for a dense graph.
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 80, 60);
        assertEquals("Constructor test with valid input", dijkstra.slowest, 50);
    }

    @Test
    public void testDijkstra() {
        CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50,80,60);
        assertEquals("Test competition with tinyEWD", 38, dijkstra.timeRequiredforCompetition());

        CompetitionDijkstra dijkstra1 = new CompetitionDijkstra("notATextFile.txt", 50, 80, 60);
        assertEquals("Test competition with invalid filename", -1, dijkstra1.timeRequiredforCompetition());

        CompetitionDijkstra dijkstra2 = new CompetitionDijkstra("tinyEWD.txt", -1, 80, 60);
        assertEquals("Test competition with negative speed", -1, dijkstra2.timeRequiredforCompetition());

        CompetitionDijkstra dijkstra3 = new CompetitionDijkstra(null, 50, 80, 60);
        assertEquals("Test competition with null filename", -1, dijkstra3.timeRequiredforCompetition());

        CompetitionDijkstra dijkstra4 = new CompetitionDijkstra("tinyEWD-2.txt", 50, 80, 60);
        assertEquals("Test competition with node that doesn't have path", -1, dijkstra4.timeRequiredforCompetition());

        CompetitionDijkstra dijkstra5 = new CompetitionDijkstra("input-J.txt", 98, 70, 84);
        assertEquals("Test competition with 0 0 file", -1, dijkstra5.timeRequiredforCompetition());

        CompetitionDijkstra dijkstra6 = new CompetitionDijkstra("tinyEWD.txt", 5, 80, 60);
        assertEquals("Test competition with less than 50 speed", -1, dijkstra6.timeRequiredforCompetition());

        CompetitionDijkstra dijkstra7 = new CompetitionDijkstra("input-C.txt", 50, 100, 67);
        assertEquals("Test competition with less than 50 speed", -1, dijkstra7.timeRequiredforCompetition());
    }



    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall floyWar = new CompetitionFloydWarshall("input-I.txt", 60,70,84);
        assertEquals("constructor failed with valid input", floyWar.slowest, 60);
    }

    @Test
    public void testFloyWar() {
        CompetitionFloydWarshall floyWar = new CompetitionFloydWarshall("tinyEWD.txt", 50,80,60);
        assertEquals("Test competition with tinyEWD", 38, floyWar.timeRequiredforCompetition());

        CompetitionFloydWarshall floyWar1 = new CompetitionFloydWarshall("notATextFile.txt", 50, 80, 60);
        assertEquals("Test competition with invalid filename", -1, floyWar1.timeRequiredforCompetition());

        CompetitionFloydWarshall floyWar2 = new CompetitionFloydWarshall("tinyEWD.txt", -1, 80, 60);
        assertEquals("Test competition with negative speed", -1, floyWar2.timeRequiredforCompetition());

        CompetitionFloydWarshall floyWar3 = new CompetitionFloydWarshall(null, 50, 80, 60);
        assertEquals("Test competition with null filename", -1, floyWar3.timeRequiredforCompetition());

        CompetitionFloydWarshall floyWar4 = new CompetitionFloydWarshall("tinyEWD-2.txt", 50, 80, 60);
        assertEquals("Test competition with node that doesn't have path", -1, floyWar4.timeRequiredforCompetition());

        CompetitionFloydWarshall floyWar5 = new CompetitionFloydWarshall("input-J.txt", 98, 70, 84);
        assertEquals("Test competition with 0 0 file", -1, floyWar5.timeRequiredforCompetition());

        CompetitionFloydWarshall floyWar6 = new CompetitionFloydWarshall("tinyEWD.txt", 5, 80, 60);
        assertEquals("Test competition with less than 50 speed", -1, floyWar6.timeRequiredforCompetition());

        CompetitionFloydWarshall floyWar7 = new CompetitionFloydWarshall("input-C.txt", 50, 100, 67);
        assertEquals("Test competition with less than 50 speed", -1, floyWar7.timeRequiredforCompetition());
    }

    //TODO - more tests

}