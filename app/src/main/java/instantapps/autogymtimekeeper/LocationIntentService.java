package instantapps.autogymtimekeeper;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationIntentService extends IntentService {

    private GymLocationListener listener;

    public LocationIntentService() {
        super("LocationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new GymLocationListener();
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 0, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, listener);
    }

    public class GymLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Intent locationChangedEvent = new Intent("LocationChanged");
            locationChangedEvent.putExtra("Latitude", location.getLatitude());
            locationChangedEvent.putExtra("Longitude", location.getLongitude());
            locationChangedEvent.putExtra("Provider", location.getProvider());
            sendBroadcast(locationChangedEvent);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}