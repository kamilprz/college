
class Ackermann{

    public final static int ITERATIONS = 10000;

    public static void main(String[] args)
    {
        final long startTime = System.nanoTime();
        for(int i = 0; i < ITERATIONS; i++){
            ackermann(3, 6);
        }
        final long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double average = ((double)elapsedTime / ITERATIONS) / 1_000_000_000.0;
        System.out.format("Average run time : %f Seconds", average);
    }


    public static int ackermann(int x, int y)
    {
        if (x == 0)
        {
            return y + 1;
        }
        else if (y == 0)
        {
            return ackermann(x - 1, 1);
        }
        else
        {
            return ackermann(x - 1, ackermann(x, y - 1));
        }
    }
}