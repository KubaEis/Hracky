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
    private synchronized void vyrobSoucastku(){
        sSoucastek.getRuce().add(1);
    }
    public void run(){
        while (true) {
            if (sSurovin.checkPlasty(naklady)) {
                vyrobSoucastku();
                IO.println("Hlava vyrobena pocet rukou:"+sSoucastek.getRuce().size()+" pocet plastu:"+sSurovin.getPlast());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                IO.println("Neni dostatek soucastek :(");
            }
        }
    }
}
