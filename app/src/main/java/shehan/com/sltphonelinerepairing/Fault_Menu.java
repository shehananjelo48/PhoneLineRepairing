package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fault_Menu extends AppCompatActivity {
TextView Technician,Telephone,ContactNo,Address,Name,Comment,FCT,CabinetNo,DPNo,LoopNo;
Button Logout,Finish;
DatabaseReference ref,Ref;
String tele;
    String cab,ID,loop,dp,Type;
    long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault__menu);
        Technician = (TextView) findViewById(R.id.tvTechnician);
        Telephone = (TextView) findViewById(R.id.tvTelephone);
        ContactNo = (TextView) findViewById(R.id.tvContact);
        Address = (TextView) findViewById(R.id.tvAddress);
        Name = (TextView) findViewById(R.id.tvName);
        Comment = (TextView) findViewById(R.id.tvComment);
        FCT = (TextView) findViewById(R.id.tvFCT);
        CabinetNo = (TextView) findViewById(R.id.tvCabinetNo);
        DPNo = (TextView) findViewById(R.id.tvDPNo);
        LoopNo = (TextView) findViewById(R.id.tvLoopNo);
        //Logout = (Button) findViewById(R.id.btnLogOut);
        Finish = (Button) findViewById(R.id.btnFinish);

        ref= FirebaseDatabase.getInstance().getReference().child("Client");
        Ref= FirebaseDatabase.getInstance().getReference().child("Fault");
        Intent intent = getIntent();



        tele = intent.getStringExtra(Technician_menu.TELEPHON_NO);
        final String cab = intent.getStringExtra(Technician_menu.CAB_NO);

        ID =getIntent().getExtras().getString("techId");
        Type=getIntent().getExtras().getString("type");
        Technician.setText(ID);

        Ref.child(cab).child(tele).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxid=(dataSnapshot.getChildrenCount());
                    String x = Long.toString(maxid-7);
                    Toast.makeText(Fault_Menu.this, x, Toast.LENGTH_SHORT).show();

                    Ref.child(cab).child(tele).child(x).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                Fault fault = dataSnapshot.getValue(Fault.class);
                                Comment.setText(fault.getComment());
                                FCT.setText(fault.getFaultConnectionType());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else{
                    Toast.makeText(Fault_Menu.this, "error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        /*Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Fault_Menu.this);
                builder.setMessage("Do you want to exit");
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
                alert.show();
                //break;
                *//*Intent intent = new Intent(Fault_Menu.this,Technician_login.class);
                startActivity(intent);
                finish();
                finish();*//*
            }
        });*/
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fault_Menu.this,Finish.class);
                intent.putExtra("ID",ID);
                intent.putExtra("Cab",cab);
                intent.putExtra("Tele",tele);
                intent.putExtra("loop",loop);
                intent.putExtra("dp",dp);
                intent.putExtra("type",Type);
                startActivity(intent);


            }
        });
     Interface();
    }
    public  void View(View view){
        Intent intent= new Intent(Fault_Menu.this,MapsActivity2.class);
        intent.putExtra("Tele",tele);
        startActivity(intent);
    }
    public void Interface(){

        ref.child(tele).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Client client = dataSnapshot.getValue(Client.class);
                    cab= client.getCabinetNo();
                    loop=client.getLoopNo();
                    dp = client.getDP_NO();
                    Telephone.setText(tele);
                    ContactNo.setText(client.getContact_No());
                    Address.setText(client.getAddress());
                    Name.setText(client.getName());
                    CabinetNo.setText(cab);
                    //cab= client.getCabinetNo();
                    DPNo.setText(client.getDP_NO());
                    LoopNo.setText(client.getLoopNo());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
