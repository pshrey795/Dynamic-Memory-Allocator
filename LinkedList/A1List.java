// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
     	A1List newNode=new A1List(address,size,key);
       	newNode.next=this.next;
      	newNode.prev=this;
       	this.next.prev=newNode;
       	this.next=newNode;
       	return newNode;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List curr=this.getFirst();
        if(curr!=null)
        {
        	while(curr.next!=null){
        		A1List curr1=curr.next;
        		if(curr.key==d.key){
        			if(curr.address==d.address && curr.size==d.size){
        				curr.prev.next=curr.next;
        				curr.next.prev=curr.prev;
        				curr.next=null;
        				curr.prev=null;
        				return true;	
      				}
        		}
        		curr=curr1;
        	}
        }
        return false;
    }

    public A1List Find(int k, boolean exact){
    	A1List curr=this.getFirst();
    	if(curr!=null){
    		if(exact){
       			while(curr.next!=null){
       				if(curr.key==k){
       					return curr;
       				}
       				curr=curr.next;
       			}
       			return null;
       		}else{
       			while(curr.next!=null){
       				if(curr.key>=k){
       					return curr;
       				}
       				curr=curr.next;
       			}
       		}
       	}
       	return null;
    }

    public A1List getFirst()
    {
       	A1List curr=this;
       	if(curr.prev==null){
       		return curr.getNext();
       	}else{
       		while(curr.prev.prev!=null){
       			curr=curr.prev;
       		}
       		if(curr.next==null){
       			return null;
       		}else{
       			return curr;
       		}
       	}
    }
    
    public A1List getNext(){
    	if(this.next==null || this.next.next==null){
    		return null;
    	}else{
    		return this.next;
    	}
    }
    
    private boolean checkLoop(){
    	if(this.next==null || this.next.next==null || this.next.next.next==null){
    		return false;
    	}else{
    		A1List h=this.next.next;
    		A1List t=this.next;
    		while(true){
    			if(t.next.next==null){
    				return false;
    			}else{
    				if(h.next.next==null || h.next.next.next==null){
    					return false;
    				}else{
    					if(h==t){
    						return true;
    					}else{
    						t=t.next;
    						h=h.next.next;
    					}
    				}
    			}
    		}
    	}
    }

    public boolean sanity()
    { 
     	   if(this.checkLoop()){
     	   	//System.out.println("Loop");
     	   	return false;								//Check if there is a loop
     	   }else{
     	   	A1List curr=this.getFirst();
     	   	if(curr!=null){
     	   		A1List head=curr.prev;					
     	   		if(head.prev!=null){						//Check if head node is correct
     	   			//System.out.println("Head node error");					
     	   			return false;                                                   
     	   		}else{
     	   			if(head.key==(-1) && head.address==(-1) && head.size==(-1)){
     	   				while(curr.next!=null){
     	   					if(curr.prev.next!=curr || curr.next.prev!=curr){		//Check pointers
     	   						//System.out.println("Pointer error");
     	   						return false;
     	   					}else{
     	   						curr=curr.next;
     	   					}
     	   				}
     	   				if(curr.key!=(-1) || curr.address!=(-1) || curr.size!=(-1)){		//Check if tail node is correct
     	   					//System.out.println("Tail node error");
     	   					return false;
     	   				}
     	   			}else{
     	   				//System.out.println("Head node error");
     	   				return false;
     	   			}	
     	   		}
     	   	}
     	   	return true;
     	   }
    }
}


