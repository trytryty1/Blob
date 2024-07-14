package main;

import java.util.HashMap;

public class ConnectionSystem {

	int maxConnections;
	HashMap<ConnectableObject, Connection> connections;
	
	public ConnectionSystem(int maxConnections) {
		this.maxConnections = maxConnections;
		
		this.connections = new HashMap<ConnectableObject, Connection>();
	}
	
	public boolean addConnectableObject(ConnectableObject obj) {
		if (connections.containsKey(obj)) {
			return false;
		}
		connections.put(obj, new Connection(obj, maxConnections));
		return true;
	}
	
	public boolean formConnection(ConnectableObject obj1, ConnectableObject obj2) {
		if(!(connections.containsKey(obj1) && connections.containsKey(obj2))) {
			return false;
		}
		if(!(connections.get(obj1).openConnection() && connections.get(obj2).openConnection())) {
			return false;
		}
		connections.get(obj1).addConnection(connections.get(obj2));
		connections.get(obj2).addConnection(connections.get(obj1));
		return true;
	}
	
	public boolean removeConnection(ConnectableObject obj1, ConnectableObject obj2) {
		if(!(connections.containsKey(obj1) && connections.containsKey(obj2))) {
			return false;
		}
		if(connections.get(obj1).contains(connections.get(obj2)) && connections.get(obj2).contains(connections.get(obj1))) {
			connections.get(obj1).removeConnection(connections.get(obj2));
			connections.get(obj2).removeConnection(connections.get(obj1));
			return true;
		}
		return false;
	}
		
}
