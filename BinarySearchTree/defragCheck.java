public class defragCheck{
	public static void main(String[] args){
		A2DynamicMem obj=new A2DynamicMem(100,2);
		Dictionary del=obj.freeBlk.getFirst();
		/*Dictionary del1=new BSTree(0,20,20);
		Dictionary del2=new BSTree(20,30,30);
		Dictionary del3=new BSTree(50,10,10);
		Dictionary del4=new BSTree(70,5,5);*/
		obj.freeBlk.Insert(0,20,20);
		obj.freeBlk.Insert(20,30,30);
		obj.freeBlk.Insert(50,10,10);
		obj.freeBlk.Insert(70,5,5);
		obj.freeBlk.Insert(75,25,25);
		obj.freeBlk.Delete(del);
		/*obj.freeBlk.Delete(del1);
		obj.freeBlk.Delete(del2);
		obj.freeBlk.Delete(del3);
		obj.freeBlk.Delete(del4);*/
		obj.Defragment();
		Dictionary curr=obj.freeBlk.getFirst();
		while(curr!=null){
			System.out.println(curr.address+" "+curr.size);
			curr=curr.getNext();
		}
	}
}
