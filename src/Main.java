import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random rn = new Random();
        int random = rn.nextInt(5,15);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kolik hracek ma byt vyrobeno?");
        int pozadavekVyroby = scanner.nextInt();
        scanner.close();

        SkladSurovin s = new SkladSurovin();
        SkladSoucastek sklad = new SkladSoucastek(pozadavekVyroby);
        VyrobceHlav vyrobceH = new VyrobceHlav("franta", s, sklad);
        VyrobceRukou vyrobceR = new VyrobceRukou("martin", s, sklad);
        VyrobceTel vyrobceT = new VyrobceTel("stepan", s, sklad);
        VyrobceNohou vyrobceN = new VyrobceNohou("ondra", s, sklad);
        Sestavovatel ses1 = new Sestavovatel("misa", sklad);
        Sestavovatel ses2 = new Sestavovatel("misanek", sklad);
        OpravarHracek opr = new OpravarHracek("martin", sklad);

        Thread vH = new Thread(vyrobceH);
        Thread vR = new Thread(vyrobceR);
        Thread vT = new Thread(vyrobceT);
        Thread vN = new Thread(vyrobceN);
        Thread S1 = new Thread(ses1);
        Thread S2 = new Thread(ses2);
        Thread Op = new Thread(opr);

        vH.start();
        vR.start();
        vT.start();
        vN.start();
        S1.start();
        S2.start();
        Op.start();

        vH.join();
        vR.join();
        vT.join();
        vN.join();
        S1.join();
        S2.join();
        Op.join();

    }
}