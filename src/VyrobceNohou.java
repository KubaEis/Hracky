public class VyrobceNohou implements Runnable {
    private String name;
    private int naklady = 30;
    private int limitPredPauzou = 0;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;

    public VyrobceNohou(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }

    private synchronized void vyrobSoucastku() {
        limitPredPauzou++;
        sSurovin.setPlast(sSurovin.getPlast() - naklady);
        sSoucastek.setNohy(sSoucastek.getNohy() + 1);
    }

    public void run() {
        while (true) {
            // Check if production target reached
            if (sSoucastek.getHracky() >= sSoucastek.getPozadavekVyroby()) {
                Log.log("Produkcni cil dosaznut. Vyrobce nohou " + name + " konci.");
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
                if (sSoucastek.getNohy() < 6) {
                    if (sSurovin.getPlast() > naklady) {
                        vyrobSoucastku();
                        Log.log("Nohy vyrobeny pocet nohou:" + sSoucastek.getNohy() + " pocet plastu:" + sSurovin.getPlast());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Log.log("Neni dostatek surovin :(");
                        Log.log("pocet nohou: " + sSoucastek.getNohy());
                        break;
                    }
                } else {
                    Log.log("Sklad nohou je plny cekam. Pocet nohou:" + sSoucastek.getNohy());
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