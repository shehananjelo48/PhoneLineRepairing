package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Technician_login extends AppCompatActivity {
EditText techID,password;
private DatabaseReference ref;

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
        setContentView(R.layout.activity_technician_login);

        techID=(EditText)findViewById(R.id.etTechnicianID);
        password=(EditText)findViewById(R.id.etpass);
        ref = FirebaseDatabase.getInstance().getReference().child("Technician");

        sharedPreferences = getSharedPreferences(fileName2, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Username3)){
            Intent intent = new Intent(Technician_login.this,Technician_menu.class);
            intent.putExtra("techId",sharedPreferences.getString(Username3,fileName2));
            intent.putExtra("A",sharedPreferences.getString(cab1,fileName2));
            intent.putExtra("B",sharedPreferences.getString(cab2,fileName2));
            intent.putExtra("C",sharedPreferences.getString(cab3,fileName2));
            intent.putExtra("D",sharedPreferences.getString(cab4,fileName2));
            intent.putExtra("E",sharedPreferences.getString(cab5,fileName2));
            intent.putExtra("type",sharedPreferences.getString(type,fileName2));
            startActivity(intent);
            finish();
        }

    }

    String Password;
    String C1,C2,C3,C4,C5,Type;
    public void btnLogin_click (View view){
        final String TechnicianID = techID.getText().toString();
        Password = password.getText().toString();

        ref.child(TechnicianID).addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange( @NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {

                            Tecnician tecnician = dataSnapshot.getValue(Tecnician.class);

                            if (Password.equals(tecnician.getPassword())) {
                                C1=tecnician.getCABNO();
                                C2=tecnician.getCABNO_1();
                                C3=tecnician.getCABNO_2();
                                C4=tecnician.getCABNO_3();
                                C5=tecnician.getCABNO_4();
                                Type=tecnician.getType();
                                String tech = tecnician.getTechnicianID();
                                Toast.makeText(Technician_login.this,
                                        "login Successful", Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor =sharedPreferences.edit();
                                editor.putString(Username3,TechnicianID);
                                editor.putString(Password3,Password);
                                editor.putString(cab1,C1);
                                editor.putString(cab2,C2);
                                editor.putString(cab3,C3);
                                editor.putString(cab4,C4);
                                editor.putString(cab5,C5);
                                editor.putString(type,Type);
                                editor.commit();
                                Intent intent = new Intent(Technician_login.this, Technician_menu.class);
                                intent.putExtra("A",C1);
                                intent.putExtra("B",C2);
                                intent.putExtra("C",C3);
                                intent.putExtra("D",C4);
                                intent.putExtra("E",C5);
                                intent.putExtra("techId",tech);
                                intent.putExtra("type",Type);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(Technician_login.this,
                                        "Enter correct Password", Toast.LENGTH_LONG).show();
                            }

                        }else {Toast.makeText(Technician_login.this,
                                "Technician doesn't Exit..", Toast.LENGTH_LONG).show();}
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Technician_login.this,
                            "Technician doesn't Exit..", Toast.LENGTH_LONG).show();
                    }
        });

    }

}
