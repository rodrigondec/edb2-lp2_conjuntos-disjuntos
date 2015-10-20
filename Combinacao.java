package trabalho.de.edbii;
/*
/author: Sourabh Bhat (heySourabh@gmail.com)
/adaptada para esse projeto
*/

public class Combinacao
{
    
    public static int[][] getCombinations(int num, int outOf)
    {
        int possibilities = get_nCr(outOf, num); 
        int[][] combinations = new int[possibilities][num];
        int arrayPointer = 0;

        int[] counter = new int[num];

        for (int i = 0; i < num; i++)
        {
            counter[i] = i;
        }
        breakLoop: while (true)
        {
            // Initializing part
            for (int i = 1; i < num; i++)
            {
                if (counter[i] >= outOf - (num - 1 - i))
                    counter[i] = counter[i - 1] + 1;
            }

            // Testing part
            for (int i = 0; i < num; i++)
            {
                if (counter[i] < outOf)
                {
                    continue;
                } else
                {
                    break breakLoop;
                }
            }

            // Innermost part
            combinations[arrayPointer] = counter.clone();
            arrayPointer++;

            // Incrementing part
            counter[num - 1]++;
            for (int i = num - 1; i >= 1; i--)
            {
                if (counter[i] >= outOf - (num - 1 - i))
                    counter[i - 1]++;
            }
        }

        return combinations;
    }
    
    //calcula o número de combinações e retorn a quantidade de combinações
    public static int get_nCr(int n, int r)
    {
        if(r > n)
        {
            throw new ArithmeticException("r is greater then n");
        }
        long numerator = 1;
        long denominator = 1;
        for (long i = n; i >= r + 1; i--)
        {
            numerator *= i;
        }
        for (long i = 2; i <= n - r; i++)
        {
            denominator *= i;
        }
       
        return (int) (numerator / denominator);
    }
}
