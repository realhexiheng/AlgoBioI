package algorithms;

/**
 * @Author Xiheng He
 * @ClassName algorithms.Sort
 * @Description TODO
 * @Date 2021/05/2021/5/22/2:20
 * @Version 1.0
 */
public interface Sort {

    public int[] sort(int[] array);

    public long getTotalOfComparison();

    public long getElapsedTime();
}
