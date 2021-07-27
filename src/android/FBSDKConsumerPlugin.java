package com.contactpigeon.fbsdkconsumerplugin;

import com.contactpigeon.fbsdkconsumerplugin.CPMainParameters;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import com.contactpigeon.fbsdkconsumerplugin.CPConnectionService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * This SDK callback between Clients and SDK library 
 */
public class FBSDKConsumerPlugin extends CordovaPlugin {
    
    CPConnectionService cpConnector;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("initialize")) {
            String CPToken = args.getString(0);
            String CPGroupName = args.getString(1);
            String CPName = args.getString(2);
            cpConnector = new CPConnectionService(this.cordova.getActivity());
            cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        cpConnector.initWithOptions(CPToken, CPGroupName, CPName, "", "");
                    }
            });
            return true;
        } else if (action.equals("askforregistration")) {
            String token = args.getString(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cpConnector.askforregistration(token); // Thread-safe.
                }
            });
            return true;
        } else if (action.equals("doPostToken")) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        cpConnector.resetcurSessionFCMTokenPosted("no");
                        cpConnector.postFCMTokenToCP(CPMainParameters.getInstance().fcmToken);
                    }
            });
            return true;
        } else if (action.equals("pageView")) {
            String utmdt = args.getString(0);
            String utmp = args.getString(1);
            cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        cpConnector.pageView(utmdt, utmp);
                    }
            });
            return true;
        } else if (action.equals("productView")) {
            String pName = args.getString(0);
            String pSku = args.getString(1);
            String utmp = args.getString(2);
            cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        cpConnector.productView(pName, pSku, utmp);
                    }
            });
            return true;
        } else if (action.equals("add2Cart")) {
            String pName = args.getString(0);
            String pSku = args.getString(1);
            int pQty = args.getInt(2);
            double pUnitPrice = args.getDouble(3);
            String utmp = args.getString(4);
            String pimg = args.getString(5);
            cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        cpConnector.add2cart(pName, pSku, pQty, pUnitPrice, pimg, utmp);
                    }
            });
            return true;
        } else if (action.equals("removeFromCart")) {
            String pName = args.getString(0);
            String pSku = args.getString(1);
            int pQty = args.getInt(2);
            double pUnitPrice = args.getDouble(3);
            String utmp = args.getString(4);
            String pimg = args.getString(5);
            cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        cpConnector.removefromcart(pName, pSku, pQty, pUnitPrice, pimg, utmp);
                    }
            });
            return true;
        }  else if (action.equals("setOrderData")) {
            String oId = args.getString(0);
            double oValue = args.getDouble(1);
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cpConnector.setOrderData(oId, oValue);
                }
            });
            return true;
        } else if (action.equals("addOrderItem")) {
            String sku = args.getString(0);
            String name = args.getString(1);
            int quant = args.getInt(2);
            double price = args.getDouble(3);
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cpConnector.addOrderItem(sku, name, quant, price);
                }
            });
            return true;
        } else if (action.equals("placeOrder")) {
            String utmp = args.getString(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cpConnector.postOrder(utmp);
                }
            });
            return true;
        } else if (action.equals("postCart")) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cpConnector.postCart();
                }
            });
            return true;
        } else if (action.equals("postCustomCart")) {
            JSONArray arrayCarts = args.getJSONArray(0);
            List<CPMainParameters.CPCartItem> cartItems = new ArrayList<>();
            for (int i = 0; i < arrayCarts.length(); i++) {
                JSONObject objectCart = arrayCarts.getJSONObject(i);
                cartItems.add(new CPMainParameters.CPCartItem(
                        objectCart.getString("sku"),
                        objectCart.getString("name"),
                        objectCart.getInt("qty"),
                        objectCart.getDouble("unitPrice"),
                        objectCart.getString("link"),
                        objectCart.getString("image")));
            }
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cpConnector.postCustomCart(cartItems);
                }
            });
            return true;
        } else if (action.equals("postContactEmail")) {
            String cp_curEmail = args.getString(0);
            String utmp = args.getString(1);
            cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        cpConnector.setContactMail(cp_curEmail,utmp);
                    }
            });
            return true;
        }
        return false;
    }

//    private void coolMethod(String message, CallbackContext callbackContext) {
//        if (message != null && message.length() > 0) {
//            callbackContext.success(message);
//        } else {
//            callbackContext.error("Expected one non-empty string argument.");
//        }
//    }
}
