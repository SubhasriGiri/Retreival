package alumni.cit.edu.in.retreival.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import alumni.cit.edu.in.retreival.DetailActivity;
import alumni.cit.edu.in.retreival.R;
import alumni.cit.edu.in.retreival.m_Model.Alumni;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/21/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 * 1. where WE INFLATE OUR MODEL LAYOUT INTO VIEW ITEM
 * 2. THEN BIND DATA
 */
public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Alumni> alumnis;

    public CustomAdapter(Context c, ArrayList<Alumni> alumnis) {
        this.c = c;
        this.alumnis = alumnis;
    }

    @Override
    public int getCount() {
        return alumnis.size();
    }

    @Override
    public Object getItem(int pos) {
        return alumnis.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        TextView nameTxt= (TextView) convertView .findViewById(R.id.nameTxt);
        TextView emailTxt= (TextView) convertView.findViewById(R.id.emailTxt);
        TextView companyTxt= (TextView) convertView.findViewById(R.id.companyTxt);
        TextView contactTxt= (TextView) convertView.findViewById(R.id.contactTxt);


        final Alumni s= (Alumni) this.getItem(position);

        nameTxt.setText(s.getName());
        emailTxt.setText(s.getEmail());
        companyTxt.setText(s.getCompany());
        contactTxt.setText(s.getContact());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getName(),s.getEmail(),s.getCompany(),s.getContact());
            }
        });

        return convertView;
    }
    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c,DetailActivity.class);
        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("EMAIL_KEY",details[1]);
        i.putExtra("COMPANY_KEY",details[2]);
        i.putExtra("CONTACT_KEY",details[3]);

        c.startActivity(i);
    }
}














