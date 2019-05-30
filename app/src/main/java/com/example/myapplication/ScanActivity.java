package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.FileNotFoundException;
public class ScanActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Barcode Scanner API";
    private static final int PHOTO_REQUEST = 10;
    private DatabaseReference mdata;
    private TextView scanResults;
    private TextView scanResults_ID;
    private TextView scanResults_Date;
    private TextView scanResults_BillAmount;
    private BarcodeDetector detector;
    private Uri imageUri;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_RESULT = "result";
    ImageButton BtnAccount, BtnHome, BtnOrder, BtnMap;
    Button BtnName,rank;
    String IDUser, fbName;
    ImageButton BtnAvartar,BtnIconStar;
    Button BtnRank,BtnPoint;
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.scan);
        if (getIntent().getStringExtra("IDUser") != null) {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        BtnName = (Button) findViewById(R.id.button6);
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
            BtnName.setText(fbName);
        }
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("customer").child(IDUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rank = (Button) findViewById(R.id.button7);
                rank.setText("Thành viên "+dataSnapshot.child("rank").getValue().toString()+"-"+ dataSnapshot.child("point").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button btnquet = (Button) findViewById(R.id.btnquet);
        scanResults = (TextView) findViewById(R.id.scan_results);
        scanResults_ID = (TextView) findViewById(R.id.txtMabill);
        scanResults_Date = (TextView) findViewById(R.id.txtNgayinbill);
        scanResults_BillAmount = (TextView) findViewById(R.id.txtTongbill);


        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        btnquet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19);
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, account_Activity.class);
                intent.putExtra("fbName", fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18);
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, MainActivity.class);
                intent.putExtra("fbName", fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.imageButton21);
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, DatHang_Activity.class);
                intent.putExtra("fbName", fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20);
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, Map_Activity.class);
                intent.putExtra("fbName", fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnAvartar = (ImageButton) findViewById(R.id.imageButton11) ;
        BtnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnName = (Button) findViewById(R.id.button6) ;
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnPoint = (Button) findViewById(R.id.button8) ;
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(ScanActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
//            launchMediaScanIntent();
//            try {
//                Bitmap bitmap = decodeBitmapUri(this, imageUri);
//                if (detector.isOperational() && bitmap != null) {
//                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//                    SparseArray<Barcode> barcodes = detector.detect(frame);
//                    for (int index = 0; index < barcodes.size(); index++) {
//                        Barcode code = barcodes.valueAt(index);
//                        String[] output = ((String)(scanResults.getText())).split("-");
//                        scanResults.setText(scanResults.getText() + code.displayValue + "\n");
//
//////                        scanResults_ID.setText(output[0] + code.displayValue + "\n");
//////                        scanResults_Date.setText(output[1] + code.displayValue + "\n");
//////                        scanResults_BillAmount.setText(output[2] + code.displayValue + "\n");
//                        Intent billIntent = new Intent(ScanActivity.this,Bill_n_voucher.class);
//                        billIntent.putExtra("IDBill", output[0]);
//                        billIntent.putExtra("billAmount", output[2]);
//                        billIntent.putExtra("date",output[1]);
//                        billIntent.putExtra("IDUser",IDUser);
//                        billIntent.putExtra("fbName",fbName);
//                        startActivity(billIntent);
//                        //Required only if you need to extract the type of barcode
//                        int type = barcodes.valueAt(index).valueFormat;
//                        switch (type) {
//                            case Barcode.CONTACT_INFO:
//                                Log.i(LOG_TAG, code.contactInfo.title);
//                                break;
//                            case Barcode.EMAIL:
//                                Log.i(LOG_TAG, code.email.address);
//                                break;
//                            case Barcode.ISBN:
//                                Log.i(LOG_TAG, code.rawValue);
//                                break;
//                            case Barcode.PHONE:
//                                Log.i(LOG_TAG, code.phone.number);
//                                break;
//                            case Barcode.PRODUCT:
//                                Log.i(LOG_TAG, code.rawValue);
//                                break;
//                            case Barcode.SMS:
//                                Log.i(LOG_TAG, code.sms.message);
//                                break;
//                            case Barcode.TEXT:
//                                Log.i(LOG_TAG, code.rawValue);
//                                break;
//                            case Barcode.URL:
//                                Log.i(LOG_TAG, "url: " + code.url.url);
//                                break;
//                            case Barcode.WIFI:
//                                Log.i(LOG_TAG, code.wifi.ssid);
//                                break;
//                            case Barcode.GEO:
//                                Log.i(LOG_TAG, code.geoPoint.lat + ":" + code.geoPoint.lng);
//                                break;
//                            case Barcode.CALENDAR_EVENT:
//                                Log.i(LOG_TAG, code.calendarEvent.description);
//                                break;
//                            case Barcode.DRIVER_LICENSE:
//                                Log.i(LOG_TAG, code.driverLicense.licenseNumber);
//                                break;
//                            default:
//                                Log.i(LOG_TAG, code.rawValue);
//                                break;
//                        }
//                    }
//                    if (barcodes.size() == 0) {
//                        scanResults.setText("Scan Failed: Found nothing to scan");
//                    }
//                } else {
//                    scanResults.setText("Could not set up the detector!");
//                }
//            } catch (Exception e) {
//                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT)
//                        .show();
//                Log.e(LOG_TAG, e.toString());
//            }
//        }
//    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture.jpg");
        imageUri = FileProvider.getUriForFile(ScanActivity.this,
                BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (imageUri != null) {
            outState.putString(SAVED_INSTANCE_URI, imageUri.toString());
            outState.putString(SAVED_INSTANCE_RESULT, scanResults.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }

    protected void startQrCodeScanner() {
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);

        } catch (Exception e) {
            //this.showToast(e.getMessage());
            String[] output = e.getMessage().split("-");
            this.showToast(output[2]);
            scanResults.setText(e.getMessage());
            scanResults_ID.setText(output[0]);
            scanResults_Date.setText(output[1]);
            scanResults_BillAmount.setText(output[2]);
            setContentView(R.layout.bill_pickvoucher);
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    protected void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
            else {
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ScanActivity.this, Bill_n_voucher.class);
                intent.putExtra("fbName", fbName);
                intent.putExtra("IDUser", IDUser);
                intent.putExtra("QR", result.getContents());
                startActivity(intent);
            }
        }
            else{
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }
