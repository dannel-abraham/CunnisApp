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

public class ControlPartosActivity extends Activity {
	
	private PartosStatsHelper statsHelper;
	
	private LinearLayout main_container;
	private TextView tvTitulo;
	private TextView textview34;
	private LinearLayout linear_general_stats;
	private TextView textview35;
	private LinearLayout linear_top_hembras;
	private LinearLayout linear14;
	private LinearLayout linear_periodo_stats;
	private TextView textview36;
	private LinearLayout linear_padres_stats;
	private TextView textview37;
	private LinearLayout linear_ultimos_partos;
	private TextView textview38;
	private LinearLayout linear_hembras_sin_partos;
	private TextView textview39;
	private LinearLayout linear_raza_stats;
	private Button btn_por_anio;
	private Button btn_por_mes;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.control_partos);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main_container = findViewById(R.id.main_container);
		tvTitulo = findViewById(R.id.tvTitulo);
		textview34 = findViewById(R.id.textview34);
		linear_general_stats = findViewById(R.id.linear_general_stats);
		textview35 = findViewById(R.id.textview35);
		linear_top_hembras = findViewById(R.id.linear_top_hembras);
		linear14 = findViewById(R.id.linear14);
		linear_periodo_stats = findViewById(R.id.linear_periodo_stats);
		textview36 = findViewById(R.id.textview36);
		linear_padres_stats = findViewById(R.id.linear_padres_stats);
		textview37 = findViewById(R.id.textview37);
		linear_ultimos_partos = findViewById(R.id.linear_ultimos_partos);
		textview38 = findViewById(R.id.textview38);
		linear_hembras_sin_partos = findViewById(R.id.linear_hembras_sin_partos);
		textview39 = findViewById(R.id.textview39);
		linear_raza_stats = findViewById(R.id.linear_raza_stats);
		btn_por_anio = findViewById(R.id.btn_por_anio);
		btn_por_mes = findViewById(R.id.btn_por_mes);
		
		btn_por_anio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cargarPartosPorPeriodo("anio");
			}
		});
		
		btn_por_mes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cargarPartosPorPeriodo("mes");
			}
		});
	}
	
	private void initializeLogic() {
		statsHelper = new PartosStatsHelper(this);
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
	private void cargarEstadisticasGenerales() {
		linear_general_stats.removeAllViews();
		HashMap<String, Object> stats = statsHelper.getEstadisticasGenerales();
		
		String[] items = {
			"📋 Total de partos: " + stats.get("total_partos"),
			"🐇 Total de crías nacidas: " + stats.get("total_crias"),
			"✅ Crías vivas: " + stats.get("crias_vivas"),
			"❌ Crías muertas: " + stats.get("crias_muertas"),
			"📈 Supervivencia: " + stats.get("porcentaje_supervivencia"),
			"📊 Promedio por parto: " + stats.get("promedio_crias"),
			"👩 Hembras paridas: " + stats.get("hembras_paridas")
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
	private void cargarPartosPorPeriodo(String periodo) {
		linear_periodo_stats.removeAllViews();
		List<HashMap<String, String>> datos = statsHelper.getPartosPorPeriodo(periodo);
		
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
			"   Partos: %s | Crías: %s (%s vivas)\n" +
			"   Supervivencia: %s",
			dato.get("periodo"),
			dato.get("partos"),
			dato.get("crias_totales"),
			dato.get("crias_vivas"),
			dato.get("porcentaje")
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
	private void cargarPadresProductivos() {
		linear_padres_stats.removeAllViews();
		List<HashMap<String, String>> padres = statsHelper.getEstadisticasPadres();
		
		if (padres.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay datos de padres.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_padres_stats.addView(tv);
			return;
		}
		
		for (HashMap<String, String> padre : padres) {
			String info = String.format(
			"👑 %s (%s)\n" +
			"   Partos: %s | Crías: %s",
			padre.get("nombre"),
			padre.get("identificador"),
			padre.get("partos"),
			padre.get("crias")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			tv.setBackgroundColor(0xFFE3F2FD);
			linear_padres_stats.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (padres.indexOf(padre) < padres.size() - 1) {
				linear_padres_stats.addView(separator);
			}
		}
	}
	private void cargarUltimosPartos() {
		linear_ultimos_partos.removeAllViews();
		List<HashMap<String, String>> partos = statsHelper.getUltimosPartos(5); // 5 últimos
		
		if (partos.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay partos registrados.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_ultimos_partos.addView(tv);
			return;
		}
		
		for (HashMap<String, String> parto : partos) {
			String info = String.format(
			"📅 %s\n" +
			"   Madre: %s\n" +
			"   Padre: %s\n" +
			"   Crías: %s totales, %s vivas",
			parto.get("fecha"),
			parto.get("madre") != null ? parto.get("madre") : "No especificada",
			parto.get("padre") != null ? parto.get("padre") : "No especificado",
			parto.get("crias_totales"),
			parto.get("crias_vivas")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			linear_ultimos_partos.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (partos.indexOf(parto) < partos.size() - 1) {
				linear_ultimos_partos.addView(separator);
			}
		}
	}
	private void cargarHembrasSinPartos() {
		linear_hembras_sin_partos.removeAllViews();
		List<HashMap<String, String>> hembras = statsHelper.getHembrasSinPartos();
		
		if (hembras.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("Todas las hembras han tenido partos.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_hembras_sin_partos.addView(tv);
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
			tv.setBackgroundColor(0xFFFFEBEE);
			linear_hembras_sin_partos.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (hembras.indexOf(hembra) < hembras.size() - 1) {
				linear_hembras_sin_partos.addView(separator);
			}
		}
	}
	private void cargarEstadisticasPorRaza() {
		linear_raza_stats.removeAllViews();
		List<HashMap<String, String>> razas = statsHelper.getPartosPorRaza();
		
		if (razas.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay datos por raza.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_raza_stats.addView(tv);
			return;
		}
		
		for (HashMap<String, String> raza : razas) {
			String info = String.format(
			"🎯 %s\n" +
			"   Partos: %s | Crías: %s (%s vivas)\n" +
			"   Supervivencia: %s",
			raza.get("raza"),
			raza.get("partos"),
			raza.get("crias_totales"),
			raza.get("crias_vivas"),
			raza.get("porcentaje")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			linear_raza_stats.addView(tv);
			
			// Separador
			View separator = new View(this);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			1
			));
			separator.setBackgroundColor(0xFFCCCCCC);
			if (razas.indexOf(raza) < razas.size() - 1) {
				linear_raza_stats.addView(separator);
			}
		}
	}
	private void cargarTodosLosDatos() {
		cargarEstadisticasGenerales();
		cargarTopHembras();
		cargarPartosPorPeriodo("anio"); // Por defecto año
		cargarPadresProductivos();
		cargarUltimosPartos();
		cargarHembrasSinPartos();
		cargarEstadisticasPorRaza();
	}
	private void cargarTopHembras() {
		linear_top_hembras.removeAllViews();
		List<HashMap<String, String>> topHembras = statsHelper.getTopHembrasPartos();
		
		if (topHembras.isEmpty()) {
			TextView tv = new TextView(this);
			tv.setText("No hay datos de partos.");
			tv.setTextSize(14);
			tv.setPadding(8, 8, 8, 8);
			linear_top_hembras.addView(tv);
			return;
		}
		
		for (HashMap<String, String> hembra : topHembras) {
			String info = String.format(
			"🏆 %s (%s)\n" +
			"   Partos: %s | Crías: %s (%s vivas)\n" +
			"   Supervivencia: %s",
			hembra.get("nombre"),
			hembra.get("identificador"),
			hembra.get("partos"),
			hembra.get("crias_totales"),
			hembra.get("crias_vivas"),
			hembra.get("porcentaje_supervivencia")
			);
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
			));
			tv.setText(info);
			tv.setTextSize(12);
			tv.setPadding(8, 6, 8, 6);
			tv.setBackgroundColor(0xFFE8F5E8);
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
	{
	}
	
}