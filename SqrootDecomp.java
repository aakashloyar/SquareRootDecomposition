package SqrtRootDec;

public class SqrootDecomp {
    public static void main(String[] args) {
        int n=10;
        int[] arr=new int[]{0,1,10,2,15,2,3,1,9,2};
        SRD s1=new SRD(arr,n);
        long a=s1.query(8,9);
        System.out.println(a);
    }
}
class SRD {
    long[] block;int chunks,rootn;
    int[] arr;
    SRD(int[] arr,int n) {
        this.arr=arr;
        init(n);
    }
    void init(int n) {
        rootn=(int)Math.sqrt(n);
        if(rootn==0) rootn++;
        chunks=rootn+1;
        block=new long[chunks];
        preprocess(n);
    }
    void preprocess(int n) {
        int s=0;
        for(int i=0;i<chunks;i++) {
            int c=0;
            long sum=0;
            while(s<n &&c<rootn) {
                sum += arr[s];
                s++;
                c++;
            }
            block[i]=sum;
        }
    }
    long query(int s,int e) {
        //so now we need to separate them in 3 blocks
        //second is all the blocks
        //first is element before second
        //third is element after second
        long res=0;
        while(s<=e && s%rootn!=0 ) res+=arr[s++];

        while(s+rootn-1<=e) {
            res+=block[s/rootn];
            s+=rootn;
        }

        while(s<=e) res+=arr[s++];

        return res;
    }
    void update(int ind,int val) {
        int bind=ind/rootn;
        block[bind]+=val-arr[ind];
        arr[ind]=val;
    }
}

