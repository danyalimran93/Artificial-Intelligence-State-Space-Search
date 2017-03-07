package searchassignment;

import java.util.ArrayList;

public class State {
    int first, second, cost;
    ArrayList<Pair> path;
    boolean visited[][];
    
    State ( int fi, int se, int c, int n, int m, boolean v[][] ) {
        first = fi;
        second = se;
        cost = c;
        path = new ArrayList<Pair>();
        
        visited = new boolean[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) visited[i][j] = v[i][j];
        }
    }
    
    void AddPair ( ArrayList<Pair> visited, int i, int j ) {
        for(int x=0; x<visited.size(); x++) 
            path.add(visited.get(x));
        
        path.add(new Pair(i, j));
    }
}
