import java.util.Random;

public class OpravarHracek implements Runnable {
    Random rn = new Random();

    private String name;
    private SkladSoucastek skladSoucastek;

    public OpravarHracek(String name, SkladSoucastek skladSoucastek) {
        this.name = name;
        this.skladSoucastek = skladSoucastek;
    }

    public synchronized void opravHrackuku() {
        if (skladSoucastek.checkPanenkyNaOpravu(1)){
            Log.log("Oprava hracky probiha.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Log.log("Oprava hracky probehla. *** pocet hracek " + skladSoucastek.getHracky());
        }else{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Log.log("Nejsou zadne hracky na opravu.");
        }
    }

    @Override
    public void run() {
        while (true) {
            if (skladSoucastek.getHracky() >= skladSoucastek.getPozadavekVyroby()) {
                Log.log("Pozadavek vyroby hracek splnen.");
                break;
            }else{
                opravHrackuku();
            }
        }
    }
}
