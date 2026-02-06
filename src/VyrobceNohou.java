public class VyrobceNohou implements Runnable {
    private String name;
    private int naklady = 30;
    private SkladSurovin sSurovin;
    private SkladSoucastek sSoucastek;
    public VyrobceNohou(String name, SkladSurovin sSurovin, SkladSoucastek sSoucastek) {
        this.name = name;
        this.sSurovin = sSurovin;
        this.sSoucastek = sSoucastek;
    }
    private synchronized void vyrobSoucastku(){
        sSurovin.setPlast(sSurovin.getPlast() - naklady);
        sSoucastek.getNohy().add(1);
    }

    public void run(){
        while (true) {
            if (sSurovin.getPlast() > naklady) {
                vyrobSoucastku();
                IO.println("Hlava vyrobena pocet nohou:"+sSoucastek.getNohy().size()+" pocet plastu:"+sSurovin.getPlast());
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
