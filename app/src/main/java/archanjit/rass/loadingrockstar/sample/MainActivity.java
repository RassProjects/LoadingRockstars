package archanjit.rass.loadingrockstar.sample;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import archanjit.rass.rockstarsloader.RockStars;
import archanjit.rass.rockstarsloader.RockStarsDialogClass;

public class MainActivity extends AppCompatActivity {
Button bbt1,bt2;
    RockStarsDialogClass dialogClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bbt1=(Button)findViewById(R.id.btn1);
        bt2=(Button)findViewById(R.id.btn2);
        bbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow(v);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new AsyncDialog().execute();
            }
        });

    }
    public void dialogShow(View v){
        RockStarsDialogClass dialogClass=RockStarsDialogClass.newInstance(this);
             dialogClass.setAnimType(RockStars.CUBE_RED);
        dialogClass.setMessage("Hi All Rockstars");
        dialogClass.cancelableOnTouch(true).show();
    }
    public class AsyncDialog extends AsyncTask<Void, String, Void> implements DialogInterface.OnCancelListener {


        @Override
        protected void onPreExecute() {
            dialogClass=RockStarsDialogClass.newInstance(MainActivity.this);
            dialogClass.setAnimType(RockStars.MAIL_LOAD_MAGENTA);
            dialogClass.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                publishProgress("Connecting");
                Thread.sleep(2000);
                publishProgress("Downloading");
                Thread.sleep(5000);
                publishProgress("Done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            dialogClass.setMessage(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            dialogClass.dismiss();
            super.onPostExecute(result);
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            this.cancel(true);
            dialogClass.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
