//Student Name: Issaq Al-Ahmed

package as7b;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTextArea;

public class NoSyncRunnable implements Runnable {

	private String message, file;
	private int count;
	private JTextArea textA;
	
	
	public NoSyncRunnable(String msg, int c, String f, JTextArea jpcTA)
	{
		message = msg;
		count = c;
		file = f;
		textA = jpcTA; 
	}
	
	@Override
	public void run() 
	{
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter (new FileWriter(file, true), true);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			Thread.sleep(100);
		} 
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		
		for(int i = 0; i < count; i++)
		{
			pw.println(message);	
			textA.append(message + "\n");
			
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}

	}

}
