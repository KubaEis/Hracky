public class VyrobceRukou implements Runnable {
    private String name;
    private int naklady = 20;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;

    public VyrobceRukou(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }

    private synchronized void vyrobSoucastku() {
        sSoucastek.setRuce(sSoucastek.getRuce() + 1);
    }

    public void run() {
        while (true) {
            // Check if production target reached
            if (sSoucastek.getHracky() >= sSoucastek.getPozadavekVyroby()) {
                Log.log("Produkcni cil dosaznut. Vyrobce rukou " + name + " konci.");
                break;
            }

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
        }
    }
}