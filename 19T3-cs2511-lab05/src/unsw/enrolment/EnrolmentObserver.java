package unsw.enrolment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrolmentObserver implements Observer {

	@Override
	public void update(Subject s) {
		if (s instanceof EnrolmentSubject) {
			update( (EnrolmentSubject) s);
		}
	}
	
	public void update(EnrolmentSubject s) {
		PrintWriter printWriter = null;
		try {
			String filename = s.course + "-" + s.term + "-" + s.zid + ".txt";
			//File file = File.createTempFile(filename, ".txt");
			FileWriter fileWriter = new FileWriter(filename, true);
			
			if (s.assessment != null) {
				printWriter = new PrintWriter(fileWriter);
			    printWriter.println(s.assessment + " " + s.mark);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();	
			} else {
				//System.out.println("printwriter not open");
			}
			
		}
		
	}

}
