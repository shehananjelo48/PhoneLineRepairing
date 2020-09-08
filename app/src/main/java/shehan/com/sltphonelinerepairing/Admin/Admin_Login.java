package shehan.com.sltphonelinerepairing.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import shehan.com.sltphonelinerepairing.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_Login extends AppCompatActivity {
    EditText Username,Password;
    //private Button Login;
    private DatabaseReference ref;

    SharedPreferences sharedPreferences;

    public static final String fileName= "login";
    public static final String Username1 = "username";
    public static final String Password1="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);
            Username = (EditText) findViewById(R.id.etUserName);
            Password = (EditText)findViewById(R.id.etPassword);
            //Login= (Button) findViewById(R.id.btnALogin);
        ref= FirebaseDatabase.getInstance().getReference().child("Admin");

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Username1)){
            Intent intent = new Intent(Admin_Login.this, Admin_menu.class);
            intent.putExtra("A",sharedPreferences.getString(Username1,fileName));
            startActivity(intent);
            finish();
        }

    }
    String password;
    public void btnLogin_click (View view){
        final String UserName =Username.getText().toString();
        password=Password.getText().toString();

            ref.child(UserName).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Admin admin = dataSnapshot.getValue(Admin.class);

                        if (password.equals(admin.getPassword())) {
                            SharedPreferences.Editor editor =sharedPreferences.edit();
                            editor.putString(Username1,UserName);
                            editor.putString(Password1,password);
                            editor.commit();
                            Toast.makeText(Admin_Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Admin_Login.this, Admin_menu.class);
                            intent.putExtra("A",UserName);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Admin_Login.this, "Enter Correct Password ..", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Admin_Login.this,
                                "Admin doesn't Exit..", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Admin_Login.this,
                            "Access Denied", Toast.LENGTH_LONG).show();
                }
            });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
