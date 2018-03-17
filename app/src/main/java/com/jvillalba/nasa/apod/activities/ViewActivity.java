package com.jvillalba.nasa.apod.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jvillalba.nasa.apod.R;
import com.squareup.picasso.Picasso;

import model.NASA;

public class ViewActivity extends AppCompatActivity {

    private ImageView imageAPOD;
    private NASA nasaAPOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        nasaAPOD = (NASA) (bundle != null ? bundle.getSerializable("Nasa") : null);

        setDataNasaAPOD(nasaAPOD);

        imageAPOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, ImageActivity.class);
                intent.putExtra("HD_URL", nasaAPOD.getHdurl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.download, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, nasaAPOD.getTitle());
                i.putExtra(Intent.EXTRA_TEXT, nasaAPOD.getHdurl());
                startActivity(Intent.createChooser(i, "Share Nasa APOD"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDataNasaAPOD(NASA nasaAPOD) {
        imageAPOD = findViewById(R.id.imageAPOD);
        Picasso.with(this).load(nasaAPOD.getUrl()).error(R.mipmap.ic_launcher_foreground).fit().into(imageAPOD);

        TextView textTitle = findViewById(R.id.textTitle);
        textTitle.setText(getConcat(textTitle,nasaAPOD.getTitle()));

        TextView dateView = findViewById(R.id.textDate);
        dateView.setText(getConcat(dateView,nasaAPOD.getDate()));

        TextView copyright = findViewById(R.id.copyright);
        copyright.setText(getConcat(copyright,nasaAPOD.getCopyright()));

        TextView explanationView = findViewById(R.id.explanation);
        explanationView.setText(nasaAPOD.getExplanation());
        explanationView.setMovementMethod(new ScrollingMovementMethod());
    }

    @NonNull
    private String getConcat(TextView textView,String NasaData) {
        try {
            return (textView.getText().toString().concat(NasaData));
        }
        catch (Exception e)
        {
            return textView.getText().toString();
        }
    }
}
