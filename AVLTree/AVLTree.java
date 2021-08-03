// Class: Height balanced AVL Tree
// Binary Search Tree

import java.util.*;

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
		super();
		
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.

    private static int Height(AVLTree node){
    	if(node==null){
    		return -1;
    	}else{
    		return node.height;
    	}
	}
    
    private static int max(int a,int b){
    	if(a>=b){
    		return a;
    	}else{
    		return b;	
    	}
	}
	
	private void checkBalance(){
		AVLTree curr=this;
		while(curr.parent!=null){
			if(Math.abs(Height(curr.left)-Height(curr.right))>1){
				curr=curr.rebalance();
			}
			curr.height=1+max(Height(curr.left), Height(curr.right));
			curr=curr.parent;
		}
	}
	
	private AVLTree rebalance(){
		if(Height(this.left)>Height(this.right)){
			AVLTree next=this.left;
			if(Height(next.left)>=Height(next.right)){
				return this.rotateRight();
			}else{
				this.left=next.rotateLeft();
				return this.rotateRight();
			}
		}else{
			AVLTree next=this.right;
			if(Height(next.right)>=Height(next.left)){
				return this.rotateLeft();
			}else{
				this.right=next.rotateRight();
				return this.rotateLeft();
			}
		}
	}

	private AVLTree rotateRight(){
		AVLTree next=this.left;
		if(this.parent.right==this){
			this.parent.right=next;
		}else{
			this.parent.left=next;
		}
		next.parent=this.parent;
		this.parent=next;
		this.left=next.right;
		if(next.right!=null){
			next.right.parent=this;
		}
		next.right=this;
		this.height=1+max(Height(this.left),Height(this.right));
		next.height=1+max(Height(next.left),Height(next.right));
		return next;
	}

	private AVLTree rotateLeft(){
		AVLTree next=this.right;
		if(this.parent.right==this){
			this.parent.right=next;
		}else{
			this.parent.left=next;
		}
		next.parent=this.parent;
		this.parent=next;
		this.right=next.left;
		if(next.left!=null){
			next.left.parent=this;
		}
		next.left=this;
		this.height=1+max(Height(this.left),Height(this.right));
		next.height=1+max(Height(next.left),Height(next.right));
		return next;
	}

    public AVLTree Insert(int address, int size, int key) 
    { 
		AVLTree newNode=new AVLTree(address, size, key);
		AVLTree curr=this;
		if(curr.parent!=null){
			while(curr.parent.parent!=null){
				curr=curr.parent;
			}
		}else{
			if(curr.right==null){
				curr.right=newNode;
				newNode.parent=curr;
				return newNode;
			}else{
				curr=curr.right;
			}
		}
		AVLTree par=null;
		while(curr!=null){
			par=curr;
			if(curr.key<key){
				curr=curr.right;
			}else if(curr.key>key){
				curr=curr.left;
			}else{
				if(curr.address<address){
					curr=curr.right;
				}else if(curr.address>address){
					curr=curr.left;
				}
			}
		}
		newNode.parent=par;
		if(par.key<key){
			par.right=newNode;
		}else if(par.key>key){
			par.left=newNode;
		}else{
			if(par.address<address){
				par.right=newNode;
			}else{
				par.left=newNode;
			}
		}
		newNode.checkBalance();
		return newNode;
    }

    public boolean Delete(Dictionary e)
    {
		AVLTree curr=this.Search(e);
		AVLTree nextNode;
		if(curr==null){
			return false;
		}	
		if(curr.right==null){
			nextNode=curr.parent;
			/*if(nextNode.key<curr.key){
				nextNode.right=curr.left;
			}else if(nextNode.key>curr.key){
				nextNode.left=curr.left;
			}else{
				if(nextNode.address<curr.address){
					nextNode.right=curr.left;
				}else{
					nextNode.left=curr.left;
				}
			}*/
			if(curr==curr.parent.right){
				curr.parent.right=curr.left;
			}else{
				curr.parent.left=curr.left;
			}
			if(curr.left!=null){
				curr.left.parent=curr.parent;
			}
		}else{
			AVLTree next=curr.right;
			nextNode=next.parent;
			while(next.left!=null){
				next=next.left;
			}
			curr.key=next.key;
			curr.address=next.address;
			curr.size=next.size;
			/*if(nextNode.key<next.key){
				nextNode.right=next.left;
			}else if(nextNode.key>next.key){
				nextNode.left=next.left;
			}else{
				if(nextNode.address<next.address){
					nextNode.right=next.left;
				}else{
					nextNode.left=next.left;
				}
			}*/
			if(next.parent.left==next){
				next.parent.left=next.right;
			}else{
				next.parent.right=next.right;
			}
			if(next.right!=null){
				next.right.parent=next.parent;
			}
			next=null;
		}
		curr=null;
		nextNode.checkBalance();
		return true;
    }
	
	private AVLTree Search(Dictionary e){
		AVLTree curr=this;
		if(this.parent!=null){
			while(curr.parent.parent!=null){
				curr=curr.parent;
			}
        }else{
    		if(curr.right==null){
   				return null;
   			}else{
    			curr=curr.right;
	   		}
		}
		while(curr!=null){
			if(curr.key<e.key){
				curr=curr.right;
			}else if(curr.key>e.key){
				curr=curr.left;
			}else{
				if(curr.address<e.address){
					curr=curr.right;
				}else if(curr.address>e.address){
					curr=curr.left;
				}else{
					return curr;
				}
			}
		}
		return null;
	}

    public AVLTree Find(int key, boolean exact)
    {
    	AVLTree curr=this;
    	if(this.parent!=null){
			while(curr.parent.parent!=null){
				curr=curr.parent;
			}
        }else{
    		if(curr.right==null){
   				return null;
   			}else{
    			curr=curr.right;
	   		}
        } 
        AVLTree foundNode=null;
        if(exact){
        	while(curr!=null){
        		if(curr.key==key){
        			foundNode=curr;
        			curr=curr.left;
        		}else{
        			if(curr.key<key){
        				curr=curr.right;
        			}else{
        				curr=curr.left;
        			}
        		}
        	}	
        }else{
        	while(curr!=null){
        		if(curr.key<key){
        			curr=curr.right;
        		}else{
        			foundNode=curr;
        			curr=curr.left;
        		}
        	}
        }
        return foundNode;
    }

    public AVLTree getFirst()
    { 
    	AVLTree curr=this;
        if(this.parent!=null){
			while(curr.parent.parent!=null){
				curr=curr.parent;
			}
        }else{
        	if(curr.right==null){
        		return null;
        	}else{
        		curr=curr.right;
        	}
        }
        while(curr.left!=null){
        	curr=curr.left;
        }
        return curr;
    }

    public AVLTree getNext()
    { 
        AVLTree curr=this;
        if(this.parent==null){
        	return null;
        }else{
        	if(curr.right!=null){
        		curr=curr.right;
        		while(curr.left!=null){
        			curr=curr.left;
        		}
        		return curr;
        	}else{
        		while(curr.parent.parent!=null && curr.parent.right==curr){
        			curr=curr.parent;
        		}
        		if(curr.parent.parent==null){
        			return null;
        		}else{
        			return curr.parent;
        		}
        	}
        }
    }
    
    //Sanity Check
    // I have included print statements in between to identify which type of sanity is violated

    private boolean checkLoop(AVLTree prevNode,HashSet<AVLTree> visited){
    	visited.add(this);
    	if(this.parent==null || this.parent==prevNode){
    		if(this.left==null || this.left==prevNode){
    			if(this.right==null || this.right==prevNode){
    				return false;
    			}else{
    				if(visited.contains(this.right) || this.right.checkLoop(this,visited)){
    					return true;
    				}else{
    					return false;
    				}
    			}
    		}else{
				if(visited.contains(this.left) || this.left.checkLoop(this,visited)){
					return true;
				}else{
					if(this.right==null || this.right==prevNode){
    						return false;
    					}else{
    						if(visited.contains(this.right) || this.right.checkLoop(this,visited)){
    							return true;
    						}else{
    							return false;
    						}
    					}
				}
    		}
    	}else{
			if(visited.contains(this.parent) || this.parent.checkLoop(this,visited)){
				return true;
			}else{
				if(this.left==null || this.left==prevNode){
    				if(this.right==null || this.right==prevNode){
    					return false;
    				}else{
    					if(visited.contains(this.right) || this.right.checkLoop(this,visited)){
    						return true;
    					}else{
    						return false;
    					}
    				}
    			}else{
					if(visited.contains(this.left) || this.left.checkLoop(this,visited)){
						return true;
					}else{
						if(this.right==null || this.right==prevNode){
    							return false;
    					}else{
    						if(visited.contains(this.right) || this.right.checkLoop(this,visited)){
    							return true;
    						}else{
								return false;
							}
    					}
					}
    			}	
			} 		
    	}
    }
    
    private boolean checkAVL(int minKey,int maxKey,int minAddr,int maxAddr){
		AVLTree curr=this; 
		if(curr.key<minKey || curr.key>maxKey){   	
			System.out.println("Search property");
    		return false;
    	}else{
    		if((curr.key==minKey || curr.key==maxKey)){
    			if(curr.address<=minAddr && curr.address>=maxAddr){
    				System.out.println("Search property");
    				return false;
    			}else{
    				if(curr.left==null && curr.right==null){
    					return true;
   					}else{
   						if(curr.left==null){
    						return (curr.right.parent==curr) && curr.right.checkAVL(curr.key,maxKey,curr.address,maxAddr);
    					}else if(curr.right==null){
    						return (curr.left.parent==curr) && curr.left.checkAVL(minKey,curr.key,minAddr,curr.address);
    					}else{
    						return (curr.right.parent==curr) && (curr.left.parent==curr) && curr.left.checkAVL(minKey,curr.key,minAddr,curr.address) && curr.right.checkAVL(curr.key,maxKey,curr.address,maxAddr);
 	   					}
    				}
    			}
    		}else{
    			if(curr.left==null && curr.right==null){
    				return true;
   				}else{
   					if(curr.left==null){
    					return (curr.right.parent==curr) && curr.right.checkAVL(curr.key,maxKey,curr.address,maxAddr);
    				}else if(curr.right==null){
    					return (curr.left.parent==curr) && curr.left.checkAVL(minKey,curr.key,minAddr,curr.address);
    				}else{
    					return (curr.right.parent==curr) && (curr.left.parent==curr) && curr.left.checkAVL(minKey,curr.key,minAddr,curr.address) && curr.right.checkAVL(curr.key,maxKey,curr.address,maxAddr);
    				}
    			}
    		}
    	}
	}
    
    private static boolean checkHeight(AVLTree head){
    	if(head==null){
    		return true;
    	}else{
    		if(Math.abs(Height(head.left)-Height(head.right))>1){
    			System.out.println("Height property");
    		}
    		return checkHeight(head.left) && checkHeight(head.right) && (Math.abs(Height(head.left)-Height(head.right))<=1);
    	}
    }

    public boolean sanity(){ 
        AVLTree head=this.parent;
    	if(head==null){
    		head=this.left;
    		if(head==null){
    			head=this.right;
    			if(head==null){
    				return true;
    			}
  			}
  		}
  		HashSet<AVLTree> h=new HashSet<AVLTree>();
  		h.add(this);
  		if(head.checkLoop(this,h)){
  			System.out.println("Loop");
  			return false;
  		}else{
  			while(head.parent!=null){
  				head=head.parent;
  			}
  			if(head.left!=null || head.key!=(-1) || head.address!=(-1) || head.size!=(-1)){
  				System.out.println("Head Pointer Error");
  				return false;
  			}else{
  				head=head.right;
  				return checkHeight(head) && head.checkAVL(Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
  			}
  		}
	}
}


