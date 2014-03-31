/* Check associativity and precedence of operataors
 *
 */
class Main
{
    public static void main(String[] s) 
    {
        A a;
        boolean b;
        a = new A();
        System.out.println(1337);
        b = a.f(30, true);

	   System.out.println(b);

    }
}


class A { 
    int a;
    int[] v;
    public int[] getv() { return v;}
    public boolean f(int x, boolean v)
    {
	if (v && a < this.getv()[a] && ! (a < 0)) x=1;
	else x=0-1;	
	return !!!(!(a+a*a+x < 123*3-1) && v && !v);
    }

}


