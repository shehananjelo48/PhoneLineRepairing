package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Cabinet_No extends AppCompatActivity {
    EditText CabinetNo ;
    Button Add;
    FirebaseDatabase database ;
    DatabaseReference ref;
    Cabinet cabinet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet__no);
        CabinetNo =(EditText) findViewById(R.id.etcabinetNo);
        Add = (Button) findViewById(R.id.btnAdd);
        cabinet= new Cabinet();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Cabinet no");


    }

    public void AddClick(View view){
        if (!TextUtils.isEmpty(CabinetNo.getText().toString())) {
            cabinet.setCabinet_no(CabinetNo.getText().toString());
            ref.child(cabinet.getCabinet_no()).setValue(cabinet).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Cabinet_No.this,
                                "Register Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Cabinet_No.this,
                                "failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Enter Cabinet No..", Toast.LENGTH_SHORT).show();
        }
    }
    public void btnDelete_CabinetNo (View view){
        if (!TextUtils.isEmpty(CabinetNo.getText().toString())) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (cabinet.getCabinet_no().equals(CabinetNo.getText().toString())) {
                        String Telephone = CabinetNo.getText().toString();
                        deleteclient(Telephone);
                    }else {
                        Toast.makeText(Cabinet_No.this, "Enter Correct Cabinet No..", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else {Toast.makeText(this, "Enter Cabinet No..", Toast.LENGTH_LONG).show();}
    }

    private void deleteclient(String telephone) {
        DatabaseReference cli =FirebaseDatabase.getInstance().getReference("Client").child(telephone);
        cli.removeValue();
    }

}
