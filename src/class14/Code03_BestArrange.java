package class14;

import java.util.Arrays;
import java.util.Comparator;

public class Code03_BestArrange {

    //    一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
//    给你每一个项目开始的时间和结束的时间
//    你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
//    返回最多的宣讲场次。
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }

        return process(programs, 0, 0);
    }

    //done 执行数量
    // timeLine 目前结束时间
    private static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        int ans = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                ans = Math.max(ans, process(copyArray(programs, i), done + 1, programs[i].end));
            }
        }
        return ans;
    }

    private static Program[] copyArray(Program[] programs, int index) {
        Program[] help = new Program[programs.length - 1];
        int helpIndex = 0;
        for (int i = 0; i < programs.length; i++) {
            if (i != index) {
                help[helpIndex++] = programs[i];
            }
        }
        return help;
    }

    // 贪心策略 结束时间早的优先
    private static int bestArrange2(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        Arrays.sort(programs, new MyComparator());
        int timeLine = 0;
        int done = 0;
        for (int i =0;i<programs.length; i++){
            if(programs[i].start>=timeLine){
                done++;
                timeLine = programs[i].end;
            }
        }
        return done;
    }


    private static class MyComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }


}
