package cu.dandroid.cunnis;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.prime.arab.ware.everythingutils.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import org.json.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class MainActivity extends Activity {
	
	final DatabaseHelper dbHelper = new DatabaseHelper(this);
	private GestorAlertas gestorAlertas;
	
	private ArrayList<String> sexo_options = new ArrayList<>();
	
	private LinearLayout linear3;
	private TextView textview9;
	private ImageView imageview3;
	private TextView textview7;
	private TextView textview8;
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
			} else {
				initializeLogic();
			}
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear3 = findViewById(R.id.linear3);
		textview9 = findViewById(R.id.textview9);
		imageview3 = findViewById(R.id.imageview3);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		progressBar = findViewById(R.id.progressBar);
	}
	
	private void initializeLogic() {
		// En tu actividad (SplashActivity o donde muestres la versión)
		try {
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			String versionName = pInfo.versionName;
			int versionCode = pInfo.versionCode;
			
			// Asignar la versión a tu TextView
			textview9.setText("v" + versionCode + "("+ versionName +")");
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		if (!FileUtil.isExistFile("/storage/emulated/0/Documents/conejos")) {
			FileUtil.makeDir("/storage/emulated/0/Documents/conejos");
			FileUtil.makeDir("/storage/emulated/0/Documents/conejos/fotos");
		}
		_verificarBaseDeDatos();
	}
	
	public void _verificarBaseDeDatos() {
		final boolean tieneRegistros = dbHelper.tieneRegistros();
		
		// Pequeña pausa para mostrar splash (opcional)
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent;
				
				if (tieneRegistros) {
					// Hay conejos registrados, ir a lista principal
					intent = new Intent(MainActivity.this, ConejosTodosActivity.class);
				} else {
					// No hay conejos, ir a pantalla para agregar el primero
					intent = new Intent(MainActivity.this, AgregarConejoActivity.class);
				}
				
				startActivity(intent);
				finish();
			}
		}, 1500); // 1.5 segundos de "splash"
	}
	
}