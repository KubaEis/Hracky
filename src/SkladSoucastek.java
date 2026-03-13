import java.util.ArrayList;
import java.util.List;

public class SkladSoucastek {
    private int hracky;
    private int tela;
    private int ruce;
    private int nohy;
    private int hlavy;
    private int pozadavekVyroby;
    private int panenkyNaOpravu;

    SkladSoucastek(int pozadavekVyroby) {
        this.pozadavekVyroby = pozadavekVyroby;
    }

    public synchronized boolean checkPanenkyNaOpravu(int pocet) {
        if (panenkyNaOpravu >= pocet) {
            panenkyNaOpravu--;
            hracky++;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean checkTela(int pocet) {
        if (tela >= pocet) {
            tela--;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean checkHlavy(int pocet) {
        if (hlavy >= pocet) {
            hlavy--;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean checkNohy(int pocet) {
        if (nohy >= pocet) {
            nohy--;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean checkRuce(int pocet) {
        if (ruce >= pocet) {
            ruce--;
            return true;
        } else {
            return false;
        }
    }

    public int getPozadavekVyroby() {
        return pozadavekVyroby;
    }

    public int getTela() {
        return tela;
    }

    public void setTela(int tela) {
        this.tela = tela;
    }

    public int getRuce() {
        return ruce;
    }

    public void setRuce(int ruce) {
        this.ruce = ruce;
    }

    public int getNohy() {
        return nohy;
    }

    public void setNohy(int nohy) {
        this.nohy = nohy;
    }

    public int getHlavy() {
        return hlavy;
    }

    public void setHlavy(int hlavy) {
        this.hlavy = hlavy;
    }

    public synchronized int getHracky() {
        return hracky;
    }

    public synchronized void setHracky(int hracky) {
        this.hracky = hracky;
    }

    public void setPozadavekVyroby(int pozadavekVyroby) {
        this.pozadavekVyroby = pozadavekVyroby;
    }

    public synchronized int getPanenkyNaOpravu() {
        return panenkyNaOpravu;
    }

    public synchronized void setPanenkyNaOpravu(int panenkyNaOpravu) {
        this.panenkyNaOpravu = panenkyNaOpravu;
    }
}