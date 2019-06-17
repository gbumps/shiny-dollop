/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author stephen
 */
public class FileUtils {
	public static boolean checkPath(String pathToFile) {
     Path path = Paths.get(pathToFile);
		 return Files.exists(path);
	} 
	
	public static void readFile(String path) {
		
	}
	
	public static void writeFile(String path) {
		
	} 
}
