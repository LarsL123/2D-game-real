package net.lars.game2.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;


/**
 * 	
 * @Date 19. juni 2018
 * @Author LarsL123
 *
 * @Info:
 * 		A class full off utility methods.
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public class Utils {
	
	public static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null){
				builder.append(line + "\n");
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return builder.toString();
		
	}
	
	public static String loadFileAsString(BufferedReader r){
		StringBuilder builder = new StringBuilder();
		
		try{
			String line;
			while((line = r.readLine()) != null){
				builder.append(line + "\n");
			}
			r.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return builder.toString();
		
	}
	
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean readBoolean(String value) {
		if(value.equals("t") || value.equals("TRUE") || value.equals("true")){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Info:
	 * 		Convert a String to a vector to a Vector2f
	 * 		Example:
	 * 				 Input: "1,3" -> new Vector2f(1,3);
	 *
	 * @param s
	 * @return
	 * 		A new Vector2f
	 */
	public static Vector2f loadVector2fFromString(String s){
		String[] nums = s.split(",");
		System.out.println(nums[0] + "    " + nums[1]);	
		return new Vector2f(Float.parseFloat(nums[0]),Float.parseFloat(nums[1]));
	}

	public static String readLine(BufferedReader reader) {
		
		try {
			return reader.readLine();
		} catch (IOException e) {
			System.err.println("Was not able to read a line from: "  + reader.toString());
			return null;
		}
	}
}
