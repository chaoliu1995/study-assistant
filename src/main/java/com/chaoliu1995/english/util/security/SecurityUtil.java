package com.chaoliu1995.english.util.security;

public class SecurityUtil {
	
	/**
	 * 输入字段内容检测，防sql注入检测.
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str){
		String inj_str = "|#'#\'#\"#INSERT#DELETE#UPDATE#TRUNCATE#DECLARE#SCRIPT#exec#insert#delete#update#truncate#declare#script#<script#Content-Type#script>#<#>#%3c#%3e"
			+"#&#;#$#%#<>#()#+#CR#LF#,#and";
	    String inj_stra[] = inj_str.split("#");
	    for (int i=0 ; i < inj_stra.length ; i++ ){
	    	if (str.indexOf(inj_stra[i])>-1){
	    		System.out.println(inj_stra[i]);
	    		return true;
	    	}
	    }
	    return false;
	}
	/**
	 * 输入字段名称检测.防跨站攻击检测。
	 * @param str
	 * @return
	 */
	public static boolean vinputname(String str){
		String inj_str = "|#'#\'#\"#%#)#(#INSERT#DELETE#UPDATE#TRUNCATE#DECLARE#SCRIPT#exec#insert#delete#update#truncate#declare#<script#Referer#REFERER#Content-Type#script>#<#>#%3c#%3e";
	    String inj_stra[] = inj_str.split("#");
	    for (int i=0 ; i < inj_stra.length ; i++ )
	        if (str.indexOf(inj_stra[i])>-1){
	        	System.out.println("vinputname::::"+str+",,"+str.indexOf(inj_stra[i])+",,,,"+inj_stra[i]);
	            return true;
	        }
	    
	    return false;
	 }
	
}
