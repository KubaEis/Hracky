import java.util.ArrayList;
import java.util.List;

public class SkladSoucastek {
    private List<Integer> hracky =  new ArrayList<>();
    private List<Integer> tela =  new ArrayList<>();
    private List<Integer> ruce =  new ArrayList<>();
    private List<Integer> nohy =  new ArrayList<>();
    private List<Integer> hlavy =  new ArrayList<>();

    public boolean checkTela(int pocet){
        if (tela.size() >= pocet){
            tela.remove(pocet);
            return true;
        }else{
            return false;
        }
    }
    public boolean checkHlavy(int pocet){
        if (hlavy.size() >= pocet){
            hlavy.remove(pocet);
            return true;
        }else{
            return false;
        }
    }
    public boolean checkNohy(int pocet){
        if (nohy.size() >= pocet){
            nohy.remove(pocet);
            return true;
        }else{
            return false;
        }
    }
    public boolean checkRuce(int pocet){
        if (ruce.size() >= pocet){
            ruce.remove(pocet);
            return true;
        }else{
            return false;
        }
    }

    public List<Integer> getTela() {
        return tela;
    }

    public void setTela(List<Integer> tela) {
        this.tela = tela;
    }

    public List<Integer> getRuce() {
        return ruce;
    }

    public void setRuce(List<Integer> ruce) {
        this.ruce = ruce;
    }

    public List<Integer> getNohy() {
        return nohy;
    }

    public void setNohy(List<Integer> nohy) {
        this.nohy = nohy;
    }

    public List<Integer> getHlavy() {
        return hlavy;
    }

    public void setHlavy(List<Integer> hlavy) {
        this.hlavy = hlavy;
    }

    public List<Integer> getHracky() {
        return hracky;
    }

    public void setHracky(List<Integer> hracky) {
        this.hracky = hracky;
    }
}
