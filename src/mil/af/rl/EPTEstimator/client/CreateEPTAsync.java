package mil.af.rl.EPTEstimator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CreateEPTAsync {
	void MMCALC(String newEvent, int sam[], int cCount, AsyncCallback<String[]> callback);
	void MMCALC(double change, AsyncCallback<String[]> callback);
	void getMarginalDistribution(AsyncCallback<String[]> callback);
	void getPearsonCorrelations(AsyncCallback<String[]> callback);
	void MMCALCReset(AsyncCallback<String[]> callback);
}
