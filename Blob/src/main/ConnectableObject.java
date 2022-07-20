package main;

public interface ConnectableObject {
	public void addedConnection(ConnectableObject obj);
	
	public void removedConnection(ConnectableObject obj);
}
