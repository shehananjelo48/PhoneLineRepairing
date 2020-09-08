package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Finish extends AppCompatActivity {
    Equipment equipment;
    String ID,Cab,Tele,loop,dp;
TextView Tech;
    TextView CabNe;
    TextView tele;
    TextView Loop;
    TextView Dp;
    String Type;
    EditText TelephoneS,DropWire,RosetBox,Splitter,Connector,Rosetcode,Chook,Lhook,Retener,RouterS,TvboxS,RemoteS,DpNew,LoopNew,CabNew;
    DatabaseReference ref,Ref,refe;
    TextView TelephoneQ,RouterQ,TvboxQ,RemoteQ;
    long maxid;
    String x;
    String faultId;


    SharedPreferences sharedPreferences;
    public static final String fileName2= "Adminlogin";
    public static final String Username3 = "username3";
    public static final String Password3="password3";
    public static final String cab1="cab1";
    public static final String cab2="cab2";
    public static final String cab3="cab3";
    public static final String cab4="cab4";
    public static final String cab5="cab5";
    public static final String type="type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getSupportActionBar().setTitle(ID);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/



        setContentView(R.layout.activity_finish);
        Tech=(TextView)findViewById(R.id.tvTechnician);
        CabNe=(TextView)findViewById(R.id.etCabNew);
        tele=(TextView)findViewById(R.id.tele);
        Loop=(TextView)findViewById(R.id.loop);
        Dp=(TextView)findViewById(R.id.dp);
        TelephoneQ = (TextView) findViewById(R.id.etTeleQ);
        /*TelephoneQ.setAdapter(adapter);
        TelephoneQ.setOnItemSelectedListener(this);*/


        TelephoneS=(EditText)findViewById(R.id.etTeleS);
        DropWire=(EditText)findViewById(R.id.etDroQ);
        RosetBox=(EditText)findViewById(R.id.etRosQ);
        Splitter=(EditText)findViewById(R.id.etSplQ);
        Connector=(EditText)findViewById(R.id.etConQ);
        Rosetcode=(EditText)findViewById(R.id.etRoQ);
        Chook=(EditText)findViewById(R.id.etCQ);
        Lhook=(EditText)findViewById(R.id.etLQ);
        Retener=(EditText)findViewById(R.id.etRQ);
        RouterQ=(TextView)findViewById(R.id.etRouQ);
        RouterS=(EditText)findViewById(R.id.etRouS);
        TvboxQ=(TextView)findViewById(R.id.etTVQ);
        TvboxS=(EditText)findViewById(R.id.etTVS);
        RemoteQ=(TextView)findViewById(R.id.etRemQ);
        RemoteS=(EditText)findViewById(R.id.etRemS);
        DpNew=(EditText)findViewById(R.id.etDP);
        LoopNew=(EditText)findViewById(R.id.etLoop);
        CabNew=(EditText)findViewById(R.id.etCab);
        ID=getIntent().getExtras().getString("ID");
        Cab = getIntent().getExtras().getString("Cab");
        Tele = getIntent().getExtras().getString("Tele");
        loop = getIntent().getExtras().getString("loop");
        dp = getIntent().getExtras().getString("dp");
        Type = getIntent().getExtras().getString("type");
        /*Toast.makeText(this, Type, Toast.LENGTH_SHORT).show();*/
        /*getSupportActionBar().setTitle(ID);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        /*TelephoneQ.setOnItemSelectedListener(this);
        RouterQ.setOnItemSelectedListener(this);
        TvboxQ.setOnItemSelectedListener(this);
        RemoteQ.setOnItemSelectedListener(this);*/


        Tech.setText(ID);
        CabNe.setText(Cab);
        tele.setText(Tele);
        Loop.setText(loop);
        Dp.setText(dp);

        ref= FirebaseDatabase.getInstance().getReference().child("Client");
        refe=FirebaseDatabase.getInstance().getReference().child("Fault");
//        Ref = FirebaseDatabase.getInstance().getReference().child("Equipment");

        sharedPreferences = getSharedPreferences(fileName2, Context.MODE_PRIVATE);
        equipment = new Equipment();
        refe.child(Cab).child(Tele).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxid=(dataSnapshot.getChildrenCount());
                    x = Long.toString(maxid-7);
                    //Toast.makeText(Finish.this, x, Toast.LENGTH_SHORT).show();
                    refe.child(Cab).child(Tele).child(x).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                Fault fault = dataSnapshot.getValue(Fault.class);
                                faultId = fault.getJobNo();
                                //Toast.makeText(Finish.this, faultId, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    /**
     *
     * click submit button
     *
     * */
    public void submit (View view){

            if (!TextUtils.isEmpty(TelephoneQ.getText().toString())){
                if (!TextUtils.isEmpty(TelephoneS.getText().toString())){
                   if (!TextUtils.isEmpty(RemoteQ.getText().toString())){
                       if (!TextUtils.isEmpty(RemoteS.getText().toString())){
                           if (!TextUtils.isEmpty(RouterQ.getText().toString())){
                               if (!TextUtils.isEmpty(RouterS.getText().toString())){
                                   if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                                       if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                           send();
                                       }else {
                                           TvboxS.setError("Enter serial No");
                                       }
                                   }else {
                                       send();
                                   }
                               }else {
                                   RouterS.setError("Enter serial No");
                               }
                           }else{
                               if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                                   if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                       send();
                                   }else {
                                       TvboxS.setError("Enter serial No");
                                   }
                               }else {
                                   send();
                               }
                           }
                       }else {
                           RemoteS.setError("Enter serial No");
                       }
                   }else {
                       if (!TextUtils.isEmpty(RouterQ.getText().toString())){
                           if (!TextUtils.isEmpty(RouterS.getText().toString())){
                               if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                                   if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                       send();
                                   }else {
                                       TvboxS.setError("Enter serial No");
                                   }
                               }else {
                                   send();
                               }
                           }else {
                               RouterS.setError("Enter serial No");
                           }
                       }else{
                           if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                               if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                   send();
                               }else {
                                   TvboxS.setError("Enter serial No");
                               }
                           }else {
                               send();
                           }
                       }
                   }
                }else {
                    TelephoneS.setError("Enter serial No");
                }
            }else {
                if (!TextUtils.isEmpty(RemoteQ.getText().toString())){
                    if (!TextUtils.isEmpty(RemoteS.getText().toString())){
                        if (!TextUtils.isEmpty(RouterQ.getText().toString())){
                            if (!TextUtils.isEmpty(RouterS.getText().toString())){
                                if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                                    if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                        send();
                                    }else {
                                        TvboxS.setError("Enter serial No");
                                    }
                                }else {
                                    send();
                                }
                            }else {
                                RouterS.setError("Enter serial No");
                            }
                        }else{
                            if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                                if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                    send();
                                }else {
                                    TvboxS.setError("Enter serial No");
                                }
                            }else {
                                send();
                            }
                        }
                    }else {
                        RemoteS.setError("Enter serial No");
                    }
                }else {
                    if (!TextUtils.isEmpty(RouterQ.getText().toString())){
                        if (!TextUtils.isEmpty(RouterS.getText().toString())){
                            if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                                if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                    send();
                                }else {
                                    TvboxS.setError("Enter serial No");
                                }
                            }else {
                                send();
                            }
                        }else {
                            RouterS.setError("Enter serial No");
                        }
                    }else{
                        if (!TextUtils.isEmpty(TvboxQ.getText().toString())){
                            if (!TextUtils.isEmpty(TvboxS.getText().toString())){
                                send();
                            }else {
                                TvboxS.setError("Enter serial No");
                            }
                        }else {
                            send();
                        }
                    }
                }
            }
            //Toast.makeText(this, time, Toast.LENGTH_SHORT).show();

    }
