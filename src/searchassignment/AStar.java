package searchassignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {
    public static int min;
    public static int goal_i, goal_j; 
    public static ArrayList<Pair> optimalPath;
    
    AStar ( ) {
        min = Integer.MAX_VALUE;
        optimalPath = new ArrayList<Pair> ();
    }
    
    void AStarSearch ( int grid[][], boolean visited[][], int n, int m, int i, int j, int end_i, int end_j ) {
        // Initializing static goal index variables
        goal_i = end_i;
        goal_j = end_j; 
        
        // Creating a Priority Queue Data Structure
        Comparator<State> comparator = new AStarPairComparator();
        PriorityQueue<State> priorityQueue = new PriorityQueue<State>(comparator);
        
        // Adding Current State to Priority Queue
        visited[i][j] = true;
        State start = new State(i, j, 0, n, m, visited);
        priorityQueue.add(start);
        
        // A* Search Algorithm
        while ( !priorityQueue.isEmpty() ) {
            State current = priorityQueue.remove();
            
            i = current.first;
            j = current.second;
            
            // Goal State Test
            if ( current.first==goal_i && current.second==goal_j ) {
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
                priorityQueue.add(temp);
            } if ( i+1<n && !current.visited[i+1][j] && grid[i+1][j]==0 ) {
                current.visited[i+1][j] = true;
                State temp = new State(i+1, j, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                priorityQueue.add(temp);
            } if ( j-1>=0 && !current.visited[i][j-1] && grid[i][j-1]==0 ) {
                current.visited[i][j-1] = true;
                State temp = new State(i, j-1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                priorityQueue.add(temp);
            } if ( j+1<m && !current.visited[i][j+1] && grid[i][j+1]==0 ) {
                current.visited[i][j+1] = true;
                State temp = new State(i, j+1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                priorityQueue.add(temp);
            }
            
            // Diagonal and Anti-Diagonal Conditional Search
            if ( i-1>=0 && j-1>=0 && !current.visited[i-1][j-1] && grid[i-1][j-1]==0 ) {
                current.visited[i-1][j-1] = true;
                State temp = new State(i-1, j-1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                priorityQueue.add(temp);
            } if ( i-1>=0 && j+1<m && !current.visited[i-1][j+1] && grid[i-1][j+1]==0 ) {
                current.visited[i-1][j+1] = true;
                State temp = new State(i-1, j+1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                priorityQueue.add(temp);
            } if ( i+1<n && j-1>=0 && !current.visited[i+1][j-1] && grid[i+1][j-1]==0 ) {
                current.visited[i+1][j-1] = true;
                State temp = new State(i+1, j-1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                priorityQueue.add(temp);
            } if ( i+1<n && j+1<m && !current.visited[i+1][j+1] && grid[i+1][j+1]==0 ) {
                current.visited[i+1][j+1] = true;
                State temp = new State(i+1, j+1, current.cost+1, n, m, current.visited);
                temp.AddPair(current.path, i, j);
                priorityQueue.add(temp);
            }
        }
    }
    
    public static int euclidean_distance ( int x1, int y1, int x2, int y2 ) {
        return (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
    
    void printResult ( ) {
        System.out.println("\n\nA* Search Result!");
        System.out.print("Optimal Path Cost: " + optimalPath.size() + "\nOptimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            System.out.print("(" + optimalPath.get(i).first + ", " + optimalPath.get(i).second + ")");
            if ( i!=optimalPath.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/AStar-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("A* Search Result!"); bw.newLine();
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


class AStarPairComparator implements Comparator<State> {
    @Override
    public int compare ( State a, State b ) {
        int distance_a = Greedy.euclidean_distance(a.first, b.first, Greedy.goal_i, Greedy.goal_j);
        int distance_b = Greedy.euclidean_distance(b.first, b.second, Greedy.goal_i, Greedy.goal_j);
        
        // A* function: f(n) = g(n) + h(n)
        if ( distance_a+a.cost > distance_b+b.cost ) return 1;
        else if ( distance_a+a.cost < distance_b+b.cost ) return -1;
        return 0;
    }
}