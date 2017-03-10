package heqiang.tct.com.checkpermissiondemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button call_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call_btn = (Button) findViewById(R.id.callbtn);
        call_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CALL_PHONE,android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }else{
            call();
        }
    }

    public void call(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(permissions[0] == android.Manifest.permission.CALL_PHONE &&
                        grantResults.length > 1 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else{
                    Toast.makeText(MainActivity.this,"no all permissoin granted!",0).show();
                }
                break;
            default:
                break;
        }
    }
}



