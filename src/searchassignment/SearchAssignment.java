package searchassignment;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class SearchAssignment {
    static int[][] grid;
    static boolean[][] visited;
    static int n, m, i, j, end_i, end_j;
    
    // Main Function!
    public static void main(String[] args) throws FileNotFoundException {
        // Reading Input File 
        ReadFile();
        
        // Depth First Search Call
        DFS dfs = new DFS();
        dfs.depthFirstSearch(grid, visited, new ArrayList<Pair>(), n, m, i, j, end_i, end_j);
        dfs.printResult(); 
        
        // Breadh First Search Call
        BFS bfs = new BFS();
        bfs.breadthFirstSearch(grid, visited, n, m, i, j, end_i, end_j);
        bfs.printResult();  
        
        // Greedy Best First Search Call
        Greedy greedy = new Greedy();
        greedy.GreedyBestFirst(grid, visited, n, m, i, j, end_i, end_j);
        greedy.printResult();
        
        
        // A* Search Call
        AStar astar = new AStar();
        astar.AStarSearch(grid, visited, n, m, i, j, end_i, end_j);
        astar.printResult();
    } // #EndOfMain!
    
    
    // function:: ReadFile
    // Reads input text file and maps it onto an array
    static void ReadFile ( ) throws FileNotFoundException {
        // Read File from Scanner Stream
        Scanner sc = new Scanner(new File("Filing/A1-in1.txt"));
        
        // Grid Dimensions
        n = sc.nextInt();
        m = sc.nextInt();
        
        grid = new int[n][m];
        visited = new boolean[n][m];
        
        // End State
        i = sc.nextInt();
        j = sc.nextInt();
        
        // Start State
        end_i = sc.nextInt();
        end_j = sc.nextInt();
        
        // Reading grid as 2D Array
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                visited[i][j] = false;
                grid[i][j] = sc.nextInt();
            }
        }
    } // #EndOfReadFile!
}
