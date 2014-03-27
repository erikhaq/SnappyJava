class Factorial2{
    public static void main(String[] a){
      int x;
      int[] array;
      Fac b;
      x = array.length;
      b = new Fac();
      array = b.DoWork(x,3, 3);
      x = 1 + 2 * 4;
      //x = b.hej();
		System.out.println(new Fac().ComputeFac(10));
    }
}

class Fac {
  int a;
  Fac b;
  public int bajs() {
    return 3;
  }
  public int[] DoWork(int h, int hej, int abba) {
    int[] b;
    int a;
    boolean p;
    return b;

  }
    public int ComputeFac(int num){
	int num_aux ;
	if (num < 1)
	    num_aux = 1 ;
	else 
	    num_aux = num * (this.ComputeFac(num-1)) ;
	return num_aux ;
    }

}
