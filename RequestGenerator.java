
import java.util.Random;

public class RequestGenerator {
    private final int UNIFORM_PROBABILITY = Experiment.RANGE;
    private static double[] zipfPmf = new double [Experiment.RANGE];
    private static double[] zipfPdf;
    private static double ZIPF_PMF_DENOMINATOR;
    private static int ALPHA = 1;


    public RequestGenerator(){
        changeDenominator();
        establishZipf();
    }

    public void changeDenominator(){
        for(int j = 1; j <= Experiment.RANGE; j++) {
            ZIPF_PMF_DENOMINATOR = ZIPF_PMF_DENOMINATOR + Math.pow(1.0/(double)j, ALPHA);
        }
    }

    public int getUniform(){
        Random rand = new Random();
        return rand.nextInt(UNIFORM_PROBABILITY)+1;
    }


    public void establishZipf(){
        for(int i = 0; i<zipfPmf.length; i++){
            double actualI = i+1.0;
            zipfPmf[i] = (1.0/(actualI)) / ZIPF_PMF_DENOMINATOR;
        }
        for(int i = 1; i<zipfPmf.length; i++){
            zipfPmf[i] = zipfPmf[i] + zipfPmf[i-1];
        }
        zipfPdf = zipfPmf;
    }

    public int getZipf() {
        Random rand = new Random();
        double uniform = rand.nextDouble();
        if (uniform< zipfPdf[0]){
            return 1;
        }
        for (int j = 0; j < zipfPdf.length; j++) {
            if (j-1>= 0 && uniform > zipfPdf[j - 1] && uniform <= zipfPdf[j]) {
                return (j + 1);
            }
        }
        return Experiment.RANGE;
    }

    public double[] getZipfPdf(){
        return zipfPdf;
    }
}