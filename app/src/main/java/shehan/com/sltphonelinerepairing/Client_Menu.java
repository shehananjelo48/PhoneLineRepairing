package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import android.widget.ImageButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Client_Menu extends AppCompatActivity {
    TextView TelephoneNo,Comment;
    String st,tele,Val,a,c,b,d,e,f,g;
    ImageButton logout;
    FirebaseDatabase database;
    DatabaseReference ref,refe;
    EditText Telephone;
    public Fault fault;
    public Client client;
    CheckBox A,B,C;
    Boolean Tele,ADSL;
    long maxid;
    String x,y;

    SharedPreferences sharedPreferences;

    public static final String fileName1= "Clientlogin";
    public static final String Username2 = "username1";
    public static final String Password2="password1";
    public static final String cab="cab";
    public static final String con="contect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__menu);
        TelephoneNo=(TextView)findViewById(R.id.textView2);
        logout= (ImageButton) findViewById(R.id.button3);
        Telephone=(EditText) findViewById(R.id.etTelephone);
        Comment=(EditText) findViewById(R.id.etComment);
        A=(CheckBox)findViewById(R.id.cb1);
        B=(CheckBox)findViewById(R.id.cb2);
        C=(CheckBox)findViewById(R.id.cb3);

        st=getIntent().getExtras().getString("Value");
        tele=getIntent().getExtras().getString("tele");
        st=getIntent().getExtras().getString("Value");
        a=getIntent().getExtras().getString("A");
        b=getIntent().getExtras().getString("B");
        c=getIntent().getExtras().getString("C");
        d=getIntent().getExtras().getString("D");
        e=getIntent().getExtras().getString("E");
        f=getIntent().getExtras().getString("F");
        g=getIntent().getExtras().getString("G");
        //TelephoneNo.setText(datetime);
        fault =new Fault();
        client =new Client();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Fault");
        refe =database.getReference().child("Client");


        TelephoneNo.setText(st);
        Telephone.setText(tele);

        sharedPreferences = getSharedPreferences(fileName1, Context.MODE_PRIVATE);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.remove(Username2);
                editor.remove(Password2);
                editor.remove(cab);
                editor.remove(con);
                editor.commit();
                Intent intent1 = new Intent(Client_Menu.this,Client_Login.class);
                startActivity(intent1);
                finish();

            }
        });

        ref.child(c).child(st).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid=(dataSnapshot.getChildrenCount());
                    x = Long.toString(maxid-6);
                    Toast.makeText(Client_Menu.this, x, Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(Client_Menu.this, "Sanduni", Toast.LENGTH_SHORT).show();
                    fault.setCabinetNo(c);
                    fault.setTelephone_No(st);
                    ref.child(c).child(fault.getTelephone_No()).setValue(fault);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //x = Long.toString(maxid);
        /*ref.child(c).child(st).child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Fault fault = dataSnapshot.getValue(Fault.class);
                    if (fault.isTeleFault()){
                        /*HashMap hashMap = new HashMap();
                        hashMap.put("TeleFaultL",true);
                        //fault.setTeleFaultL(true);
                        ref.child(c).child(st).child("teleFaultL").setValue(true);
                        //Toast.makeText(Client_Menu.this, "cc", Toast.LENGTH_SHORT).show();
                    }else {
                        //fault.setTeleFaultL(false);
                        ref.child(c).child(st).child("teleFaultL").setValue(false);
                        //Toast.makeText(Client_Menu.this, "bb", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Client_Menu.this, "aa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
    String z;
    public void SubmitClick(View view){
        if (!TextUtils.isEmpty(Comment.getText().toString())) {
            if (!!(A.isChecked()||B.isChecked()||C.isChecked())){
                Calendar calendar=Calendar.getInstance();
                /*SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-YYYY");
                String date = simpleDateFormat.format(calendar.getTime());

                SimpleDateFormat SDF=new SimpleDateFormat("hh:mm:ss a");
                String time = SDF.format(calendar.getTime());*/

                /*SimpleDateFormat she=new SimpleDateFormat("DD-MMM-YYYY/hh:mm:ss a");
                String job = she.format(calendar.getTime());*/
                String date = DateFormat.getDateInstance().format(calendar.getTime());
                String time = DateFormat.getTimeInstance().format(calendar.getTime());

                if (A.isChecked() && B.isChecked() && C.isChecked()) {
                    Val = "Telephone/Peo TV/Internet";
                    Tele=true;
                    ADSL=true;
                    z="Data/Voice";
                }else if (A.isChecked() && B.isChecked()) {
                    Val = "Telephone/Peo TV";
                    Tele=true;
                    ADSL=true;
                    z="Data/Voice";
                } else if (A.isChecked() && C.isChecked()) {
                    Val= "Telephone/Internet";
                    Tele=true;
                    ADSL=true;
                    z="Data/Voice";
                } else if (B.isChecked() && C.isChecked()) {
                    Val = "Peo TV/Internet";
                    ADSL=true;
                    Tele=false;
                    z="Data/Voice";
                } else if (A.isChecked()) {
                    Val = "Telephone";
                    Tele=true;
                    ADSL=false;
                    z="Voice";
                } else if (B.isChecked()) {
                    Val = "Peo TV";
                    ADSL=true;
                    Tele=false;
                    z="Data";
                } else if (C.isChecked()) {
                    Val = "Internet";
                    ADSL=true;
                    Tele=false;
                    z="Data";
                }

                fault.setTeleFault(Tele);
                fault.setADSLFault(ADSL);
                fault.setUGFault(true);
                fault.setComment(Comment.getText().toString());
                fault.setFaultConnectionType(Val);
                fault.setSubmitTime(time);
                fault.setSubmitDate(date);
                fault.setTelephone_No(TelephoneNo.getText().toString());
                String id = c+"/"+st+"/"+(x);
                //String id=c+"/"+st;
                fault.setJobNo(id);
                fault.setRateD(-1);
                fault.setRateV(-1);
                fault.setFaultType(z);

                /*client.setContact_No(Telephone.getText().toString());
                client.setTelephone_No(st);
                client.setConnectionType(a);
                client.setAddress(b);
                client.setCabinetNo(c);
                client.setDP_NO(d);
                client.setName(e);
                client.setNIC(f);
                client.setLoopNo(g);
                refe.child(st).setValue(client);*/

                refe.child(st).child("contact_No").setValue(Telephone.getText().toString());

                ref.child(id).setValue(fault).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            start();
                            Toast.makeText(Client_Menu.this,
                                    "Fault Sent", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor =sharedPreferences.edit();
                            editor.remove(Username2);
                            editor.remove(Password2);
                            editor.remove(cab);
                            editor.remove(con);
                            editor.commit();
                            Intent intent = new Intent(Client_Menu.this,Client_Login.class);
                            intent.putExtra("A",st);
                            intent.putExtra("B",c);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Client_Menu.this,
                                    "Send failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });



            }else {
                Toast.makeText(this, "Checked which one has fault", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Enter about Fault", Toast.LENGTH_SHORT).show();
        }
    }
    public void start(){
        y = Long.toString(maxid-7);
        Toast.makeText(this, y, Toast.LENGTH_SHORT).show();
        ref.child(c).child(st).child(y).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Fault fault = dataSnapshot.getValue(Fault.class);
                    if (fault.isTeleFault()){
                        ref.child(c).child(st).child("teleFault").setValue(true);

                    }else {
                        ref.child(c).child(st).child("teleFault").setValue(false);

                    }

                    if (fault.isADSLFault()){
                        ref.child(c).child(st).child("adslfault").setValue(true);
                    }else {
                        ref.child(c).child(st).child("adslfault").setValue(false);
                    }

                }else {
                    Toast.makeText(Client_Menu.this, "aa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


