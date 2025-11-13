import java.util.Random;

public class Backend {
    //s and d searcher from a given num
    //fast power algo
    private long num;
    private long repeats;

    public Backend(long num,long repeats){
        this.num=num;
        this.repeats=repeats;
    }

    public long getNum(){
        return this.num;
    }

    private int getS(){
        int s = 0;
        long num = this.num - 1;

        while(true){
            if(num%2==0){
                s+=1;
                num=num/2;
            }else{
                break;
            }
        }
        return s;
    }

    private long getD(int s){
        return (long) ((this.num-1)/Math.pow(2,s));
    }

    private long getRandomNum(){
        long r = new Random().nextLong(this.num);
        if(r==0) r=1;
        return r;
    }

    public void run(){
        int s = getS();
        long d = getD(s);
        System.out.println("\n"+(this.num-1) + " = 2^"+s+" * "+d+"\n");
        long k=0;
        for(int i=0;i<repeats;i++) {
            boolean isPrime=false;
            long randNum = getRandomNum();
            System.out.println("random number = " + randNum + ";   ");
            for (int n = 0; n < s; n++){
                long pow=(long) Math.pow(2,n) * d;
                System.out.println(randNum+"^"+pow+" mod "+ this.num);
                FastPow fastPow = new FastPow(randNum, pow, this.num);
                long remaining = fastPow.run();
                //System.out.println("Remaining = "+remaining);
                if(remaining==this.num-1 || remaining==1){
                    isPrime=true;
                    k+=1;
                    break;
                }
            }
            if(isPrime) {
                System.out.println("Given number " + this.num + " is prime. Primarity witness: " + randNum+"\n");
            }else{
                System.out.println("Given number " + this.num + " is not prime.\n");
            }

            try {
                Thread.sleep(100); //for better random
            }catch (Exception e){
                System.out.println("Error! "+e);
            }
        }
        //run all functions
        //print result here

        System.out.println("\nResult: "+k+" out of "+this.repeats+" iterations proved primarity of "+this.num);
        if(k==repeats){
            System.out.println("This number is prime.");
        }else{
            System.out.println("This number is not prime.");
        }
    }
}
