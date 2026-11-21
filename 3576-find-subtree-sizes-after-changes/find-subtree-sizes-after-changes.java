
import java.util.*;
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n=parent.length;
        List<Integer>[] adj=new ArrayList[n];
        for(int i=0;i<n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int i=1;i<n;i++){
            adj[parent[i]].add(i);
        }
        int[] newParent=parent.clone();
        int[] last=new int[26];
        Arrays.fill(last,-1);
        int[] prev=new int[n];
        int[] stack=new int[2*n+1];
        int top=0;
        stack[top++]=0;
        while(top>0){
            int u=stack[--top];
            if(u<0){
                u=~u;
                last[s.charAt(u)-'a']=prev[u];
                continue;
            }
            int c=s.charAt(u)-'a';
            prev[u]=last[c];
            if(last[c]!=-1){
                newParent[u]=last[c];
            }
            last[c]=u;
            stack[top++]=~u;
            for(int i=adj[u].size()-1;i>=0;i--){
                stack[top++]=adj[u].get(i);
            }
        }
        List<Integer>[] tree=new ArrayList[n];
        for(int i=0;i<n;i++){
            tree[i]=new ArrayList<>();
        }
        for(int i=1;i<n;i++){
            tree[newParent[i]].add(i);
        }
        int[] ans=new int[n];
        Arrays.fill(ans,1);
        int[] order=new int[n];
        top=0;
        int size=0;
        stack[top++]=0;
        while(top>0){
            int u=stack[--top];
            order[size++]=u;
            for(int v:tree[u]){
                stack[top++]=v;
            }
        }
        for(int i=size-1;i>0;i--){
            int u=order[i];
            ans[newParent[u]]+=ans[u];
        }
return ans;
    }
}
