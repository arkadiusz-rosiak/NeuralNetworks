package Cw9_Factorization;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

public class Factorization {


    long NWD(long a, long b) {
        if(a % b == 0){
            return b;
        }
        else{
            return NWD(b, a % b);
        }
    }


    long solveDL(long a, long n) {
        long r = 1;

        while (pow(a, r) % n != 1 && r < 10) {
            r++;
        }
        if (r == 10) {
            return -1;
        } else {
            return r;
        }
    }


     void factorize(long n){

        long a = ThreadLocalRandom.current().nextLong(n);


         long nwd = NWD(a, n);

         if(nwd > 1){
             System.out.println(n + " => " + nwd);
         }
         else{

             long r = solveDL(a, n);
             if (r == -1) {
                 factorize(n);
             } else if (r % 2 == 0) {

                 double pow = Math.pow(a, r / 2);

                 if (NWD(n, (long) pow + 1) > 1) {
                     System.out.println(n + " => " + (pow + 1));
                 } else if (NWD(n, (long) pow - 1) > 1) {
                     System.out.println(n + " => " + (pow - 1));
                 } else {
                     factorize(n);
                 }
             } else {
                 factorize(n);
             }
         }
    }


    public static void main(String[] args) {
        Factorization app = new Factorization();
        app.factorize(12);
        app.factorize(91);
        app.factorize(143);
        app.factorize(1737); // Euler
        app.factorize(1859); // Hipoteza Riemanna https://www.youtube.com/watch?v=usE0TwqPDME
    }


}
