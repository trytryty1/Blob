package main;

public class Connection {

	int maxConnections;
	int connectionCount;
	Connection[] connections;
	ConnectableObject parent;
	
	/**
	 * @param The parent or content of this connection
	 * @param The maximum amount of connections possible for this system
	 */
	public Connection(ConnectableObject parent, int maxConnections) {
		this.parent = parent;
		this.maxConnections = maxConnections;
		connections = new Connection[maxConnections];
		this.connectionCount = 0;
	}
	
	
	/**
	 * Sets all connections to null
	 */
	public void clearConnections() {
		for (int i = 0; i < maxConnections; ++i) {
			connections[i] = null;
		}
	}
	
	/**
	 * @return Returns true if there are no connections for this object
	 */
	public boolean empty() {
		return connectionCount == 0;
	}

	/**
	 * @return Returns true if there is an open slot to connect
	 */
	public boolean openConnection() {
		return connectionCount < maxConnections;
	}

	public boolean addConnection(Connection object) {
		if(contains(object)) {
			return false;
		}
		for (int i = 0; i < maxConnections; ++i) {
			if (connections[i] == null) {
				connections[i] = object;
				connectionCount++;
				return true;
			}
		}
		return false;
	}

	public boolean removeConnection(Connection object) {
		for (int i = 0; i < maxConnections; ++i) {
			if (connections[i] == object) {
				connections[i] = null;
				connectionCount--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return Returns the connection array, some of them may be null
	 * 
	 */
	public Connection[] getConnections() {
		return connections;
	}
	
	public boolean contains(Connection obj) {
		for (int i = 0; i < maxConnections; ++i) {
			if (connections[i] == obj) {
				return true;
			}
		}
		return false;
	}

}
