package algorithm;

/**
 * @Author Xiheng He
 * @ClassName MaxKnapsack
 * @Description TODO
 * @Date 2021/06/2021/6/21/2:26
 * @Version 1.0
 */

public class MaxKnapsack {

    private int n;
    private int[] price;
    private int[] size;
    private int capacity;
    private double r;
    private boolean mode;

    /**
     * constructor for PPTAS for Max-Knapsack
     * @param n
     * @param cost
     * @param size
     * @param capacity
     */
    public MaxKnapsack(int n, int[] cost, int[] size, int capacity) {
        this.n = n;
        this.price = cost;
        this.size = size;
        this.capacity = capacity;
        this.mode = false;
    }

    /**
     * constructor for FPTAS for Maximum Knapsack
     * @param n
     * @param cost
     * @param size
     * @param capacity
     * @param r
     */
    public MaxKnapsack(int n, int[] cost, int[] size, int capacity, double r) {
        this.n = n;
        this.price = cost;
        this.size = size;
        this.capacity = capacity;
        this.r = r;
        this.mode = true;
    }

    /**
     * Pseudo-polynomial time algorithm for Maximum Knapsack
     * @return PPTAS solution
     */
    public boolean[] pptas() {
        // calculate max profit
        int P = 0;
        for (int i = 0; i < n; i++){
            if(this.price[i] >= P)
                P = this.price[i];
        }
        int nP = n * P;
        //initialize profit array
        int[][] A = new int[2][nP + 1];
        //initialize subset array
        boolean[][][] S = new boolean[2][nP+1][n];
        //empty subsets
        boolean[] emptySets = new boolean[n];
        for(int i = 0; i < n; i++) emptySets[i] = false;

        A[0][0] = 0;
        S[0][0] = emptySets.clone();
        for (int j = 1; j <= nP; j++){
            if(j == price[0]){
                A[0][j] = this.size[0];
                S[0][j] = emptySets.clone();
                S[0][j][0] = true;
            } else{
                A[0][j] = 999999999;
                S[0][j] = null;
            }
        }
        A[1][0] = 0;
        S[1][0] = emptySets.clone();

        //recursively run A[1] and S[1] for subsequent
        for(int i = 1; i < n; i++) {
            for (int j = 1; j <= nP; j++) {
                if( price[i] <= j) {
                    if(size[i] + A[0][j - price[i]] < A[0][j]){
                        A[1][j] = size[i] + A[0][j - price[i]];
                        S[1][j] = S[0][j - price[i]].clone();
                        S[1][j][i] = true;
                    } else {
                        A[1][j] = A[0][j];
                        if(S[0][j] == null) S[1][j] = null;
                        else S[1][j] = S[0][j].clone();
                    }
                } else {
                    A[1][j] = A[0][j];
                    if(S[0][j] == null) S[1][j] = null;
                    else S[1][j] = S[0][j].clone();
                }
            }

            //save A[1] and S[1]
            A[0] = A[1].clone();
            S[0] = S[1].clone();
        }
        boolean[] result = null;
        for(int j = 1; j <= nP; j++) {
            if (A[0][j] <= this.capacity) {
                result = S[0][j];
            }
        }
        return result;
    }

    /**
     * Full-polynomial time algorithm for Maximum Knapsack
     * @return FPTAS Solution
     */
    public boolean[] fptas() {
        // calculate max profit
        int P = 0;
        for (int i = 0; i < n; i++) {
            if (price[i] >= P)
                P = price[i];
        }
        //calculate scaling factor f, f differs from t in our lecture
        double f = (r * P) / n;
        if (f < 1)
            throw new RuntimeException("value of r not possible for FPTAS!");

        int[] price_ = new int[n];
        for(int i = 0; i < n; i++)
            price_[i] = (int) (price[i] / f);
        MaxKnapsack recurRes = new MaxKnapsack(n, price_, size, capacity);
        return recurRes.pptas();
    }

    /**
     * this method output result and total price for a maximal Knapsack
     * @param res
     */
    public void printResult(boolean[] res) {
        int totalPrice = 0;

        System.out.print("Subset: { ");

        for(int i = 0; i < res.length; i++) {
            if (res[i]) {
                totalPrice += price[i];
                System.out.print(i + 1 + " ");
            }
        }
        System.out.println(new StringBuilder("}" + "\n" + "total price for knapsack is " + totalPrice));
    }

    public int getN() {
        return n;
    }

    public int[] getPrice() {
        return price;
    }

    public int[] getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getR() {
        return r;
    }
}

