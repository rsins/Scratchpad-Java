
class Node:
    
    def __init__(self, id):
        self.nodeId = id
        self.connections = dict()
        
    def addEdge(self, node, weight):
        self.connections[node] = weight
    
    def deleteEdge(self, node):
        self.connections.pop(node)
    
    def clear(self):
        for n in connections:
            n.deleteEdge(self)
        self.connections.clear()
    
    def print(self):
        print("* ", self.nodeId)
        for n in self.connections:
            print("   - ", n.nodeId, " : ", self.connections[n])
        
class Graph:
    nodes = dict()
    
    def addNode(self, nodeId, connectionIdsWithWeight):
        node = None
        if nodeId in self.nodes:
            node = self.nodes[nodeId]
        node = Node(nodeId)
        for e in connectionIdsWithWeight:
            edgeNode = None
            if e in self.nodes:
                edgeNode = self.nodes[e]
            else:
                edgeNode = Node(e)
            node.addEdge(edgeNode, connectionIdsWithWeight[e])
            edgeNode.addEdge(node, connectionIdsWithWeight[e])
            self.nodes[nodeId] = node
            self.nodes[e] = edgeNode
    
    def deleteNode(self, nodeId):
        if nodeId in self.nodes:
            node = nodes.pop(nodeId)
            node.clear()
    
    def print(self):
        for n in self.nodes:
            self.nodes[n].print()

g = Graph()
g.addNode(0, {1:10, 2:15})
g.addNode(3, {1:5, 4:8})
g.print()
