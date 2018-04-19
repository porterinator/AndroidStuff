package merryweather.com.azurposconsumer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import merryweather.com.azurposservice.service.Post;
import merryweather.com.azurposservice.service.IAzurposService;


public class MainActivity extends AppCompatActivity {

    private IAzurposService mService;

    private boolean mIsBound;


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IAzurposService.Stub.asInterface(service);
            try {
                Post post = mService.getLastPost();
                if (post != null)
                Toast.makeText(MainActivity.this, post.id + " " + post.title + "\n" + post.body, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent("merryweather.com.azurposservice.service.iservice");
        intent.setPackage("merryweather.com.azurposservice");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConnection);
    }
}
