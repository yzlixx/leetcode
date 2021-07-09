package class14;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {

    //    输入: 正数数组costs、正数数组profits、正数K、正数M
//costs[i]表示i号项目的花费
//    profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
//    K表示你只能串行的最多做k个项目
//            M表示你初始的资金
//    说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
//    输出：你最后获得的最大钱数。
    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        // 贪心策略 做收益最大的项目
        PriorityQueue<Program> profitQueue = new PriorityQueue<>(new MyProfitComparatar());
        for (int i = 0; i < Profits.length; i++) {
            profitQueue.add(new Program(Profits[i], Capital[i]));
        }
        int done = 0;
        while (!profitQueue.isEmpty() && done<K) {
            if (profitQueue.peek().c <= W) {
                done++;
                W += profitQueue.poll().p;
            }
        }
        return W;
    }

    public static class MyProfitComparatar implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.p - o2.p;
        }
    }
}
