class Solution {
    public int countComponents(int n, int[][] edges) {
        int ans = 0;
        boolean[] used = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!used[i]){
                ans++; 
                dfs(edges, used, i);
            } 
            
            // for(int[] edge : edges) {
            //     for(int element : edge) {
            //         if(element == i) {
            //             used[edge[0]] = true;
            //             used[edge[1]] = true;
            //         }
            //     }
            // }
            
        }
        return ans;
    }
    
    private void dfs(int[][] edges, boolean[] used, int num) {
        if(used[num]) return;
        used[num] = true;
        for(int[] edge : edges) {
            for(int element : edge) {
                if(element == num) {
                    if(edge[0] == num) dfs(edges, used, edge[1]);
                    else dfs(edges, used, edge[0]);
                }
            }
        }
    }
}


//official answer DFS
class Solution {
    
    private void dfs(List<Integer>[] adjList, int[] visited, int startNode) {
       visited[startNode] = 1;
        
       for (int i = 0; i < adjList[startNode].size(); i++) {
           if (visited[adjList[startNode].get(i)] == 0) {
               dfs(adjList, visited, adjList[startNode].get(i));
           }
       }
   }
   
   public int countComponents(int n, int[][] edges) {
       int components = 0;
       int[] visited = new int[n];
       
       List<Integer>[] adjList = new ArrayList[n]; 
       for (int i = 0; i < n; i++) {
           adjList[i] = new ArrayList<Integer>();
       }
       
       for (int i = 0; i < edges.length; i++) {
           adjList[edges[i][0]].add(edges[i][1]);
           adjList[edges[i][1]].add(edges[i][0]);
       }
       
       for (int i = 0; i < n; i++) {
           if (visited[i] == 0) {
               components++;
               dfs(adjList, visited, i);
           }
       }
       return components;
   }
}

//Disjoint Set Union (DSU)
public class Solution {

    private int find(int[] representative, int vertex) {
        if (vertex == representative[vertex]) {
            return vertex;
        }
        
        return representative[vertex] = find(representative, representative[vertex]);
    }
    
    private int combine(int[] representative, int[] size, int vertex1, int vertex2) {
        vertex1 = find(representative, vertex1);
        vertex2 = find(representative, vertex2);
        
        if (vertex1 == vertex2) {
            return 0;
        } else {
            if (size[vertex1] > size[vertex2]) {
                size[vertex1] += size[vertex2];
                representative[vertex2] = vertex1;
            } else {
                size[vertex2] += size[vertex1];
                representative[vertex1] = vertex2;
            }
            return 1;
        }
    }

    public int countComponents(int n, int[][] edges) {
        int[] representative = new int[n];
        int[] size = new int[n];
        
        for (int i = 0; i < n; i++) {
            representative[i] = i;
            size[i] = 1;
        }
        
        int components = n;
        for (int i = 0; i < edges.length; i++) { 
            components -= combine(representative, size, edges[i][0], edges[i][1]);
        }

        return components;
    }
}