package com.centuryLink.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= {"/pythonexecutor" })
public class ChatBotController {
	

	/**
	 * 
	 * @param details
	 * @return
	 * 
	 * This method adds new entry for Pickup and drop based on EmpId, year, month given by the user in json
	 */
	@CrossOrigin(origins = "*")
	@PostMapping
	public String getAnswer(@RequestBody String question) {
		StringBuilder builder = new StringBuilder();
		int exitCode = 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		try {
			String python_val[] = new String[] { "C:\\Anaconda3\\python.exe",
					"C:\\Users\\AC38815\\Desktop\\ChatBot_Working_Files\\Python-Spyder Files\\untitled0.py",
					question};
			//ProcessBuilder pb = new ProcessBuilder(Arrays.asList(python_val));
			Date date = new Date();
			System.out.println("start time -->"+dateFormat.format(date));
			ProcessBuilder pb = new ProcessBuilder(Arrays.asList(python_val));
			Process p = null;
			//Process p = pb.start();
			

			if (pb != null) {
				try {
					p = pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (p != null) {
					try {
						exitCode = p.waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = bufferedReader.readLine();
		    while (line != null) {
		      builder.append(line).append(System.lineSeparator());
		      line = bufferedReader.readLine();
		    }
		    bufferedReader.close();	

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		//Date date2 = new Date();
		//System.out.println("Buffer read end time -->"+dateFormat.format(date2));
		//System.out.println("question-->"+question);
		//System.out.println("answere-->"+builder.toString());
		//answer.setAnswer();
		return builder.toString();
		
	}	
}