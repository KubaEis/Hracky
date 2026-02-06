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
    private synchronized void vyrobSoucastku(){
        sSoucastek.getHlavy().add(1);
    }
    public void run() {
        while (true) {
            if (sSurovin.checkPlasty(nakladyP) && sSurovin.checkVlasu(nakladyV)) {
                vyrobSoucastku();
                IO.println("Hlava vyrobena pocet hlav:"+sSoucastek.getHlavy().size()+" pocet plastu:"+sSurovin.getPlast()+" pocet vlasu:"+sSurovin.getVlasy());
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
