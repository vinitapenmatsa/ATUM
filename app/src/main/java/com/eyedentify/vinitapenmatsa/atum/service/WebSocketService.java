package com.eyedentify.vinitapenmatsa.atum.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.eyedentify.vinitapenmatsa.atum.R;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.amazonaws.regions.Regions;

import java.io.UnsupportedEncodingException;
import ua.naiksoftware.stomp.client.StompClient;

/**
 * Created by vinitapenmatsa on 1/20/18.
 */

public class WebSocketService extends Service {

    private static final String LOG_TAG = "WEB_SOCKET";
    private LocalBinder binder = new LocalBinder();
    private StompClient stompClient;

    AWSIotMqttManager mqttManager;
    String clientId = "TEST_CLIENT_ID_ANDROID";

    CognitoCachingCredentialsProvider credentialsProvider;

    // Customer specific IoT endpoint
    // AWS Iot CLI describe-endpoint call returns: XXXXXXXXXX.iot.<region>.amazonaws.com,
    private static final String CUSTOMER_SPECIFIC_ENDPOINT = "a2c75bxcf8mzrv.iot.ap-southeast-1.amazonaws.com";

    // Cognito pool ID. For this app, pool needs to be unauthenticated pool with
    // AWS IoT permissions.
    private static final String COGNITO_POOL_ID = "ap-south-1:b5183105-d718-445c-8489-a811e12eae17";

    // Region of AWS IoT
    private static final Regions MY_REGION = Regions.AP_SOUTHEAST_1;

    public class LocalBinder extends Binder{
        public WebSocketService getServiceInstance(){ return WebSocketService.this;}
    }


    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }

    public StompClient getStompClient(){
        return stompClient;
    }

    @Override
    public void onCreate(){

        Log.i(LOG_TAG, "onCreate called");
        // Initialize the AWS Cognito credentials provider
        credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(), // context
                COGNITO_POOL_ID, // Identity Pool ID
                MY_REGION // Region
        );

        // MQTT Client
        mqttManager = new AWSIotMqttManager(clientId, CUSTOMER_SPECIFIC_ENDPOINT);

        try {
            Log.i(LOG_TAG , "Connecting to AWS IoT...");
            mqttManager.connect(credentialsProvider, new AWSIotMqttClientStatusCallback() {
                @Override
                public void onStatusChanged(final AWSIotMqttClientStatus status,
                                            final Throwable throwable) {
                    Log.d(LOG_TAG, "Status = " + String.valueOf(status));

                    if (status == AWSIotMqttClientStatus.Connecting) {
                        Log.i(LOG_TAG, "Connecting...");

                    } else if (status == AWSIotMqttClientStatus.Connected) {
                        Log.i(LOG_TAG, "Connected");

                    } else if (status == AWSIotMqttClientStatus.Reconnecting) {
                        if (throwable != null) {
                            Log.e(LOG_TAG, "Connection error.", throwable);
                        }
                        Log.i(LOG_TAG, "Reconnecting");
                    } else if (status == AWSIotMqttClientStatus.ConnectionLost) {
                        if (throwable != null) {
                            Log.e(LOG_TAG, "Connection error.", throwable);
                            throwable.printStackTrace();
                        }
                        Log.i(LOG_TAG, "Disconnected");
                    } else {
                        Log.i(LOG_TAG, "Disconnected");
                    }
                }
            });

            // Subscribe to topic
            Log.i(LOG_TAG , "Subscribing to AWS IoT topic SIMS_VISAKA_POWER");
            mqttManager.subscribeToTopic("SIMS_VISAKA_POWER", AWSIotMqttQos.QOS0,
                    new AWSIotMqttNewMessageCallback() {
                        @Override
                        public void onMessageArrived(final String topic, final byte[] data) {
                            try {
                                String message = new String(data, "UTF-8");
                                Log.i(LOG_TAG, "Message arrived:");
                                Log.i(LOG_TAG, "   Topic: " + topic);
                                Log.i(LOG_TAG, " Message: " + message);

                                Log.i(LOG_TAG, message);

                            } catch (UnsupportedEncodingException e) {
                                Log.e(LOG_TAG, "Message encoding error.", e);
                            }
                        }
                    });
        } catch (final Exception e) {
            Log.e(LOG_TAG, "Connection error.", e);
            Log.e(LOG_TAG, "Error! " + e.getMessage());
        }
    }

    @Override
    public void onDestroy(){
        Log.i(LOG_TAG , "Disconnecting from Stomp");
        stompClient.disconnect();
    }

}
