package main;

public class BlobSegment implements ConnectableObject {
	Transform position;
	Kinematic kinematic;

	Blob.BlobType type;
	// The size is the radius of the segment
	float size = 7;
	
	ConnectionSystem connections;

	public BlobSegment(float x, float y) {
		position = new Transform(x, y);
		kinematic = new Kinematic(position);
		this.type = Blob.BlobType.NORMAL;
	}
	
	public BlobSegment(float x, float y, Blob.BlobType type) {
		position = new Transform(x, y);
		kinematic = new Kinematic(position);
		this.type = type;
	} 

	public void update() {
		kinematic.update();
		
	}

	@Override
	public void addedConnection(ConnectableObject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedConnection(ConnectableObject obj) {
		// TODO Auto-generated method stub
		
	}
	
}
