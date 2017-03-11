import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin Valjaots on 11.03.2017.
 */
public class ElGamal {
    private int p;
    private int q;
    private int g;
    private int zStarJark;
    private int x;
    private List<Integer> publicKey = new ArrayList<>();
    private int secretKey;
    private int h;
    private int m;
    private List<Integer> groupG = new ArrayList<>();
    private List<BigInteger> c = new ArrayList<>();
    private BigInteger m_decrypted;

    public ElGamal(){
        ElGamal_NumberGen ng = new ElGamal_NumberGen();
        this.p = ng.p;
        this.q = ng.q;
        this.g = ng.g;
        this.zStarJark = ng.zStarJark;
        this.x = ng.x;
        this.groupG = ng.groupG;

        this.h = (int)Math.pow(g, x) % p;
        this.publicKey.add(this.p);
        this.publicKey.add(this.q);
        this.publicKey.add(this.g);
        this.publicKey.add(this.h);
        this.secretKey = this.x;

        ElGamal_Crypt eg_c = new ElGamal_Crypt();
        this.m = eg_c.chooseM(this.groupG);
        this.c = eg_c.encrypt(this.m, this.p, this.q, BigInteger.valueOf(this.g), BigInteger.valueOf(this.h));
        this.m_decrypted = eg_c.decrypt(this.secretKey, this.q, this.p, this.c.get(0), this.c.get(1));

    }

    public static void main(String [] args){
        ElGamal eg = new ElGamal();
        System.out.print("Algarv q: " + eg.q + "\n");
        System.out.print("Algarv p (ja grupi Z* moodul): " + eg.p + "\n");
        System.out.print("Z* grupi jark: " + eg.zStarJark + "\n");
        System.out.print("Arv g: " + eg.g + "\n");
        System.out.print("Suvaline arv x: " + eg.x + "\n");
        System.out.print("Arv h: " + eg.h + "\n");
        System.out.print("Arv (sonum) M: " + eg.m + "\n");

        System.out.print("\nGrupp G:");
        for (int i = 0; i < eg.groupG.size(); i++){
            System.out.print("\n" + eg.groupG.get(i));
        }

        System.out.print("\n\nKryptogramm C: ");
        for (int i = 0; i < eg.c.size(); i++){
            System.out.print("\n" + eg.c.get(i));
        }

        System.out.print("\n\nDekrypteeritud M: " + eg.m_decrypted);

        if (eg.m_decrypted.compareTo(BigInteger.valueOf(eg.m)) == 0){
            System.out.print("\nYA DID IT, KID!\n");
        }
    }
}
