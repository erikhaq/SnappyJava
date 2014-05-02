class ultracompiledeluxe {
  public static void main(String[] args) {
    Ultra p;
    Compile j;
    j = new Compile();
    p = j.newUltra();
    System.out.println(p.getInt());
  }
}

class Ultra {
  public int getInt() {
    return 9;
  }
}
class Compile {
  public Ultra newUltra() {
    Ultra pvar;
    return pvar;
  }

}
