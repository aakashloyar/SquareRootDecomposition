package SqrtRootDec;
import java.util.*;
public class MOsAlgorithm {
    //this algorithm has complexity of O((n+q)(sqrtn))
    //also called offline queries
    //step-1 sort the query array
    //step-2 move pointer and get answer
    //**only applicable if add and remove operation work in O(1) or O(logn)
    public static void main(String[] args) {
        int[] arr=new int[]{0,1,10,2,15,2,3,1,9,2};
        int q=5;
        int[][] que=new int[q][3];
        que[0]=new int[]{0,2,5};
        que[1]=new int[]{1,5,9};
        que[2]=new int[]{2,0,9};
        que[3]=new int[]{3,6,8};
        que[4]=new int[]{4,4,4};
        int n=10;
        MO mo=new MO(arr,que,n,q);
        long[] res=mo.func1();
        for(int i=0;i<q;i++) System.out.println(res[i]);

    }
}
class MO {
    int[] arr;
    int[][] que;
    int q;
    int rootn;
    long sum;
    int s,e;
    MO(int[] arr, int[][] que, int n, int q) {
        this.arr=arr;
        this.rootn=(int)Math.sqrt(n);
        this.sum=0;
        this.s=0;
        this.e=-1;
        this.q=q;
        this.que=que;
        //sort();
    }
    long[] func1() {
        long[] res=new long[q];
        for(int i=0;i<q;i++) {
            query(que[i][1],que[i][2]);
            res[que[i][0]]=sum;
        }
        return res;
    }
    void query(int l,int r) {
        while(s>l) add(arr[--s]);
        while(e<r) add(arr[++e]);
        while(s<l) remove(arr[s++]);
        while(e>r) remove(arr[e--]);
    }
    void add(int x) {
        sum+=x;
    }
    void remove(int x) {
        sum-=x;
    }
    void sort() {
        Arrays.sort(que,(int[] a,int[] b)->{
            int bna=a[1]/rootn;
            int bnb=a[1]/rootn;
            if(bna!=bnb) return bna-bnb;
            return a[2]-b[2];
        });
    }

}
