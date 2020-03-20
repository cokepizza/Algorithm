package Code4991;

import java.io.*;
import java.util.*;
aa
public class Code4991 {
    public static class Node {
        int y, x;
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int [][]dir = {{ 0, 0, 1, -1 }, { 1, -1, 0, 0 }};
    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nx = Integer.parseInt(st.nextToken());
        int ny = Integer.parseInt(st.nextToken());
        char[][] path = new char[ny][nx];

        for(int i=0; i<ny; ++i) {
            String pathStr = br.readLine();
            path[i] = pathStr.toCharArray();
        }
        
        int sy = -1, sx = -1, dirtyCount = 0;
        for(int i=0; i<ny; ++i) {
            for(int j=0; j<nx; ++j) {
                if(path[i][j] == 'o') {
                    sy = i;
                    sx = j;
                } else if(path[i][j] == '*') {
                    ++dirtyCount;
                }
            }
        }

        for(int i=0; i<dirtyCount; ++i) {
            boolean [][]visitied = new boolean[sy][sx];
            LinkedList <Node> queue = new LinkedList <>();
            while(!queue.isEmpty()) {
                
            }
        }

    }
}