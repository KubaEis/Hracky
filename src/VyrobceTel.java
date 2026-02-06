public class VyrobceTel implements Runnable {
    private String name;
    private int naklady = 50;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;
    public VyrobceTel(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }
    private synchronized void vyrobSoucastku(){
        sSoucastek.getTela().add(1);
    }
    public void run(){
        while (true) {
            if (sSurovin.checkPlasty(naklady)) {
                vyrobSoucastku();
                IO.println("Hlava vyrobena pocet tel:"+sSoucastek.getTela().size()+" pocet plastu:"+sSurovin.getPlast());
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
