package searchassignment;

import java.util.ArrayList;

public class DFS {
    private int min = Integer.MAX_VALUE;
    public ArrayList<Pair> optimalPath = new ArrayList<Pair> ();
    
    void depthFirstSearch ( int grid[][], boolean array[][], ArrayList<Pair> temp, int n, int m, int i, int j, int end_i, int end_j ) {
        // Copy Constructor
        boolean visited[][] = new boolean[5][5];
        copyBoolean(visited, array, n, m);
        
        ArrayList<Pair> path = new ArrayList<Pair> ();
        copyList(path, temp);
        path.add(new Pair(i, j));
        
        // If Indexes are Out of Bound then Return from Branch
        if ( i<0 || j<0 || i>=n || j>=m ) return;
        
        // Goal State Test
        if ( i==end_i && j==end_j ) {
            if ( path.size() <= min ) {
                min = path.size();
                optimalPath = path;
            }
        }
        
        // Horizontal and Vertical Search
        if (i-1>=0 && !visited[i-1][j] && grid[i-1][j]==0) { // Up 
            visited[i-1][j] = true;
            depthFirstSearch(grid, visited, path, n, m, i-1, j, end_i, end_j);
        } if (i+1<5 && !visited[i+1][j] && grid[i+1][j]==0) { // Down
            visited[i+1][j] = true;
            depthFirstSearch(grid, visited, path, n, m, i+1, j, end_i, end_j);
        } if (j-1>=0 && !visited[i][j-1] && grid[i][j-1]==0) { // Left
            visited[i][j-1] = true;
            depthFirstSearch(grid, visited, path, n, m, i, j-1, end_i, end_j);
        } if (j+1<5 && !visited[i][j+1] && grid[i][j+1]==0) { // Right
            visited[i][j+1] = true;
            depthFirstSearch(grid, visited, path, n, m, i, j+1, end_i, end_j);
        }
        
        // Diagonal and Anti-Diagonal Search
        if (i-1>=0 && j-1>=0 && !visited[i-1][j-1] && grid[i-1][j-1]==0) { // Top-Left
            visited[i-1][j-1] = true;
            depthFirstSearch(grid, visited, path, n, m, i-1, j-1, end_i, end_j);
        } if (i-1>=0 && j+1<5 && !visited[i-1][j+1] && grid[i-1][j+1]==0) { // Top-Right
            visited[i-1][j+1] = true;
            depthFirstSearch(grid, visited, path, n, m, i-1, j+1, end_i, end_j);
        } if (i+1<5 && j-1>=0 && !visited[i+1][j-1] && grid[i+1][j-1]==0) { // Bottom-Left
            visited[i+1][j-1] = true;
            depthFirstSearch(grid, visited, path, n, m, i+1, j-1, end_i, end_j);
        } if (i+1<5 && j+1<5 && !visited[i+1][j+1] && grid[i+1][j+1]==0) { // Bottom-Right
            visited[i+1][j+1] = true;
            depthFirstSearch(grid, visited, path, n, m, i+1, j+1, end_i, end_j);
        }
    } // #EndOfDepthFirstSearch!
    
    void copyBoolean ( boolean visited[][], boolean v[][], int n, int m ) {
        for(int i=0; i<n; i++) {
            System.arraycopy(v[i], 0, visited[i], 0, m);
        }
    } // #EndOfCopyBoolean!
    
    void copyList ( ArrayList<Pair> path, ArrayList<Pair> temp ) {
        for(int i=0; i<temp.size(); i++) path.add(temp.get(i));
    } // #EndOfCopyList!
    
    void printResult ( ) {
        System.out.println("Depth First Search Result!");
        System.out.print("Optimal Path Cost: " + optimalPath.size() + "\nOptimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            System.out.print("(" + optimalPath.get(i).first + ", " + optimalPath.get(i).second + ")");
            if ( i!=optimalPath.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
}
