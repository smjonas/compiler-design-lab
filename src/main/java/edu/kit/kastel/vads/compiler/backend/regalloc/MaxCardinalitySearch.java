package edu.kit.kastel.vads.compiler.backend.regalloc;

import edu.kit.kastel.vads.compiler.backend.regalloc.graph.InterferenceGraph;
import edu.kit.kastel.vads.compiler.backend.regalloc.graph.InterferenceGraphNode;

import java.util.*;

public class MaxCardinalitySearch {

    public List<InterferenceGraphNode> search(InterferenceGraph interferenceGraph) {
        List<InterferenceGraphNode> simplicialEliminationOrdering = new ArrayList<>();
        int n = interferenceGraph.nodeCount();
        // Initialize node weights to 0
        Map<InterferenceGraphNode, Integer> weights = new HashMap<>(n);
        for (var node : interferenceGraph.getNodes()) {
            weights.put(node, 0);
        }
        var nodesToSearch = new ArrayList<>(interferenceGraph.getNodes());
        for (int i = 0; i < n; i++) {
            assert !nodesToSearch.isEmpty() : "no more nodes left to search";
            var maxWeightNode = nodesToSearch.stream().max(
                    (n1, n2) -> Math.max(weights.get(n1), weights.get(n2))
            ).get();
            simplicialEliminationOrdering.add(maxWeightNode);
            for (var neighboringNode : maxWeightNode.neighbors()) {
                if (nodesToSearch.contains(neighboringNode)) {
                    int currentWeight = weights.get(maxWeightNode);
                    weights.put(maxWeightNode, currentWeight + 1);
                }
            }
            nodesToSearch.remove(maxWeightNode);
        }
        return simplicialEliminationOrdering;
    }
}
