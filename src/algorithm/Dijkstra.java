package algorithm;

import java.util.*;

public class Dijkstra {
    // 图的邻接表表示，key为节点，value为邻接节点和边的权重
    private static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[] dijkstra(int n, List<List<Edge>> graph, int start) {
        // 距离数组，初始化为无穷大
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 优先队列（最小堆），根据距离排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});  // 起点入队，距离为0

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0], currentDist = node[1];

            // 如果当前距离大于已知最短距离，跳过
            if (currentDist > dist[u]) {
                continue;
            }

            // 遍历 u 的邻居
            for (Edge edge : graph.get(u)) {
                int v = edge.to, weight = edge.weight;
                int newDist = currentDist + weight;

                // 如果通过 u 到 v 的距离更短，更新并加入队列
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new int[]{v, newDist});
                }
            }
        }

        return dist;  // 返回从起点到所有节点的最短距离
    }

    public static void main(String[] args) {
        int n = 6;  // 图的节点数
        List<List<Edge>> graph = new ArrayList<>();

        // 初始化图的邻接表
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 添加边 (u, v, weight)
        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(4, 5));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(4, 2));
        graph.get(2).add(new Edge(3, 4));
        graph.get(3).add(new Edge(0, 7));
        graph.get(3).add(new Edge(2, 6));
        graph.get(4).add(new Edge(1, 3));
        graph.get(4).add(new Edge(3, 9));

        int start = 0;  // 从节点 0 开始
        int[] dist = dijkstra(n, graph, start);

        // 输出从起点到各个节点的最短距离
        // the result should be:
        // 从节点 0 到其他节点的最短路径：
        // 到节点 0 的最短距离是 0
        // 到节点 1 的最短距离是 8
        // 到节点 2 的最短距离是 9
        // 到节点 3 的最短距离是 13
        // 到节点 4 的最短距离是 5
        // 到节点 5 的最短距离是 ∞
        System.out.println("从节点 " + start + " 到其他节点的最短路径：");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("到节点 " + i + " 的最短距离是 " + (dist[i] == Integer.MAX_VALUE ? "∞" : dist[i]));
        }
    }
}