package mil.af.rl.EPTEstimator.server;

import mil.af.rl.EPTEstimator.client.CreateEPT;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import mil.af.rl.jcat.MMC.EPT;
import mil.af.rl.jcat.MMC.mmc;

import java.text.DecimalFormat;
/**
 * The server side implementation of the RPC service.
 */


@SuppressWarnings("serial")
public class CreateEPTImpl extends RemoteServiceServlet implements 
		CreateEPT {

	EPT theEPT = null;
	String nextEvent = null;
	DecimalFormat threeDec = new DecimalFormat("0.000");
	
/*	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}
		if (theEPT == null)theEPT = new EPT();
		theEPT.addEffect(input);
		
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
*/	
	public String[] MMCALCReset(){
		theEPT= EPT.newEPT();
		String[] retVal = {"Reset"};
		return retVal;
	}
	
	public String[] MMCALC(double chg){
		if(theEPT == null){
			theEPT = EPT.newEPT();
		}
		mmc r = theEPT.MMCALC((float)chg);
		String[] sResult = {"","","",""};
		sResult[0] = threeDec.format(r.cv);
		sResult[1] = threeDec.format(r.cv - r.down);
		sResult[2] = threeDec.format(r.cv + r.up);
		if(r.eventIndex == -1){
			sResult[3] = "done";
		}else{
			sResult[3] = Integer.toString(r.eventIndex);
		}
		return sResult;
	}
	
	public  String[] MMCALC(String singleEvent, int cor[], int cCount){
		if(theEPT == null){
			theEPT = EPT.newEPT();
		}
		mmc r = theEPT.MMCALC(singleEvent, cor, cCount);
		String[] sResult = {"","","",""};
		sResult[0] = threeDec.format(r.cv);
		sResult[1] = threeDec.format(r.cv - r.down);
		sResult[2] = threeDec.format(r.cv + r.up);
		if(r.eventIndex == -1){
			sResult[3] = "Done";
		}else{
			sResult[3] = Integer.toString(r.eventIndex);
		}
		return sResult;
	}
	
	public String[] getMarginalDistribution(){
		if(theEPT == null){
			theEPT = EPT.newEPT();
		}
		double[] dist = theEPT.getMarginalDistribution();
		String[] displayDist = new String[dist.length];
		int j = 0;
		for(double prob: dist){
			displayDist[j++] = threeDec.format(prob);
		}
		return displayDist;
	}
	
	public String[] getPearsonCorrelations(){
		if(theEPT == null){
			theEPT = EPT.newEPT();
		}
		double [] thePCor = theEPT.getPearsonCorrelations();
		String[] corList = new String[thePCor.length];
		int j = 0;
		for(double cor: thePCor){
			corList[j++] = threeDec.format(cor);
		}
		return corList;
	}
}

	
