public class LeetCode1720 {

    /**
     * 未知 整数数组 arr 由 n 个非负整数组成。
     * <p>
     * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
     * <p>
     * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
     * <p>
     * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
     * <p>
     * 示例 1：
     * <p>
     * 输入：encoded = [1,2,3], first = 1
     * 输出：[1,0,2,1]
     * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
     * 示例 2：
     * <p>
     * 输入：encoded = [6,2,7,3], first = 4
     * 输出：[4,2,0,7,4]
     *  
     * <p>
     * 提示：
     * <p>
     * 2 <= n <= 104
     * encoded.length == n - 1
     * 0 <= encoded[i] <= 105
     * 0 <= first <= 105
     */
    public static int[] decode(int[] encoded, int first) {
        // 异或的一些基本特性
        // arr[i]^arr[i+1] = encoded[i]
        // arr[i]^arr[i+1]^arr[i] = encoded[i]^arr[i];
        // arr[i+1] = encoded[i]^arr[i];

        int[] arr = new int[encoded.length + 1];
        //初始化第一个值
        arr[0] = first;

        for (int i = 0; i < encoded.length; i++) {
            arr[i + 1] = encoded[i] ^ arr[i];
        }

        return arr;
    }

    //*************************
    //对数器

    //生成长度maxLen,最大值为maxValue的数组
    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random()*maxLen-2)+2;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }

        return arr;
    }

    //生成encode数组
    public static int[] encodeArr(int[] arr) {
        int[] encodedArr = new int[arr.length - 1];

        for (int i = 0; i < arr.length - 1; i++) {

            encodedArr[i] = arr[i] ^ arr[i + 1];

        }

        return encodedArr;
    }

    //判断数组相等

    public static boolean arrEqual(int[] arr1,int[] arr2){
        if(arr1.length != arr2.length){
            return false;
        }

        for (int i = 0; i < arr1.length ; i++) {
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testNum = 100000;
        int maxLen = 1000;
        int maxValue = 10000;
        for (int i = 0; i < testNum; i++) {
            int[] arr = randomArr(maxLen, maxValue);
            int[] encodedArr = encodeArr(arr);
            int[] decodeArr = decode(encodedArr, arr[0]);
            if(!arrEqual(arr,decodeArr)){
                System.out.println("数组不等");
            }

        }
    }
}
