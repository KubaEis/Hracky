public class Sestavovatel implements Runnable {
    private String name;
    private SkladSoucastek skladSoucastek;
    public Sestavovatel(String name, SkladSoucastek skladSoucastek) {
        this.name = name;
        this.skladSoucastek = skladSoucastek;
    }
    public void vyrobHrackuku() {
        IO.println("Hracka sestavena pocet hracek: "+skladSoucastek.getHracky().size());
        skladSoucastek.getHracky().add(1);
    }
    @Override
    public void run() {
        while (true) {
            if (skladSoucastek.checkHlavy(1) && skladSoucastek.checkNohy(1) && skladSoucastek.checkRuce(1) && skladSoucastek.checkTela(1)) {
                vyrobHrackuku();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
