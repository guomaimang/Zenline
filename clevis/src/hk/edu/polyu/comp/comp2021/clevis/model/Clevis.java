package hk.edu.polyu.comp.comp2021.clevis.model;
import java.util.ArrayList;

public class Clevis {

    public Clevis(){}

    private static ArrayList<Graph> graphs = new ArrayList<>();
    private static ArrayList<Collection> collections = new ArrayList<>();

    public static void addGraph(Graph g){
        graphs.add(g);
    }
    public static void addCollection(Collection c){
        collections.add(c);
    }

    public static boolean deleteShape(String shapeName){
        for (int i = 0; i < graphs.size()-1 ; i++) {
            if (graphs.get(i).name.equals(shapeName)) return deleteGraph(graphs.get(i));
        }
        for (int i = 0; i < collections.size()-1; i++) {
            if(collections.get(i).name.equals(shapeName)) return deleteCollection(collections.get(i));
        }
        System.out.println("There is not such a shape!");
        return false;
    } // This boolean is used for take down version
    public static boolean deleteGraph(Graph g){
        if (g.isDelete){
            System.out.println("Delete Successful!");
            return true;
        }else{
            System.out.println("There is not such a shape! You have already delete it!");
            return false;
        }
    }
    public static boolean deleteCollection(Collection g){
        if (g.isDelete){
            System.out.println("Delete Successful!");
            return true;
        }else{
            System.out.println("There is not such a shape! You have already delete it!");
            return false;
        }
    }

    public boolean listShape(String shapeName){
        for (int i = 0; i < graphs.size()-1 ; i++) {
            if (graphs.get(i).name.equals(shapeName)) {
                graphs.get(i).listSelf();
                return true;}
        }
        for (int i = 0; i < collections.size()-1; i++) {
             if(collections.get(i).name.equals(shapeName)) {
                collections.get(i).listSelf();
                return true;}
        }
        System.out.println("There is not such a shape!");
        return false;
    }

    // public int getGraphNum(){return graphs.size();}
    // public int getCollectionNum(){return collections.size();}

    public void printClevis(){}
}
