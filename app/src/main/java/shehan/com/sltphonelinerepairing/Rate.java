package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class Rate extends AppCompatActivity {
    DatabaseReference ref;
    FirebaseDatabase database;
    StorageReference mStorageRef;
    String a,b;
    long maxid=0;
    RatingBar ratingBarV,ratingBarD;
    Button buttonV,buttonD;
    ImageView ImageViewV,ImageViewD;
    TextView NameV,NameD,v,d,ID;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference Faultref = databaseReference.child("Fault");
    StorageReference mStoragerefernce,storageRef;
    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Technician");
        a = getIntent().getExtras().getString("Value");
        b = getIntent().getExtras().getString("C");
        /*a="ee";
        b="sss";*/
        ratingBarV = findViewById(R.id.ratingBarV);
        ratingBarD = findViewById(R.id.ratingBarD);
        ID = findViewById(R.id.textView2);
        buttonV = findViewById(R.id.btnSubmitV);
        buttonD = findViewById(R.id.btnSubmitD);
        ImageViewV = findViewById(R.id.PicV);
        ImageViewD = findViewById(R.id.PicD);
        NameV = findViewById(R.id.technicianNameV);
        NameD = findViewById(R.id.technicianNameD);
        v = findViewById(R.id.V);
        d = findViewById(R.id.D);
        mStoragerefernce = FirebaseStorage.getInstance().getReference().child("Technician");
        ID.setText(a);

        Faultref.child(b).child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Fault fault = dataSnapshot.getValue(Fault.class);
                    maxid = (dataSnapshot.getChildrenCount());
                    x = Long.toString(maxid - 7);
                    load();
                    Toast.makeText(Rate.this, x, Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    String urlD;
    String urlV;
    public void load() {
        Faultref.child(b).child(a).child(x).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Fault fault = dataSnapshot.getValue(Fault.class);
                    if (fault.getFaultType().equals("Data/Voice")) {
                        ImageViewV.setVisibility(View.VISIBLE);
                        v.setVisibility(View.VISIBLE);
                        NameV.setVisibility(View.VISIBLE);
                        buttonV.setVisibility(View.VISIBLE);
                        ratingBarV.setVisibility(View.VISIBLE);
                        ImageViewD.setVisibility(View.VISIBLE);
                        d.setVisibility(View.VISIBLE);
                        NameD.setVisibility(View.VISIBLE);
                        buttonD.setVisibility(View.VISIBLE);
                        ratingBarD.setVisibility(View.VISIBLE);
                        urlD = fault.getTechnicianIDD();
                        //Picasso.get().load(urlD).into(ImageViewD);
                        urlV = fault.getTechnicianIDV();
                        //Picasso.get().load(urlV).into(ImageViewV);
                        ref.child(urlD).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    Tecnician tecnician = dataSnapshot.getValue(Tecnician.class);
                                    String Name = tecnician.getName();
                                    NameD.setText(Name);
                                    String img = tecnician.getmImageUri();
                                    Picasso.get().load(img).into(ImageViewD);
                                    norD = tecnician.getNOR();
                                    rateD=tecnician.getRate();
                                    //norD = tecnician.getNOR();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        ref.child(urlV).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    Tecnician tecnician = dataSnapshot.getValue(Tecnician.class);
                                    String Name = tecnician.getName();
                                    NameV.setText(Name);
                                    String img = tecnician.getmImageUri();
                                    Picasso.get().load(img).into(ImageViewV);
                                    norV = tecnician.getNOR();
                                    rateV=tecnician.getRate();
                                    //norV = tecnician.getNOR();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else if (fault.getFaultType().equals("Voice")) {
                        ImageViewV.setVisibility(View.VISIBLE);
                        v.setVisibility(View.VISIBLE);
                        NameV.setVisibility(View.VISIBLE);
                        buttonV.setVisibility(View.VISIBLE);
                        ratingBarV.setVisibility(View.VISIBLE);
                        ImageViewD.setVisibility(View.INVISIBLE);
                        d.setVisibility(View.INVISIBLE);
                        NameD.setVisibility(View.INVISIBLE);
                        buttonD.setVisibility(View.INVISIBLE);
                        ratingBarD.setVisibility(View.INVISIBLE);
                        urlV = fault.getTechnicianIDV();
                        //Picasso.get().load(urlV).into(ImageViewV);
                        ref.child(urlV).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    Tecnician tecnician = dataSnapshot.getValue(Tecnician.class);
                                    String Name = tecnician.getName();
                                    NameV.setText(Name);
                                    String img = tecnician.getmImageUri();
                                    Picasso.get().load(img).into(ImageViewV);
                                    norV = tecnician.getNOR();
                                    rateV=tecnician.getRate();
                                    //norV = tecnician.getNOR();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else {
                        ImageViewD.setVisibility(View.VISIBLE);
                        d.setVisibility(View.VISIBLE);
                        NameD.setVisibility(View.VISIBLE);
                        buttonD.setVisibility(View.VISIBLE);
                        ratingBarD.setVisibility(View.VISIBLE);
                        ImageViewV.setVisibility(View.INVISIBLE);
                        v.setVisibility(View.INVISIBLE);
                        NameV.setVisibility(View.INVISIBLE);
                        buttonV.setVisibility(View.INVISIBLE);
                        ratingBarV.setVisibility(View.INVISIBLE);
                        urlD = fault.getTechnicianIDD();
                        //Picasso.get().load(urlD).into(ImageViewD);
                        ref.child(urlD).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    Tecnician tecnician = dataSnapshot.getValue(Tecnician.class);
                                    String Name = tecnician.getName();
                                    NameD.setText(Name);
                                    String img = tecnician.getmImageUri();
                                    Picasso.get().load(img).into(ImageViewD);
                                    norD = tecnician.getNOR();
                                    rateD=tecnician.getRate();
                                    //norD = tecnician.getNOR();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    float rateV,rateD;
    float norD,norV;
    public static final float x1= 1;

    float ValV,ValD;
    public static int r=1;
    public void SubmitV(View view){
        ValV = ratingBarV.getRating();
        Faultref.child(b).child(a).child(x).child("rateV").setValue(ValV);
        rateV=rateV+ValV;
        ref.child(urlV).child("rate").setValue(rateV);
        norV=norV+x1;
        ref.child(urlV).child("nor").setValue(norV);


    }
    public void SubmitD(View view){
        ValD = ratingBarD.getRating();
        Faultref.child(b).child(a).child(x).child("rateD").setValue(ValD);

        rateD=rateD+ValD;
        ref.child(urlD).child("rate").setValue(rateD);
        norD=norD+x1;
        ref.child(urlD).child("nor").setValue(norD);

    }
}
