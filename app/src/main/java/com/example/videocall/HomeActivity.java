package com.example.videocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    GoogleSignInClient googleSignInClient;

    //defining permission codes
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int RECORD_AUDIO_PERMISSION_CODE = 102;
    private static final int MODIFY_AUDIO_SETTINGS_PERMISSION_CODE = 103;
    private static final int ACCESS_NETWORK_STATE_PERMISSION_CODE = 104;
    private static final int BLUETOOTH_PERMISSION_CODE = 105;
    private static final int READ_PHONE_STATE_PERMISSION_CODE = 106;
    private static final int WAKE_LOCK_PERMISSION_CODE = 107;
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 108;
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 109;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.MODIFY_AUDIO_SETTINGS) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.MODIFY_AUDIO_SETTINGS}, MODIFY_AUDIO_SETTINGS_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, ACCESS_NETWORK_STATE_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.BLUETOOTH}, BLUETOOTH_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.WAKE_LOCK}, WAKE_LOCK_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navigationHeader = navigationView.getHeaderView(0);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        ImageView userProfileImage = navigationHeader.findViewById(R.id.user_profile);
        TextView userName = navigationHeader.findViewById(R.id.user_name);
        TextView userEmail = navigationHeader.findViewById(R.id.user_email);


        ImageView home_user_image = findViewById(R.id.user_profile_home);
        TextView home_user_name = findViewById(R.id.user_name_home);
        ImageView join_video = findViewById(R.id.image_join_video);

        join_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoactivity = new Intent(HomeActivity.this, VideoCallActivity.class);
                startActivity(videoactivity);
                finish();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            //String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            //String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            userName.setText(personName);
            userEmail.setText(personEmail);
            //Glide.with(this).load(String.valueOf(personPhoto)).into(userProfileImage);

            home_user_name.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(home_user_image);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Audio Record Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Audio Record Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == MODIFY_AUDIO_SETTINGS_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Audio Settings Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Audio Settings Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == ACCESS_NETWORK_STATE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Network Access Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Network Access Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == BLUETOOTH_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Bluetooth Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Bluetooth Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == READ_PHONE_STATE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Phone state Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Phone State Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == WAKE_LOCK_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Lock Screen Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Lock Screen Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Storage read Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Storage read Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this,
                        "Storage Write Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(HomeActivity.this,
                        "Storage Write Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                signOut();
                break;
            default:
                Toast.makeText(this, menuItem.toString(), Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent signin = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(signin);
                        finish();
                    }
                });
    }
}