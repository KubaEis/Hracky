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
        sSoucastek.setTela(sSoucastek.getTela()+1);
    }
    public void run(){
        while (true) {
            if (sSurovin.checkPlasty(naklady)) {
                vyrobSoucastku();
                Log.log("Telo vyrobeno pocet tel:"+sSoucastek.getTela()+" pocet plastu:"+sSurovin.getPlast());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                Log.log("Neni dostatek surovin :(");
                Log.log("pocet tel: "+sSoucastek.getTela());
                break;
            }
        }
    }
}
