import java.util.Random;

public class Sestavovatel implements Runnable {
    Random rn = new Random();
    private int pocetVizualnichKontrol = 0;
    private int pocetNeproslychVizualnichKontrol = 0;
    private int pocetFunkcnichKontrol = 0;
    private int pocetNeproslychFunkcnichKontrol = 0;
    private int limitPredPauzou = 0;

    private String name;
    private SkladSoucastek skladSoucastek;

    public Sestavovatel(String name, SkladSoucastek skladSoucastek) {
        this.name = name;
        this.skladSoucastek = skladSoucastek;
    }

    public synchronized void vyrobHrackuku() {
        Log.log("Probiha kontrola kvality hracky");
        if (rn.nextInt(1,11) == 1){
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pocetNeproslychVizualnichKontrol++;
            skladSoucastek.setPanenkyNaOpravu(skladSoucastek.getPanenkyNaOpravu()+1);
            Log.log("Hracka neprosla vitualni kontrolou. (skrabanec)\n Byla poslana na opravu");
        }else if(rn.nextInt(1,6) == 1){
            Log.log("Hracka prosla vizualni kontrolou.");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pocetVizualnichKontrol++;
            pocetNeproslychFunkcnichKontrol++;
            skladSoucastek.setPanenkyNaOpravu(skladSoucastek.getPanenkyNaOpravu()+1);
            Log.log("Hracka neprosla kontrolou funkcnosti. (uvolnena koncetina)\n Byla poslana na opravu");
        }else {
            pocetFunkcnichKontrol++;
            pocetVizualnichKontrol++;
            limitPredPauzou++;
            Log.log("Hracka prosla vizualni kontrolou.");
            Log.log("Hracka prosla kontrolou funkcnosti.");
            skladSoucastek.setHracky(skladSoucastek.getHracky() + 1);
            Log.log("Hracka sestavena *** pocet hracek " + skladSoucastek.getHracky()+" *** pocet [proslych, nepovedenych] viz. kontrol ["+pocetVizualnichKontrol+", "+pocetNeproslychVizualnichKontrol+"] *** pocet [proslych, nepovedenych] fun. kontrol [" +pocetFunkcnichKontrol+", "+pocetNeproslychFunkcnichKontrol+"] ***");
        }
    }

    @Override
    public void run() {
        while (true) {
            if (skladSoucastek.getHracky() >= skladSoucastek.getPozadavekVyroby()) {
                Log.log("Pozadavek vyroby hracek splnen.");
                break;
            }
            if (limitPredPauzou == 5){
                Log.log("Sestavitel "+name+" si dava pauzu.");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                limitPredPauzou = 0;
            }else{
                if (skladSoucastek.checkHlavy(1) && skladSoucastek.checkNohy(1) && skladSoucastek.checkRuce(1) && skladSoucastek.checkTela(1)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    vyrobHrackuku();
                    Log.log("Hracka byla vyrobena :)");
                } else {
                    Log.log("Neni dostatek soucastek :(");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}