/**
 *
 * send the data to the data base
 *
 * */

    public void send(){
        Ref = FirebaseDatabase.getInstance().getReference().child("Equipment");
        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        String time = DateFormat.getTimeInstance().format(calendar.getTime());


        refe.child(Cab).child(Tele).child(x).child("passDate").setValue(date);
        refe.child(Cab).child(Tele).child(x).child("passTime").setValue(time);
        if (Type.equals("Data")){
            refe.child(Cab).child(Tele).child(x).child("technicianIDD").setValue(ID);
            refe.child(Cab).child(Tele).child(x).child("adslfault").setValue(false);
            refe.child(Cab).child(Tele).child("adslfault").setValue(false);
        }else {
            refe.child(Cab).child(Tele).child(x).child("technicianIDV").setValue(ID);
            refe.child(Cab).child(Tele).child(x).child("teleFault").setValue(false);
            refe.child(Cab).child(Tele).child("teleFault").setValue(false);
        }
        if (!TextUtils.isEmpty(DpNew.getText().toString())) {
            ref.child(Tele).child("dp_NO").setValue(DpNew);
        }
        if (!TextUtils.isEmpty(LoopNew.getText().toString())) {
            ref.child(Tele).child("loopNo").setValue(LoopNew.getText().toString());
        }
        if (!TextUtils.isEmpty(CabNew.getText().toString())) {
            ref.child(Tele).child("cabinetNO").setValue(CabNew);
        }
        if (!TextUtils.isEmpty(TelephoneQ.getText().toString())) {
            equipment.setTeleQ(Integer.parseInt(TelephoneQ.getText().toString()));
        }
        if (!TextUtils.isEmpty(DropWire.getText().toString())) {
            equipment.setDropWireQ(Integer.parseInt(DropWire.getText().toString()));
        }
        if (!TextUtils.isEmpty(RosetBox.getText().toString())) {
            equipment.setRosetBQ(Integer.parseInt(RosetBox.getText().toString()));
        }
        if (!TextUtils.isEmpty(Splitter.getText().toString())) {
            equipment.setSpilterQ(Integer.parseInt(Splitter.getText().toString()));
        }
        if (!TextUtils.isEmpty(Connector.getText().toString())) {
            equipment.setDrop_conQ(Integer.parseInt(Connector.getText().toString()));
        }
        if (!TextUtils.isEmpty(Rosetcode.getText().toString())) {
            equipment.setRosetCQ(Integer.parseInt(Rosetcode.getText().toString()));
        }
        if (!TextUtils.isEmpty(Chook.getText().toString())) {
            equipment.setC_hQ(Integer.parseInt(Chook.getText().toString()));
        }
        if (!TextUtils.isEmpty(Lhook.getText().toString())) {
            equipment.setL_hQ(Integer.parseInt(Lhook.getText().toString()));
        }
        if (!TextUtils.isEmpty(Retener.getText().toString())) {
            equipment.setReternerQ(Integer.parseInt(Retener.getText().toString()));
        }
        if (!TextUtils.isEmpty(RouterQ.getText().toString())) {
            equipment.setRouterQ(Integer.parseInt(RouterQ.getText().toString()));
        }
        if (!TextUtils.isEmpty(TvboxQ.getText().toString())) {
            equipment.setPeoBQ(Integer.parseInt(TvboxQ.getText().toString()));
        }
        if (!TextUtils.isEmpty(RemoteQ.getText().toString())) {
            equipment.setRemoteQ(Integer.parseInt(RemoteQ.getText().toString()));
        }
        equipment.setTeleS(TelephoneS.getText().toString());
        equipment.setRouterS(RouterS.getText().toString());
        equipment.setTVboxS(TvboxS.getText().toString());
        equipment.setRemoteS(RemoteS.getText().toString());
        equipment.setTech_ID(ID);
        equipment.setTelephone(Tele);
        equipment.setFaultId(faultId);
        Ref.child(equipment.getTech_ID()).child(equipment.getTelephone()).child(x)
                .setValue(equipment).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Finish.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Finish.this,Technician_menu.class);
                    intent.putExtra("techId",sharedPreferences.getString(Username3,fileName2));
                    intent.putExtra("A",sharedPreferences.getString(cab1,fileName2));
                    intent.putExtra("B",sharedPreferences.getString(cab2,fileName2));
                    intent.putExtra("C",sharedPreferences.getString(cab3,fileName2));
                    intent.putExtra("D",sharedPreferences.getString(cab4,fileName2));
                    intent.putExtra("E",sharedPreferences.getString(cab5,fileName2));
                    intent.putExtra("type",sharedPreferences.getString(type,fileName2));
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Finish.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
