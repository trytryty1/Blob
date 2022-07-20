package main;

import java.awt.Color;

public class Blob {
	
	public enum BlobType {
		NORMAL, REPULSE(Color.RED, 0, 10, 0, 10);
		
		Color color;
		
		float pullForce;
		float pushForce;
		
		float pullDistance;
		float pushDistance;
		
		
		BlobType(Color color, int pullForce, int pushForce, int pullDistance, int pushDistance) {
			this.color = color;
			this.pullForce = pullForce;
			this.pushForce = pushForce;
			this.pullDistance = pullDistance;
			this.pushDistance = pushDistance;
		}
		BlobType() {
			this.color = Color.GREEN;
			this.pullForce = 1;
			this.pushForce = 1;
			this.pullDistance = 1;
			this.pushDistance = 1;
		}
	}
	
	int segmentCount = 10;
	BlobSegment[] segments;
	BlobSegment leader;

	float seg_distance;
	float seg_pull;
	float seg_push;

	ConnectionSystem connectionsys;

	public Blob() {
		connectionsys = new ConnectionSystem(3);
		genBlob(2);
		
		leader = segments[0];
	}

	public void genBlob(int type) {
		segmentCount = 10;
		segments = new BlobSegment[segmentCount];
		if(type == 1) {
			segments = new BlobSegment[10*10];
			for (int i = 0; i < 10; ++i) {
				for (int t = 0; t < 10; ++t) {
					segments[i*10 + t] = new BlobSegment(250 + (i * 17), 250 + (t * 17), BlobType.NORMAL);
					connectionsys.addConnectableObject(segments[i*10 + t]);
				}
			}
			segmentCount = 10*10;
		} else {
			for (int i = 0; i < segmentCount; ++i) {
				segments[i] = new BlobSegment(250 + i * 20, 250 + i * 10);
				connectionsys.addConnectableObject(segments[i]);
			}
		}
		//segments[5].type = BlobType.REPULSE;
	}

	public void update() {
		float force = 3 * (connectionsys.connections.get(leader).connectionCount * 5 + 1);
		if (Input.LEFT) {
			leader.kinematic.applyForce(-force, 0);
		}
		if (Input.RIGHT) {
			leader.kinematic.applyForce(force, 0);
		}
		if (Input.UP) {
			leader.kinematic.applyForce(0, -force);
		}
		if (Input.DOWN) {
			leader.kinematic.applyForce(0, force);
		}
		for (int i = 0; i < segmentCount; ++i) {
			segments[i].update();
		}
		checkCollision();
	}

	public void checkCollision() {
		for (int i = 0; i < segmentCount; ++i) {
			BlobSegment obj1 = segments[i];
			for (int t = 0; t < segmentCount; ++t) {
				if (i != t) {
					BlobSegment obj2 = segments[t];

					float distance = Main.distance(obj1.position, obj2.position);

					// TODO: ITs the same if statement lol, nvm
					if (distance < obj1.size + obj2.size * obj2.type.pushDistance) {
						float deltaX = obj1.position.x - obj2.position.x;
						float deltaY = obj1.position.y - obj2.position.y;
						float angle = (float) Math.atan2(deltaY, deltaX);
						float forceScale = (float) (1/(distance*0.07+1)) * obj2.type.pushForce;
						obj1.kinematic.applyForce((float) Math.cos(angle) * forceScale,
								(float) Math.sin(angle) * forceScale);
					}

					if (distance < obj1.size + obj2.size) {
						connectionsys.formConnection(obj1, obj2);
					}
				}
			}
		}
		for (Connection connection1 : connectionsys.connections.values()) {
			BlobSegment obj1 = (BlobSegment) connection1.parent;
			if (obj1 != null) {
				for (int i = 0; i < connection1.maxConnections; ++i) {
					if (connection1.connections[i] != null) {
						Connection connection2 = connection1.connections[i];
						BlobSegment obj2 = (BlobSegment) connection2.parent;
						float distance = Main.distance(obj1.position, obj2.position);
						if (distance > obj1.size + (obj2.size * obj2.type.pullDistance) + 4) {
							float deltaX = obj1.position.x - obj2.position.x;
							float deltaY = obj1.position.y - obj2.position.y;
							float angle = (float) Math.atan2(deltaY, deltaX);
							
							float scaleFactor = distance / 10;
							scaleFactor *= obj2.type.pullForce;
							obj1.kinematic.applyForce((float) -Math.cos(angle) * scaleFactor, (float) -Math.sin(angle)* scaleFactor);
						}
					}
				}
			}
		}
	}
}