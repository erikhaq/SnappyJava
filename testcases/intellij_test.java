
class FieldInheritence{
  public static void main(String[] args) {
    System.out.println(new Field().test());
  }

}

class Field3 extends Field2 {

}

class Field2 extends Field {
  public int getI() {
    return i;
  }

}

class Field {

  int i;
  Field f;
  int[] a;

  public int test() {
    Field2 f;
    int r;
    f = new Field2();

    r =f.setI(101);
    r = f.getI();
    return 5;
  }

  public int setI(int v) {
    i = v;
    return 0;
  }

}


class TestSuper {
  TestSub testSub;
  TestSuper testSuper;
  public int testmethod() {
    testSub = new TestSub();
    testSuper = new TestSubSub();
    return 1;
  }

}

class TestSub extends TestSuper {
  TestSuper t;
  public int testmethod() {
    t = new TestSub();
    return 0;
  }
}

class TestSubSub extends TestSub {

}

//class TestCircle extends TestCircle2 {
//
//}

//class TestCircle2 extends TestCircle {
//
//}



/*class Test {
  public static void main(String[] args) {
    CEQ c;
    //boolean b;
    c = new Blap();
    //b = c == c;
    /*if(1 == 1) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
  }
}

class CEQ {
  /*public int test1() {
    int x;
    int y;
    x = 2;
    y = 1;
    //if(x == y) {
    //  return true;
    //} else {
    //  return false;
    //}
    return 0;
  }
}

class Blap extends CEQ {

}

/*class Factorial2{
    public static void main(String[] a){
      int main;
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
*/