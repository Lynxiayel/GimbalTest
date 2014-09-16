package edu.mcgill.lynxiayel.gimbaltest;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	private ContextCoreConnector contextCoreConnector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contextCoreConnector = ContextCoreConnectorFactory.get(this);
		checkContextConnectorStatus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void checkContextConnectorStatus() {
		if (contextCoreConnector.isPermissionEnabled()) {
			// Gimbal is already enabled
		} else {
			contextCoreConnector.enable(this, new Callback<Void>() {

				@Override
				public void success(Void arg0) {
					// Gimbal is ready
				}

				@Override
				public void failure(int arg0, String arg1) {
					Log.e("failed to enable", arg1);
				}
			});
		}
	}
}
