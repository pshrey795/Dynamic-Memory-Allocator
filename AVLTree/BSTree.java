// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

import java.util.*;

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.  
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key);
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree curr=this;
        BSTree presentNode=this.Find(key,true);
        if(presentNode!=null){
        	if(presentNode.address==address && presentNode.size==size){
        		return presentNode;
        	}
        }
        BSTree newNode=new BSTree(address,size,key);
        if(this.parent!=null){
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
        BSTree par=null;
	while(curr!=null){
		par=curr;
		if(curr.key<key){
			curr=curr.right;
		}else if(curr.key>key){
			curr=curr.left;
		}else{
			if(curr.address<address){
				curr=curr.right;
			}else{
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
	return newNode;
    }

    public boolean Delete(Dictionary e)
    { 
        BSTree curr=this.Find(e.key,true);
        if(curr==null){
        	return false;
        }else{
        	while(curr!=null && curr.key==e.key){
        		if(curr.address==e.address && curr.size==e.size){
        			if(curr.right==null){
        				if(curr.key<curr.parent.key){
        					curr.parent.left=curr.left;
        				}else if(curr.key>curr.parent.key){
        					curr.parent.right=curr.left;
        				}else{
        					if(curr.address<curr.parent.address){
        						curr.parent.left=curr.left;
        					}else{
        						curr.parent.right=curr.left;
        					}
        				}
        				if(curr.left!=null){
        					curr.left.parent=curr.parent;
        				}
        				curr=null;
        			}else{
        				BSTree next=curr.getNext();
        				curr.key=next.key;
        				curr.address=next.address;
        				curr.size=next.size;
        				if(next.parent!=curr){
        					next.parent.left=next.right;
        				}else{
        					next.parent.right=next.right;
        				}
        				if(next.right!=null){
        					next.right.parent=next.parent;
        				}
        				next=null;
        			}
        			return true;
        		}else{
        			curr=curr.getNext();
        		}
        	}
        	return false;
        }
    }
        
    public BSTree Find(int key, boolean exact)
    {
    	BSTree curr=this;
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
        BSTree foundNode=null;
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

    public BSTree getFirst()
    { 
    	BSTree curr=this;
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

    public BSTree getNext()
    { 
        BSTree curr=this;
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
    
    //All functions that follow are for checking sanity
    
    private boolean checkLoop(BSTree prevNode,HashSet<BSTree> visited){
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
    							return false;   						}
    					}
				}
    			}	
		} 		
    	}
    }
    
     /*private static boolean checkLoop(BSTree currNode,BSTree prevNode,HashSet<BSTree> visited){
    	if(currNode==null){
    		return true;
    	}
    	else{
    	visited.add(currNode);
    	if(currNode.parent==prevNode){
    		if(visited.contains(currNode.left) || visited.contains(currNode.right)){
    			return true;
    		}else{
    			return checkLoop(currNode.left,currNode,visited) && checkLoop(currNode.right,currNode,visited);
    		}
    	}else{
		if(currNode.left==prevNode){
			if(visited.contains(currNode.parent) || visited.contains(currNode.right)){
    				return true;
    			}else{
    				return checkLoop(currNode.parent,currNode,visited) && checkLoop(currNode.right,currNode,visited);
    			}
		}else{
			if(visited.contains(currNode.parent) || visited.contains(currNode.left)){
    				return true;
    			}else{
    				return checkLoop(currNode.parent,currNode,visited) && checkLoop(currNode.left,currNode,visited);
    			}
		}		
    	}
    	}
    }*/
    
    private boolean checkBST(int minKey,int maxKey,int minAddr,int maxAddr){
	BSTree curr=this; 
	if(curr.key<minKey || curr.key>maxKey){   	
    		return false;
    	}else{
    		if((curr.key==minKey || curr.key==maxKey)){
    			if(curr.address<=minAddr && curr.address>=maxAddr){
    				return false;
    			}else{
    		if(curr.left==null && curr.right==null){
    			return true;
   		}else{
   			if(curr.left==null){
    				return (curr.right.parent==curr) && curr.right.checkBST(curr.key,maxKey,curr.address,maxAddr);
    			}else if(curr.right==null){
    				return (curr.left.parent==curr) && curr.left.checkBST(minKey,curr.key,minAddr,curr.address);
    			}else{
    				return (curr.right.parent==curr) && (curr.left.parent==curr) && curr.left.checkBST(minKey,curr.key,minAddr,curr.address) && curr.right.checkBST(curr.key,maxKey,curr.address,maxAddr);
    			}
    		}
    		}
    		}else{
    			if(curr.left==null && curr.right==null){
    			return true;
   		}else{
   			if(curr.left==null){
    				return (curr.right.parent==curr) && curr.right.checkBST(curr.key,maxKey,curr.address,maxAddr);
    			}else if(curr.right==null){
    				return (curr.left.parent==curr) && curr.left.checkBST(minKey,curr.key,minAddr,curr.address);
    			}else{
    				return (curr.right.parent==curr) && (curr.left.parent==curr) && curr.left.checkBST(minKey,curr.key,minAddr,curr.address) && curr.right.checkBST(curr.key,maxKey,curr.address,maxAddr);
    			}
    		}
    		}
    	}
    }

    public boolean sanity(){ 
    	BSTree head=this.parent;
    	if(head==null){
    		head=this.left;
    		if(head==null){
    			head=this.right;
    			if(head==null){
    				return true;
    			}
  		}
  	}
  	HashSet<BSTree> h=new HashSet<BSTree>();
  	h.add(this);
  	if(head.checkLoop(this,h)){
  		return false;
  	}else{
  		while(head.parent!=null){
  			head=head.parent;
  		}
  		if(head.left!=null || head.key!=(-1) || head.address!=(-1) || head.size!=(-1)){
  			return false;
  		}else{
  			head=head.right;
  			return head.checkBST(Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
  		}
  	}
    }
}
