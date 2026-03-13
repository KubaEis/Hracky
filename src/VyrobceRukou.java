public class VyrobceRukou implements Runnable {
    private String name;
    private int naklady = 20;
    private int limitPredPauzou = 0;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;

    public VyrobceRukou(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }

    private synchronized void vyrobSoucastku() {
        limitPredPauzou++;
        sSoucastek.setRuce(sSoucastek.getRuce() + 1);
    }

    public void run() {
        while (true) {
            if (sSoucastek.getHracky() >= sSoucastek.getPozadavekVyroby()) {
                Log.log("Produkcni cil dosahnut. Vyrobce rukou " + name + " konci.");
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
                if (sSoucastek.getRuce() < 8) {
                    if (sSurovin.checkPlasty(naklady)) {
                        vyrobSoucastku();
                        Log.log("Ruce vyrobeny pocet rukou:" + sSoucastek.getRuce() + " pocet plastu:" + sSurovin.getPlast());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Log.log("Neni dostatek surovin :(");
                        Log.log("pocet rukou: " + sSoucastek.getRuce());
                        break;
                    }
                } else {
                    Log.log("Sklad rukou je plny cekam. Pocet rukou:" + sSoucastek.getRuce());
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