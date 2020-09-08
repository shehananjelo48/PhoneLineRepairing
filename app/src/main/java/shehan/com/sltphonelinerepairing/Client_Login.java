package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Asserts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Client_Login extends AppCompatActivity {

    EditText TelephoneNo,NICno;
    private DatabaseReference ref,Ref;
    long maxid=0;
    String x;

    SharedPreferences sharedPreferences;

    public static final String fileName1= "Clientlogin";
    public static final String Username2 = "username1";
    public static final String Password2="password1";
    public static final String cab="cab";
    public static final String con="contect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__login);
        TelephoneNo=(EditText) findViewById(R.id.etTelephoneNo);
        NICno=(EditText) findViewById(R.id.etNic);
        //remember=(CheckBox) findViewById(R.id.rememberMe);
        ref= FirebaseDatabase.getInstance().getReference().child("Client");
        Ref= FirebaseDatabase.getInstance().getReference().child("Fault");

        sharedPreferences = getSharedPreferences(fileName1, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Username2)){
            Intent intent = new Intent(Client_Login.this,Client_Menu.class);
            intent.putExtra("Value",sharedPreferences.getString(Username2,fileName1));
            intent.putExtra("C",sharedPreferences.getString(cab,fileName1));
            intent.putExtra("tele",sharedPreferences.getString(con,fileName1));
            startActivity(intent);
            finish();
        }

    }
    String Nic;
    String Contect,a,b,c,d,e,f,g;

    public void btnLogin_click (View view){
        final String Telephone=TelephoneNo.getText().toString();
         Nic =NICno.getText().toString();

        ref.child(Telephone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Client client = dataSnapshot.getValue(Client.class);


                    if (Nic.equals(client.getNIC())) {
                        Contect = client.getContact_No();
                        a = client.getConnectionType();
                        b = client.getAddress();
                        c = client.getCabinetNo();
                        d = client.getDP_NO();
                        e = client.getName();
                        f = client.getNIC();
                        g = client.getLoopNo();

                        done();

                    } else {
                        Toast.makeText(Client_Login.this,
                                "Enter correct Password", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Client_Login.this,
                            "Client doesn't Exit..", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Client_Login.this,
                        "Access Denied", Toast.LENGTH_LONG).show();
            }
        });
    }
    String type,D,V;
    float rateD,rateV;
    public void login(){
        final String Telephone=TelephoneNo.getText().toString();
        Ref.child(c).child(Telephone).child(x).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Fault fault = dataSnapshot.getValue(Fault.class);
                    type=fault.getFaultType();
                    rateD=fault.getRateD();
                    rateV=fault.getRateV();
                    D=Float.toString(rateD);
                    V=Float.toString(rateV);


                    if (!(fault.isTeleFault() || fault.isADSLFault())) {
                        if (type.equals("Data/Voice")){

                            if (rateD==-1||rateV==-1) {
                                Intent intent = new Intent(Client_Login.this,Rate.class);
                                intent.putExtra("Value", Telephone);
                                intent.putExtra("C", c);
                                startActivity(intent);
                                finish();

                            }else {
                                Toast.makeText(Client_Login.this,
                                        "login Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Client_Login.this, Client_Menu.class);
                                intent.putExtra("Value", TelephoneNo.getText().toString());
                                intent.putExtra("tele", Contect);
                                intent.putExtra("A", a);
                                intent.putExtra("B", b);
                                intent.putExtra("C", c);
                                intent.putExtra("D", d);
                                intent.putExtra("E", e);
                                intent.putExtra("F", f);
                                intent.putExtra("G", g);

                                setUser();
                                startActivity(intent);
                                finish();
                            }
                        }else if (type.equals("Voice")){
                            if (rateV == -1) {
                                Intent intent = new Intent(Client_Login.this,Rate.class);
                                intent.putExtra("Value", TelephoneNo.getText().toString());
                                intent.putExtra("C", c);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(Client_Login.this,
                                        "login Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Client_Login.this, Client_Menu.class);
                                intent.putExtra("Value", TelephoneNo.getText().toString());
                                intent.putExtra("tele", Contect);
                                intent.putExtra("A", a);
                                intent.putExtra("B", b);
                                intent.putExtra("C", c);
                                intent.putExtra("D", d);
                                intent.putExtra("E", e);
                                intent.putExtra("F", f);
                                intent.putExtra("G", g);

                                setUser();
                                startActivity(intent);
                                finish();
                            }
                        }else {
                            if (rateD== -1) {
                                Intent intent = new Intent(Client_Login.this,Rate.class);
                                intent.putExtra("Value", TelephoneNo.getText().toString());
                                intent.putExtra("C", c);
                                startActivity(intent);
                                finish();
                                //Toast.makeText(Client_Login.this, "2323445678", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Client_Login.this,
                                        "login Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Client_Login.this, Client_Menu.class);
                                intent.putExtra("Value", TelephoneNo.getText().toString());
                                intent.putExtra("tele", Contect);
                                intent.putExtra("A", a);
                                intent.putExtra("B", b);
                                intent.putExtra("C", c);
                                intent.putExtra("D", d);
                                intent.putExtra("E", e);
                                intent.putExtra("F", f);
                                intent.putExtra("G", g);

                                setUser();
                                startActivity(intent);
                                finish();
                            }
                        }
                        /*if (fault.getRateD() == -1) {
                            Intent intent = new Intent(Client_Login.this,Rate.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Client_Login.this,
                                    "login Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Client_Login.this, Client_Menu.class);
                            intent.putExtra("Value", TelephoneNo.getText().toString());
                            intent.putExtra("tele", Contect);
                            intent.putExtra("A", a);
                            intent.putExtra("B", b);
                            intent.putExtra("C", c);
                            intent.putExtra("D", d);
                            intent.putExtra("E", e);
                            intent.putExtra("F", f);
                            intent.putExtra("G", g);

                            startActivity(intent);
                            finish();
                        }*/
                    } else {
                        Toast.makeText(Client_Login.this, "poddak inna", Toast.LENGTH_SHORT).show();
                        /*AlertDialog.Builder builder= new AlertDialog.Builder(Client_Login.this);
                        builder.setMessage("Hitapan tikak hadanna enakan");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                i.putExtra("finish",true);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            }
                        });

                        AlertDialog alert =builder.create();
                        alert.show();*/
                    }



                }else {
                    Toast.makeText(Client_Login.this,
                            "login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Client_Login.this, Client_Menu.class);
                    intent.putExtra("Value", TelephoneNo.getText().toString());
                    intent.putExtra("tele", Contect);
                    intent.putExtra("A", a);
                    intent.putExtra("B", b);
                    intent.putExtra("C", c);
                    intent.putExtra("D", d);
                    intent.putExtra("E", e);
                    intent.putExtra("F", f);
                    intent.putExtra("G", g);

                    setUser();
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Client_Login.this,
                        "login Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Client_Login.this, Client_Menu.class);
                intent.putExtra("Value", TelephoneNo.getText().toString());
                intent.putExtra("tele", Contect);
                intent.putExtra("A", a);
                intent.putExtra("B", b);
                intent.putExtra("C", c);
                intent.putExtra("D", d);
                intent.putExtra("E", e);
                intent.putExtra("F", f);
                intent.putExtra("G", g);

                setUser();
                startActivity(intent);
                finish();
            }
        });
    }
    public void done(){
        final String Telephone=TelephoneNo.getText().toString();
        Ref.child(c).child(Telephone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxid=dataSnapshot.getChildrenCount();
                    x= Long.toString(maxid-7);
                    //Toast.makeText(Client_Login.this, x, Toast.LENGTH_SHORT).show();
                    login();
                }else {
                    Toast.makeText(Client_Login.this,
                        "login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Client_Login.this, Client_Menu.class);
                    intent.putExtra("Value", TelephoneNo.getText().toString());
                    intent.putExtra("tele", Contect);
                    intent.putExtra("A", a);
                    intent.putExtra("B", b);
                    intent.putExtra("C", c);
                    intent.putExtra("D", d);
                    intent.putExtra("E", e);
                    intent.putExtra("F", f);
                    intent.putExtra("G", g);

                    setUser();
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void setUser(){
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(Username2,TelephoneNo.getText().toString());
        editor.putString(Password2,Nic);
        editor.putString(cab,c);
        editor.putString(con,Contect);
        editor.commit();
    }
}
