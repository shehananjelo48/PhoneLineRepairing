package shehan.com.sltphonelinerepairing.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import shehan.com.sltphonelinerepairing.R;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_Admin extends AppCompatActivity {
    EditText userName,password,adminCode,adminId;
    Button register ;
    FirebaseDatabase database;
    DatabaseReference ref;
    private Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__admin);
        userName = (EditText) findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPassword);
        adminCode = (EditText) findViewById(R.id.etAdminCode);
        register = (Button) findViewById(R.id.btnRegister);
        adminId = (EditText) findViewById(R.id.etAdminID);
        admin = new Admin();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Admin");
    }
    String SuperCode;
    String AID;
    /**
     * check condition
     * */
    public void btnRegister_Admin (View view){
        admin.getAdminID();
        String SA="Admin 1";
        SuperCode=adminCode.getText().toString();
        ref.child(SA).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final Admin admin = dataSnapshot.getValue(Admin.class);
                    if (SuperCode.equals(admin.getSuperCode())) {
                        //Add_data();


                            AID = adminId.getText().toString();
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (AID.equals(admin.getAdminID())){
                                        Toast.makeText(Register_Admin.this, "Enter Another Admin Id", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Add_data();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                    }else {
                        Toast.makeText(Register_Admin.this, "Enter Correct Super Admin Code ", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Register_Admin.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Register_Admin.this, "Database Error ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * add data to the database
     * */
    public void Add_data(){

            if (!TextUtils.isEmpty(userName.getText().toString())) {
                if (!TextUtils.isEmpty(password.getText().toString())) {
                    if (!TextUtils.isEmpty(adminCode.getText().toString())) {
                        if (!TextUtils.isEmpty(adminId.getText().toString())) {
                            admin.setUserName(userName.getText().toString());
                            admin.setPassword(password.getText().toString());
                            admin.setSuperCode(adminCode.getText().toString());
                            admin.setAdminID(adminId.getText().toString());
                            ref.child(admin.getAdminID()).setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register_Admin.this,
                                                "Register Successful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Register_Admin.this,
                                                "failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(this, "Enter Admin Id..", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Enter Super Admin Code..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Enter Password..", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Enter Username..", Toast.LENGTH_SHORT).show();
            }

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
    }
}
