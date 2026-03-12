public class VyrobceHlav implements Runnable {
    private String name;
    private int nakladyP = 10;
    private int nakladyV = 2;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;

    public VyrobceHlav(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }

    private synchronized void vyrobSoucastku() {
        sSoucastek.setHlavy(sSoucastek.getHlavy() + 1);
    }

    public void run() {
        while (true) {
            // Check if production target reached
            if (sSoucastek.getHracky() >= sSoucastek.getPozadavekVyroby()) {
                Log.log("Produkcni cil dosaznut. Vyrobce hlav " + name + " konci.");
                break;
            }

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
        }
    }
}