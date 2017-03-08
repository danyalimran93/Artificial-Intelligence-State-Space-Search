package searchassignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static searchassignment.AStar.optimalPath;

public class IDS {
    static int min;
    static int grid[][];
    static ArrayList<Pair> optimalPath;
    
    IDS () {
        min = Integer.MAX_VALUE;
        optimalPath = new ArrayList<Pair> ();
    }
    
    void iterativeDeepeningSearch ( int array[][], boolean visited[][], int n, int m, int i, int j, int end_i, int end_j ) {
        // Copying grid to static array
        grid = array;
        
        // Creating Source and Destination States
        State dest = new State(end_i, end_j, 0, n, m, visited);
        
        visited[i][j] = true;
        State source = new State(i, j, 1, n, m, visited);
        
        // Repeating until limit found
        for ( int limit=0; limit<=(n*m-2); limit++ ) {
            for ( int x=0; x<n; x++ ) 
                for ( int y=0; y<m; y++ ) source.visited[x][y] = false;
        
            // Depth Limited Search
            depthLimitedSearch(source, dest, limit, n, m);
        }
        
    }
    
    // Private Function for Iterative Deepening Algorithm
    private boolean depthLimitedSearch ( State current, State dest, int limit, int n, int m ) {
        int i = current.first;
        int j = current.second;
        
        // If values out of bound
        if ( limit<=0 || i<0 || j<0 || i>=n || j>=m )
            return false;
        
        // Goal State Test
        if ( i==dest.first && j==dest.second ) {
            if ( current.cost < min ) {
                min = current.cost;
                optimalPath = current.path;
                
                optimalPath.add(new Pair(i, j));
            }
            
            return true;
        }
        
        // Horizontal and Vertical Conditional Search
        if ( i-1>=0 && !current.visited[i-1][j] && grid[i-1][j]==0 ) {
            current.visited[i-1][j] = true;
            State next = new State(i-1, j, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( i+1<n && !current.visited[i+1][j] && grid[i+1][j]==0 ) {
            current.visited[i+1][j] = true;
            State next = new State(i+1, j, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( j-1>=0 && !current.visited[i][j-1] && grid[i][j-1]==0 ) {
            current.visited[i][j-1] = true;
            State next = new State(i, j-1, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( j+1<m && !current.visited[i][j+1] && grid[i][j+1]==0 ) {
            current.visited[i][j+1] = true;
            State next = new State(i, j+1, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        }
        
        // Diagonal and Anti-Diagonal Conditional Search
        if ( i-1>=0 && j-1>=0 && !current.visited[i-1][j-1] && grid[i-1][j-1]==0 ) {
            current.visited[i-1][j-1] = true;
            State next = new State(i-1, j-1, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( i-1>=0 && j+1<m && !current.visited[i-1][j+1] && grid[i-1][j+1]==0 ) {
            current.visited[i-1][j+1] = true;
            State next = new State(i-1, j+1, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( i+1<n && j-1>=0 && !current.visited[i+1][j-1] && grid[i+1][j-1]==0 ) {
            current.visited[i+1][j-1] = true;
            State next = new State(i+1, j-1, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( i+1<n && j+1<m && !current.visited[i+1][j+1] && grid[i+1][j+1]==0 ) {
            current.visited[i+1][j+1] = true;
            State next = new State(i+1, j+1, current.cost+1, n, m, current.visited);
            next.AddPair(current.path, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        }
        
        // If nothing is searches on this level
        return false;
    }
    
    void printResult ( ) {
        System.out.println("\n\nIterative Deepening Search Result!");
        System.out.print("Optimal Path Cost: " + optimalPath.size() + "\nOptimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            System.out.print("(" + optimalPath.get(i).first + ", " + optimalPath.get(i).second + ")");
            if ( i!=optimalPath.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/IDS-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("Iterative Deepening Search Result!"); bw.newLine();
        bw.write("Optimal Path Cost: " + optimalPath.size()); bw.newLine();
        bw.write("Optimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            bw.write("(" + optimalPath.get(i).first + ", " + optimalPath.get(i).second + ")");
            if ( i!=optimalPath.size()-1 ) bw.write(" ");
        } bw.write("}"); bw.newLine();
        
        bw.flush();
        bw.close();
        fw.close();
    } // #EndOfWriteFile!
}
