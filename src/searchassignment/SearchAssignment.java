package searchassignment;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SearchAssignment {
    static int[][] grid;
    static boolean[][] visited;
    static int n, m, i, j, end_i, end_j;
    
    // Main Function!
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Reading Input File 
        ReadFile();
        
        // Depth First Search Call
        DFS dfs = new DFS();
        dfs.depthFirstSearch(grid, visited, new ArrayList<Pair>(), n, m, i, j, end_i, end_j);
        dfs.printResult(); 
        dfs.writeFile();
        
        // Breadh First Search Call
        BFS bfs = new BFS();
        bfs.breadthFirstSearch(grid, visited, n, m, i, j, end_i, end_j);
        bfs.printResult();  
        bfs.writeFile();
        
        // Iterative Deepening Search Call
        IDS ids = new IDS();
        ids.iterativeDeepeningSearch(grid, visited, n, m, i, j, end_i, end_j);
        ids.printResult();
        ids.writeFile();
        
        // Greedy Best First Search Call
        Greedy greedy = new Greedy();
        greedy.GreedyBestFirst(grid, visited, n, m, i, j, end_i, end_j);
        greedy.printResult();
        greedy.writeFile();
        
        // A* Search Call
        AStar astar = new AStar();
        astar.AStarSearch(grid, visited, n, m, i, j, end_i, end_j);
        astar.printResult();
        astar.writeFile();
    } // #EndOfMain!
    
    
    // function:: ReadFile
    // Reads input text file and maps it onto an array
    static void ReadFile ( ) throws FileNotFoundException {
        // Read File from Scanner Stream
        Scanner sc = new Scanner(new File("Filing/A1-in2.txt"));
        
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
        System.out.println("Grid");
        for(int x=0; x<n; x++) {
            for(int y=0; y<m; y++) {
                visited[x][y] = false;
                grid[x][y] = sc.nextInt();
                
                if ( x==i && y==j ) System.out.print("S ");
                else if ( x==end_i && y==end_j ) System.out.print("D ");
                else System.out.print(grid[x][y] + " ");
            } System.out.println();
        }
    } // #EndOfReadFile!
}
