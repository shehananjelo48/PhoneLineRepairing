package shehan.com.sltphonelinerepairing;

import androidx.appcompat.app.AppCompatActivity;
import shehan.com.sltphonelinerepairing.Admin.Admin_Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button client, admin , tecnician1 ;
    ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        client = (Button) findViewById(R.id.btnClient);
        tecnician1 = (Button) findViewById(R.id.btnTecnician);
        admin = (Button) findViewById(R.id.btnAdmin);
        i = (ImageView) findViewById(R.id.imageView);
        Calendar calendar=Calendar.getInstance();
        /*String date = DateFormat.getDateInstance().format(calendar.getTime());*/

        calendar.add(Calendar.DATE, -3);  // number of days to add
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
//        i.setImageResource(R.drawable.cloud);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Client_Login.class);
                startActivity(intent);
                //finish();
            }
        });

        tecnician1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Technician_login.class);
                startActivity(intent);
                //finish();

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, Admin_Login.class);
                startActivity(intent);
                //finish();

            }
        });

    }
}
