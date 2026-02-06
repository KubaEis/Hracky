void main() throws InterruptedException {
    SkladSurovin s = new SkladSurovin();
    SkladSoucastek sklad = new SkladSoucastek();
    VyrobceHlav vyrobceH = new VyrobceHlav("franta",s,sklad);
    VyrobceRukou vyrobceR = new VyrobceRukou("martin",s,sklad);
    VyrobceTel vyrobceT = new VyrobceTel("stepan je picus",s,sklad);
    VyrobceNohou vyrobceN = new VyrobceNohou("ondra",s,sklad);
    Sestavovatel ses1 = new Sestavovatel("misa",sklad);
    Sestavovatel ses2 = new Sestavovatel("misanek",sklad);
    Thread vH = new Thread(vyrobceH);
    Thread vR = new Thread(vyrobceR);
    Thread vT = new Thread(vyrobceT);
    Thread vN = new Thread(vyrobceN);
    Thread S1 = new Thread(ses1);
    Thread S2 = new Thread(ses2);
    vH.start();
    vR.start();
    vT.start();
    vN.start();
    S1.start();
    S2.start();
    vH.join();
    vR.join();
    vT.join();
    vN.join();
    S1.join();
    S2.join();
}
