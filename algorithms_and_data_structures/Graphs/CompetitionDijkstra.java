
import java.io.FileInputStream;
import java.util.*;

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

class CompetitionDijkstra {

    int sA, sB, sC;
    int slowest;

    String filename;

    private TreeMap<Integer, Node> map;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,sB,sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) {
        this.filename = filename;
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        this.initialise();
    }

    private void initialise() {

        slowest = Math.min(sA, sB);
        slowest = Math.min(slowest, sC);
        if (filename == null) slowest = -1;
        map = new TreeMap<>();

        // initialise the TreeMap and the adjacency lists of each node
        try {
            Scanner scanner = new Scanner(new FileInputStream(filename));
            int V = scanner.nextInt();
            int S = scanner.nextInt();
            for (int i = 0; i < S; i++) {
                if (scanner.hasNext()) {
                    int intersection1 = scanner.nextInt();
                    int intersection2 = scanner.nextInt();
                    double length = scanner.nextDouble() * 1000;
                    Node node1, node2;

                    if (map.get(intersection1) == null) {
                        node1 = new Node(intersection1);
                        map.put(intersection1, node1);
                    } else node1 = map.get(intersection1);

                    if (map.get(intersection2) == null) {
                        node2 = new Node(intersection2);
                        map.put(intersection2, node2);
                    } else node2 = map.get(intersection2);

                    node1.addAdjacent(node2, length);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            slowest = -1;
        }
    }

    private double getMaxCost(int start) {

        LinkedList<Node> nodes = new LinkedList<>();
        for (Node node : map.values()) {
            if (node.id == start) node.cost = 0;
            else node.cost = Double.MAX_VALUE;
            nodes.add(node);
        }

        for (int i = 0; i < map.values().size(); i++) {
            for (Node node : nodes) {
                for (Path path : node.paths) {
                    double newCost = node.cost + path.cost;
                    if (newCost < path.dest.cost) {
                        path.dest.cost = newCost;
                    }
                }
            }
        }

        double max = Double.MIN_VALUE;
        for (Node node : map.values()) {
            if (node.cost == Double.MAX_VALUE) return node.cost;
            else if (node.cost > max)
                max = node.cost;
        }
        return max;
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)){
            return -1;
        }

        if (map.size() == 0 || slowest <= 0) return -1;
        double maxDist = -1;
        for (Node node : map.values()) {
            double dist = getMaxCost(node.id);
            if (dist == Double.MAX_VALUE) return -1;
            maxDist = Math.max(maxDist, dist);
        }
        return (int) Math.ceil(maxDist / slowest);
    }

    private class Node {
        int id;
        double cost = Double.MAX_VALUE; //tentative cost
        ArrayList<Path> paths = new ArrayList<>();

        Node(int id) {
            this.id = id;
        }

        void addAdjacent(Node node, double cost) {
            paths.add(new Path(node, cost));
        }
    }

    private class Path {
        Node dest;
        double cost;

        Path(Node dest, double cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}