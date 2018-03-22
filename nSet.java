import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Cosmos Wang
 */


public class nSet {
	public int Max; 
	private int n_long; 
	private long[] store; 

	public nSet(int n) {
	   	this.Max = n;
	   	if (n < 0) { this.Max = 1; }
	   	this.n_long = (n >> 6) + 1; 
	   	this.store = new long[n_long];
	   	for (int i=0;i<n_long;i++) store[i]=0;
	}
	
	public void add(int x) {
		if (x < 0 || x > this.Max) return;
		int i = (x>>6);    
		int j = x - (i<<6); 
		this.store[i] |= ((long) 1<<j); 
	}
	
	public boolean find(int x) {
		if (x < 0 || x > this.Max) return false;
		int i = (x>>6);    
		int j = x - (i<<6); 
		long y = this.store[i];
		return ((y>>j) & ((long) 1)) == 1; 
	}
	
	
	public boolean isEmpty () {
		long size = 0;
		for (long n : store) {
			size|=n;
		}
	    return size==0;
	} 
	
	public void clear () {
	    for (int i=0;i<n_long;i++) {
	    	store[i]&=0;
	    }
	} 
	
	public int size () {
        int size = 0;
        for (int i=1; i<64; i++) {
        	if (find(i) & i<Max) {
        		size++; 
        	}
        }
        return size;
	} 
	
	public boolean delete (int x) {
	   if(!find(x)) {
	   	  return false;
	   }
	   int i = x>>6;
	   int j = 1<<(x-i);
       store[i] -= j;
       return true;     
	} 
	
	public nSet union (nSet X) {
        nSet sexy = new nSet(Max);
	   	for (int i=0;i<n_long;i++) {
	   		sexy.store[i]=X.store[i]|store[i];
	   	}
        return sexy;
	} 
	
	public nSet intersect (nSet X) {
        nSet sexy = new nSet(Max);
        for (int i=0;i<n_long;i++) {
        	sexy.store[i]=X.store[i]&store[i];
        }
        return sexy;
	} 
	
    public nSet subtract (nSet X) {
        nSet sexy = new nSet(Max);
        for (int i=0;i<n_long;i++) {
        	sexy.store[i]=store[i]&(~X.store[i]);
        }
        return sexy;
	} 
	
    public nSet complement() {
        nSet sexy = new nSet(Max);
        for (int i=0;i<n_long;i++) {
        	sexy.store[i]=~store[i];
        }
        return sexy;
	} 
	
    public boolean equal(nSet X) {
	boolean sexy = true;
        for (int i=0;i<n_long;i++) {
        	if(store[i]-X.store[i]!=0) {
        		sexy=false;
        	}
        }
        return sexy;
	}
	
    public boolean isSubset(nSet X) {
    	boolean a = !this.subtract(X).isEmpty();
    	boolean b = X.complement().subtract(complement()).equal(subtract(X));
	    return  a&&b;
	}
	
    public int[] toArray () {
    	int[] sexy = new int[size()];
    	int i = 0;
        for(int n=0;n<Max;n++) {
        	if(find(n)) {
        		sexy[i]=n; 
        		i++;
        	}
        }
        return sexy;
	} 
	
	public void enumerate() {

        int[] a = toArray();
        int l = a.length; 

        for (int i = 0; i < (1<<l); i++){
            for (int j = 0; j < l; j++) {
            	if ((i & (1 << j)) > 0) {
            		System.out.print(a[j] + " ");
            	}
        	}
		}
	}
	 
	
    public static void main(String[] args) {
		nSet legend_cosmos_never_give_up = new nSet(20000);
		}	

    
}

