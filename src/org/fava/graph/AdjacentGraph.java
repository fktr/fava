package org.fava.graph;

public class AdjacentGraph {

    private class ENode {
        int index;
        ENode next;
    }

    private class VNode {
        char data;
        ENode firstEdge;
    }

    private VNode[] graph;
    private boolean directed;

    public AdjacentGraph(char[] nodes, char[][] edges, boolean directed) {
        graph = new VNode[nodes.length];
        for (int i = 0; i < nodes.length; ++i) {
            graph[i] = new VNode();
            graph[i].data = nodes[i];
            graph[i].firstEdge = null;
        }

        this.directed = directed;

        for (int i = 0; i < edges.length; ++i) {
            char c0 = edges[i][0];
            char c1 = edges[i][1];
            int p0 = getPosition(c0);
            int p1 = getPosition(c1);
            if (p0 == -1 || p1 == -1) {
                System.out.println("illegal data");
                System.exit(-1);
            }
            insert(p0, p1);
            if (!directed) {
                insert(p1, p0);
            }
        }
    }

    public void print() {
        for (int i = 0; i < graph.length; ++i) {
            System.out.printf("%d(%c):", i, graph[i].data);
            ENode node = graph[i].firstEdge;
            while (node != null) {
                System.out.printf(" %d(%c)", node.index, graph[node.index].data);
                node = node.next;
            }
            System.out.printf("\n");
        }
    }

    private void dfsPrint(int i, boolean[] visited) {
        System.out.printf("%c ", graph[i].data);
        visited[i] = true;
        ENode node = graph[i].firstEdge;
        while (node != null) {
            if (!visited[node.index]) {
                dfsPrint(node.index, visited);
            }
            node = node.next;
        }
    }

    public void dfsPrint() {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < visited.length; ++i) {
            visited[i] = false;
        }

        for (int i = 0; i < graph.length; ++i) {
            if (!visited[i]) {
                dfsPrint(i, visited);
            }
        }
        System.out.printf("\n");
    }

    public void bfsPrint() {
        int head = 0;
        int rear = 0;
        boolean[] visited = new boolean[graph.length];
        int[] queue = new int[graph.length];

        for (int i = 0; i < visited.length; ++i) {
            visited[i] = false;
        }

        for (int i = 0; i < graph.length; ++i) {
            if (!visited[i]) {
                System.out.printf("%c ", graph[i].data);
                visited[i] = true;
                queue[rear++] = i;
            }

            while (head != rear) {
                int k = queue[head++];
                ENode node = graph[k].firstEdge;
                while (node != null) {
                    if (!visited[node.index]) {
                        System.out.printf("%c ", graph[node.index].data);
                        visited[node.index] = true;
                        queue[rear++] = node.index;
                    }
                    node = node.next;
                }
            }
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'B'},
                {'B', 'C'},
                {'B', 'E'},
                {'B', 'F'},
                {'C', 'E'},
                {'D', 'C'},
                {'E', 'B'},
                {'E', 'D'},
                {'F', 'G'}};

        AdjacentGraph directedGraph = new AdjacentGraph(vexs, edges, true);
        directedGraph.print();
        directedGraph.dfsPrint();
        directedGraph.bfsPrint();

        AdjacentGraph undirectedGraph = new AdjacentGraph(vexs, edges, false);
        undirectedGraph.print();
        undirectedGraph.dfsPrint();
        directedGraph.bfsPrint();
    }

    private boolean contains(int p0, int p1) {
        ENode node = graph[p0].firstEdge;
        while (node != null) {
            if (node.index == p1) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    private void insert(int p0, int p1) {
        if (contains(p0, p1)) {
            return;
        }

        ENode n0 = new ENode();
        n0.index = p1;
        n0.next = null;
        if (graph[p0].firstEdge == null) {
            graph[p0].firstEdge = n0;
        } else {
            ENode n1 = graph[p0].firstEdge;
            while (n1.next != null) {
                n1 = n1.next;
            }
            n1.next = n0;
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < graph.length; ++i) {
            if (graph[i].data == ch) {
                return i;
            }
        }

        return -1;
    }
}
