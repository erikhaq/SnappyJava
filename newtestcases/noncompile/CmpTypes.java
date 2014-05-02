// EXT:CEQ

class CmpTypes {
  public static void main(String[] stringis) {
    TorbjornGranlund t;
    JoshuaBloch j;
    t = new TorbjornGranlund();
    j = new JoshuaBloch();
    if(t == j) { //This should produce compile error
      System.out.println(1338);
    } else {
      System.out.println(1337);
    }
  }
}

class TorbjornGranlund {

}

class JoshuaBloch {

}
