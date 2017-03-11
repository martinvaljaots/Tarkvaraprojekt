import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Martin Valjaots on 11.03.2017.
 */
public class ElGamal_NumberGen {
    private List<Integer> safePrimes = new ArrayList<>();
    protected int p;
    protected int q;
    protected int g;
    protected int zStarJark;
    protected int x;
    protected List<Integer> groupG = new ArrayList<>();

    public ElGamal_NumberGen(){
        primeGenerator();
        this.q = getRandomSafePrime(safePrimes);
        this.p = 2 * this.q + 1;
        this.zStarJark = 2 * this.q;
        this.g = calculateG();
        this.x = generateRandomX(this.q);
        generateGroupG(this.g, this.q, this.p);
        //System.out.print("numbergen: q " + this.q + " p " + this.p + " Z* jark " + this.zStarJark + " g " + this.g + " x " + this.x + "\n");

    }
    //used: https://examples.javacodegeeks.com/java-basics/for-loop/generate-prime-numbers-with-for-loop/
    private void primeGenerator(){
        for (int i = 1; i < 15; i++){
            boolean isSafePrime = true;

            for (int j=2; j < i; j++){
                // if possible q isn't a prime or if 2*q + 1 isn't a prime
                if (i % j == 0 || (i * 2 + 1 ) % j == 0){
                    isSafePrime = false;
                    break;
                }
            }
            if(isSafePrime){
                safePrimes.add(i);
            }
        }
    }

    //http://stackoverflow.com/questions/19346965/get-random-number-from-arraylist
    private int getRandomSafePrime(List<Integer> primeNumbers){
        Random rand = new Random();
        int randomPrime = primeNumbers.get(rand.nextInt(primeNumbers.size()));
        return randomPrime;
    }

    private int calculateG(){
        int result;
        for (int i = 1; i <= this.p; i++){
            for (int j = 1; j <= this.q; j++) {
                result = (int)Math.pow(i, j) % this.p;
                if (result == 1 && j == this.q) {
                    return i;
                }else if (result == 1 && j != this.q){
                    break;
                }
            }
        }
        return 0;
    }

    private int generateRandomX(int q){
        Random rand = new Random();
        int randomX = rand.nextInt((q  - 0) + 1);
        /* x is a number between 0 and q-1, since x belongs in Zq
           "nextInt is normally exclusive of the top value, so add 1 to make it inclusive"
           so since I need to do  through q-1, I just write in 1 through q
           format: rand.nextInt(max - min) + min
           http://stackoverflow.com/questions/363681/how-to-generate-random-integers-within-a-specific-range-in-java
        */
        return randomX;
    }

    private void generateGroupG (int g, int q, int p){
        for (int i = 0; i < q; i++){
            this.groupG.add((int)Math.pow(g, i) % p);
            //System.out.print("\nGrupp G: " + this.groupG.get(i-1) + "\n");
        }
    }
}
