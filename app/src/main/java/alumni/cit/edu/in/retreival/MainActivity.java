package alumni.cit.edu.in.retreival;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import alumni.cit.edu.in.retreival.m_FireBase.FirebaseHelper;
import alumni.cit.edu.in.retreival.m_Model.Alumni;
import alumni.cit.edu.in.retreival.m_UI.CustomAdapter;

/*
1.INITIALIZE FIREBASE DB
2.INITIALIZE UI
3.DATA INPUT
 */
public class MainActivity extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    ListView lv;
    EditText nameEditTxt,emailTxt,companyTxt,contactTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.lv);

        //INITIALIZE FIREBASE DB
        db= FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);

        //ADAPTER
        adapter=new CustomAdapter(this,helper.retrieve());
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
    }

    //DISPLAY INPUT DIALOG
    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog);

        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        emailTxt= (EditText) d.findViewById(R.id.emailEditText);
        companyTxt= (EditText) d.findViewById(R.id.companyText);
        contactTxt= (EditText) d.findViewById(R.id.contactText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String name=nameEditTxt.getText().toString();
                String email=emailTxt.getText().toString();
                String company=companyTxt.getText().toString();
                 String contact=contactTxt.getText().toString();


                //SET DATA
                Alumni s=new Alumni();
                s.setName(name);
                s.setEmail(email);
                s.setCompany(company);
                s.setContact(contact);


                //SIMPLE VALIDATION
                if(name != null && name.length()>0)
                {
                    //THEN SAVE
                    if(helper.save(s))
                    {
                        //IF SAVED CLEAR EDITXT
                        nameEditTxt.setText("");
                        emailTxt.setText("");
                        companyTxt.setText("");
                        contactTxt.setText("");

                        adapter=new CustomAdapter(MainActivity.this,helper.retrieve());
                        lv.setAdapter(adapter);


                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        d.show();
    }



}

