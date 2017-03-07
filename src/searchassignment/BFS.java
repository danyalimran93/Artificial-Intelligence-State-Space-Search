package searchassignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private int min = Integer.MAX_VALUE;
    public ArrayList<Pair> optimalPath = new ArrayList<Pair> ();
    
    void breadthFirstSearch ( int grid[][], boolean visited[][], int n, int m, int i, int j, int end_i, int end_j ) { 
        // Queue for BFS
        Queue<State> queue = new LinkedList<State> ();
        
        // Starting State Pushing in Queue Frontier
        State start = new State(i, j, 0, n, m, visited);
        queue.add(start);
        
        // BFS Algorithm Implementation
        while ( !queue.isEmpty() ) {
            State current = queue.remove();
            
            i = current.first;
            j = current.second;
            
            // Goal State Test
            if ( current.first==end_i && current.second==end_j ) {
                if ( current.cost < min ) {
                    min = current.cost;
                    optimalPath = current.path;
                    
                    optimalPath.add(new Pair(i, j));
                }
            }
            
            // Horizontal and Vertical Conditional Search
            if ( i-1>=0 && !current.visited[i-1][j] && grid[i-1][j]==0 ) {
                current.visited[i-1][j] = true;
                State temp = new State(i-1, j, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            } if ( i+1<5 && !current.visited[i+1][j] && grid[i+1][j]==0 ) {
                current.visited[i+1][j] = true;
                State temp = new State(i+1, j, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            } if ( j-1>=0 && !current.visited[i][j-1] && grid[i][j-1]==0 ) {
                current.visited[i][j-1] = true;
                State temp = new State(i, j-1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            } if ( j+1<5 && !current.visited[i][j+1] && grid[i][j+1]==0 ) {
                current.visited[i][j+1] = true;
                State temp = new State(i, j+1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            }
            
            // Diagonal and Anti-Diagonal Conditional Search
            if ( i-1>=0 && j-1>=0 && !current.visited[i-1][j-1] && grid[i-1][j-1]==0 ) {
                current.visited[i-1][j-1] = true;
                State temp = new State(i-1, j-1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            } if ( i-1>=0 && j+1<5 && !current.visited[i-1][j+1] && grid[i-1][j+1]==0 ) {
                current.visited[i-1][j+1] = true;
                State temp = new State(i-1, j+1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            } if ( i+1<5 && j-1>=0 && !current.visited[i+1][j-1] && grid[i+1][j-1]==0 ) {
                current.visited[i+1][j-1] = true;
                State temp = new State(i+1, j-1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            } if ( i+1<5 && j+1<5 && !current.visited[i+1][j+1] && grid[i+1][j+1]==0 ) {
                current.visited[i+1][j+1] = true;
                State temp = new State(i+1, j+1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                queue.add(temp);
            }
        }
    }
    
    void printResult ( ) {
        System.out.println("\n\nBreadth First Search Result!");
        System.out.print("Optimal Path Cost: " + optimalPath.size() + "\nOptimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            System.out.print("(" + optimalPath.get(i).first + ", " + optimalPath.get(i).second + ")");
            if ( i!=optimalPath.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
}
