import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n=nums.length;
        int N=n+1;

        int[] p=new int[N];
        int[] c=new int[N];
        int[] s=new int[N];

        for(int i=0;i<n;i++) {
            s[i]=nums[i];
        }

        s[n]=0;

        int[] cnt=new int[Math.max(100001,N)];

        for(int x:s) {
            cnt[x]++;
        }

        for(int i=1;i<cnt.length;i++) {
            cnt[i]+=cnt[i-1];
        }

        for(int i=N-1;i>=0;i--) {
            p[--cnt[s[i]]]=i;
        }

        int classes=1;
        c[p[0]]=0;

        for(int i=1;i<N;i++) {
            if(s[p[i]]!=s[p[i-1]]) {
                classes++;
            }
            c[p[i]]=classes-1;
        }

        int[] pn=new int[N];
        int[] cn=new int[N];

        for(int k=1;k<N;k*=2) {
            for(int i=0;i<N;i++) {
                pn[i]=p[i]-k;
                if(pn[i]<0) {
                    pn[i]+=N;
                }
            }

            cnt=new int[classes];

            for(int x:pn) {
                cnt[c[x]]++;
            }

            for(int i=1;i<classes;i++) {
                cnt[i]+=cnt[i-1];
            }

            for(int i=N-1;i>=0;i--) {
                int x=pn[i];
                p[--cnt[c[x]]]=x;
            }

            cn[p[0]]=0;
            int newClasses=1;

            for(int i=1;i<N;i++) {
                int cur=p[i];
                int prev=p[i-1];

                if(c[cur]!=c[prev] ||
                   c[(cur+k)%N]!=c[(prev+k)%N]) {
                    newClasses++;
                }

                cn[cur]=newClasses-1;
            }

            c=cn.clone();
            classes=newClasses;

            if(classes==N) {
                break;
            }
        }

        int[] sa=new int[n];

        for(int i=1;i<N;i++) {
            sa[i-1]=p[i];
        }

        int[] rank=new int[n];

        for(int i=0;i<n;i++) {
            rank[sa[i]]=i;
        }

        int[] lcp=new int[n];
        int k=0;

        for(int i=0;i<n;i++) {
            int r=rank[i];

            if(r==n-1) {
                k=0;
                continue;
            }

            int j=sa[r+1];

            while(i+k<n && j+k<n && nums[i+k]==nums[j+k]) {
                k++;
            }

            lcp[r+1]=k;

            if(k>0) {
                k--;
            }
        }

        int ans=n;

        for(int r=0;r<n;r++) {
            int common=0;

            if(r>0) {
                common=Math.max(common,lcp[r]);
            }

            if(r<n-1) {
                common=Math.max(common,lcp[r+1]);
            }

            int len=common+1;

            if(len<=n-sa[r]) {
                ans=Math.min(ans,len);
            }
        }

        return ans;
    }
}