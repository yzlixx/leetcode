package class15;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/7/9 18:20
 */
public class Code01_FriendCircles {

    //  1代表认识，0代表不认识
    // int[][] = [[1,1,0,0],[1,1,1,0],[0,1,1,0],[0,0,0,1]]
    //    1  2  3  4
    // 1  1  1  0  0
    // 2  1  1  1  0
    // 3  0  1  1  0
    // 4  0  0  0  1
    public static int findCircleNum(int[][] M) {
        int length = M.length;
        UnionFind unionFind = new UnionFind(length);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
               if (M[i][j] == 1){
                   unionFind.union(i,j);
               }
            }
        }
        return unionFind.sets();
    }


    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int sets;
        public UnionFind(int length){
            parent = new int[length];
            size = new int[length];
            sets = length;
        }


        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if(f1 != f2){
                int size1 = size[f1];
                int size2 = size[f2];
                if(size1 >= size2){
                    size[f1] = size1+size2;
                    parent[f2] = f1;
                }else{
                    size[f2] = size1+size2;
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        private int find(int i) {
            int hi = i;
           while(hi != parent[hi]){
               hi = parent[hi];
           };
           parent[i] = hi;
           return hi;
        }

        public int sets() {
            return sets;
        }
    }
}
