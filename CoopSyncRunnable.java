
//Student Name: Issaq Al-Ahmed


package as7b;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTextArea;

public class CoopSyncRunnable implements Runnable {

	private String message, file;
	private int count;
	private Object syncObj;
	private JTextArea textA;
	
	public CoopSyncRunnable(String msg, int c, String f, Object so, JTextArea ta)
	{
		message = msg;
		count = c;
		file = f;
		syncObj = so;
		textA = ta; 
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
				
				// synchronized is required here because notify and wait both act on one variable object. without it one might act without the other being ready.
				synchronized(syncObj)
				{
					syncObj.notify(); //allows two objects of this type to alternate by alerting the other object that it has completed.
					syncObj.wait();
				}
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		
		}
			
		synchronized (syncObj) 
		{
	        syncObj.notify ();
		}

	}

}
