import java.util.Scanner;

public class Menu {
    final String EXIT = "exit";
    final String CLEAR = "clear";
    Scanner scanner = new Scanner(System.in);
    String option;
    Graph graph;

    Menu(String s[]) {
        graph = s.length > 0 ? new Graph(s[0].equals("seed")) : new Graph(false);
        System.out.println("Menu");
        do {
            System.out.println("1) Ingresa un nodo");
            System.out.println("2) Relacionar nodos");
            System.out.println("3) Matriz adyacente");
            System.out.println("4) Busqueda por profundidad");
            System.out.println("5) Busqueda por anchura");
            System.out.println("6) Buscar por nombre");
            System.out.println("7) Nodo mayor");

            this.option = this.scanner.nextLine();
            switch (this.option) {
            case "1": // 1) Ingresa un nodo
                this.addNode();
                break;
            case "2": // 2) Maneja relaciones
                Integer selection;
                Node selectedNode;
                System.out.println("> Que nodo quieres relacionar ?");
                this.graph.printNodes();
                selection = this.scanner.nextInt();
                this.scanner.nextLine();
                selectedNode = this.graph.getNodeByIndex(selection);
                this.linkNodes(selectedNode);
                break;
            case "3": // 3) Matriz Adyacente
                this.graph.printAdjacencyMatrix();
                break;
            case "4":
                this.depthFirstSearch();
                break;
            case "5":
                this.breadthFirstSearch();
                break;
            case "6":
                this.searchByName();
                break;
            case "7":
                this.searchLargest();
                break;
            case CLEAR:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case EXIT:
                System.out.println("> Adios ...");
                break;
            default:
                System.out.println("> " + this.option + ": no es una opcion correcta");
                break;
            }
        } while (!this.option.equals(EXIT));

    }

    void addNode() {
        String name, input;
        Integer weight, index = this.graph.getNextIndex();
        System.out.println("> Ingresa el nombre de tu nodo:");
        name = this.scanner.nextLine();
        System.out.println("> Ingresa el peso de tu nodo:");
        weight = this.scanner.nextInt();
        this.scanner.nextLine();
        Node newNode = new Node(index, name, weight);

        if (!this.graph.isEmpty()) {
            System.out.println("> ¿Quieres agregar una relacion? (y/N)");
            input = scanner.nextLine();
            if (input.equals("Y") || input.equals("y") || input.equals("")) {
                linkNodes(newNode);
            }
        }

        this.graph.add(newNode);
    }

    void linkNodes(Node node) {
        Integer input;
        System.out.println("> Con cual nodo quieres relacionarlo?");
        this.graph.printAvaliableNodes(node);
        input = this.scanner.nextInt();
        this.scanner.nextLine();
        if (input != node.getIndex()) {
            try {
                Node relatedNode = this.graph.getNodeByIndex(input);
                node.link(relatedNode);
                node.printRelations();
            } catch (Exception e) {
                System.out.println("> El nodo que quieres relacionar no existe.");
            }

        }
    }

    void depthFirstSearch() {
        try {
            Integer start, goal;
            Node startNode, goalNode;
            System.out.println("> ¿De que nodo vas a partir?");
            this.graph.printNodes();
            start = this.scanner.nextInt();
            this.scanner.nextLine();
            startNode = this.graph.getNodeByIndex(start);
            System.out.println("> ¿A que nodo quieres llegar?");
            this.graph.printAvaliableNodes(startNode);
            goal = this.scanner.nextInt();
            this.scanner.nextLine();
            goalNode = this.graph.getNodeByIndex(goal);
            this.graph.depthFirstSearch(startNode, goalNode);
        } catch (Exception e) {
            System.out.println("> El nodo que ingresaste no existe.");
        }
    }

    void breadthFirstSearch() {
        try {
            Integer start, goal;
            Node startNode, goalNode;
            System.out.println("> ¿De que nodo vas a partir?");
            this.graph.printNodes();
            start = this.scanner.nextInt();
            this.scanner.nextLine();
            startNode = this.graph.getNodeByIndex(start);
            System.out.println("> ¿A que nodo quieres llegar?");
            this.graph.printAvaliableNodes(startNode);
            goal = this.scanner.nextInt();
            this.scanner.nextLine();
            goalNode = this.graph.getNodeByIndex(goal);
            this.graph.breadthFirstSearch(startNode, goalNode);
        } catch (Exception e) {
            System.out.println("> El nodo que ingresaste no existe.");
        }
    }

    void searchByName() {
        try {
            String query;
            System.out.print("> Ingresa el nombre de el nodo que quieres buscar: ");
            query = scanner.nextLine();
            Node found = this.graph.getNodeByName(query);
            if (found != null) {
                System.out.println("> Se encontro '" + query + "':");
                found.printProperties();
            } else {
                System.out.println("> No se encontro ningun nodo llamado '" + query + "'");
            }
        } catch (Exception e) {
            System.out.println("> El nodo que estas buscando no existe.");
        }
    }

    void searchLargest() {
        Node largest = this.graph.getLargestNode();
        System.out.println("> El nodo mayor es: ");
        largest.printProperties();
    }
}