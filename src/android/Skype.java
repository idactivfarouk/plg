package org.apache.cordova.skype;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
 
public class Skype extends CordovaPlugin
{
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException 
    {
        if (action.equals("echo")) {
            String mySkypeUri = args.getString(0);
            this.initiateSkypeUri(mySkypeUri, callbackContext);
            return true;
        }
        return false;
    }
	
	public void initiateSkypeUri(String mySkypeUri, CallbackContext callbackContext) 
	{

	  // Make sure the Skype for Android client is installed
	  if (!this->isSkypeClientInstalled(myContext)) 
	  {
		goToMarket(myContext);
		return;
	  }

	  // Create the Intent from our Skype URI
	  Uri skypeUri = Uri.parse(mySkypeUri);
	  Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);

	  // Restrict the Intent to being handled by the Skype for Android client only
	  myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
	  myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	  // Initiate the Intent. It should never fail since we've already established the
	  // presence of its handler (although there is an extremely minute window where that
	  // handler can go away...)
	  myContext.startActivity(myIntent);

	  return;
	}

	/**
	 * Determine whether the Skype for Android client is installed on this device.
	 */
	 
	public boolean isSkypeClientInstalled(Context myContext) 
	{
	  PackageManager myPackageMgr = myContext.getPackageManager();
	  try {
		myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
	  }
	  catch (PackageManager.NameNotFoundException e) {
		return (false);
	  }
	  return (true);
	}

	/**
	 * Install the Skype client through the market: URI scheme.
	 */
	 
	public void goToMarket(Context myContext) 
	{
	  Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
	  Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
	  myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  myContext.startActivity(myIntent);

	  return;
	}

}

