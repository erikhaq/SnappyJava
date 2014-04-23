// EXT:ISC
// EXT:ICG
// EXT:CLE
// EXT:CGE
// EXT:CEQ
// EXT:CNE

class AncientApparition {
  public static void main(String[] spiritBreaker) {
    int res;
    boolean bootsoftravel;
    Spectre spec;
    NaturesProphet np;
    Slardar slar;
    Riki riki;
    LegionCommander lc;
    DefenseOfTheAncients dota;
    bootsoftravel = true;
    lc = new Spectre();
    res = lc.setXandY();
    System.out.println(lc.setXandY());
    spec = new Spectre();
    res = spec.setXandY();
    System.out.println(spec.getSomeStrangeNumber());
    slar = new NaturesProphet();
    res = slar.setXandY();
    System.out.println(slar.getX());
    np = new Spectre();
    res = np.setXandY();
    System.out.println(np.getXAndY());
    riki = new Riki();
    res = riki.setXandY();
    System.out.println(riki.setXandY() + riki.getX());
    dota = new DefenseOfTheAncients();
    if(dota.compareThingseses(spec,np)) {
      System.out.println(2147483647);
    } else {
      System.out.println(2147483647+1);
    }
  }

}

class DefenseOfTheAncients {
  public boolean compareThingseses(Spectre s, NaturesProphet n) {
    boolean returnval;
    int divinerapier;
    int sangeandyasha;
    sangeandyasha = s.getSomeStrangeNumber()*s.getXAndY()-s.getXAndY()*s.setXandY();
    divinerapier = n.getXAndY()*n.getX()*n.getX()*n.getXAndY();
    if(s == n && sangeandyasha == divinerapier && sangeandyasha != sangeandyasha && divinerapier >= sangeandyasha && sangeandyasha <= divinerapier) {
      returnval = true;
    } else {
      returnval = false;
    }
    return returnval;
  }
}

class NaturesProphet extends Slardar {
  public int getXAndY() {
    return this.getX() + this.getY();
  }
}

class Spectre extends NaturesProphet {
  public int getSomeStrangeNumber() {
    return this.getX()*this.getY()+this.getXAndY()*this.getXAndY()+this.getX()*this.getY();
  }
}

class Slardar extends Riki {
  public int getY() {
    return y;
  }
}

class Riki extends LegionCommander {
  public int getX() {
    return x;
  }
}

class LegionCommander {
  int x;
  int y;

  public int setXandY() {
    x = 17;
    y = 41;
    return 31;
  }

}
