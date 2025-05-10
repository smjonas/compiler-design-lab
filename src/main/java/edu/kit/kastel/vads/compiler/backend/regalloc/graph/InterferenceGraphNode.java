package edu.kit.kastel.vads.compiler.backend.regalloc.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
Represents a variable or register in an interference graph.
Registers are treated specially, as there is already a register assigned to them.
 */
public record InterferenceGraphNode(String variableName, boolean isRegister, List<InterferenceGraphNode> neighbors)    {

    public static InterferenceGraphNode of(String variableName, boolean isRegister) {
        return new InterferenceGraphNode(variableName, isRegister, new ArrayList<>());
    }

    public static InterferenceGraphNode of(String variableName) {
        return new InterferenceGraphNode(variableName, false, new ArrayList<>());
    }

    public void addNeighbor(InterferenceGraphNode neighbor) {
        assert !neighbors.contains(neighbor) : "neighbor was already added to node";
        this.neighbors.add(neighbor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterferenceGraphNode that = (InterferenceGraphNode) o;
        return isRegister == that.isRegister && Objects.equals(variableName, that.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName, isRegister);
    }
}