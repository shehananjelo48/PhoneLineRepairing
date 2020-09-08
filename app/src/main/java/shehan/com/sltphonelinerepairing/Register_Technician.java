package shehan.com.sltphonelinerepairing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Register_Technician extends AppCompatActivity implements IFirebaseLoadDone {
    EditText techID, name, aria, cabNO, vehiNo, teleNo, useNam, password, cPassword;
    Button register,mButtonChoose;
    private Cabinet cabinet;
    private Tecnician tecnician;
    FirebaseDatabase database;
    DatabaseReference ref,cabinetRef;
    SearchableSpinner searchableSpinner,searchableSpinner1,searchableSpinner2,searchableSpinner3,searchableSpinner4;
    IFirebaseLoadDone iFirebaseLoadDone;
    List<Cabinet> cabinets;
    boolean isFirstTimeClik=true;
    ImageView mImageView;
    StorageReference mStoragerefernce;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar mprogressbar;
    private String downloadImageURL;
    RadioButton radioButton;
    RadioGroup radioGroup;
    TextView cab1,cab2,cab3,cab4,cab5,type;



private Uri mImageuri;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST || requestCode==RESULT_OK
        || data != null){
          mImageuri = data.getData();
          mImageView.setImageURI(mImageuri);
            //Picasso.get().load(mImageuri).into(mImageView);
        }
    }
    private String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void rbclick(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton =findViewById(radioId);
        type.setText(radioButton.getText());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__technician);

        mprogressbar=(ProgressBar) findViewById(R.id.progress);
        mButtonChoose=(Button) findViewById(R.id.btnbrowse);
        mImageView=(ImageView) findViewById(R.id.Propic);
        techID = (EditText) findViewById(R.id.etTechnicianID);
        name = (EditText) findViewById(R.id.etName);
        aria = (EditText) findViewById(R.id.etAria);
        //cabNO = (EditText) findViewById(R.id.etCabinetNo);
        vehiNo = (EditText) findViewById(R.id.etVehicalNo);
        teleNo = (EditText) findViewById(R.id.etTelephoneNo);
        useNam = (EditText) findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPassword);
        cPassword = (EditText) findViewById(R.id.etConformPassword);
        register = (Button) findViewById(R.id.btnAddTechnician);
        radioGroup = findViewById(R.id.radioGroup);
        cab1=findViewById(R.id.etCabinetNo);
        cab2=findViewById(R.id.etCabinetNo1);
        cab3=findViewById(R.id.etCabinetNo2);
        cab4=findViewById(R.id.etCabinetNo3);
        cab5=findViewById(R.id.etCabinetNo4);
        type=findViewById(R.id.etType);
        //textPersonName
        searchableSpinner=(SearchableSpinner)findViewById(R.id.searchable_spinner);
        searchableSpinner1=(SearchableSpinner)findViewById(R.id.searchable_spinner_1);
        searchableSpinner2=(SearchableSpinner)findViewById(R.id.searchable_spinner_2);
        searchableSpinner3=(SearchableSpinner)findViewById(R.id.searchable_spinner_3);
        searchableSpinner4=(SearchableSpinner)findViewById(R.id.searchable_spinner_4);
        /*searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long I) {
                if (!isFirstTimeClik){
                    cabNO.setText(cabinet.getCabinet_no() );
                }
                else {
                    isFirstTimeClik = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Register_Technician.this, "Select you first..", Toast.LENGTH_SHORT).show();
            }
        });*/
        cabinetRef = FirebaseDatabase.getInstance().getReference().child("Cabinet no");
        iFirebaseLoadDone =this;
        cabinetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Cabinet> cabinet = new ArrayList<>();
                for (DataSnapshot cabinetSnapshot:dataSnapshot.getChildren()){
                    cabinet.add(cabinetSnapshot.getValue(Cabinet.class));
                }
                iFirebaseLoadDone.onFirebaseLoadSuccess(cabinet);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });


        cabinet=new Cabinet();
        tecnician = new Tecnician();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Technician");
        mStoragerefernce= FirebaseStorage.getInstance().getReference().child("Technician");

        mButtonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        });






    }

    private void  Uploadfille(){
        /*if (mImageuri != null){

            StorageReference filerefernce = mStoragerefernce.child(System.currentTimeMillis()
            +"."+getFileExt(mImageuri));

            filerefernce.putFile(mImageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Register_Technician.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                    Tecnician tecnician = new Tecnician(techID.getText().toString().trim(),taskSnapshot.getUploadSessionUri().toString());
                    tecnician.se
                    Uri downloadUri = taskSnapshot.getD
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register_Technician.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "Select Technician Picture", Toast.LENGTH_SHORT).show();
        }*/
    }
    String s;
    public void btnAddTechnician(View view) {

        mprogressbar.setVisibility(View.VISIBLE);
        if (password.getText().toString().equals(cPassword.getText().toString())) {
            if (!TextUtils.isEmpty(techID.getText().toString())) {
                if (!TextUtils.isEmpty(name.getText().toString())) {
                    if (!TextUtils.isEmpty(aria.getText().toString())) {
                        //if (!TextUtils.isEmpty(cabNO.getText().toString())) {
                            if (!TextUtils.isEmpty(vehiNo.getText().toString())) {
                                if (!TextUtils.isEmpty(teleNo.getText().toString())) {
                                    if (!TextUtils.isEmpty(useNam.getText().toString())) {
                                        if (!TextUtils.isEmpty(password.getText().toString())) {

                                            //if (mImageuri != null) {
                                                if (radioButton.callOnClick()) {

                                                    s = techID.getText().toString();
                                                    final StorageReference filerefernce = mStoragerefernce.child(mImageuri.getLastPathSegment() + s + ".jpg");
                                                    final UploadTask uploadTask = filerefernce.putFile(mImageuri);

                                                    uploadTask.addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            String message = e.toString();
                                                            Toast.makeText(Register_Technician.this, "Error" + message, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                        @Override
                                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                                                @Override
                                                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                                                    if (!task.isSuccessful()) {
                                                                        throw task.getException();

                                                                    }
                                                                    downloadImageURL = filerefernce.getDownloadUrl().toString();
                                                                    //savedata();
                                                                    return filerefernce.getDownloadUrl();
                                                                }
                                                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Uri> task) {
                                                                    if (task.isSuccessful()) {
                                                                        downloadImageURL = task.getResult().toString();

                                                                            savedata();

                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });
                                                }else {Toast.makeText(this, "select voice or data", Toast.LENGTH_SHORT).show();}
                                                /*} else {
                                                    Toast.makeText(this, "Select Technician Picture", Toast.LENGTH_SHORT).show();
                                                }*/






                                        } else {
                                            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(this, "Enter Username", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(this, "Enter Telephone No", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(this, "Enter Vahicel No", Toast.LENGTH_LONG).show();
                            }
                        /*} else {
                            Toast.makeText(this, "Enter Cabinet No", Toast.LENGTH_LONG).show();
                        }*/
                    } else {
                        Toast.makeText(this, "Enter Aria", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Enter Technician ID", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Conform Password & password not equal", Toast.LENGTH_LONG).show();
        }
    }


    public void btnsearch_Technician (View view){
        if (!TextUtils.isEmpty(techID.getText().toString())) {
            String TechnicianID = techID.getText().toString();
            ref.child(TechnicianID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Tecnician tecnician = dataSnapshot.getValue(Tecnician.class);
                        name.setText(tecnician.getName());
                        Picasso.get().load(tecnician.getmImageUri()).into(mImageView);
                        aria.setText(tecnician.getAria());
                        //cabNO.setText(tecnician.getCABNO());
                        vehiNo.setText(tecnician.getVehicalNo());
                        teleNo.setText(tecnician.getTelephoneNo());
                        useNam.setText(tecnician.getUserName());
                        password.setText(tecnician.getPassword());
                        cab1.setText(tecnician.getCABNO());
                        cab2.setText(tecnician.getCABNO_1());
                        cab3.setText(tecnician.getCABNO_2());
                        cab4.setText(tecnician.getCABNO_3());
                        cab5.setText(tecnician.getCABNO_4());
                        type.setText(tecnician.getType());

                        Toast.makeText(Register_Technician.this,
                                "Searching Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Register_Technician.this,
                                "Technician doesn't Exit..", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Register_Technician.this,
                            "Technician doesn't Exit..", Toast.LENGTH_LONG).show();
                }

            });
        }else {Toast.makeText(this, "Enter Telephone NO", Toast.LENGTH_LONG).show();}
    }
    public void btnDelete_Technician (View view){
        if (!TextUtils.isEmpty(techID.getText().toString())) {
            String Technician = techID.getText().toString();
            deleteclient(Technician);
            Toast.makeText(Register_Technician.this, "Enter Telephone NO", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Register_Technician.this, "Enter Telephone NO", Toast.LENGTH_LONG).show();
        }
    }


    private void deleteclient (String technician){
        DatabaseReference tech = FirebaseDatabase.getInstance().getReference("Technician").child(technician);
        tech.removeValue();
    }


    @Override
    public void onActivityResult(int req) {

    }

    @Override
    public void onFirebaseLoadSuccess(List<Cabinet> cabinetList) {
        cabinets = cabinetList;
        //get all name
        List<String> name_list = new ArrayList<>();
        for (Cabinet cabinet : cabinetList)
            name_list.add(cabinet.getCabinet_no());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,name_list);
        searchableSpinner.setAdapter(adapter);
        searchableSpinner1.setAdapter(adapter);
        searchableSpinner2.setAdapter(adapter);
        searchableSpinner3.setAdapter(adapter);
        searchableSpinner4.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {

     }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void savedata(){
        //tecnician.setmImageUri(downloadUrl.toString());
        tecnician.setTechnicianID(techID.getText().toString());
        tecnician.setName(name.getText().toString());
        tecnician.setAria(aria.getText().toString());
        //tecnician.setCABNO(cabNO.getText().toString());
        tecnician.setCABNO(searchableSpinner.getSelectedItem().toString());
        tecnician.setCABNO_1(searchableSpinner1.getSelectedItem().toString());
        tecnician.setCABNO_2(searchableSpinner2.getSelectedItem().toString());
        tecnician.setCABNO_3(searchableSpinner3.getSelectedItem().toString());
        tecnician.setCABNO_4(searchableSpinner4.getSelectedItem().toString());
        tecnician.setVehicalNo(vehiNo.getText().toString());
        tecnician.setTelephoneNo(teleNo.getText().toString());
        tecnician.setUserName(useNam.getText().toString());
        tecnician.setPassword(password.getText().toString());
        tecnician.setmImageUri(downloadImageURL);
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        tecnician.setType(radioButton.getText().toString());
        tecnician.setNOR(1);


        ref.child(tecnician.getTechnicianID()).setValue(tecnician)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register_Technician.this,
                                    "Register Successful", Toast.LENGTH_LONG).show();
                            mprogressbar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(Register_Technician.this,
                                    "failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
