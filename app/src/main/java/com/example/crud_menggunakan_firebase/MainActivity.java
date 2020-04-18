package com.example.crud_menggunakan_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "wadev";
    private DatabaseReference databaseReference;

    private EditText etnama, etemail, etdesc;
    private Button btn_save;

    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        etnama = findViewById(R.id.editnama);
        etemail = findViewById(R.id.editemail);
        etdesc = findViewById(R.id.editdesc);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Snama = etnama.getText().toString();
                String Semail = etemail.getText().toString();
                String Sdesc = etdesc.getText().toString();

                if (btn_save.getText().equals("Save")){

                if (Snama.equals("")){
                    etnama.setError("Silahkan masukan nama yang berbeda!");
                    etnama.requestFocus();
                }else if (Semail.equals("")){
                    etemail.setError("Silakan masukan email yang berbeda!");
                    etemail.requestFocus();
                }else if (Sdesc.equals("")){
                    etdesc.setError("Silahkan masukan deskripsi yang berbeda!");
                }else {
                    loading = ProgressDialog.show(MainActivity.this,
                            null,
                            "Please Wait...",
                            true,
                            false);


                    submitUser(new RequestData(
                            Snama.toLowerCase(),
                            Semail.toLowerCase(),
                            Sdesc.toLowerCase()));
                }
                }
            }
        });
    }
    private void submitUser(RequestData requests){
        databaseReference.child("Requests")
                .push()
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        loading.dismiss();

                        etnama.setText("");
                        etemail.setText("");
                        etdesc.setText("");

                        Toast.makeText(MainActivity.this,
                                "Data Berhasil di Tambahkan",
                                Toast.LENGTH_SHORT).show();

                    }
    });

    }
}
