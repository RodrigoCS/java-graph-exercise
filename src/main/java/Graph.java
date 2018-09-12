import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    List<Node> nodes = new ArrayList<Node>();
    Stack<Node> stack; // For DFS and searchs that require stacks
    Queue<Node> queue; // For DFS and searchs that require stacks
    Boolean found = false;

    Graph(Boolean seed) {
        if (seed)
            this.seed();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public void printNodes() {
        for (Node node : this.nodes) {
            System.out.println("[" + node.getIndex() + "] > " + node.getName() + " : " + node.getWeight());
        }
    }

    public Boolean isEmpty() {
        return this.nodes.size() <= 0;
    }

    public int getNextIndex() {
        return this.nodes.size();
    }

    public Node getNodeByIndex(Integer index) {
        return this.nodes.get(index);
    }

    public Node getNodeByName(String name) {
        for (Node node : this.nodes) {
            if (node.getName().equals(name))
                return node;
        }
        return null;
    }

    public Node getLargestNode() {
        Node aux = new Node(-1, "AUX", 0);
        for (Node node : this.nodes) {
            if (node.weight > aux.weight) {
                aux = node;
            }
        }
        return aux;
    }

    public void printAvaliableNodes(Node node) {
        for (Node nextNode : this.nodes) {
            if (!node.getIndex().equals(nextNode.getIndex()))
                System.out.println(
                        "[" + nextNode.getIndex() + "] > " + nextNode.getName() + " : " + nextNode.getWeight());
        }
    }

    public void printAdjacencyMatrix() {
        // Print first line
        String firstLine = "    ";
        for (Node node : this.nodes) {
            firstLine += " " + node.getName() + " ";
        }
        System.out.println(firstLine);
        for (Character c : firstLine.toCharArray()) {
            System.out.print("-");
        }
        System.out.println();
        for (Node nodeX : this.nodes) {
            System.out.print(" " + nodeX.getName() + " |");
            for (Node nodeY : this.nodes) {
                System.out.print(nodeX.relations.contains(nodeY) ? " 1 " : " 0 ");
            }
            System.out.println();
        }
    }

    public void depthFirstSearch(Node start, Node goal) {
        System.out.println("> Buscando nodo " + goal.getName());
        this.stack = new Stack();
        this.stack.push(start);
        Node popped = this.stack.pop();
        this._depthFirstSearch(popped, goal);
    }

    void _depthFirstSearch(Node current, Node goal) {
        if (this.found || current.equals(null) || current.visited)
            return;
        current.setVisited(true);
        if (!this.found)
            System.out.println("> (" + current.getName() + ")");
        if (current.equals(goal)) {
            this.found = true;
            this.clearSearch();
            System.out.println("Encotrado nodo " + current.getName());
            return;
        }
        for (Node relation : current.relations) {
            if (!this.stack.contains(relation) && !relation.visited) {
                this.stack.push(relation);
            }
        }
        Node popped = this.stack.pop();
        this._depthFirstSearch(popped, goal);
    }

    public void breadthFirstSearch(Node start, Node goal) {
        System.out.println("> Buscando nodo " + goal.getName());
        this.queue = new LinkedList<Node>();
        this.queue.add(start);
        Node polled = this.queue.poll();
        this._breadthFirstSearch(polled, goal);
    }

    void _breadthFirstSearch(Node current, Node goal) {
        if (this.found || current.equals(null) || current.visited)
            return;
        current.setVisited(true);
        if (!this.found)
            System.out.println("> (" + current.getName() + ")");
        if (current.equals(goal)) {
            this.found = true;
            this.clearSearch();
            System.out.println("Encotrado nodo " + current.getName());
            return;
        }
        for (Node relation : current.relations) {
            if (!this.queue.contains(relation) && !relation.visited) {
                this.queue.add(relation);
            }
        }
        Node polled = this.queue.poll();
        this._breadthFirstSearch(polled, goal);
    }

    void clearSearch() {
        for (Node node : this.nodes) {
            this.stack = null;
            this.queue = null;
            this.found = false;
            node.setVisited(false);
        }
    }

    void seed() {
        Node A = new Node(0, "A", 9);
        Node B = new Node(1, "B", 6);
        Node C = new Node(2, "C", 7);
        Node D = new Node(3, "D", 2);
        Node E = new Node(4, "E", 8);
        Node F = new Node(5, "F", 1);
        Node G = new Node(6, "G", 4);

        A.link(B);
        A.link(F);
        A.link(G);
        B.link(A);
        B.link(E);
        C.link(D);
        E.link(F);
        E.link(G);
        F.link(A);
        F.link(B);
        F.link(G);
        G.link(F);
        G.link(D);
        G.link(B);

        this.nodes.add(A);
        this.nodes.add(B);
        this.nodes.add(C);
        this.nodes.add(D);
        this.nodes.add(E);
        this.nodes.add(F);
        this.nodes.add(G);
    }
}