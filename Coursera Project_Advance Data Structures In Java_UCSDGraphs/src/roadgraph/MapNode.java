package roadgraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import geography.GeographicPoint;

public class MapNode {

	private GeographicPoint geoNode;
	private Set<MapEdge> edgesToNeighbors;
	
	private int hashInternal = 0;
	
	public MapNode(GeographicPoint point) {
		this.geoNode = point;
		this.edgesToNeighbors = new HashSet<MapEdge>();
	}
	
	public GeographicPoint getGeoPoint() {
		return geoNode;
	}
	
	public List<MapEdge> getEdgesToNeighbors() {
		List<MapEdge> result = new ArrayList<>(edgesToNeighbors);
		return result;
	}
	
	public void addEdgeToNeighbor(MapNode toPoint, String roadName, String roadType, double length) {
		edgesToNeighbors.add(new MapEdge(this, toPoint, roadName, roadType, length));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof MapNode)) return false;
		MapNode that = (MapNode) obj;
		if (!this.geoNode.equals(that.geoNode)) return false;
		// if ((!this.neighbors.containsAll(that.neighbors)) || (!that.neighbors.containsAll(this.neighbors))) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		if (hashInternal == 0) {
			/*
			int hash = 31;
			hash = hash + 31 * geoPoint.hashCode() +  
						  31 * 31 * neighbors.hashCode();
			hashInternal = hash;
			*/
			hashInternal = geoNode.hashCode();
		}
		return hashInternal;
	}
}
