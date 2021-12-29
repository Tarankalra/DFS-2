// TC - O(m*n) -> O(V+E) = O(mn + 4mn) = O(mn)
// SC - O(mn) for recursive stack

import java.util.LinkedList;
import java.util.Queue;

public class CountIslands {
    int [][] dirs;
    int m;
    int n;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        m = grid.length;
        n = grid[0].length;

        int numIslands = 0;
        // since the adjacent elements count as a part of the island
        // so moving in 4 directions
        // this represents the relation between the vertex and the edges
        // edges are in 4 different directions of a vertex
        dirs = new int [][]{{-1,0}, {1,0}, {0, -1}, {0,1}};


        for(int i =0; i< m ;i++) {
            for(int j =0; j<n; j++) {
                // we are incrementing the count only if the element is 1
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    numIslands++;
                }
            }
        }

        return numIslands;
    }

    private void dfs(char [][] grid, int row, int col) {
        // if the row and col are out of bounds or not equal to 1, they are not valid element, so we ignore them
        if(row < 0 || row >=m || col <0 || col >=n || grid[row][col]!= '1') {
            return;
        }

        grid[row][col] = '0'; // mark the element as visited
        // we can also use another 2-D matrix to keep account of the visted elements
        // but since we are ignoring both visited and 0 elements, we are overriding the elements 1 as 0 only

        int nr;
        int nc;
        for(int [] dir : dirs) {
            nr = row + dir[0];
            nc = col + dir[1];
            dfs(grid, nr, nc); // moving in all the four directions
        }
    }

    int numIslands;
    // TC - O(m*n) --> we are visiting each element twice for the worst case
    // SC - O(m*n) for the queue, tho it will be less, but say all elements are 1, then it will be O(m*n)
    public int numIslandsBFS(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        m = grid.length;
        n = grid[0].length;

        numIslands = 0;

        dirs = new int [][]{{-1,0}, {1,0}, {0, -1}, {0,1}};

        Queue<int []> q = new LinkedList <>();
        for(int i =0; i< m ;i++) {
            for(int j =0; j<n; j++) {
                if(grid[i][j] == '1') {
                    getNumIslandsBFS(grid, q, i, j);  // we doing BFS for each element if its valid
                }

            }
        }
        return numIslands;
    }

    private void getNumIslandsBFS(char[][] grid, Queue <int[]> q, int row, int col) {
        q.add(new int[] {row, col});
        grid[row][col] = '0'; // marking the element as visited
        while(!q.isEmpty()) {
            int [] elem = q.poll();
            for (int [] dir : dirs) {
                int nr = elem[0] + dir[0];
                int nc = elem[1] + dir[1];
                if(isValidIndex(nr, nc, grid)) {
                    q.add(new int[] {nr, nc}); // add in the queue if its valid
                    grid[nr][nc] = '0'; // marking the element as visited if added in the queue
                }
            }
        }
        numIslands++; // increasing the count when the queue is empty, because all the adjacent 1's are considered as an island
    }

    private boolean isValidIndex(int row, int col, char [][] grid) {
        return row >= 0 && row < m && col >= 0 && col < n && grid[row][col] == '1';
    }

}
