package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import shehan.com.sltphonelinerepairing.Interface.OnLocationReceved;
import shehan.com.sltphonelinerepairing.LocationFunction.GetCurrentLocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class Add_Client extends AppCompatActivity implements IFirebaseLoadDone, OnLocationReceved {
    SearchableSpinner searchableSpinner;



    EditText teleNo,nicNo,Address,ContactNO,Name,dp,Cabinet,loop;
    Button Add;
    CheckBox c1,c2,c3;
    public Client client;
    FirebaseDatabase database;
    DatabaseReference ref;
    List<Cabinet> cabinets;
    String A;
    IFirebaseLoadDone iFirebaseLoadDone;
    DatabaseReference cabinetRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__client);
        teleNo = (EditText) findViewById (R.id.etTelephoneNo);
        nicNo = (EditText) findViewById (R.id.etNicNo);
        Address = (EditText) findViewById (R.id.etAddress);
        ContactNO = (EditText) findViewById (R.id.etContactNo);
        Name = (EditText) findViewById (R.id.etName);
        dp = (EditText) findViewById (R.id.etDpNo);
        Cabinet = (EditText) findViewById (R.id.etCabinetNo);
        loop = (EditText) findViewById (R.id.etLoopNo);
        Add = (Button) findViewById(R.id.btnAddClient);

        searchableSpinner=(SearchableSpinner)findViewById(R.id.searchable_spinner);

        c1 = (CheckBox) findViewById (R.id.cbTelephon);
        c2 = (CheckBox) findViewById (R.id.cbPeo);
        c3 = (CheckBox) findViewById (R.id.cbRouter);

        //searchableSpinner = (SearchableSpinner)findViewById(R.id.searchable_sp);

        client =new Client();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Client");
        cabinetRef = FirebaseDatabase.getInstance().getReference().child("Cabinet no");
        iFirebaseLoadDone =this;
        cabinetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Cabinet> cabinet = new ArrayList<>();
                for (DataSnapshot cabinetSnapshot:dataSnapshot.getChildren()){
                    cabinet.add(cabinetSnapshot.getValue(Cabinet.class));
                }
                iFirebaseLoadDone.onFirebaseLoadSuccess(cabinet);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });



    }
    
    /**
     * save customer location to firebase
     * get location
     * */
    
    public void btnAdd_location (View view){
      
        /*if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            GetCurrentLocation getCurrentLocation = new GetCurrentLocation(this,this,this);
            getCurrentLocation.getLocation();
            
        }*/
        

    }
    public void btnAddClient_Client (View view) {
        if (!TextUtils.isEmpty(teleNo.getText().toString())){
            if (!TextUtils.isEmpty(nicNo.getText().toString())) {
                if (!TextUtils.isEmpty(Address.getText().toString())) {
                    if (!TextUtils.isEmpty(ContactNO.getText().toString())) {
                        if (!TextUtils.isEmpty(Name.getText().toString())) {
                            if (!TextUtils.isEmpty(dp.getText().toString())) {
                                if (!TextUtils.isEmpty(Cabinet.getText().toString())) {
                                    if (!TextUtils.isEmpty(loop.getText().toString())) {
                                        if(!!(c1.isChecked() || c2.isChecked() || c3.isChecked())) {


                                            if (c1.isChecked() && c2.isChecked() && c3.isChecked()) {
                                                A = "Telephone/Peo TV/Internet";
                                            } else if (c1.isChecked() && c2.isChecked()) {
                                                A = "Telephone/Peo TV";
                                            } else if (c1.isChecked() && c3.isChecked()) {
                                                A = "Telephone/Internet";
                                            } else if (c2.isChecked() && c3.isChecked()) {
                                                A = "Peo TV/Internet";
                                            } else if (c1.isChecked()) {
                                                A = "Telephone";
                                            } else if (c2.isChecked()) {
                                                A = "Peo TV";
                                            } else if (c3.isChecked()) {
                                                A = "Internet";
                                            }
                                            client.setTelephone_No(teleNo.getText().toString());
                                            client.setNIC(nicNo.getText().toString());
                                            client.setAddress(Address.getText().toString());
                                            client.setContact_No(ContactNO.getText().toString());
                                            client.setName(Name.getText().toString());
                                            client.setDP_NO(dp.getText().toString());
                                            client.setCabinetNo(searchableSpinner.getSelectedItem().toString());
                                            client.setLoopNo(loop.getText().toString());
                                            client.setConnectionType(A);

                                            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                                                GetCurrentLocation getCurrentLocation = new GetCurrentLocation(this,this,this);
                                                getCurrentLocation.getLocation();

                                            }

                                            ref.child(client.getTelephone_No()).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(Add_Client.this,
                                                                "Register Successful", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        Toast.makeText(Add_Client.this,
                                                                "failed", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        }else {Toast.makeText(this, "Checked one", Toast.LENGTH_LONG).show();}
                                    }else {Toast.makeText(this, "Enter Loop No", Toast.LENGTH_LONG).show();}
                                }else {Toast.makeText(this, "Enter Cabinet No", Toast.LENGTH_LONG).show();}
                            }else {Toast.makeText(this, "Enter DP No", Toast.LENGTH_LONG).show();}
                        }else {Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show();}
                    }else {Toast.makeText(this, "Enter Contact NO", Toast.LENGTH_LONG).show();}
                }else {Toast.makeText(this, "Enter Address", Toast.LENGTH_LONG).show();}
            }else {Toast.makeText(this, "Enter NIC NO", Toast.LENGTH_LONG).show();}
        }else {Toast.makeText(this, "Enter Telephone NO", Toast.LENGTH_LONG).show();}
    }



    public void btnsearch_Client (View view) {
        if (!TextUtils.isEmpty(teleNo.getText().toString())) {
            String Telephone = teleNo.getText().toString();
            ref.child(Telephone).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Client client = dataSnapshot.getValue(Client.class);
                        nicNo.setText(client.getNIC());
                        Address.setText(client.getAddress());
                        ContactNO.setText(client.getContact_No());
                        Name.setText(client.getName());
                        dp.setText(client.getDP_NO());
                        Cabinet.setText(client.getCabinetNo());
                        loop.setText(client.getLoopNo());

                        if (client.getConnectionType().equals("Telephone/Peo TV/Internet")) {
                            c1.setChecked(client.getConnectionType().equals("Telephone/Peo TV/Internet"));
                            c2.setChecked(client.getConnectionType().equals("Telephone/Peo TV/Internet"));
                            c3.setChecked(client.getConnectionType().equals("Telephone/Peo TV/Internet"));
                        } else if (client.getConnectionType().equals("Telephone/Peo TV")) {
                            c1.setChecked(client.getConnectionType().equals("Telephone/Peo TV"));
                            c2.setChecked(client.getConnectionType().equals("Telephone/Peo TV"));
                        } else if (client.getConnectionType().equals("Telephone/Internet")) {
                            c1.setChecked(client.getConnectionType().equals("Telephone/Internet"));
                            c3.setChecked(client.getConnectionType().equals("Telephone/Internet"));
                        } else if (client.getConnectionType().equals("Peo TV/Internet")) {
                            c2.setChecked(client.getConnectionType().equals("Peo TV/Internet"));
                            c3.setChecked(client.getConnectionType().equals("Peo TV/Internet"));
                        } else if (client.getConnectionType().equals("Telephone")) {
                            c1.setChecked(client.getConnectionType().equals("Telephone"));
                        } else if (client.getConnectionType().equals("Peo TV")) {
                            c2.setChecked(client.getConnectionType().equals("Peo TV"));
                        } else {
                            c3.setChecked(client.getConnectionType().equals("Internet"));
                        }

                        Toast.makeText(Add_Client.this,
                                "Searching Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Add_Client.this,
                                "Client doesn't Exit..", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Add_Client.this,
                            "Client doesn't Exit..", Toast.LENGTH_LONG).show();
                }
            });
        }else {Toast.makeText(this, "Enter Telephone NO", Toast.LENGTH_LONG).show();}
    }
    public void btnDelete_Client (View view){
        if (!TextUtils.isEmpty(teleNo.getText().toString())) {
            String Telephone = teleNo.getText().toString();
            deleteclient(Telephone);
        }else {Toast.makeText(this, "Enter Telephone NO", Toast.LENGTH_LONG).show();}
    }

    private void deleteclient(String telephone) {
        DatabaseReference cli =FirebaseDatabase.getInstance().getReference("Client").child(telephone);
        cli.removeValue();
    }

    @Override
    public void onActivityResult(int req) {

    }

    @Override
    public void onFirebaseLoadSuccess(List<shehan.com.sltphonelinerepairing.Cabinet> cabinetList) {
        cabinets = cabinetList;
        //get all name
        List<String> name_list = new ArrayList<>();
        for (Cabinet cabinet : cabinetList)
            name_list.add(cabinet.getCabinet_no());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,name_list);
        searchableSpinner.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {

    }

    
    /**
     * trigger whwn location result fetching
     * */
    @Override
    public void OnLocation(boolean result, Location location) {
        
        /*check location result get*/
        if(result){

            double lat = location.getLatitude();
            double lang = location.getLongitude();

            /*set client location to pojo*/
            client.setLatitude(lat);
            client.setLongitude(lang);
            ref.child(client.getTelephone_No()).setValue(client);



            Toast.makeText(this, lat+" "+lang, Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Location pick error,Try again", Toast.LENGTH_SHORT).show();
        }
        
    }
}
