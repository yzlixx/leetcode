package class14;

import java.util.HashSet;

public class Code01_Light {

//    给定一个字符串str，只由‘X’和‘.’两种字符构成。
//            ‘X’表示墙，不能放灯，也不需要点亮
//            ‘.’表示居民点，可以放灯，需要点亮
//            如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
//            返回如果点亮str中所有需要点亮的位置，至少需要几盏灯

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

    private static int minLight1(String road) {
        char[] chars = road.toCharArray();
        int light = 0;
        int start = 0;
        while(start < chars.length){
            if(chars[start] != 'X'){
                light++;
                if(start+1<chars.length && start+2<chars.length && chars[start+1] == '.' && chars[start+2] == '.'){
                    start += 3;
                }else if(start+1<chars.length && chars[start+1] == '.'){
                    start +=2;
                }else{
                    start +=1;
                }
            }else{
                start +=1;
            }
        }
        return light;
    }

    private static int minLight2(String road) {
        char[] chars = road.toCharArray();
        return process(chars,0,new HashSet<>());
    }

    private static int process(char[] chars, int index, HashSet<Integer> lights) {
        if(index == chars.length){
            for(int i = 0; i <chars.length; i++){
                if(chars[i] != 'X'){
                    if(!lights.contains(i-1) && !lights.contains(i) && !lights.contains(i+1)){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else{
            int no = process(chars,index+1,lights);
            int yes = Integer.MAX_VALUE;
            if(chars[index] == '.'){
                lights.add(index);
                yes = process(chars,index+1,lights);
                lights.remove(index);
            }
            return Math.min(no,yes);
        }
    }

}
