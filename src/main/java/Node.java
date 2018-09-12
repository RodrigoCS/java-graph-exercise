import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    Integer index, weight;
    List<Node> relations = new ArrayList<Node>();
    Boolean visited = false;

    Node(Integer index, String name, Integer weight) {
        this.setIndex(index);
        this.setName(name);
        this.setWeight(weight);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param visited the visited to set
     */
    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    /**
     * @return the visited
     */
    public Boolean getVisited() {
        return visited;
    }

    public void link(Node relatedNode) {
        if (!this.relations.contains(relatedNode)) {
            this.relations.add(relatedNode);
            relatedNode.relations.add(this); // Comentar para relaciones de una sola direccion
        } else {
            System.out.println("La relacion entre " + this.getName() + " y " + relatedNode.getName() + " ya existe");
        }
    }

    public void printRelations() {
        for (Node relation : relations) {
            System.out.println("[" + relation.getIndex() + "] > " + relation.getName() + " : " + relation.getWeight());
        }
    }

    public void printProperties() {
        System.out.println("> Indice: " + this.getIndex());
        System.out.println("> Nombre: " + this.getName());
        System.out.println("> Peso: " + this.getWeight());
        System.out.println("> Relaciones: ");
        this.printRelations();
    }

}