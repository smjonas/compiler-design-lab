package edu.kit.kastel.vads.compiler.backend.regalloc.graph;

import java.util.List;

/**
Represents an interface graph, an undirected graph where nodes
are variables or registers. There exists an edge between two
nodes if the corresponding variables interfere, i.e., they may
contain different values at a given point of time during the execution
of the program and should thus be assigned to different registers.
 */
public class InterferenceGraph {

    private List<InterferenceGraphNode> nodes;

    public InterferenceGraph(List<InterferenceGraphNode> nodes) {
        this.nodes = nodes;
    }

    public void addEdge(InterferenceGraphNode node1, InterferenceGraphNode node2) {
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }

    public List<InterferenceGraphNode> getNodes() {
        return nodes;
    }

    public int nodeCount() {
        return nodes.size();
    }
}
