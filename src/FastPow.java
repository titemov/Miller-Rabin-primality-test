import java.util.ArrayList;

public class FastPow {
    private long randNum;
    private long power;
    private long mod;
    public FastPow(long randNum, long power, long mod){
        this.randNum=randNum;
        this.power =power;
        this.mod=mod;
    }

    private ArrayList<Integer> factorizeNum(){
        long power = this.power;
        ArrayList<Integer> powers = new ArrayList<>();

        int k=-1;
        while (power>0){
            k+=1;
            //System.out.println("current pow: " +pow);
            long temp1 = (long) Math.pow(2,k);
            long temp2 = (long) Math.pow(2,k+1);
            if(temp2>power){
                power=power-temp1;
                powers.add(k);
                k=-1;
            }
        }
        return powers;
    }

    private long getRemainingFromMultiplication(long rem1, long rem2){
        //алгоритм сложения-удвоения
        long mod = this.mod;
        rem1+=this.mod;
        rem2+=this.mod;//in case if rem1 and rem2 both equals to zero
        long a = Math.min(rem1,rem2);
        long b = Math.max(rem1,rem2);
        long c = 0;
        long[] currentStep = new long[]{a,b,c};
        long[] nextStep = new long[3];
        //System.out.println(Arrays.toString(currentStep));

        while(currentStep[0]!=1) {
            nextStep[0] = currentStep[0] / 2;
            nextStep[1] = (currentStep[1] * 2) % mod;
            if (currentStep[0] % 2 == 1) {
                nextStep[2] = (currentStep[1]+currentStep[2])%mod;
            } else {
                nextStep[2] = currentStep[2];
            }

            for(int i=0;i<currentStep.length;i++){
                currentStep[i]=nextStep[i];
                nextStep[i]=0; //any need?
            }
            //System.out.println(Arrays.toString(currentStep));
        }
        return (currentStep[1]+currentStep[2])%mod;
    }

    public long run(){
        ArrayList<Integer> powers = factorizeNum();

        ArrayList<Long> rems = new ArrayList<>();
        for(int i=0;i<powers.size();i++){
            rems.add(this.randNum % this.mod);
        }

        while(powers.get(0)!=0){
            long temp = rems.get(0);
            long rem = getRemainingFromMultiplication(temp,temp);

            for(int n=0;n<powers.size();n++)
            {
                if(powers.get(n)!=0){
                    rems.set(n,rem);
                    powers.set(n,powers.get(n)-1);
                }
            }
        }

        for(int i=1;i<rems.size();i++){
            rems.set(0,getRemainingFromMultiplication(rems.get(0),rems.get(i)));
        }

        return rems.get(0);
    }
}
