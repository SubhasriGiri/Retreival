package alumni.cit.edu.in.retreival;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {


    TextView nameTxt,emailTxt, companyTxt;
    EditText phoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        emailTxt= (TextView) findViewById(R.id.emailDetailTxt);
        companyTxt = (TextView) findViewById(R.id.companyDetailTxt);
        phoneTxt =(EditText)findViewById(R.id.phone);

        //GET INTENT
        Intent i=this.getIntent();


        //RECEIVE DATA
        String name=i.getExtras().getString("NAME_KEY");
        String email=i.getExtras().getString("EMAIL_KEY");
        String company=i.getExtras().getString("COMPANY_KEY");
        int phone=Integer.parseInt(i.getExtras().getString("CONTACT_KEY"));

        //BIND DATA
        nameTxt.setText(name);
        emailTxt.setText(email);
        companyTxt.setText(company);
        phoneTxt.setText(phone);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
