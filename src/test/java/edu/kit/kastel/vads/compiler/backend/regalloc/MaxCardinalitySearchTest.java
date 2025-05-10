package edu.kit.kastel.vads.compiler.backend.regalloc;

import edu.kit.kastel.vads.compiler.backend.regalloc.graph.InterferenceGraph;
import edu.kit.kastel.vads.compiler.backend.regalloc.graph.InterferenceGraphNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

class MaxCardinalitySearchTest {

    @Test
    void searchShouldWorkWithInterferenceGraphFromLecture() {
        var f1 = InterferenceGraphNode.of("f1");
        var f2 = InterferenceGraphNode.of("f2");
        var f3 = InterferenceGraphNode.of("f3");
        var f4 = InterferenceGraphNode.of("f4");
        var f5 = InterferenceGraphNode.of("f5");
        var eax = InterferenceGraphNode.of("%eax", true);
        var nodes = List.of(f1, f2, f3, f4, f5, eax);
        InterferenceGraph graph = new InterferenceGraph(nodes);
        graph.addEdge(f1, f2);
        graph.addEdge(f2, f3);
        graph.addEdge(f3, f4);

        var actual = new MaxCardinalitySearch().search(graph);

        var expectedSimplicialEliminationOrdering = List.of(f1, f2, f3, f4, f5, eax);
        assertThat(actual).containsExactlyElementsOf(expectedSimplicialEliminationOrdering);
    }
}