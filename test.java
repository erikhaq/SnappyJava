class Factorial2{
    public static void main(String[] a){
      int x;
      int x;
		System.out.println(new Fac().ComputeFac(10));
    }
}
class Fac {
  int a;
  boolean a;

    public int ComputeFac(int num){
	int num_aux ;
	if (num < 1)
	    num_aux = 1 ;
	else 
	    num_aux = num * (this.ComputeFac(num-1)) ;
	return num_aux ;
    }

}
