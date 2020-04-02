package LD1801099;
import java.util.*;
import java.lang.*;
class Genetic
{
void fit1(int a[],int n, int arr[])
{
    int j,p,b,c,fit = 0;
      for(j=0;j<n;j++)
      {
          p =  Math.abs(a[j]-arr[0]);
          b =  Math.abs(a[j]-arr[1]);
          c =  Math.abs(a[j]-arr[2]);

          if(p>b)
          {
                  if(b>c)
            fit+=c;
                  else
          fit+=b;
          }
          else
          if(p>c)
                  fit+=c;
          else
                  fit+=p;
      }
      arr[3]=fit;
}
void chrom(int a[],int n,int arr[][],int m)
{  
    
    int i,j,k;
    for(i=0;i<m;i++)
    {
        for(j=0;j<3;j++)
        {
            k = (int)(Math.random() * (n - 0));
            arr[i][j] = a[k];
        }
    }
     for(i=0;i<m;i++)
     {
         fit1(a,n,arr[i]);
     }

}
void sel_cross_mut(int a[],int n,int arr[][],int m)
{
    
    int i,t,k=0,j=0,w;
    int arr1[][]=new int[m][4];
    for(i=0;i<m/2;i++)
    {  
        int mini=9999999;
        for(t=0;t<20;t++)
           { w = (int)(Math.random()*m);
             if(arr[w][3]<mini)
             {   mini = arr[w][3];
                 k=w;}
           }
        mini =9999999;
        for(t=0;t<20;t++)
           { w = (int)(Math.random()*m);
             if(arr[w][3]<mini)
             {   mini = arr[w][3];
                 j=w;}
           }

           
         int c = (int)(Math.random()*3);;

         for(t=0;t<=c;t++)
         {
             arr1[i][t] = arr[k][t];
             arr1[m-i-1][t] = arr[j][t];
         }
         for(t=c+1;t<=2;t++)
         {
             arr1[i][t] = arr[j][t];
             arr1[m-i-1][t] = arr[k][t];
         }
        
    }
     
    int y =(int)(Math.random()*3);;
    for(i=0;i<y;i++)
    {
        int g,h,r;
        g = (int)(Math.random()*m);
        h = (int)(Math.random()*3);
        r = (int)(Math.random()*n);;
        arr1[g][h] = a[r];
    }
    for(i=0;i<m;i++)
     {
         fit1(a,n,arr1[i]);
     }

     for(i=0;i<m;i++)
     {
          for(t=0;t<4;t++)
          {
              arr[i][t]=arr1[i][t];
          }
     }

    
}
public static void  main(String[]args)
{

    System.out.println("Enter no. of students: ");
    int i,j,n;
    Scanner sc=new Scanner(System.in);
    n=sc.nextInt();
    Genetic ob=new Genetic();
    int a[]=new int[n];
    for(i=0;i<n;i++)
     {
         a[i] =(int)(Math.random()*101)+1;                
     }
    int pop[][]=new int[50][4];                   
    ob.chrom(a,n,pop,50);
    int gmi = 99999999;
    int gminimum[]=new int[4];
    int itr = 2000;
    int mi = 99999999;
    while(((itr--)>0)||((mi-gmi)>0) )
    {
    int minimum[]=new int[4];
    for(i=0;i<50;i++)
    {
        if(pop[i][3] < mi)
        {
            mi = pop[i][3];
            minimum[0] = pop[i][0];
            minimum[1] = pop[i][1];
            minimum[2] = pop[i][2];
            minimum[3] = pop[i][3];
        }
    }
    if(mi<gmi)
    {
        gmi = mi;
        gminimum[0] = minimum[0];
        gminimum[1]=minimum[1];
        gminimum[2]=minimum[2];
        gminimum[3]=minimum[3];
    }
     ob.sel_cross_mut(a,n,pop,50);
    }
    ArrayList<ArrayList<Integer>> g1,g2,g3;
    g1=new ArrayList<ArrayList<Integer>>();
    g2=new ArrayList<ArrayList<Integer>>();
    g3=new ArrayList<ArrayList<Integer>>();
     int p,b,c;
      for(j=0;j<n;j++)
      {
        ArrayList<Integer>l=new ArrayList<Integer>();
          p =  Math.abs(a[j]-gminimum[0]);
          b =  Math.abs(a[j] -gminimum[1]);
          c =  Math.abs(a[j]-gminimum[2]);
          l.add(a[j]);
          l.add(j);
          if(p>b)
          {
                  if(b>c)
                         g3.add(l);
                         else
                     g2.add(l);
          }
          else
                  {
                  if(p>c)
                         g3.add(l);
                  else
                         g1.add(l);
                  }
      }
    System.out.println("-----------group 1:"+gminimum[0]+" group 2:"+gminimum[1]+" group 3 : "+gminimum[2]+" fitness : "+gminimum[3]+" "+"----------------");
    System.out.println("group 1");
    for(j=0;j<g1.size();j++)
    {
        System.out.println("student  "+g1.get(j).get(1)+" : "+g1.get(j).get(0));
    }
    System.out.println("group 2");
    for(j=0;j<g2.size();j++)
    {
        System.out.println("student  "+g2.get(j).get(1)+" : "+g2.get(j).get(0));
    }
    System.out.println("group 3");
    for(j=0;j<g3.size();j++)
    {
        System.out.println("student  "+g3.get(j).get(1)+" : "+g3.get(j).get(0));
    }
   
}
}




















