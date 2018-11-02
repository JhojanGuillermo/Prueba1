package com.jguiller.cordova.plugin;
// The native Toast API
import android.widget.Toast;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jnbis.api.*;
import java.io.File;

public class Prueba1 extends CordovaPlugin{
    private String binarioImg;
    int duration = Toast.LENGTH_LONG;
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
        if (!action.equals("show")) {
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            return false;
        }
        File base64;
        try{
            JSONObject options = args.getJSONObject(0);
            base64 = new File(options.getString("base64"));
        } catch (JSONException e){
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }

        base64 = Jnbis.wsq()
                .decode("path/to/wsq/file.wsq")
                .toPng()
                .asFile("/path/to/final/file.png");
        
        binarioImg = String.valueOf(base64);

        Toast toast = Toast.makeText(cordova.getActivity(), binarioImg, duration);

        toast.show();
        
        PluginResult pluginResult = new
        PluginResult(PluginResult.Status.OK);
        callbackContext.sedPluginResult(pluginResult);
        return true;
    }
}