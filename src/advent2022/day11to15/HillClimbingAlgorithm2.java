package advent2022.day11to15;
import java.util.*;
import java.io.*;
import common.Dijkstra;
import common.Node;

public class HillClimbingAlgorithm2 {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File(
            System.getProperty("user.dir") + "/lib/advent2022/day12.txt"));

        int rows = 41; //5 41
        int clmns = 159; //8 159
        ArrayList<Integer> starts = new ArrayList<>();

        char[][] hill = new char[rows][clmns];
        int e = -1;
        int cnt = -1;
        for (int i = 0; i < rows; i++){
            String temp = input.nextLine();
            for (int j = 0; j < clmns; j++){
                cnt++;
                hill[i][j] = temp.charAt(j);
                if (temp.charAt(j) == 'a'){
                    starts.add(cnt);
                } else if (temp.charAt(j) == 'S'){
                    starts.add(cnt);
                    hill[i][j] = 'a';
                } else if (temp.charAt(j) == 'E'){
                    e = cnt;
                    hill[i][j] = 'z';
                }
            }
        }

        int verts = cnt + 1;

        List<List<Node>> adj = new ArrayList<List<Node>>();
        for (int i = 0; i < verts; i++){
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }
        
        cnt = -1;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < clmns; j++){
                cnt++;

                if (i != 0 && hill[i-1][j] <= hill[i][j] + 1)
                    adj.get(cnt).add(new Node(cnt - clmns, 1));
                if (i != rows - 1 && hill[i+1][j] <= hill[i][j] + 1)
                    adj.get(cnt).add(new Node(cnt + clmns, 1));
                if (j != 0 && hill[i][j-1] <= hill[i][j] + 1)
                    adj.get(cnt).add(new Node(cnt - 1, 1));
                if (j != clmns - 1 && hill[i][j+1] <= hill[i][j] + 1)
                    adj.get(cnt).add(new Node(cnt + 1, 1));
                
            }
        }

        int min = 9999;
        for (int i = 0; i < starts.size(); i++){
            Dijkstra object = new Dijkstra(verts);
            object.algorithm(adj, starts.get(i));
            if (object.dist[e] < min)
                min = object.dist[e];
        }
        System.out.println(min);
        input.close();
    }
}
