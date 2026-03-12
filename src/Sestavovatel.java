public class Sestavovatel implements Runnable {
    private String name;
    private SkladSoucastek skladSoucastek;

    public Sestavovatel(String name, SkladSoucastek skladSoucastek) {
        this.name = name;
        this.skladSoucastek = skladSoucastek;
    }

    public synchronized void vyrobHrackuku() {
        skladSoucastek.setHracky(skladSoucastek.getHracky() + 1);
        Log.log("Hracka sestavena pocet hracek: " + skladSoucastek.getHracky());
    }

    @Override
    public void run() {
        while (true) {
            if (skladSoucastek.getHracky() >= skladSoucastek.getPozadavekVyroby()) {
                Log.log("Pozadavek vyroby hracek splnen.");
                break;
            }

            if (skladSoucastek.checkHlavy(1) && skladSoucastek.checkNohy(1)
                    && skladSoucastek.checkRuce(1) && skladSoucastek.checkTela(1)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                vyrobHrackuku();
                Log.log("Hracka byla vyrobena :)");
            } else {
                // Wait longer before rechecking if parts aren't available
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}