package com.vamshi.JavaProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JavaFirstRound {
	
	 static BufferedReader  br,br1=null;
	  static List<String> list=new LinkedList<String>();
	  static Set<String> set=new TreeSet<String>();
		
	
	
	public static void main(String[] args) throws IOException{
		try {
		String strLine;
		//reading the file 
		br=new BufferedReader(new FileReader("C:\\Users\\vvkvr\\Desktop\\textfile.txt"));
	
		
	 br1=new BufferedReader(new  InputStreamReader(System.in));
	 
	   while((strLine = br.readLine())!=null) {
		   StringTokenizer k=new StringTokenizer(strLine);
		   while (k.hasMoreElements()) { 
			String str = (String) k.nextElement();
			if(!str.equals("")){
				str=str.replaceAll("[< >]", "");
				list.add(str);
			}	
		 }   
	}
	   
	   String testTag=br1.readLine();
	   
	   //perform the operation based on input 
	   try {
		 int testNo=Integer.parseInt(testTag);
		 fetchByLineNo(testNo);
		 
		 
	   }catch ( NumberFormatException nFE) {
		   String testStr =testTag;
		   fetchByParentName(testStr);
		   
	  }
	   //print result
	   if(!set.isEmpty()) {
				/*
				 * set.forEach((strr)->{ System.out.print(strr+" "); });
				 */
		   
		   Set<String> printSet=set.stream().filter(p->p.startsWith("parent")).collect(Collectors.toSet());
		   printSet.forEach((strr)->{ System.out.print(strr+" "); });
		   if(printSet.isEmpty()) {
			 Set printSet2 = set.stream().filter(p->p.startsWith("child")).collect(Collectors.toSet());
			 printSet2.forEach((strr)->{ System.out.print(strr+" "); });
			 if(printSet2.isEmpty()) {
				 Set printSet3=set.stream().filter(p->p.startsWith("grandchild")).collect(Collectors.toSet());
				 printSet3.forEach((strr)->{ System.out.print(strr+" "); });
				 
			 }
		   }
	   }
	   

	}catch (Exception e) {
		e.printStackTrace();
	}

}
	
	//internal index finding
	public static void findByIndex(int i,int j){
		for(int index=i;index<j;index++) {
			 //boolean checker=list.stream().anyMatch(p->p.startsWith("parent"));
         	String s=list.get(index);
         	   String result1 =s.replaceAll("[/]", "");
         	   
         		  if(result1.startsWith("parent")||result1.startsWith("child")||result1.startsWith("grandchild")) { 
             		   set.add(result1);
             		   
             	
         	   }
         		  
         	  
         }
		
		
	}
	
	
	
	
	
	//find by parent tag name
	public static void fetchByParentName(String testStr) {
		 if(list.contains(testStr)) {
	           int i=list.indexOf(testStr)+1;
	           int j=list.indexOf("/"+testStr);
	           findByIndex(i, j);
	         
		   }
		 else {
			 System.out.println("the give parent tag not found ");
		 }
	}
	
	
	//find by line no
	public static void fetchByLineNo(int lineNo) {
		  int rLno=lineNo-1;
		  String testStr="";
		if(list.get(rLno)!=null)
		{
		  testStr=list.get(rLno);
		  if(testStr.startsWith("p")) {
			  fetchByParentName(testStr);
		  }
		  else {
			  System.out.println("The line no not  have any parent tag");
		  }
		}
		else {
			System.out.println("The give line goesnt container anything");
		}
	}	
	
}
