import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Martin Valjaots on 11.03.2017.
 */
public class ElGamal_Crypt {

    public int chooseM(List<Integer> groupG){
        Random rand = new Random();
        int m = rand.nextInt(groupG.size() - 0) + 0;
        return groupG.get(m);
    }

    public List<BigInteger> encrypt(int m, int p, int q, BigInteger g, BigInteger h){
        Random rand = new Random();
        int r = rand.nextInt(q - 0)+ 1 + 0;
        System.out.print("\nRandom r: " + r + "\n");
        List<BigInteger> c = new ArrayList<>();

        //BigInteger u = BigInteger.valueOf((int)Math.pow(g, r) % p);
        BigInteger u = g.pow(r);
        u = u.remainder(BigInteger.valueOf(p));
        //BigInteger v = BigInteger.valueOf(((int)Math.pow(h, r) * m) % p);
        BigInteger v = BigInteger.valueOf(m).multiply(h.pow(r));
        v = v.remainder(BigInteger.valueOf(p));
        c.add(u);
        c.add(v);
        return c;
    }

    public BigInteger decrypt(int x, int q, int p, BigInteger u, BigInteger v){
        BigInteger m;
        m = (v.multiply(u.pow(q-x)));
        m = m.remainder((BigInteger.valueOf(p)));
        return m;
    }


}
