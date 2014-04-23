class main{
    public static void main(String[] a){
      LinkedList list;
      Node n;
      list = new LinkedList();
      n = new Node();
      list.init();
      n.setValue(10);
      list.addNode(n);
    }
}
class Node {
  int value;
  Node next;
  Node prev;
  boolean nextExists;
  boolean prevExists;

  public Node newLinkedList() {
    nextExists = false;
    prevExists = false;
    return this;
  }

  public int setValue(int v) {
    this.value = v;
    return this.value;
  }

  public Node setNext(Node n) {
      this.next = n;
      return this.next;
  }
  public Node setPrev(Node p) {
      this.prev = p;
      return this.prev;
  }
  public Node getNext() {
      return this.next;
  }
  public Node getPrev() {
      return this.prev;
  }
  

}
class LinkedList {
  Node first;
  Node last;
  boolean isSet;
  public boolean init() {
    isSet = false;
    return true;
  }

  public boolean addNode(Node n) {
    if(!isSet){
      this.first = this.last = n;
      isSet = true;
    } else {
      Node prev = last;
      prev.setNext(n);
      last = n;
      last.setPrev(prev);
    }
    return true;
  }
}

  
