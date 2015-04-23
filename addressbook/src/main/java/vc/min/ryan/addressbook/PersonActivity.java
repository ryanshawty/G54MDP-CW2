package vc.min.ryan.addressbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ryan on 26/03/2015.
 */
public class PersonActivity extends Activity {

    private final String TAG = "PersonActivity";
    private BookManager mBookManager;
    private ImageView mPicture;

    private TextView mFirstName;
    private TextView mLastName;
    private TextView mPhone;
    private TextView mEmail;
    private ImageButton mBPhone;
    private ImageButton mBEmail;

    private Context mContext;
    @Override
    public void onCreate(Bundle instance){
        super.onCreate(instance);
        mContext = this;
        setContentView(R.layout.person_info);

        Intent intent = getIntent();
        int id = intent.getIntExtra("personId", -1);

        if(id == -1){
            Toast.makeText(this, "Something has gone wrong", Toast.LENGTH_LONG).show();
            return;
        }

        mPicture = (ImageView) findViewById(R.id.piPhoto);
        mFirstName = (TextView) findViewById(R.id.piFirstName);
        mLastName = (TextView) findViewById(R.id.piLastName);
        mPhone = (TextView) findViewById(R.id.piNumber);
        mEmail = (TextView) findViewById(R.id.piEmail);
        mBPhone = (ImageButton) findViewById(R.id.piBPhone);

        mBookManager = new BookManager(this);

        final Person person = mBookManager.getPerson(id);
        if(person.getPhotoPath() != null)
            mPicture.setImageBitmap(person.getPhotoBM());

        mFirstName.setText(person.getFirstName());
        mLastName.setText(person.getLastName());
        mPhone.setText(person.getPhoneNumber());
        mEmail.setText(person.getEmail());

        mBPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + person.getPhoneNumber()));
                startActivity(intent);
            }
        });
    }
}
