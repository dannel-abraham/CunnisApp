package cu.dandroid.cunnis;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.prime.arab.ware.everythingutils.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import cu.dandroid.cunnis.StatisticsManager;


public class EstadisticasActivity extends Activity {
	
	final DatabaseHelper dbHelper = new DatabaseHelper(this);
	private StatisticsManager statsManager;
	
	private LinearLayout linear67;
	private ScrollView vscroll4;
	private ImageButton btnAtras;
	private TextView tvTitulo;
	private ImageButton btnActualizar;
	private LinearLayout linear68;
	private LinearLayout linear69;
	private LinearLayout linear75;
	private LinearLayout linear79;
	private LinearLayout linear80;
	private LinearLayout linear85;
	private ProgressBar progressBar;
	private TextView textview61;
	private LinearLayout linear70;
	private LinearLayout linear71;
	private LinearLayout linear72;
	private LinearLayout linear73;
	private LinearLayout linear74;
	private TextView textview62;
	private TextView tvTotalActivos;
	private TextView textview63;
	private TextView tvTotalMachos;
	private TextView textview64;
	private TextView tvTotalHembras;
	private TextView textview65;
	private TextView tvPesoPromedio;
	private TextView textview66;
	private TextView tvEdadPromedio;
	private TextView textview67;
	private LinearLayout linear76;
	private LinearLayout linear77;
	private LinearLayout linear78;
	private TextView textview68;
	private TextView tvCachorros;
	private TextView textview69;
	private TextView tvJovenes;
	private TextView textview70;
	private TextView tvAdultos;
	private TextView textview71;
	private LinearLayout layoutRazas;
	private TextView tvSinRazas;
	private TextView textview72;
	private LinearLayout linear81;
	private LinearLayout linear82;
	private LinearLayout linear83;
	private LinearLayout linear84;
	private TextView textview73;
	private TextView tvPesoMaximo;
	private TextView textview74;
	private TextView tvPesoMinimo;
	private TextView textview75;
	private TextView tvPesoPromedioMachos;
	private TextView textview76;
	private TextView tvPesoPromedioHembras;
	private TextView textview77;
	private LinearLayout linear86;
	private LinearLayout linear87;
	private LinearLayout linear88;
	private TextView textview78;
	private TextView tvTotalPartos;
	private TextView textview79;
	private TextView tvTotalCrias;
	private TextView textview80;
	private TextView tvPromedioCrias;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.estadisticas);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear67 = findViewById(R.id.linear67);
		vscroll4 = findViewById(R.id.vscroll4);
		btnAtras = findViewById(R.id.btnAtras);
		tvTitulo = findViewById(R.id.tvTitulo);
		btnActualizar = findViewById(R.id.btnActualizar);
		linear68 = findViewById(R.id.linear68);
		linear69 = findViewById(R.id.linear69);
		linear75 = findViewById(R.id.linear75);
		linear79 = findViewById(R.id.linear79);
		linear80 = findViewById(R.id.linear80);
		linear85 = findViewById(R.id.linear85);
		progressBar = findViewById(R.id.progressBar);
		textview61 = findViewById(R.id.textview61);
		linear70 = findViewById(R.id.linear70);
		linear71 = findViewById(R.id.linear71);
		linear72 = findViewById(R.id.linear72);
		linear73 = findViewById(R.id.linear73);
		linear74 = findViewById(R.id.linear74);
		textview62 = findViewById(R.id.textview62);
		tvTotalActivos = findViewById(R.id.tvTotalActivos);
		textview63 = findViewById(R.id.textview63);
		tvTotalMachos = findViewById(R.id.tvTotalMachos);
		textview64 = findViewById(R.id.textview64);
		tvTotalHembras = findViewById(R.id.tvTotalHembras);
		textview65 = findViewById(R.id.textview65);
		tvPesoPromedio = findViewById(R.id.tvPesoPromedio);
		textview66 = findViewById(R.id.textview66);
		tvEdadPromedio = findViewById(R.id.tvEdadPromedio);
		textview67 = findViewById(R.id.textview67);
		linear76 = findViewById(R.id.linear76);
		linear77 = findViewById(R.id.linear77);
		linear78 = findViewById(R.id.linear78);
		textview68 = findViewById(R.id.textview68);
		tvCachorros = findViewById(R.id.tvCachorros);
		textview69 = findViewById(R.id.textview69);
		tvJovenes = findViewById(R.id.tvJovenes);
		textview70 = findViewById(R.id.textview70);
		tvAdultos = findViewById(R.id.tvAdultos);
		textview71 = findViewById(R.id.textview71);
		layoutRazas = findViewById(R.id.layoutRazas);
		tvSinRazas = findViewById(R.id.tvSinRazas);
		textview72 = findViewById(R.id.textview72);
		linear81 = findViewById(R.id.linear81);
		linear82 = findViewById(R.id.linear82);
		linear83 = findViewById(R.id.linear83);
		linear84 = findViewById(R.id.linear84);
		textview73 = findViewById(R.id.textview73);
		tvPesoMaximo = findViewById(R.id.tvPesoMaximo);
		textview74 = findViewById(R.id.textview74);
		tvPesoMinimo = findViewById(R.id.tvPesoMinimo);
		textview75 = findViewById(R.id.textview75);
		tvPesoPromedioMachos = findViewById(R.id.tvPesoPromedioMachos);
		textview76 = findViewById(R.id.textview76);
		tvPesoPromedioHembras = findViewById(R.id.tvPesoPromedioHembras);
		textview77 = findViewById(R.id.textview77);
		linear86 = findViewById(R.id.linear86);
		linear87 = findViewById(R.id.linear87);
		linear88 = findViewById(R.id.linear88);
		textview78 = findViewById(R.id.textview78);
		tvTotalPartos = findViewById(R.id.tvTotalPartos);
		textview79 = findViewById(R.id.textview79);
		tvTotalCrias = findViewById(R.id.tvTotalCrias);
		textview80 = findViewById(R.id.textview80);
		tvPromedioCrias = findViewById(R.id.tvPromedioCrias);
		
		btnAtras.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		btnActualizar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_actualizarUI(_obtenerEstadisticasCompletas());
			}
		});
	}
	
	private void initializeLogic() {
		_initi();
		_actualizarUI(_obtenerEstadisticasCompletas());
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_cargarEstadisticasAsync();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		_cerrarManager("");
		/*
_cerrarManager(statsManagerRef);
*/
	}
	
	public String _formatearEdad(final double _dias) {
		if (statsManager instanceof StatisticsManager) {
			StatisticsManager manager = (StatisticsManager) statsManager;
			//return manager.formatAge(_dias);
			return manager.formatAge((long) _dias);
		}
		return _dias + " días";
		
	}
	
	
	public void _cerrarManager(final String _managerRef) {
		if (statsManager instanceof StatisticsManager) {
			StatisticsManager manager = (StatisticsManager) statsManager;
			manager.close();
			statsManager = null;
		}
	}
	
	
	public void _cargarEstadisticasAsync() {
		// Mostrar progress
		if (progressBar != null) {
			progressBar.setVisibility(View.VISIBLE);
		}
		
		// En hilo
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					final HashMap<String, Object> todasStats = _obtenerEstadisticasCompletas();
					
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_actualizarUI(todasStats);
							if (progressBar != null) {
								progressBar.setVisibility(View.GONE);
							}
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (progressBar != null) {
								progressBar.setVisibility(View.GONE);
							}
							Toast.makeText(EstadisticasActivity.this, 
							"Error al cargar estadísticas", Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		}).start();
	}
	
	
	public HashMap<String, Object> _obtenerEstadisticasCompletas() {
		if (statsManager instanceof StatisticsManager) {
			StatisticsManager manager = (StatisticsManager) statsManager;
			
			// DEPURACIÓN: Agrega este Toast para verificar
			Toast.makeText(this, "Obteniendo estadísticas...", Toast.LENGTH_SHORT).show();
			
			HashMap<String, Object> datos = manager.getAllStats();
			
			// DEPURACIÓN: Verifica si se obtuvieron datos
			if (datos != null && !datos.isEmpty()) {
				Toast.makeText(this, 
				"Datos obtenidos (" + datos.size() + " categorías)", 
				Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, 
				"Map de datos VACÍO - Base de datos vacía?", 
				Toast.LENGTH_LONG).show();
			}
			
			return datos;
		} else {
			Toast.makeText(this, 
			"ERROR: Manager NO inicializado", 
			Toast.LENGTH_LONG).show();
		}
		return new HashMap<String, Object>();
	}
	
	
	public void _actualizarUI(final HashMap<String, Object> _todasts) {
		try {
			// Obtener cada sección (casting)
			HashMap<String, Object> basicStats = (HashMap<String, Object>) _todasts.get("basic");
			HashMap<String, Object> weightStats = (HashMap<String, Object>) _todasts.get("weight");
			HashMap<String, Object> ageStats = (HashMap<String, Object>) _todasts.get("age");
			HashMap<String, Object> reproStats = (HashMap<String, Object>) _todasts.get("reproduction");
			ArrayList<HashMap<String, Object>> breedStats = (ArrayList<HashMap<String, Object>>) _todasts.get("breeds");
			
			// 1. Actualizar estadísticas básicas
			if (basicStats != null) {
				if (tvTotalActivos != null) tvTotalActivos.setText(basicStats.get("total").toString());
				if (tvTotalMachos != null) tvTotalMachos.setText(basicStats.get("males").toString());
				if (tvTotalHembras != null) tvTotalHembras.setText(basicStats.get("females").toString());
			}
			
			// 2. Actualizar estadísticas de peso
			if (weightStats != null) {
				if (tvPesoPromedio != null) tvPesoPromedio.setText(weightStats.get("average").toString() + " lb");
				if (tvPesoMaximo != null) tvPesoMaximo.setText(weightStats.get("max").toString() + " lb");
				if (tvPesoMinimo != null) tvPesoMinimo.setText(weightStats.get("min").toString() + " lb");
				if (tvPesoPromedioMachos != null) tvPesoPromedioMachos.setText(weightStats.get("averageMales").toString() + " lb");
				if (tvPesoPromedioHembras != null) tvPesoPromedioHembras.setText(weightStats.get("averageFemales").toString() + " lb");
			}
			
			// 3. Actualizar estadísticas de edad
			if (ageStats != null) {
				// Formatear edad
				long avgDays = Long.parseLong(ageStats.get("averageDays").toString());
				String edadFormateada = _formatearEdad(avgDays);
				if (tvEdadPromedio != null) tvEdadPromedio.setText(edadFormateada);
				
				if (tvCachorros != null) tvCachorros.setText(ageStats.get("babies").toString());
				if (tvJovenes != null) tvJovenes.setText(ageStats.get("young").toString());
				if (tvAdultos != null) tvAdultos.setText(ageStats.get("adults").toString());
			}
			
			// 4. Actualizar estadísticas de reproducción
			if (reproStats != null) {
				if (tvTotalPartos != null) tvTotalPartos.setText(reproStats.get("totalBirths").toString());
				if (tvTotalCrias != null) tvTotalCrias.setText(reproStats.get("totalOffspring").toString());
				if (tvPromedioCrias != null) tvPromedioCrias.setText(reproStats.get("averageOffspring").toString());
			}
			
			// 5. Actualizar distribución por razas
			if (breedStats != null) {
				_actualizarRazasUI(breedStats);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public void _actualizarRazasUI(final ArrayList<HashMap<String, Object>> _razasList) {
		// Limpiar layout
		if (layoutRazas != null) {
			layoutRazas.removeAllViews();
			
			if (_razasList == null || _razasList.isEmpty()) {
				if (tvSinRazas != null) {
					tvSinRazas.setVisibility(View.VISIBLE);
				}
				return;
			}
			
			if (tvSinRazas != null) {
				tvSinRazas.setVisibility(View.GONE);
			}
			
			for (HashMap<String, Object> razaInfo : _razasList) {
				String nombre = (String) razaInfo.get("name");
				String cantidad = razaInfo.get("count").toString();
				String porcentaje = (String) razaInfo.get("percentage");
				
				// Crear layout para cada raza
				LinearLayout fila = new LinearLayout(this);
				fila.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
				));
				fila.setOrientation(LinearLayout.HORIZONTAL);
				fila.setPadding(0, 8, 0, 8);
				
				// TextView para nombre
				TextView tvNombre = new TextView(this);
				tvNombre.setLayoutParams(new LinearLayout.LayoutParams(
				0,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1
				));
				tvNombre.setText(nombre);
				tvNombre.setTextColor(0xFF666666);
				tvNombre.setTextSize(14);
				
				// TextView para cantidad
				TextView tvCantidad = new TextView(this);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
				);
				params.setMargins(16, 0, 0, 0);
				tvCantidad.setLayoutParams(params);
				tvCantidad.setText(cantidad + " (" + porcentaje + "%)");
				tvCantidad.setTextColor(0xFF4CAF50);
				tvCantidad.setTextSize(14);
				tvCantidad.setTypeface(null, android.graphics.Typeface.BOLD);
				
				fila.addView(tvNombre);
				fila.addView(tvCantidad);
				layoutRazas.addView(fila);
			}
		}
	}
	
	
	public void _initi() {
		statsManager = new StatisticsManager(this);
	}
	
}