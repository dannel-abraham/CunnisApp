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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.prime.arab.ware.everythingutils.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class ControlCelosActivity extends Activity {
	
	private CelosStatsHelper statsHelper;
	
	private LinearLayout main_container;
	private TextView tvTitulo;
	private TextView textview52;
	private LinearLayout linear_general_stats;
	private TextView textview53;
	private LinearLayout linear_frecuencia_stats;
	private TextView textview54;
	private LinearLayout linear_top_hembras;
	private LinearLayout linear17;
	private LinearLayout linear_periodo_stats;
	private TextView textview55;
	private LinearLayout linear_ultimos_celos;
	private TextView textview56;
	private LinearLayout linear_hembras_sin_celos;
	private TextView textview57;
	private LinearLayout linear_celos_edad;
	private Button btn_por_anio;
	private Button btn_por_mes;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.control_celos);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main_container = findViewById(R.id.main_container);
		tvTitulo = findViewById(R.id.tvTitulo);
		textview52 = findViewById(R.id.textview52);
		linear_general_stats = findViewById(R.id.linear_general_stats);
		textview53 = findViewById(R.id.textview53);
		linear_frecuencia_stats = findViewById(R.id.linear_frecuencia_stats);
		textview54 = findViewById(R.id.textview54);
		linear_top_hembras = findViewById(R.id.linear_top_hembras);
		linear17 = findViewById(R.id.linear17);
		linear_periodo_stats = findViewById(R.id.linear_periodo_stats);
		textview55 = findViewById(R.id.textview55);
		linear_ultimos_celos = findViewById(R.id.linear_ultimos_celos);
		textview56 = findViewById(R.id.textview56);
		linear_hembras_sin_celos = findViewById(R.id.linear_hembras_sin_celos);
		textview57 = findViewById(R.id.textview57);
		linear_celos_edad = findViewById(R.id.linear_celos_edad);
		btn_por_anio = findViewById(R.id.btn_por_anio);
		btn_por_mes = findViewById(R.id.btn_por_mes);
		
		btn_por_anio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cargarCelosPorPeriodo("anio");
			}
		});
		
		btn_por_mes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cargarCelosPorPeriodo("mes");
			}
		});
	}
	
	private void initializeLogic() {
		statsHelper = new CelosStatsHelper(this);
		cargarTodosLosDatos();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (statsHelper != null) {
			statsHelper.close();
		}
		
	}
	public void _metodos() {
	}
	private void cargarTodosLosDatos() {
		cargarEstadisticasGenerales();
		cargarEstadisticasFrecuencia();
		cargarTopHembras();
		cargarCelosPorPeriodo("anio"); // Por defecto año
		cargarUltimosCelos();
		cargarHembrasSinCelos();
		cargarCelosPorEdad();
	}
	private void cargarEstadisticasGenerales() {
		linear_general_stats.removeAllViews();
		HashMap<String, Object> stats = statsHelper.getEstadisticasGenerales();
		
		String[] items = {
			"📋 Total de celos: " + stats.get("total_celos"),
			"👩 Hembras en celo: " + stats.get("hembras_celo"),
			"📅 Primer celo: " + stats.get("primer_celo"),
			"📅 Último celo: " + stats.get("ultimo_celo"),
			"🕐 Celos último mes: " + stats.get("celos_ultimo_mes")
		};
		
		for (String item : items) {
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(item);
			tv.setTextSize(14);
			tv.setPadding(8, 6, 8, 6);
			linear_general_stats.addView(tv);
		}
	}
	private void cargarEstadisticasFrecuencia() {
		linear_frecuencia_stats.removeAllViews();
		HashMap<String, Object> stats = statsHelper.getEstadisticasFrecuencia();
		
		String[] items = {
			"📊 Celos últimos 30 días: " + stats.get("celos_30_dias"),
			"📈 Celos últimos 60 días: " + stats.get("celos_60_dias"),
			"📋 Promedio celos/hembra: " + stats.get("promedio_celos_hembra")
		};
		
		for (String item : items) {
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(item);
			tv.setTextSize(14);
			tv.setPadding(8, 6, 8, 6);
			linear_frecuencia_stats.addView(tv);
		}
	}
	private void cargarTopHembras() {
		linear_top_hembras.removeAllViews();
		List<HashMap<String, String>> topHembras = statsHelper.getTopHembrasCelos();
		
		if (topHembras.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay datos de celos.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_top_hembras.addView(tv);
			return;
		}
		
		for (HashMap<String, String> hembra : topHembras) {
			String info = String.format(
			"🏆 %s (%s)\n" +
			"   Total celos: %s\n" +
			"   Primer celo: %s\n" +
			"   Último celo: %s",
			hembra.get("nombre"),
			hembra.get("identificador"),
			hembra.get("total_celos"),
			hembra.get("primer_celo"),
			hembra.get("ultimo_celo")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			tv.setBackgroundColor(0xFFFCE4EC);
			linear_top_hembras.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (topHembras.indexOf(hembra) < topHembras.size() - 1) {
				linear_top_hembras.addView(separator);
			}
		}
	}
	private void cargarCelosPorPeriodo(String periodo) {
		linear_periodo_stats.removeAllViews();
		List<HashMap<String, String>> datos = statsHelper.getCelosPorPeriodo(periodo);
		
		if (datos.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay datos para este período.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_periodo_stats.addView(tv);
			return;
		}
		
		for (HashMap<String, String> dato : datos) {
			String info = String.format(
			"📅 %s\n" +
			"   Total celos: %s\n" +
			"   Hembras: %s",
			dato.get("periodo"),
			dato.get("total_celos"),
			dato.get("hembras")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			linear_periodo_stats.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (datos.indexOf(dato) < datos.size() - 1) {
				linear_periodo_stats.addView(separator);
			}
		}
	}
	private void cargarUltimosCelos() {
		linear_ultimos_celos.removeAllViews();
		List<HashMap<String, String>> celos = statsHelper.getUltimosCelos(5); // 5 últimos
		
		if (celos.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay celos registrados.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_ultimos_celos.addView(tv);
			return;
		}
		
		for (HashMap<String, String> celo : celos) {
			String info = String.format(
			"📅 %s\n" +
			"   Hembra: %s (%s)\n" +
			"   Observaciones: %s",
			celo.get("fecha"),
			celo.get("hembra_nombre"),
			celo.get("hembra_identificador"),
			celo.get("observaciones") != null && !celo.get("observaciones").isEmpty() ? 
			celo.get("observaciones") : "Ninguna"
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			linear_ultimos_celos.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (celos.indexOf(celo) < celos.size() - 1) {
				linear_ultimos_celos.addView(separator);
			}
		}
	}
	private void cargarHembrasSinCelos() {
		linear_hembras_sin_celos.removeAllViews();
		List<HashMap<String, String>> hembras = statsHelper.getHembrasSinCelos();
		
		if (hembras.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("Todas las hembras han tenido celos.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_hembras_sin_celos.addView(tv);
			return;
		}
		
		for (HashMap<String, String> hembra : hembras) {
			String info = String.format(
			"👩 %s (%s)\n" +
			"   Nacimiento: %s",
			hembra.get("nombre"),
			hembra.get("identificador"),
			hembra.get("fecha_nacimiento")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			tv.setBackgroundColor(0xFFFFF3E0);
			linear_hembras_sin_celos.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (hembras.indexOf(hembra) < hembras.size() - 1) {
				linear_hembras_sin_celos.addView(separator);
			}
		}
	}
	private void cargarCelosPorEdad() {
		linear_celos_edad.removeAllViews();
		List<HashMap<String, String>> datos = statsHelper.getCelosPorEdadHembra();
		
		if (datos.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay datos por edad.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_celos_edad.addView(tv);
			return;
		}
		
		for (HashMap<String, String> dato : datos) {
			String info = String.format(
			"🎯 %s\n" +
			"   Total celos: %s\n" +
			"   Hembras: %s",
			dato.get("rango_edad"),
			dato.get("total_celos"),
			dato.get("hembras")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			linear_celos_edad.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (datos.indexOf(dato) < datos.size() - 1) {
				linear_celos_edad.addView(separator);
			}
		}
	}
	{
	}
	
}