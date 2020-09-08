package shehan.com.sltphonelinerepairing.Admin;

import androidx.appcompat.app.AppCompatActivity;
import shehan.com.sltphonelinerepairing.Cabinet_No;
import shehan.com.sltphonelinerepairing.R;
import shehan.com.sltphonelinerepairing.Register_Technician;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Admin_menu extends AppCompatActivity {
    public Button Admin;
    public Button Add_Client;
    public Button Cabinet;
    public Button  register_Technician;
    ImageButton logout;
    TextView Id;
    SharedPreferences sharedPreferences;
    String A;
    public static final String fileName ="login";
    public static final String Username ="username";
    public static final String Password="password";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        register_Technician = (Button) findViewById(R.id.btnAddTechnician);
        Add_Client = (Button) findViewById(R.id.btnAddClient);
        Admin = (Button) findViewById(R.id.btnAdmin);
        Cabinet = (Button) findViewById(R.id.btnClient);
        logout=findViewById(R.id.button3);
        Id=findViewById(R.id.id);
        //A=getIntent().getExtras().getString("A");
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        A=getIntent().getExtras().getString("A");
        Id.setText(A);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.remove(Username);
                editor.remove(Password);
                editor.commit();
                Intent intent1 = new Intent(Admin_menu.this, Admin_Login.class);
                startActivity(intent1);
                finish();
            }
        });
        register_Technician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.remove(Username);
                editor.remove(Password);
                editor.commit();*/
                Intent intent1 = new Intent(Admin_menu.this, Register_Technician.class);
                startActivity(intent1);
                /*finish();*/
            }
        });

        Add_Client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Admin_menu.this, shehan.com.sltphonelinerepairing.Add_Client.class);
                startActivity(intent2);
            }
        });

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Admin_menu.this, Register_Admin.class);
                startActivity(intent3);
            }
        });
    }
    public void CabinetClick (View view){
        Intent intent = new Intent(this, Cabinet_No.class);
        startActivity(intent);
    }
}
