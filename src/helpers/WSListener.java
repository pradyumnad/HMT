package helpers;

public interface WSListener {
	/*	Called after the WS Request is completed with response	*/
	public void onRequestCompleted(String response);

	/*	Called after the WS Request is failed with error	*/
	public void onRequestFailed(Exception exception);
}
