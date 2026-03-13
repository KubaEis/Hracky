public class VyrobceHlav implements Runnable {
    private String name;
    private int nakladyP = 10;
    private int nakladyV = 2;
    private int limitPredPauzou = 0;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;

    public VyrobceHlav(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }

    private synchronized void vyrobSoucastku() {
        limitPredPauzou++;
        sSoucastek.setHlavy(sSoucastek.getHlavy() + 1);
    }

    public void run() {
        while (true) {
            if (sSoucastek.getHracky() >= sSoucastek.getPozadavekVyroby()) {
                Log.log("Produkcni cil dosahnut. Vyrobce hlav " + name + " konci.");
                break;
            }
            if (limitPredPauzou == 5){
                Log.log("Vyrobce hlav "+name+" si dava pauzu.");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                limitPredPauzou = 0;
            }else {
                if (sSoucastek.getHlavy() < 10) {
                    if (sSurovin.checkPlasty(nakladyP) && sSurovin.checkVlasu(nakladyV)) {
                        vyrobSoucastku();
                        Log.log("Hlava vyrobena pocet hlav:" + sSoucastek.getHlavy() + " pocet plastu:" + sSurovin.getPlast() + " pocet vlasu:" + sSurovin.getVlasy());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Log.log("Neni dostatek surovin :(");
                        Log.log("pocet hlav: " + sSoucastek.getHlavy());
                        break;
                    }
                } else {
                    Log.log("Sklad hlav je plny cekam. Pocet hlav:" + sSoucastek.getHlavy());
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