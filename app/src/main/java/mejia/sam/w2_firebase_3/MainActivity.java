package mejia.sam.w2_firebase_3;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText editTextName;
    private EditText editTextCharge;
    private TextView textViewPersons;
    private Button buttonSave,buttonSearch;
    private int n = 0;
    private ArrayList<Client> clients = new ArrayList<Client>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference("Clients");

        buttonSave = (Button) findViewById(R.id.btnSave);
        textViewPersons = (TextView) findViewById(R.id.tvPersons);
        editTextCharge = (EditText) findViewById(R.id.etCharge);
        editTextName = (EditText) findViewById(R.id.etName);
//        buttonSearch = (Button) findViewById(R.id.btnSerch);

//        buttonSearch.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,Main3Activity.class);
//                i.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) clients);
////                ParcelableArrayListExtra("list",clients);
//                startActivity(i);
//            }
//        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client cli = new Client();

                cli.setName(editTextName.getText().toString());
                cli.setCharge(editTextCharge.getText().toString());
                cli.setId(clients.size());
                ref.child(Integer.toString(clients.size())).setValue(cli);





                //Value event listener for realtime data update
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        clients = new ArrayList<Client>();
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            //Getting the data from snapshot
                            Client cli = postSnapshot.getValue(Client.class);
                            clients.add(cli);
                            //Adding it to a string
                            String string = "Id: " + cli.getId() + "\nName: " + cli.getName() + "\nCharge: " + cli.getCharge() + "\n\n";
                            //Displaying it on textview
                            textViewPersons.setText(string);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });

                        }
        });
    }
}
