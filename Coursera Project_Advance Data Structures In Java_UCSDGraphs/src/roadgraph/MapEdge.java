package roadgraph;

public class MapEdge {

	private MapNode from;
	private MapNode to;
	private String roadName;
	private String roadType;
	private double roadLength;
	private int hashInternal = 0;
	
	public MapEdge(MapNode fromPoint, MapNode toPoint, String roadName,
			String roadType, double length) {
		this.from = fromPoint;
		this.to = toPoint;
		this.roadName = roadName;
		this.roadType = roadType;
		this.roadLength = length;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof MapEdge)) return false;
		MapEdge that = (MapEdge) obj;
		if (!this.from.equals(that.from)) return false;
		if (!this.to.equals(that.to)) return false;
		if (!this.roadName.equals(that.roadName)) return false;
		if (!this.roadType.equals(that.roadType)) return false;
		if (this.roadLength != that.roadLength) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		if (hashInternal == 0) {
			int hash = 31;
			hash = hash + 31 * from.hashCode() + 
						  31 * 31 * to.hashCode() + 
						  31 * 31 * 31 * roadName.hashCode() +
						  31 * 31 * 31 * roadType.hashCode() + 
						  (int) (31 * 31 * 31 * 31 * Double.valueOf(roadLength).hashCode());
			hashInternal = hash;
		}
		return hashInternal;
	}
	
	public MapNode getFromNode() {
		return from;
	}

	public MapNode getToNode() {
		return to;
	}

	public String getRoadName() {
		return roadName;
	}

	public String getRoadType() {
		return roadType;
	}

	public double getRoadLength() {
		return roadLength;
	}
	
}
