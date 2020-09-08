package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Technician_menu extends AppCompatActivity {
ListView listViewTechnician;
DatabaseReference ref,Ref;
List<Fault> faultList;
String A,B,C,D,E,tech,Type;
TextView TechId,Rate1;
ImageButton Logout;
long maxid;
String Cab[]=new String[5];
SwipeRefreshLayout refreshLayout;
public static final String TELEPHON_NO = "telephonNo";
    public static final String CAB_NO = "cabNo";

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
        setContentView(R.layout.activity_technician_menu);
        listViewTechnician =(ListView) findViewById(R.id.listViewTechnician);
        TechId=(TextView)findViewById(R.id.tvTechnician);
        Rate1=(TextView)findViewById(R.id.tvRate);
        Logout = (ImageButton)findViewById(R.id.btnlogout);
        refreshLayout=findViewById(R.id.refreshLayout);
        ref = FirebaseDatabase.getInstance().getReference("Fault");
        Ref = FirebaseDatabase.getInstance().getReference("Technician");
        faultList=new ArrayList<>();
        A=getIntent().getExtras().getString("A");
        B=getIntent().getExtras().getString("B");
        C=getIntent().getExtras().getString("C");
        D=getIntent().getExtras().getString("D");
        E=getIntent().getExtras().getString("E");
        tech = getIntent().getExtras().getString("techId");
        Type= getIntent().getExtras().getString("type");
        String X;
        if (type.equals("Data")){
            X="isADSLFault()";
        }else{
            X="";
        }




        final Fault fault= new Fault();

        sharedPreferences = getSharedPreferences(fileName2, Context.MODE_PRIVATE);
        TechId.setText(tech);

        Cab[0]=A;
        Cab[1]=B;
        Cab[2]=C;
        Cab[3]=D;
        Cab[4]=E;
        faultList.clear();


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.remove(Username3);
                editor.remove(Password3);
                editor.remove(cab1);
                editor.remove(cab2);
                editor.remove(cab3);
                editor.remove(cab4);
                editor.remove(cab5);
                editor.remove(type);
                editor.commit();
                Intent intent = new Intent(Technician_menu.this,Technician_login.class);
                startActivity(intent);
                finish();
            }
        });

        listViewTechnician.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fault fault1 = faultList.get(position);
                Intent intent = new Intent(getApplicationContext(),Fault_Menu.class);
                intent.putExtra(TELEPHON_NO,fault1.getTelephone_No());
                intent.putExtra(CAB_NO,fault1.getCabinetNo());
                intent.putExtra("techId",tech);
                intent.putExtra("type",Type);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                faultList.clear();
                for (int i=0; i<=4;i++) {
                    ref.child(Cab[i]).addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //faultList.clear();
                            for (DataSnapshot faultSnapshot : dataSnapshot.getChildren()) {
                                Fault fault = faultSnapshot.getValue(Fault.class);

                                if (Type.equals("Data")) {
                                    if (fault.isADSLFault()) {
                                        faultList.add(fault);
                                    }
                                }else if (Type.equals("Voice")){
                                    if (fault.isTeleFault()){
                                        faultList.add(fault);
                                    }
                                }
                                ArrayFault adapter = new ArrayFault(Technician_menu.this, faultList);
                                listViewTechnician.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
                refreshLayout.setRefreshing(false);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        faultList.clear();
        for (int i=0; i<=4;i++) {
            ref.child(Cab[i]).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //faultList.clear();
                    for (DataSnapshot faultSnapshot : dataSnapshot.getChildren()) {
                        Fault fault = faultSnapshot.getValue(Fault.class);

                        if (fault.isADSLFault()) {

                            faultList.add(fault);

                        } else {

                        }

                        ArrayFault adapter = new ArrayFault(Technician_menu.this, faultList);
                        listViewTechnician.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        refreshLayout.setRefreshing(false);

        Ref.child(tech).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Tecnician tecnician = dataSnapshot.getValue(Tecnician.class);
                    float nof = tecnician.getNOR();
                    float rate = tecnician.getRate();
                    float Rate = rate/nof;
                    String s = String.valueOf(Rate);
                    Rate1.setText(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
