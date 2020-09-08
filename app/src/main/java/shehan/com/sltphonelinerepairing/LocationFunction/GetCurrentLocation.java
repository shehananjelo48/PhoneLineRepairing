package shehan.com.sltphonelinerepairing.LocationFunction;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import shehan.com.sltphonelinerepairing.Interface.OnLocationReceved;

public class GetCurrentLocation {

    private OnLocationReceved onLocationReceved;
    private Activity activity;
    private Context context;

    public GetCurrentLocation(OnLocationReceved onLocationReceved, Activity activity, Context context) {
        this.onLocationReceved = onLocationReceved;
        this.activity = activity;
        this.context = context;
    }

    /**
     * get current location
     * */
    public void getLocation(){

        /*location request*/
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        LocationServices.getFusedLocationProviderClient(this.activity)
                .requestLocationUpdates(locationRequest,new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);

                        LocationServices.getFusedLocationProviderClient(activity).removeLocationUpdates(this);

                        /*get location result*/
                        if(locationResult != null){
                            onLocationReceved.OnLocation(true,locationResult.getLastLocation());
                        }
                        else{
                            onLocationReceved.OnLocation(false,null);
                        }

                    }
                }, Looper.getMainLooper());

    }


}
