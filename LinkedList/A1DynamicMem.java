public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }


    public int Allocate(int blockSize) {
    	Dictionary curr=freeBlk.Find(blockSize,false);
    	if(curr==null || blockSize<=0){
    		return -1;
    	}else{
    		int a=curr.address;
    		this.allocBlk.Insert(curr.address,blockSize,curr.address);
    		if(curr.size==blockSize){
    			this.freeBlk.Delete(curr);	
    		}else{
    			this.freeBlk.Insert(curr.address+blockSize,curr.size-blockSize,curr.size-blockSize);	
    			this.freeBlk.Delete(curr);
    		}
    		return a;
    	}
    }  
    // return 0 if successful, -1 otherwise
   
    public int Free(int startAddr) {
        Dictionary curr=this.allocBlk.Find(startAddr,true);
        if(curr==null){
        	return -1;
        }else{
        	this.freeBlk.Insert(startAddr,curr.size,curr.size);
        	this.allocBlk.Delete(curr);
        	return 0;
        }
    }
}
