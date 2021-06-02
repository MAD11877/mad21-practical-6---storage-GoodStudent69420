package sg.edu.np.s10207996;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    User user1 = new User("MAD",
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin scelerisque facilisis orci in scelerisque. Nam sollicitudin, sem at semper egestas, erat nunc mattis turpis, quis efficitur ligula est cursus risus. Ut varius dapibus nulla at porta. Aenean vel est et est dapibus commodo nec eu tortor. Etiam blandit lacus erat, ac facilisis odio posuere ut. Vivamus a porta ex, vitae bibendum velit. Aliquam sodales arcu a leo pharetra tristique.",
//            1, false);
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
//        Intent in = getIntent();
//        String profileNum = in.getStringExtra("idNum");
//
//        TextView nameTxt = findViewById(R.id.nameTxt);
//        nameTxt.setText("MAD " + profileNum);
//
//        TextView desTxt = findViewById(R.id.desTxt);
//        desTxt.setText(user.getDescription());

        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String description = in.getStringExtra("description");
        int id = in.getIntExtra("id",0);
        boolean value = in.getExtras().getBoolean("value");
        user = new User(name, description, id, value);

        TextView nameTxt = findViewById(R.id.nameTxt);
        nameTxt.setText(user.getName());

        TextView desTxt = findViewById(R.id.desTxt);
        desTxt.setText(user.getDescription());

        Button followBtn = findViewById(R.id.followBtn);

        if (user.getFollowed() == false){
            followBtn.setText("Follow");
        }
        else{followBtn.setText("Unfollow");}

        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.Change();
                db.updateUser(user);
                if (user.getFollowed() == false){
                    followBtn.setText("Follow");
                    Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
                }
                else{
                    followBtn.setText("Unfollow");
                    Toast.makeText(getApplicationContext(),"Followed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Log.d("debug", "create");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("debug", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "restart");
    }
}