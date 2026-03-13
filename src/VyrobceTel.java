public class VyrobceTel implements Runnable {
    private String name;
    private int naklady = 50;
    private int limitPredPauzou = 0;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;

    public VyrobceTel(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }

    private synchronized void vyrobSoucastku() {
        limitPredPauzou++;
        sSoucastek.setTela(sSoucastek.getTela() + 1);
    }

    public void run() {
        while (true) {
            // Check if production target reached
            if (sSoucastek.getHracky() >= sSoucastek.getPozadavekVyroby()) {
                Log.log("Produkcni cil dosahnut. Vyrobce tel " + name + " konci.");
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
                if (sSoucastek.getTela() < 5) {
                    if (sSurovin.checkPlasty(naklady)) {
                        vyrobSoucastku();
                        Log.log("Telo vyrobeno pocet tel:" + sSoucastek.getTela() + " pocet plastu:" + sSurovin.getPlast());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Log.log("Neni dostatek surovin :(");
                        Log.log("pocet tel: " + sSoucastek.getTela());
                        break;
                    }
                } else {
                    Log.log("Sklad tel je plny cekam. Pocet tel:" + sSoucastek.getTela());
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