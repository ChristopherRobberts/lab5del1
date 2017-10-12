import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;

import java.util.Arrays;
import java.util.Iterator;

public class Paths {
    private boolean[] marked;
    private int[] id;
    private int count;
    private int curr;

    private void inspectVertices(Graph g){
        marked = new boolean[g.numberOfVertices()];
        id = new int[g.numberOfVertices()];

        for(int i = 0; i < marked.length; i++){
            if(!marked[i]){
                dfs(g, i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        id[v] = count;
        Iterator<Edge> edgeList = g.adj(v).iterator();

        while(edgeList.hasNext()){
            curr = edgeList.next().to;
            if(!marked[curr]){
                v = curr;
                dfs(g, v);
            }
        }
    }

    private int[] getId(){
        return id;
    }
    private int getCount(){
        return this.count;
    }
    public static void main(String[] args) {
        Graph g = DataSource.load();
        Paths paths = new Paths();
        paths.inspectVertices(g);
        System.out.println(Arrays.toString(paths.getId()));
        int counter = 0;
        int [] temp = paths.getId();
        for(int i = 0; i < temp.length; i++){
            if(temp[i] == 0){
                counter++;
            }
        }
        System.out.println("The number of vertices in the first graph equals : " + counter);
        System.out.println(paths.getCount());
    }
}