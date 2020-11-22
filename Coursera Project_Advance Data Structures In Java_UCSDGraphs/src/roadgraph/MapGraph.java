/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	
	private static final boolean DEBUG = true;
	
	private static class PathQueueElement<T, P extends Comparable<P>> 
	implements Comparable<PathQueueElement<T, P>> {
		private List<T> path;
		private P priority;
		
		public PathQueueElement(List<T> srcPath, P priority) {
			path = new ArrayList<T>(srcPath);
			this.priority = priority;
		}
		
		public List<T> getPath() {
			return new ArrayList<T>(this.path);
		}
		
		public T getLastInPath() {
			return this.path.get(this.path.size() - 1);
		}
		
		public P getPriority() {
			return priority;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (obj == null) return false;
			if (!(obj instanceof PathQueueElement)) return false;
			PathQueueElement<T, P> that = (PathQueueElement<T, P>) obj;
			return this.path.equals(that.path);
		}
		
		@Override
		public int hashCode() {
			return path.hashCode();
		}
		
		@Override
		public int compareTo(PathQueueElement<T, P> that) {
			return (this.priority.compareTo(that.priority));
		}
		
	}
	
	private static class ComplexPriority implements Comparable<ComplexPriority> {
		private List<Double> priorities;
		private Double sum;
		
		public ComplexPriority(Double[] priorities) {
			this.priorities = new ArrayList<Double>();
			sum = 0d;
			for (Double p : priorities) {
				this.priorities.add(p);
				sum += p;
			}
		}

		public Double[] getPriorities() {
			Double[] result = new Double[this.priorities.size()];
			for (int i = 0; i < this.priorities.size(); i++) result[i] = this.priorities.get(i);
			return result;
		}
		
		public Double getValue() {
			return sum;
		}
		
		@Override
		public int compareTo(ComplexPriority that) {
			Double d = this.sum - that.sum;
			if (d > 0) return +1;
			if (d < 0) return -1;
			return 0;
		}
	}
	
	//TODO: Add your member variables here in WEEK 3
	private Map<GeographicPoint, MapNode> adjList;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		adjList = new HashMap<GeographicPoint, MapNode>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return adjList.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		return adjList.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		int count = 0;
		for (Entry<GeographicPoint, MapNode> e : adjList.entrySet()) {
			count += e.getValue().getEdgesToNeighbors().size();
		}
		return count;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		if (location == null) return false;
		if (adjList.containsKey(location)) return false;
		adjList.put(location, new MapNode(location));
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		if (!adjList.containsKey(from)) throw new IllegalArgumentException();
		if (!adjList.containsKey(to)) throw new IllegalArgumentException();
		if ((from == null) || (to == null) || 
			(roadName == null) || (roadType == null) || 
			(length < 0)) 
			throw new IllegalArgumentException();
		
		adjList.get(from).addEdgeToNeighbor(adjList.get(to), roadName, roadType, length);
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		if (start == null) return null;
		if (goal == null) return null;
		if (!adjList.containsKey(start)) return null;
		if (!adjList.containsKey(goal)) return null;
		
		MapNode startNode = adjList.get(start);
		MapNode goalNode = adjList.get(goal);
		Set<MapEdge> visitedEdges = new HashSet<MapEdge>();
		Set<MapNode> visitedNodes = new HashSet<MapNode>();
		Queue<PathQueueElement<MapEdge, Integer>> queue = new ArrayDeque<PathQueueElement<MapEdge, Integer>>();
		for (MapEdge e : startNode.getEdgesToNeighbors()) {
			addNextElementToQueueForIntegerPriority(queue, new ArrayList<MapEdge>(), e, 0);
		}
		visitedNodes.add(startNode);
		if (DEBUG) debugBFSNodeVisit(startNode);
		
		nodeSearched.accept(start);
		
		while (!queue.isEmpty()) {
			PathQueueElement<MapEdge, Integer> curPath = queue.poll();
			
			MapEdge curEdge = curPath.getLastInPath(); 
			if (visitedEdges.contains(curEdge)) continue;
			
			// Hook for visualization.  See writeup.
			//nodeSearched.accept(next.getLocation());
			nodeSearched.accept(curEdge.getToNode().getGeoPoint());
			
			visitedEdges.add(curEdge);
			visitedNodes.add(curEdge.getToNode());
			if (curEdge.getToNode().equals(goalNode)) {
				if (DEBUG) System.out.println("BFS Nodes visited in search:" + visitedNodes.size());
				return generatePathFromEdges(curPath.getPath());
			}

			// Add to queue next path to be explored.
			for (MapEdge e : curEdge.getToNode().getEdgesToNeighbors()) {
				if (visitedEdges.contains(e)) continue;
				addNextElementToQueueForIntegerPriority(queue, curPath.getPath(), e, 0);
			}
			if (DEBUG) debugBFSNodeVisit(curEdge.getToNode());
		}

		return null;
	}
	
	private void debugBFSNodeVisit(MapNode node) {
		String strDebug = "BFS visiting [NODE at location (Lat: " + 
				node.getGeoPoint().getX() + 
		 		  " Lon: " + node.getGeoPoint().getY() + 
		 		  ") intersects streets: ";
		for (MapEdge e : node.getEdgesToNeighbors()) {
			strDebug += e.getRoadName() + ", ";
		}
		strDebug += "]";
		System.out.println(strDebug);
	}

	private void addNextElementToQueueForIntegerPriority(Queue<PathQueueElement<MapEdge, Integer>> queue, 
														 List<MapEdge> srcPath, 
														 MapEdge nextEdge, 
														 int priority) {
		List<MapEdge> next = new ArrayList<MapEdge>(srcPath);
		next.add(nextEdge);
		PathQueueElement<MapEdge, Integer> qEl = new PathQueueElement<MapEdge, Integer>(next, priority);
		queue.add(qEl);
	}
	
	private List<GeographicPoint> generatePathFromEdges(List<MapEdge> curPath) {
		Double distance = 0d;
		List<GeographicPoint> path = new ArrayList<>();
		path.add(curPath.get(0).getFromNode().getGeoPoint());
		for (MapEdge e : curPath) {
			path.add(e.getToNode().getGeoPoint());
			distance += e.getRoadLength();
		}
		if (DEBUG) System.out.println("Total Distance Covered : " + distance);
		return path;
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		if (start == null) return null;
		if (goal == null) return null;
		if (!adjList.containsKey(start)) return null;
		if (!adjList.containsKey(goal)) return null;
		
		MapNode startNode = adjList.get(start);
		MapNode goalNode = adjList.get(goal);
		Set<MapEdge> visitedEdges = new HashSet<MapEdge>();
		Set<MapNode> visitedNodes = new HashSet<MapNode>();
		Queue<PathQueueElement<MapEdge, ComplexPriority>> queue = new PriorityQueue<PathQueueElement<MapEdge, ComplexPriority>>();
		for (MapEdge e : startNode.getEdgesToNeighbors()) {
			addNextElementToQueueForComplexPriority(queue, 
													new ArrayList<MapEdge>(), 
													e, 
													new ComplexPriority(new Double[] {e.getRoadLength()}));
		}
		visitedNodes.add(startNode);
		if (DEBUG) debugDijkstraNodeVisit(startNode, new ComplexPriority(new Double[] {0d}));
		
		nodeSearched.accept(start);
		
		while (!queue.isEmpty()) {
			PathQueueElement<MapEdge, ComplexPriority> curPath = queue.poll();
			
			MapEdge curEdge = curPath.getLastInPath(); 
			if (visitedEdges.contains(curEdge)) continue;
			
			// Hook for visualization.  See writeup.
			//nodeSearched.accept(next.getLocation());
			nodeSearched.accept(curEdge.getToNode().getGeoPoint());
			
			visitedEdges.add(curEdge);
			visitedNodes.add(curEdge.getToNode());
			if (curEdge.getToNode().equals(goalNode)) {
				if (DEBUG) System.out.println("DIJKSTRA Nodes visited in search:" + visitedNodes.size());
				return generatePathFromEdges(curPath.getPath());
			}

			// Add to queue next path to be explored.
			for (MapEdge e : curEdge.getToNode().getEdgesToNeighbors()) {
				if (visitedEdges.contains(e)) continue;
				ComplexPriority cp = curPath.getPriority();
				double distance = cp.getPriorities()[0] + e.getRoadLength();
				addNextElementToQueueForComplexPriority(queue, 
														curPath.getPath(), 
														e, 
														new ComplexPriority(new Double[] {distance}));
			}
			if (DEBUG) debugDijkstraNodeVisit(curEdge.getToNode(), curPath.getPriority());
		}

		return null;
	}

	private void debugDijkstraNodeVisit(MapNode node, ComplexPriority cp) {
		String strDebug = "DIJKSTRA visiting [NODE at location (Lat: " + 
				node.getGeoPoint().getX() + 
		 		  " Lon: " + node.getGeoPoint().getY() + 
		 		  ") intersects streets: ";
		for (MapEdge e : node.getEdgesToNeighbors()) {
			strDebug += e.getRoadName() + ", ";
		}
		strDebug += "] Actual = " + cp.getPriorities()[0];
		System.out.println(strDebug);
	}
	
	private void addNextElementToQueueForComplexPriority(Queue<PathQueueElement<MapEdge, ComplexPriority>> queue, 
														 List<MapEdge> srcPath, 
														 MapEdge nextEdge, 
														 ComplexPriority priority) {
		List<MapEdge> next = new ArrayList<MapEdge>(srcPath);
		next.add(nextEdge);
		PathQueueElement<MapEdge, ComplexPriority> qEl = new PathQueueElement<MapEdge, ComplexPriority>(next, priority);
		queue.add(qEl);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		
		if (start == null) return null;
		if (goal == null) return null;
		if (!adjList.containsKey(start)) return null;
		if (!adjList.containsKey(goal)) return null;
		
		MapNode startNode = adjList.get(start);
		MapNode goalNode = adjList.get(goal);
		Set<MapEdge> visitedEdges = new HashSet<MapEdge>();
		Set<MapNode> visitedNodes = new HashSet<MapNode>();
		Queue<PathQueueElement<MapEdge, ComplexPriority>> queue = new PriorityQueue<PathQueueElement<MapEdge, ComplexPriority>>();
		for (MapEdge e : startNode.getEdgesToNeighbors()) {
			addNextElementToQueueForComplexPriority(queue, 
													new ArrayList<MapEdge>(), 
													e, 
													new ComplexPriority(new Double[] {e.getRoadLength(), goal.distance(e.getToNode().getGeoPoint())}));
		}
		visitedNodes.add(startNode);
		if (DEBUG) debugAStarNodeVisit(startNode, new ComplexPriority(new Double[] {0d, 0d}));
		
		nodeSearched.accept(start);
		
		while (!queue.isEmpty()) {
			PathQueueElement<MapEdge, ComplexPriority> curPath = queue.poll();
			
			MapEdge curEdge = curPath.getLastInPath(); 
			if (visitedEdges.contains(curEdge)) continue;
			
			// Hook for visualization.  See writeup.
			//nodeSearched.accept(next.getLocation());
			nodeSearched.accept(curEdge.getToNode().getGeoPoint());
			
			visitedEdges.add(curEdge);
			visitedNodes.add(curEdge.getToNode());
			if (curEdge.getToNode().equals(goalNode)) {
				if (DEBUG) System.out.println("A* Nodes visited in search:" + visitedNodes.size());
				return generatePathFromEdges(curPath.getPath());
			}

			// Add to queue next path to be explored.
			for (MapEdge e : curEdge.getToNode().getEdgesToNeighbors()) {
				
				if (visitedEdges.contains(e)) continue;
				ComplexPriority cp = curPath.getPriority();
				double distance = cp.getPriorities()[0] + e.getRoadLength();
				addNextElementToQueueForComplexPriority(queue, 
														curPath.getPath(), 
														e, 
														new ComplexPriority(new Double[] {distance, goal.distance(e.getToNode().getGeoPoint())}));
			}
			if (DEBUG) debugAStarNodeVisit(curEdge.getToNode(), curPath.getPriority());
		}

		return null;
	}

	private void debugAStarNodeVisit(MapNode node, ComplexPriority cp) {
		String strDebug = "A* visiting [NODE at location (Lat: " + 
				node.getGeoPoint().getX() + 
		 		  " Lon: " + node.getGeoPoint().getY() + 
		 		  ") intersects streets: ";
		for (MapEdge e : node.getEdgesToNeighbors()) {
			strDebug += e.getRoadName() + ", ";
		}
		strDebug += "] Actual = " + cp.getPriorities()[0] + ", Pred: " + cp.getValue();
		System.out.println(strDebug);
	}
	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute.equals(testroute2) + "\n ------------------------------");
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute.equals(testroute2) + "\n ------------------------------");
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute.equals(testroute2) + "\n ------------------------------");
		
		
		/* Use this code in Week 3 End of Week Quiz */
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);
		System.out.println(route.equals(route2) + "\n ------------------------------");
	}
	
}
