package Code4991;

import java.io.*;
import java.util.*;

public class Code4991 {
    public static class Node {
        int y, x;
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int [][]dir = {{ 0, 0, 1, -1 }, { 1, -1, 0, 0 }};
    private static int nx, ny, size;
    private static char [][]path;
    private static Node []listArr;
    private static int [][]dp, dist;
    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        loop: while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nx = Integer.parseInt(st.nextToken());
            ny = Integer.parseInt(st.nextToken());
            if(nx == 0 && ny == 0) break;
            path = new char[ny][nx];
    
            for(int i=0; i<ny; ++i) {
                String pathStr = br.readLine();
                path[i] = pathStr.toCharArray();
            }
            
            ArrayList <Node> list = new ArrayList <>();
            for(int i=0; i<ny; ++i) {
                for(int j=0; j<nx; ++j) {
                    if(path[i][j] == 'o') {
                        list.add(0, new Node(i, j));
                    } else if(path[i][j] == '*') {
                        list.add(new Node(i, j));
                    }
                }
            }

            size = list.size();
            listArr = new Node[size];
            for(int i=0; i<size; ++i) {
                listArr[i] = list.get(i);
            }

            dist = new int[size][size];
            for(int i=0; i<size; ++i) {
                for(int j=i+1; j<size; ++j) {
                    int distance = getLocalMinDist(i, j);
                    if(distance == -1) {
                        sb.append(distance + "\n");
                        continue loop;
                    }
                    dist[i][j] = dist[j][i] = distance;
                }
            }

            int visitied = 1 << 0;
            dp = new int[size][1 << size];
            for(int i=0; i<size; ++i) {
                Arrays.fill(dp[i], -1);
            }
            int min = getGlobalMinDist(0, visitied);
            sb.append(min + "\n");
        }

        System.out.print(sb.substring(0, sb.length() - 1));
    }

    private static int getLocalMinDist(int s, int e) {
        LinkedList <Node> queue = new LinkedList <>();
        boolean [][] visitied = new boolean[ny][nx];
        queue.add(new Node(listArr[s].y, listArr[s].x));
        int count = 0;
        boolean pass = false;
        loop: while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; ++i) {
                Node node = queue.poll();
                int y = node.y;
                int x = node.x;
                if(y == listArr[e].y && x == listArr[e].x) {
                    pass = true;
                    break loop;
                };

                for(int j=0; j<4; ++j) {
                    int dy = y + dir[0][j];
                    int dx = x + dir[1][j];
                    if(dy < 0 || dx < 0 || dy >= ny || dx >= nx || visitied[dy][dx] || path[dy][dx] == 'x') continue;
                    visitied[dy][dx] = true;
                    queue.add(new Node(dy, dx));
                }
            }
            ++count;
        }

        if(!pass) return -1;
        return count;
    }

    private static int getGlobalMinDist(int index, int visitied) {
        if(visitied == (1 << size) -1) return 0;
        if(dp[index][visitied] != -1) return dp[index][visitied];

        int minDist = Integer.MAX_VALUE;
        for(int i=0; i<size; ++i) {
            if((visitied & (1 << i)) == 0) {
                minDist = Math.min(minDist, getGlobalMinDist(i, visitied | (1 << i)) + dist[index][i]);
            }
        }

        return dp[index][visitied] = minDist;
    }
}