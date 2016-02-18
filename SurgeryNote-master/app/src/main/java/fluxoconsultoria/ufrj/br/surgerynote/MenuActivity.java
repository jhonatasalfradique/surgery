package fluxoconsultoria.ufrj.br.surgerynote;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoProfile;
import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoSurgery;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Profile;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;


public class MenuActivity extends ActionBarActivity
{

    private Button profileButton, surgeriesButton, newSurgeryButton, mediaButton, calendarButton, uploadButton;

    private DaoSurgery daoSurgery = new DaoSurgery();
    private DaoProfile daoProfile = new DaoProfile();

    private List<Surgery> surgeries;
    private Profile profile;

    private IInAppBillingService mService;
    private ServiceConnection mServiceConn;
    private final String inAppId = "android.test.purchased"; //replace this with your in-app product id

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.mServiceConn = new ServiceConnection()
        {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder)
            {
                mService = IInAppBillingService.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName)
            {
                mService = null;
            }
        };

        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);

        this.profileButton = (Button) findViewById(R.id.profileButton);
        this.surgeriesButton = (Button) findViewById(R.id.surgeriesButton);
        this.newSurgeryButton = (Button) findViewById(R.id.newSurgeryButton);
        this.mediaButton = (Button) findViewById(R.id.mediaButton);
        this.calendarButton = (Button) findViewById(R.id.calendarButton);
        this.uploadButton = (Button) findViewById(R.id.uploadButton);

        this.profileButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (toast != null)
                {
                    toast.cancel();
                }
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.surgeriesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (toast != null)
                {
                    toast.cancel();
                }
                Intent intent = new Intent(MenuActivity.this, SurgeryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.newSurgeryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (toast != null)
                {
                    toast.cancel();
                }

                surgeries = new ArrayList<>();
                if (daoSurgery.getAll() != null)
                {
                    surgeries = daoSurgery.getAll();
                }

                profile = daoProfile.getProfile();

                if (profile != null)
                {
//                    Commented code: Premium subscription
//
//                    if (surgeries.size() == 5 && !profile.isSubscribed())
//                    {
//                        requestPayment();
//                    }
//                    else
//                    {
//                        Intent intent = new Intent(MenuActivity.this, NewSurgeryActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                        startActivity(intent);
//                    }

                    Intent intent = new Intent(MenuActivity.this, NewSurgeryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                else
                {
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "You must have a profile to register a new surgery.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        this.mediaButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (toast != null)
                {
                    toast.cancel();
                }
                Intent intent = new Intent(MenuActivity.this, SurgeryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("MEDIA_MODE", true);
                startActivity(intent);
            }
        });

        this.calendarButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (toast != null)
                {
                    toast.cancel();
                }
                Intent intent = new Intent(MenuActivity.this, CalendarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.uploadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (toast != null)
                {
                    toast.cancel();
                }
                Intent intent = new Intent(MenuActivity.this, SurgeryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("UPLOAD_MODE", true);
                startActivity(intent);
            }
        });
    }

    private void requestPayment()
    {
        ArrayList<String> skuList = new ArrayList<String>();
        skuList.add(inAppId);
        Bundle querySkus = new Bundle();
        querySkus.putStringArrayList("ITEM_ID_LIST", skuList);

        Bundle skuDetails;

        try
        {
            skuDetails = mService.getSkuDetails(3, getPackageName(), "inapp", querySkus);

            int response = skuDetails.getInt("RESPONSE_CODE");
            if (response == 0)
            {
                ArrayList<String> responseList = skuDetails.getStringArrayList("DETAILS_LIST");

                for (String thisResponse : responseList)
                {
                    JSONObject object = new JSONObject(thisResponse);
                    String sku = object.getString("productId");
                    String price = object.getString("price");

                    if (sku.equals(inAppId))
                    {
                        Log.d("Price", price);

                        Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(), sku, "inapp",
                                                                       "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");

                        PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");

                        startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(),
                                                   Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
                    }
                }
            }
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IntentSender.SendIntentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
//            Toast.makeText(getApplicationContext(), "Already Purchased", "You have already purchased that item. Please contact support.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1001)
        {
            int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
            String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
            String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");

            if (resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject jo = new JSONObject(purchaseData);
                    String sku = jo.getString("productId");
                    Toast.makeText(this, "You have bought the " + sku + ". Excellent choice, adventurer!", Toast.LENGTH_SHORT).show();

                    profile = daoProfile.getProfile();
                    profile.setSubscribed(true);
                    daoProfile.update(profile);
                }
                catch (JSONException e)
                {
                    Toast.makeText(this, "Failed to parse purchase data.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mService != null)
        {
            unbindService(mServiceConn);
        }
    }
}
