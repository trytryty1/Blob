package main;

public class Kinematic {
	
	float velx = 0, vely = 0;
	Transform transform;
	float friction = 0.8f;
	
	public Kinematic(Transform transform) {
		this.transform = transform;
	}
	
	public Kinematic() {
		this.transform = new Transform();
	}
	
	public void update() {
		this.transform.translate(velx, vely);
		if(this.velx != 0 || this.vely != 0) {
			this.velx *= friction;
			this.vely *= friction;
			
			if(Math.abs(velx) < 0.1) {
				velx = 0;
			}
			if(Math.abs(vely) < 0.1) {
				vely = 0;
			}
		}
	}
	
	public void applyForce(float x, float y) {
		this.velx += x;
		this.vely += y;
	}
}
