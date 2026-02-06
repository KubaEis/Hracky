import java.util.ArrayList;

public class SkladSurovin {
    private int plast = 10000;
    private int vlasy = 1000;
    SkladSurovin() {}
    public boolean checkPlasty(int naklady){
        if  (naklady <= plast){
            plast -= naklady;
            return true;
        }else{
            return false;
        }
    }
    public boolean checkVlasu(int naklady){
        if  (naklady <= vlasy){
            vlasy -= naklady;
            return true;
        }else{
            return false;
        }
    }
    public synchronized int getPlast() {
        return plast;
    }

    public void setPlast(int plast) {
        this.plast = plast;
    }

    public synchronized int getVlasy() {
        return vlasy;
    }

    public void setVlasy(int vlasy) {
        this.vlasy = vlasy;
    }
}
