package cu.dandroid.cunnis;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.prime.arab.ware.everythingutils.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.database.Cursor;  // For Cursor type
import android.database.sqlite.SQLiteDatabase;  // If using SQLite
import android.widget.Toast;
import android.content.DialogInterface;



public class DetallesActivity extends Activity {
	
	final DatabaseHelper dbHelper = new DatabaseHelper(this);
	int conejoId;
	private HashMap<String, Object> conejo = new HashMap<>();
	GestorAlertas gestorAlertas;
	
	private LinearLayout linear22;
	private LinearLayout linear_acciones_rapidas;
	private ScrollView vscroll3;
	private LinearLayout linear_botones_inferiores;
	private ImageView ivFoto;
	private LinearLayout linear23;
	private TextView tvNombre;
	private TextView tvIdentificador;
	private TextView tvEstado;
	private Button btnRegistrarCelo;
	private Button btnRegistrarMonta;
	private Button btnRegistrarParto;
	private LinearLayout linear_contenido_principal;
	private TextView tvTituloInfoGeneral;
	private LinearLayout linear_info_general;
	private TextView tvTituloReproduccion;
	private LinearLayout layoutReproduccion;
	private TextView tvTituloPesos;
	private ListView lvPesos;
	private TextView tvTituloPartos;
	private ListView lvPartos;
	private LinearLayout linear_info_reproductiva_detalle;
	private LinearLayout linear_fila_raza;
	private LinearLayout linear_fila_sexo;
	private LinearLayout linear_fila_edad;
	private LinearLayout linear_fila_peso;
	private LinearLayout linear_fila_nacimiento;
	private TextView textview_etiqueta_raza;
	private TextView tvRaza;
	private TextView textview_etiqueta_sexo;
	private TextView tvGenero;
	private TextView textview_etiqueta_edad;
	private TextView tvEdad;
	private TextView textview_etiqueta_peso;
	private TextView tvPeso;
	private TextView textview_etiqueta_nacimiento;
	private TextView tvFechaNacimiento;
	private TextView tvPartos;
	private TextView tvUltimoCelo;
	private LinearLayout linear_info_hembra;
	private LinearLayout linear_info_macho;
	private TextView tvTituloAlertas;
	private LinearLayout linear_alertas;
	private TextView tvEstadoCelo;
	private TextView tvProximoCelo;
	private TextView tvTotalPartos;
	private TextView tvTotalCrias;
	private TextView tvCriasVivas;
	private TextView tvCriasMuertas;
	private TextView tvEstadoSemental;
	private TextView tvTotalMontas;
	private TextView tvHijasEngendradas;
	private TextView tvCriasEngendradas;
	private TextView tvMontasExitosas;
	private TextView tvProximaAlertaPeso;
	private TextView textview5;
	private TextView textview6;
	private Button btnProgramarRecordatorio;
	private Button btnVolver;
	private Button btnRegistrarPeso;
	private Button btnEditar;
	
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.detalles);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		gestorAlertas = new GestorAlertas(this);
		linear22 = findViewById(R.id.linear22);
		linear_acciones_rapidas = findViewById(R.id.linear_acciones_rapidas);
		vscroll3 = findViewById(R.id.vscroll3);
		linear_botones_inferiores = findViewById(R.id.linear_botones_inferiores);
		ivFoto = findViewById(R.id.ivFoto);
		linear23 = findViewById(R.id.linear23);
		tvNombre = findViewById(R.id.tvNombre);
		tvIdentificador = findViewById(R.id.tvIdentificador);
		tvEstado = findViewById(R.id.tvEstado);
		btnRegistrarCelo = findViewById(R.id.btnRegistrarCelo);
		btnRegistrarMonta = findViewById(R.id.btnRegistrarMonta);
		btnRegistrarParto = findViewById(R.id.btnRegistrarParto);
		linear_contenido_principal = findViewById(R.id.linear_contenido_principal);
		tvTituloInfoGeneral = findViewById(R.id.tvTituloInfoGeneral);
		linear_info_general = findViewById(R.id.linear_info_general);
		tvTituloReproduccion = findViewById(R.id.tvTituloReproduccion);
		layoutReproduccion = findViewById(R.id.layoutReproduccion);
		tvTituloPesos = findViewById(R.id.tvTituloPesos);
		lvPesos = findViewById(R.id.lvPesos);
		tvTituloPartos = findViewById(R.id.tvTituloPartos);
		lvPartos = findViewById(R.id.lvPartos);
		linear_info_reproductiva_detalle = findViewById(R.id.linear_info_reproductiva_detalle);
		linear_fila_raza = findViewById(R.id.linear_fila_raza);
		linear_fila_sexo = findViewById(R.id.linear_fila_sexo);
		linear_fila_edad = findViewById(R.id.linear_fila_edad);
		linear_fila_peso = findViewById(R.id.linear_fila_peso);
		linear_fila_nacimiento = findViewById(R.id.linear_fila_nacimiento);
		textview_etiqueta_raza = findViewById(R.id.textview_etiqueta_raza);
		tvRaza = findViewById(R.id.tvRaza);
		textview_etiqueta_sexo = findViewById(R.id.textview_etiqueta_sexo);
		tvGenero = findViewById(R.id.tvGenero);
		textview_etiqueta_edad = findViewById(R.id.textview_etiqueta_edad);
		tvEdad = findViewById(R.id.tvEdad);
		textview_etiqueta_peso = findViewById(R.id.textview_etiqueta_peso);
		tvPeso = findViewById(R.id.tvPeso);
		textview_etiqueta_nacimiento = findViewById(R.id.textview_etiqueta_nacimiento);
		tvFechaNacimiento = findViewById(R.id.tvFechaNacimiento);
		tvPartos = findViewById(R.id.tvPartos);
		tvUltimoCelo = findViewById(R.id.tvUltimoCelo);
		linear_info_hembra = findViewById(R.id.linear_info_hembra);
		linear_info_macho = findViewById(R.id.linear_info_macho);
		tvTituloAlertas = findViewById(R.id.tvTituloAlertas);
		linear_alertas = findViewById(R.id.linear_alertas);
		tvEstadoCelo = findViewById(R.id.tvEstadoCelo);
		tvProximoCelo = findViewById(R.id.tvProximoCelo);
		tvTotalPartos = findViewById(R.id.tvTotalPartos);
		tvTotalCrias = findViewById(R.id.tvTotalCrias);
		tvCriasVivas = findViewById(R.id.tvCriasVivas);
		tvCriasMuertas = findViewById(R.id.tvCriasMuertas);
		tvEstadoSemental = findViewById(R.id.tvEstadoSemental);
		tvTotalMontas = findViewById(R.id.tvTotalMontas);
		tvHijasEngendradas = findViewById(R.id.tvHijasEngendradas);
		tvCriasEngendradas = findViewById(R.id.tvCriasEngendradas);
		tvMontasExitosas = findViewById(R.id.tvMontasExitosas);
		tvProximaAlertaPeso = findViewById(R.id.tvProximaAlertaPeso);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		btnProgramarRecordatorio = findViewById(R.id.btnProgramarRecordatorio);
		btnVolver = findViewById(R.id.btnVolver);
		btnRegistrarPeso = findViewById(R.id.btnRegistrarPeso);
		btnEditar = findViewById(R.id.btnEditar);
		d = new AlertDialog.Builder(this);
		
		ivFoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String fotoRuta = "";
				if (conejo.containsKey("foto_ruta")) {
					fotoRuta = conejo.get("foto_ruta").toString();
				}
				
				if (fotoRuta.isEmpty()) {
					Toast.makeText(DetallesActivity.this, "No hay foto para mostrar", Toast.LENGTH_SHORT).show();
					return;
				}
				
				// Crear y configurar el diálogo
				dCD = new CustomDialogByArabWare(DetallesActivity.this);
				dCD.setLayout(R.layout.ver_foto);
				dCD.setCancelable(true);
				
				// Mostrar el diálogo
				dCD.show();
				
				// Obtener la ImageView del diálogo
				ImageView ivFotoGrande = (ImageView) dCD.getView(R.id.ivFotoGrande);
				if (ivFotoGrande != null) {
					// Configurar el ImageView para pantalla completa
					ivFotoGrande.setScaleType(ImageView.ScaleType.FIT_CENTER);
					ivFotoGrande.setAdjustViewBounds(true);
					
					try {
						String rutaCompleta = fotoRuta;
						if (!fotoRuta.startsWith("/storage") && 
						!fotoRuta.startsWith("content://") && 
						!fotoRuta.startsWith("file://")) {
							rutaCompleta = "/storage/emulated/0/Documents/Conejos/" + fotoRuta;
						}
						
						File archivoFoto = new File(rutaCompleta);
						if (archivoFoto.exists()) {
							// Cargar la imagen
							Bitmap bitmap = BitmapFactory.decodeFile(rutaCompleta);
							if (bitmap != null) {
								ivFotoGrande.setImageBitmap(bitmap);
								
								// Opcional: Ajustar más el tamaño
								DisplayMetrics displayMetrics = new DisplayMetrics();
								getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
								
								int screenWidth = displayMetrics.widthPixels;
								int screenHeight = displayMetrics.heightPixels;
								
								// Ajustar tamaño del ImageView programáticamente
								ivFotoGrande.getLayoutParams().width = screenWidth;
								ivFotoGrande.getLayoutParams().height = screenHeight;
								ivFotoGrande.requestLayout();
							} else {
								ivFotoGrande.setImageResource(R.drawable.avatar);
							}
						} else {
							ivFotoGrande.setImageResource(R.drawable.avatar);
						}
					} catch (Exception e) {
						e.printStackTrace();
						ivFotoGrande.setImageResource(R.drawable.avatar);
					}
				}
			}
		});
		
		btnRegistrarCelo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_registrarCeloConAlerta();
			}
		});
		
		btnRegistrarMonta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_registrarMonta();
			}
		});
		
		btnRegistrarParto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_registrarParto();
			}
		});
		
		btnProgramarRecordatorio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_mostrarMenuRecordatorios();
			}
		});
		
		btnRegistrarPeso.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_registrarPesoConAlerta();
			}
		});
	}
	
	private void initializeLogic() {
		//private void initializeLogic() {
		// Obtener el ID del conejo del Intent
		// En el método initializeLogic() o al principio de onCreate:
		conejoId = getIntent().getIntExtra("CONEJO_ID", -1);
		// Cargar datos del conejo
		if (conejoId != -1) {
			_actualizarTodo(); // Usar el nuevo método unificado
		} else {
			Toast.makeText(this, "Error: No se especificó el conejo", Toast.LENGTH_SHORT).show();
			finish();
		}
		// Configurar listeners de botones
		btnVolver.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		btnEditar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Aquí puedes agregar la lógica para editar el conejo
				//Toast.makeText(DetallesActivity.this, "Funcionalidad de editar en desarrollo", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(DetallesActivity.this, EditarActivity.class);
				intent.putExtra("ID_CONEJO", conejoId);
				startActivity(intent);
			}
		});
		
		// Cargar datos del conejo
		if (conejoId != -1) {
			_cargarDatosConejo();
		} else {
			Toast.makeText(this, "Error: No se especificó el conejo", Toast.LENGTH_SHORT).show();
			finish();
		}
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_actualizarTodo();
	}
	
	public void _cargarDatosConejo() {
		/*
//private void cargarDatosConejo() {
    conejo = dbHelper.obtenerConejoPorId(conejoId);
    
    if (conejo != null) {
        // Nombre
        tvNombre.setText(conejo.nombre);
        
        // Raza
        tvRaza.setText("Raza: " + (conejo.raza != null ? conejo.raza : "No especificada"));
        
        // Género
        String genero = conejo.sexo.equals("M") ? "Macho" : "Hembra";
        tvGenero.setText("Sexo: " + genero);
        
        // Edad
        String edad = _calcularEdad(conejo.fechaNacimiento);
        tvEdad.setText("Edad: " + edad);
        
        // Peso
        tvPeso.setText("Peso: " + String.format("%.2f kg", conejo.peso));
        
        // Fecha de nacimiento
        tvFechaNacimiento.setText("Nacimiento: " + conejo.fechaNacimiento);
        
        // Foto
        if (conejo.fotoRuta != null && !conejo.fotoRuta.isEmpty()) {
            _cargarFoto(conejo.fotoRuta);
        }
        
        // Estadísticas adicionales
        tvSemental.setText("Semental: " + (conejo.sexo.equals("M") ? "Sí" : "No"));
        tvRetirado.setText("Estado: Activo");
        
        // Cargar pesos y partos
        _cargarHistorialPesos();
        if (conejo.sexo.equals("H")) {
            _cargarHistorialPartos();
        } else {
            tvPartos.setText("Partos: N/A");
            tvEncelo.setText("En celo: N/A");
        }
    }
//}
*/
		// Usar el método ya existente en DatabaseHelper
		DatabaseHelper.Conejo conejoObj = dbHelper.obtenerConejoPorId(conejoId);
		
		if (conejoObj != null) {
			// Llenar el HashMap (por compatibilidad con código existente)
			conejo.put("nombre", conejoObj.nombre);
			conejo.put("identificador", conejoObj.identificador);
			conejo.put("sexo", conejoObj.sexo);
			conejo.put("raza", conejoObj.raza);
			conejo.put("fecha_nacimiento", conejoObj.fechaNacimiento);
			conejo.put("peso", String.valueOf(conejoObj.peso));
			conejo.put("foto_ruta", conejoObj.fotoRuta);
			
			// Usar directamente el objeto Conejo para cargar datos
			tvNombre.setText(conejoObj.nombre);
			
			if (tvIdentificador != null) {
				tvIdentificador.setText("" + conejoObj.identificador);
			}
			
			String raza = conejoObj.raza;
			tvRaza.setText("" + (raza != null && !raza.isEmpty() ? raza : "No especificada"));
			
			String genero = conejoObj.sexo.equals("M") ? "Macho" : "Hembra";
			tvGenero.setText("" + genero);
			
			String edad = _calcularEdad(conejoObj.fechaNacimiento);
			tvEdad.setText("" + edad);
			
			tvPeso.setText("" + conejoObj.peso + " lb");
			tvFechaNacimiento.setText("" + conejoObj.fechaNacimiento);
			
			// Foto
			String fotoRuta = conejoObj.fotoRuta;
			if (fotoRuta != null && !fotoRuta.isEmpty()) {
				tvEstado.setText(fotoRuta);
				_cargarFoto(fotoRuta);
			}
			
			// Si es hembra, mostrar secciones de reproducción
			if (conejoObj.sexo.equals("H")) {
				layoutReproduccion.setVisibility(View.VISIBLE);
				tvTituloReproduccion.setVisibility(View.VISIBLE);
				tvTituloPartos.setVisibility(View.VISIBLE);
				lvPartos.setVisibility(View.VISIBLE);
				
				_cargarHistorialPartos();
				_verificarCelo();
			} else {
				layoutReproduccion.setVisibility(View.GONE);
				tvTituloReproduccion.setVisibility(View.GONE);
				tvTituloPartos.setVisibility(View.GONE);
				lvPartos.setVisibility(View.GONE);
			}
			
			_cargarHistorialPesos();
		} else {
			Toast.makeText(this, "No se encontró el conejo con ID: " + conejoId, Toast.LENGTH_SHORT).show();
			finish();
		}
		
	}
	
	
	public String _calcularEdad(final String _fechaNacimiento) {
		/*
//private String calcularEdad(String fechaNacimiento) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date nacimiento = sdf.parse(_fechaNacimiento);
        Date hoy = new Date();
        
        long diff = hoy.getTime() - nacimiento.getTime();
        long dias = diff / (24 * 60 * 60 * 1000);
        
        if (dias < 30) {
            return dias + " días";
        } else if (dias < 365) {
            long meses = dias / 30;
            return meses + " mes" + (meses != 1 ? "es" : "");
        } else {
            long años = dias / 365;
            long meses = (dias % 365) / 30;
            return años + " año" + (años != 1 ? "s" : "") + 
                   (meses > 0 ? ", " + meses + " mes" + (meses != 1 ? "es" : "") : "");
        }
    } catch (Exception e) {
        return "Desconocida";
    }
//}
*/
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			java.util.Date nacimiento = sdf.parse(_fechaNacimiento);
			java.util.Date hoy = new java.util.Date();
			
			long diff = hoy.getTime() - nacimiento.getTime();
			long dias = diff / (24 * 60 * 60 * 1000);
			
			if (dias < 30) {
				return dias + " días";
			} else if (dias < 365) {
				long meses = dias / 30;
				return meses + " mes" + (meses != 1 ? "es" : "");
			} else {
				long años = dias / 365;
				long meses = (dias % 365) / 30;
				return años + " año" + (años != 1 ? "s" : "") + 
				(meses > 0 ? ", " + meses + " mes" + (meses != 1 ? "es" : "") : "");
			}
		} catch (Exception e) {
			return "Desconocida";
		}
	}
	
	
	public void _cargarFoto(final String _fotoRuta) {
		//public void _cargarFoto(final String _fotoRuta) {
		try {
			if (_fotoRuta != null && !_fotoRuta.isEmpty()) {
				String rutaCompleta = _fotoRuta;
				
				// Si es ruta relativa, construir completa
				if (!_fotoRuta.startsWith("/storage") && 
				!_fotoRuta.startsWith("content://") && 
				!_fotoRuta.startsWith("file://")) {
					rutaCompleta = "/storage/emulated/0/Documents/Conejos/" + _fotoRuta;
				}
				
				if (rutaCompleta.startsWith("content://") || rutaCompleta.startsWith("file://")) {
					Uri uri = Uri.parse(rutaCompleta);
					ivFoto.setImageURI(uri);
				} else {
					File imgFile = new File(rutaCompleta);
					if (imgFile.exists()) {
						Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
						ivFoto.setImageBitmap(bitmap);
					} else {
						ivFoto.setImageResource(R.drawable.avatar);
					}
				}
			} else {
				ivFoto.setImageResource(R.drawable.avatar);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ivFoto.setImageResource(R.drawable.avatar);
		}
		
	}
	
	
	public void _cargarHistorialPesos() {
		
		// CAMBIAR ESTO:
		//private void cargarHistorialPesos() {
		// Obtener pesos de la base de datos
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(
		DatabaseHelper.TABLE_PESOS,
		new String[]{DatabaseHelper.COLUMN_PESO, DatabaseHelper.COLUMN_FECHA_PESO, 
			DatabaseHelper.COLUMN_OBSERVACIONES_PESO},
		DatabaseHelper.COLUMN_PESO_CONEOJO_ID + " = ?",
		new String[]{String.valueOf(conejoId)},
		null, null, 
		DatabaseHelper.COLUMN_FECHA_PESO + " DESC"
		);
		
		List<Map<String, String>> datos = new ArrayList<>();
		while (cursor.moveToNext()) {
			Map<String, String> peso = new HashMap<>();
			peso.put("peso", cursor.getDouble(0) + " lb");
			peso.put("fecha", cursor.getString(1));
			peso.put("observaciones", cursor.getString(2));
			datos.add(peso);
		}
		cursor.close();
		
		if (datos.isEmpty()) {
			Map<String, String> vacio = new HashMap<>();
			vacio.put("peso", "No hay pesos registrados");
			vacio.put("fecha", "");
			vacio.put("observaciones", "");
			datos.add(vacio);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(
		this,
		datos,
		android.R.layout.simple_list_item_2,
		new String[]{"peso", "fecha", "observaciones"},
		new int[]{android.R.id.text1, android.R.id.text2}
		);
		lvPesos.setAdapter(adapter);
		//}
	}
	
	
	public void _cargarHistorialPartos() {
		if (conejo.get("sexo").equals("H")) {
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor cursor = db.query(
			DatabaseHelper.TABLE_PARTOS,
			new String[]{DatabaseHelper.COLUMN_FECHA_PARTO, DatabaseHelper.COLUMN_CRIAS_VIVAS,
				DatabaseHelper.COLUMN_CRIAS_TOTAL, DatabaseHelper.COLUMN_OBSERVACIONES_PARTO},
			DatabaseHelper.COLUMN_PARTO_MADRE_ID + " = ?",
			new String[]{String.valueOf(conejoId)},
			null, null,
			DatabaseHelper.COLUMN_FECHA_PARTO + " DESC"
			);
			
			int totalPartos = cursor.getCount();
			tvPartos.setText("Partos: " + totalPartos);
			
			List<Map<String, String>> datos = new ArrayList<>();
			while (cursor.moveToNext()) {
				Map<String, String> parto = new HashMap<>();
				parto.put("fecha", cursor.getString(0));
				parto.put("crias", cursor.getInt(1) + "/" + cursor.getInt(2) + " crías");
				parto.put("observaciones", cursor.getString(3));
				datos.add(parto);
			}
			cursor.close();
			
			if (datos.isEmpty()) {
				Map<String, String> vacio = new HashMap<>();
				vacio.put("fecha", "No hay partos registrados");
				vacio.put("crias", "");
				vacio.put("observaciones", "");
				datos.add(vacio);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(
			this,
			datos,
			android.R.layout.simple_list_item_2,
			new String[]{"fecha", "crias", "observaciones"},
			new int[]{android.R.id.text1, android.R.id.text2}
			);
			lvPartos.setAdapter(adapter);
			
			// Verificar si está en celo
			_verificarCelo();
		}
		//}
		
	}
	
	
	public void _verificarCelo() {
		// VERIFICAR ÚLTIMO CELO REGISTRADO
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(
		DatabaseHelper.TABLE_CELOS,
		new String[]{DatabaseHelper.COLUMN_FECHA_CELO},
		DatabaseHelper.COLUMN_CELO_HEMBRA_ID + " = ?",
		new String[]{String.valueOf(conejoId)},
		null, null,
		DatabaseHelper.COLUMN_FECHA_CELO + " DESC",
		"1"
		);
		
		if (cursor != null && cursor.moveToFirst()) {
			String ultimoCelo = cursor.getString(0);
			tvUltimoCelo.setText("Último celo: " + ultimoCelo);
		} else {
			tvUltimoCelo.setText("Celo: No registrado");
		}
		
		if (cursor != null) {
			cursor.close();
		}
		db.close();
		
	}
	
	
	public void _libsCD() {
	}
	public static class CustomDialogByArabWare {
		
		private AlertDialog alertDialog;
		private View view;
		private Context context;
		private boolean isCancelable;
		private boolean isActivity;
		private Fragment fragmentContext;
		
		public CustomDialogByArabWare(Activity activity) {
			
			context = activity;
			
			alertDialog = new AlertDialog.Builder(context).create();
			
			alertDialog.getWindow().setBackgroundDrawable(null);
			
			isActivity = true;
			
		}
		
		public CustomDialogByArabWare(Fragment fragment) {
			
			fragmentContext = fragment;
			
			context = fragment.getActivity();
			
			alertDialog = new AlertDialog.Builder(context).create();
			
			alertDialog.getWindow().setBackgroundDrawable(null);
			
			isActivity = false;
			
		}
		
		public void setLayout(int layout) {
			
			if(isActivity) {
				
				view = (View) ((Activity)context).getLayoutInflater().inflate(layout, null);
				
			} else {
				
				view = (View) fragmentContext.getActivity().getLayoutInflater().inflate(layout, null);
				
			}
			
			alertDialog.setView(view);
			
		}
		
		public void show() {
			alertDialog.show();
		}
		
		public void setCancelable(boolean b) {
			alertDialog.setCancelable(b);
			isCancelable = b;
		}
		
		public boolean isCancelable() {
			return isCancelable;
		}
		
		
		public View getView(int viewId) {
			return view.findViewById(viewId);
		}
		
		public void dimiss() {
			alertDialog.dismiss();
		}
		
	}
	{
	}
	private CustomDialogByArabWare dCD;
	{
	}
	
	
	public void _cargarInfoHembra(final double _conejoId, final String _fechaNacimiento) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			java.util.Date fechaNacimiento = sdf.parse(_fechaNacimiento);
			java.util.Date hoy = new java.util.Date();
			long diff = hoy.getTime() - fechaNacimiento.getTime();
			long meses = diff / (30L * 24 * 60 * 60 * 1000);
			
			android.database.sqlite.SQLiteDatabase db = dbHelper.getReadableDatabase();
			
			android.database.Cursor cursorPartos = db.rawQuery(
			"SELECT COUNT(*) FROM partos WHERE madre_id = ?", 
			new String[]{String.valueOf(conejoId)}
			);
			
			int totalPartos = 0;
			int totalCrias = 0;
			int totalCriasVivas = 0;
			int totalCriasMuertas = 0;
			
			if (cursorPartos.moveToFirst()) {
				totalPartos = cursorPartos.getInt(0);
			}
			cursorPartos.close();
			
			android.database.Cursor cursorCrias = db.rawQuery(
			"SELECT SUM(crias_totales), SUM(crias_vivas), SUM(crias_totales - crias_vivas) FROM partos WHERE madre_id = ?", 
			new String[]{String.valueOf(conejoId)}
			);
			
			if (cursorCrias.moveToFirst()) {
				totalCrias = cursorCrias.getInt(0);
				totalCriasVivas = cursorCrias.getInt(1);
				totalCriasMuertas = cursorCrias.getInt(2);
			}
			cursorCrias.close();
			
			android.database.Cursor cursorCelo = db.rawQuery(
			"SELECT fecha_celo FROM celos WHERE hembra_id = ? ORDER BY fecha_celo DESC LIMIT 1", 
			new String[]{String.valueOf(conejoId)}
			);
			
			String estadoCelo = "No registrado";
			String proximoCelo = "No calculable";
			
			if (cursorCelo.moveToFirst()) {
				String ultimoCeloStr = cursorCelo.getString(0);
				
				try {
					java.util.Date ultimoCelo = sdf.parse(ultimoCeloStr);
					long diasDesdeCelo = (hoy.getTime() - ultimoCelo.getTime()) / (24 * 60 * 60 * 1000);
					
					int cicloCelo = 16;
					long diasParaProximo = cicloCelo - (diasDesdeCelo % cicloCelo);
					
					if (diasDesdeCelo <= 2) {
						estadoCelo = "EN CELO";
					} else if (diasParaProximo <= 2) {
						estadoCelo = "PRÓXIMO CELO (" + diasParaProximo + " días)";
					} else {
						estadoCelo = "No en celo (" + diasDesdeCelo + " días desde último)";
					}
					
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.setTime(ultimoCelo);
					cal.add(java.util.Calendar.DAY_OF_YEAR, cicloCelo);
					proximoCelo = sdf.format(cal.getTime());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cursorCelo.close();
			
			// Asegúrate de que estos TextView existen en tu layout
			tvTotalPartos.setText("Total partos: " + totalPartos);
			tvTotalCrias.setText("Total crías: " + totalCrias);
			tvCriasVivas.setText("Crías vivas: " + totalCriasVivas);
			tvCriasMuertas.setText("Crías muertas: " + totalCriasMuertas);
			tvEstadoCelo.setText("Estado celo: " + estadoCelo);
			tvProximoCelo.setText("Próximo celo: " + proximoCelo);
			
			if (meses >= 5) {
				tvEstadoCelo.append(" (Madura)");
			} else {
				tvEstadoCelo.append(" (Inmadura - " + meses + " meses)");
			}
			
			db.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			android.widget.Toast.makeText(this, "Error al cargar info hembra", android.widget.Toast.LENGTH_SHORT).show();
		}
	}
	
	
	public void _cargarInfoMacho(final String _fechaNacimiento) {
		//public void _cargarInfoMacho(final int conejoId, final String _fechaNacimiento) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			java.util.Date fechaNacimiento = sdf.parse(_fechaNacimiento);
			java.util.Date hoy = new java.util.Date();
			long diff = hoy.getTime() - fechaNacimiento.getTime();
			long meses = diff / (30L * 24 * 60 * 60 * 1000);
			
			boolean esSemental = meses >= 6;
			
			android.database.sqlite.SQLiteDatabase db = dbHelper.getReadableDatabase();
			
			android.database.Cursor cursorMontas = db.rawQuery(
			"SELECT COUNT(*) FROM partos WHERE padre_id = ?", 
			new String[]{String.valueOf(conejoId)}
			);
			
			int totalMontas = 0;
			int totalCriasEngendradas = 0;
			int totalHijas = 0;
			
			if (cursorMontas.moveToFirst()) {
				totalMontas = cursorMontas.getInt(0);
			}
			cursorMontas.close();
			
			android.database.Cursor cursorCrias = db.rawQuery(
			"SELECT SUM(crias_totales) FROM partos WHERE padre_id = ?", 
			new String[]{String.valueOf(conejoId)}
			);
			
			if (cursorCrias.moveToFirst()) {
				totalCriasEngendradas = cursorCrias.getInt(0);
			}
			cursorCrias.close();
			
			android.database.Cursor cursorHijas = db.rawQuery(
			"SELECT COUNT(*) FROM conejos WHERE padre_id = ? AND sexo = 'H'", 
			new String[]{String.valueOf(conejoId)}
			);
			
			if (cursorHijas.moveToFirst()) {
				totalHijas = cursorHijas.getInt(0);
			}
			cursorHijas.close();
			
			int montasExitosas = totalMontas;
			
			tvEstadoSemental.setText("Semental: " + (esSemental ? "SÍ" : "No (" + meses + " meses)"));
			tvTotalMontas.setText("Montas realizadas: " + totalMontas);
			tvCriasEngendradas.setText("Crías engendradas: " + totalCriasEngendradas);
			tvHijasEngendradas.setText("Hijas engendradas: " + totalHijas);
			tvMontasExitosas.setText("Montas exitosas: " + montasExitosas);
			
			if (esSemental) {
				tvEstadoSemental.setTextColor(android.graphics.Color.GREEN);
			} else {
				tvEstadoSemental.setTextColor(android.graphics.Color.RED);
			}
			
			db.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			android.widget.Toast.makeText(this, "Error al cargar info macho", android.widget.Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public void _registrarCelo() {
		//public void _registrarCelo(final int conejoId) {
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
		builder.setTitle("Registrar Celo");
		
		final android.widget.EditText input = new android.widget.EditText(this);
		input.setText(new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date()));
		builder.setView(input);
		
		builder.setPositiveButton("Registrar", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(android.content.DialogInterface dialog, int which) {
				String fecha = input.getText().toString();
				
				android.content.ContentValues values = new android.content.ContentValues();
				values.put("hembra_id", conejoId);
				values.put("fecha_celo", fecha);
				values.put("observaciones", "Registrado desde app");
				
				android.database.sqlite.SQLiteDatabase db = dbHelper.getWritableDatabase();
				long resultado = db.insert("celos", null, values);
				db.close();
				
				if (resultado != -1) {
					android.widget.Toast.makeText(DetallesActivity.this, "Celo registrado exitosamente", android.widget.Toast.LENGTH_SHORT).show();
					_cargarDatosConejo();
				} else {
					android.widget.Toast.makeText(DetallesActivity.this, "Error al registrar celo", android.widget.Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		builder.setNegativeButton("Cancelar", null);
		builder.show();
		
	}
	
	
	public void _registrarMonta() {
		//public void _registrarMonta() {
		try {
			// Obtener lista de hembras maduras (más de 5 meses) de la base de datos
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			
			// Consulta para obtener hembras maduras y activas
			String query = "SELECT id, nombre, fecha_nacimiento FROM " + DatabaseHelper.TABLE_CONEJOS + 
			" WHERE sexo = 'H' AND estado = 'activo'";
			
			Cursor cursor = db.rawQuery(query, null);
			
			List<Map<String, String>> listaHembras = new ArrayList<>();
			List<Integer> idsHembras = new ArrayList<>();
			List<String> nombresHembras = new ArrayList<>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			Date hoy = new Date();
			
			while (cursor.moveToNext()) {
				int id = cursor.getInt(0);
				String nombre = cursor.getString(1);
				String fechaNacimiento = cursor.getString(2);
				
				// Calcular edad para ver si es madura (más de 5 meses)
				try {
					Date fechaNac = sdf.parse(fechaNacimiento);
					long diff = hoy.getTime() - fechaNac.getTime();
					long meses = diff / (30L * 24 * 60 * 60 * 1000);
					
					if (meses >= 5) {
						// Es madura, agregar a la lista
						idsHembras.add(id);
						nombresHembras.add(nombre + " (" + meses + " meses)");
						
						Map<String, String> hembra = new HashMap<>();
						hembra.put("id", String.valueOf(id));
						hembra.put("nombre", nombre);
						hembra.put("meses", String.valueOf(meses));
						listaHembras.add(hembra);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cursor.close();
			db.close();
			
			if (listaHembras.isEmpty()) {
				Toast.makeText(this, "No hay hembras maduras disponibles", Toast.LENGTH_SHORT).show();
				return;
			}
			
			// Crear diálogo con spinner
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Registrar Monta");
			
			final LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.setPadding(50, 10, 50, 10);
			
			// TextView para mostrar información
			TextView tvInfo = new TextView(this);
			tvInfo.setText("Seleccione la hembra para la monta:");
			tvInfo.setTextSize(14);
			tvInfo.setPadding(0, 0, 0, 10);
			layout.addView(tvInfo);
			
			// Spinner para seleccionar hembra
			final Spinner spinnerHembras = new Spinner(this);
			
			ArrayAdapter<String> adapter = new ArrayAdapter<>(
			this, 
			android.R.layout.simple_spinner_item, 
			nombresHembras
			);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerHembras.setAdapter(adapter);
			
			// Campo para fecha
			final EditText etFecha = new EditText(this);
			etFecha.setHint("Fecha (YYYY-MM-DD)");
			etFecha.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
			
			// CheckBox para indicar si la monta fue exitosa
			final CheckBox cbExito = new CheckBox(this);
			cbExito.setText("Monta exitosa");
			cbExito.setChecked(true);
			
			// Campo para observaciones
			final EditText etObservaciones = new EditText(this);
			etObservaciones.setHint("Observaciones (opcional)");
			
			layout.addView(spinnerHembras);
			layout.addView(etFecha);
			layout.addView(cbExito);
			layout.addView(etObservaciones);
			
			builder.setView(layout);
			
			// Variables finales para usar en el listener
			final List<Integer> finalIdsHembras = idsHembras;
			
			builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int selectedPosition = spinnerHembras.getSelectedItemPosition();
					
					if (selectedPosition >= 0 && selectedPosition < finalIdsHembras.size()) {
						int hembraId = finalIdsHembras.get(selectedPosition);
						String fecha = etFecha.getText().toString();
						String observaciones = etObservaciones.getText().toString();
						boolean exito = cbExito.isChecked();
						
						// Validar fecha
						if (fecha.isEmpty()) {
							Toast.makeText(DetallesActivity.this, "La fecha es obligatoria", Toast.LENGTH_SHORT).show();
							return;
						}
						
						if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
							Toast.makeText(DetallesActivity.this, "Formato de fecha inválido. Use YYYY-MM-DD", Toast.LENGTH_SHORT).show();
							return;
						}
						
						// Insertar en la base de datos
						ContentValues values = new ContentValues();
						values.put("macho_id", conejoId);
						values.put("hembra_id", hembraId);
						values.put("fecha_monta", fecha);
						values.put("observaciones", observaciones);
						values.put("exito", exito ? 1 : 0);
						
						SQLiteDatabase db = dbHelper.getWritableDatabase();
						long resultado = db.insert("montas", null, values);
						db.close();
						
						if (resultado != -1) {
							Toast.makeText(DetallesActivity.this, "Monta registrada exitosamente", Toast.LENGTH_SHORT).show();
							_actualizarTodo();
						} else {
							Toast.makeText(DetallesActivity.this, "Error al registrar monta", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(DetallesActivity.this, "Seleccione una hembra", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
			builder.setNegativeButton("Cancelar", null);
			builder.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public void _registrarParto() {
		//public void _registrarParto() {
		try {
			// Primero verificar si el conejo es hembra
			if (!conejo.get("sexo").equals("H")) {
				Toast.makeText(this, "Solo se pueden registrar partos para hembras", Toast.LENGTH_SHORT).show();
				return;
			}
			
			// Obtener lista de machos para seleccionar padre
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			
			// Consulta para obtener machos maduros (más de 6 meses)
			String query = "SELECT id, nombre, fecha_nacimiento FROM " + DatabaseHelper.TABLE_CONEJOS + 
			" WHERE sexo = 'M' AND estado = 'activo'";
			
			Cursor cursor = db.rawQuery(query, null);
			
			List<Integer> idsMachos = new ArrayList<>();
			List<String> nombresMachos = new ArrayList<>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			Date hoy = new Date();
			
			while (cursor.moveToNext()) {
				int id = cursor.getInt(0);
				String nombre = cursor.getString(1);
				String fechaNacimiento = cursor.getString(2);
				
				// Calcular edad para ver si es maduro (más de 6 meses)
				try {
					Date fechaNac = sdf.parse(fechaNacimiento);
					long diff = hoy.getTime() - fechaNac.getTime();
					long meses = diff / (30L * 24 * 60 * 60 * 1000);
					
					if (meses >= 6) {
						// Es maduro, agregar a la lista
						idsMachos.add(id);
						nombresMachos.add(nombre + " (" + meses + " meses)");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cursor.close();
			db.close();
			
			// Agregar opción "Desconocido" al inicio
			idsMachos.add(0, 0);
			nombresMachos.add(0, "Desconocido / No registrado");
			
			// Crear diálogo para registrar parto
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Registrar Parto");
			
			final LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.setPadding(50, 10, 50, 10);
			
			// Campo para fecha
			final EditText etFecha = new EditText(this);
			etFecha.setHint("Fecha parto (YYYY-MM-DD)");
			etFecha.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
			etFecha.setInputType(InputType.TYPE_CLASS_DATETIME);
			
			// Campo para fecha de monta (opcional)
			final EditText etFechaMonta = new EditText(this);
			etFechaMonta.setHint("Fecha monta (YYYY-MM-DD, opcional)");
			
			// Spinner para seleccionar padre
			TextView tvPadreLabel = new TextView(this);
			tvPadreLabel.setText("Padre de las crías:");
			tvPadreLabel.setPadding(0, 10, 0, 5);
			
			final Spinner spinnerPadre = new Spinner(this);
			ArrayAdapter<String> adapterPadre = new ArrayAdapter<>(
			this, 
			android.R.layout.simple_spinner_item, 
			nombresMachos
			);
			adapterPadre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerPadre.setAdapter(adapterPadre);
			
			// Campo para total de crías
			final EditText etCriasTotales = new EditText(this);
			etCriasTotales.setHint("Total de crías nacidas");
			etCriasTotales.setInputType(InputType.TYPE_CLASS_NUMBER);
			
			// Campo para crías vivas
			final EditText etCriasVivas = new EditText(this);
			etCriasVivas.setHint("Crías vivas");
			etCriasVivas.setInputType(InputType.TYPE_CLASS_NUMBER);
			
			// Campo para observaciones
			final EditText etObservaciones = new EditText(this);
			etObservaciones.setHint("Observaciones (opcional)");
			
			layout.addView(etFecha);
			layout.addView(etFechaMonta);
			layout.addView(tvPadreLabel);
			layout.addView(spinnerPadre);
			layout.addView(etCriasTotales);
			layout.addView(etCriasVivas);
			layout.addView(etObservaciones);
			
			builder.setView(layout);
			
			// Variables finales para usar en el listener
			final List<Integer> finalIdsMachos = idsMachos;
			
			builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String fecha = etFecha.getText().toString();
					String fechaMonta = etFechaMonta.getText().toString();
					String criasTotalesStr = etCriasTotales.getText().toString();
					String criasVivasStr = etCriasVivas.getText().toString();
					String observaciones = etObservaciones.getText().toString();
					int selectedPosition = spinnerPadre.getSelectedItemPosition();
					int padreId = (selectedPosition >= 0 && selectedPosition < finalIdsMachos.size()) ? 
					finalIdsMachos.get(selectedPosition) : 0;
					
					// Validaciones
					if (fecha.isEmpty()) {
						Toast.makeText(DetallesActivity.this, "La fecha es obligatoria", Toast.LENGTH_SHORT).show();
						return;
					}
					
					if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
						Toast.makeText(DetallesActivity.this, "Formato de fecha inválido. Use YYYY-MM-DD", Toast.LENGTH_SHORT).show();
						return;
					}
					
					if (criasTotalesStr.isEmpty()) {
						Toast.makeText(DetallesActivity.this, "El total de crías es obligatorio", Toast.LENGTH_SHORT).show();
						return;
					}
					
					if (criasVivasStr.isEmpty()) {
						criasVivasStr = criasTotalesStr; // Asumir que todas están vivas si no se especifica
					}
					
					try {
						int criasTotales = Integer.parseInt(criasTotalesStr);
						int criasVivas = Integer.parseInt(criasVivasStr);
						
						if (criasTotales <= 0) {
							Toast.makeText(DetallesActivity.this, "El total de crías debe ser mayor a 0", Toast.LENGTH_SHORT).show();
							return;
						}
						
						if (criasVivas < 0) {
							Toast.makeText(DetallesActivity.this, "Las crías vivas no pueden ser negativas", Toast.LENGTH_SHORT).show();
							return;
						}
						
						if (criasVivas > criasTotales) {
							Toast.makeText(DetallesActivity.this, "Las crías vivas no pueden ser más que el total", Toast.LENGTH_SHORT).show();
							return;
						}
						
						// Insertar en la base de datos CON LOS NOMBRES CORRECTOS DE COLUMNAS
						ContentValues values = new ContentValues();
						values.put(DatabaseHelper.COLUMN_PARTO_MADRE_ID, conejoId);
						if (padreId > 0) {
							values.put(DatabaseHelper.COLUMN_PARTO_PADRE_ID, padreId);
						}
						if (!fechaMonta.isEmpty()) {
							values.put(DatabaseHelper.COLUMN_FECHA_MONTA_PARTO, fechaMonta);
						}
						values.put(DatabaseHelper.COLUMN_FECHA_PARTO, fecha);
						values.put(DatabaseHelper.COLUMN_CRIAS_TOTAL, criasTotales);
						values.put(DatabaseHelper.COLUMN_CRIAS_VIVAS, criasVivas);
						values.put(DatabaseHelper.COLUMN_OBSERVACIONES_PARTO, observaciones);
						values.put(DatabaseHelper.COLUMN_CRIAS_REGISTRADAS, 0);
						
						SQLiteDatabase db = dbHelper.getWritableDatabase();
						long resultado = db.insert(DatabaseHelper.TABLE_PARTOS, null, values);
						db.close();
						
						if (resultado != -1) {
							Toast.makeText(DetallesActivity.this, "Parto registrado exitosamente", Toast.LENGTH_SHORT).show();
							_actualizarTodo();
						} else {
							Toast.makeText(DetallesActivity.this, "Error al registrar parto", Toast.LENGTH_SHORT).show();
						}
					} catch (NumberFormatException e) {
						Toast.makeText(DetallesActivity.this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
			builder.setNegativeButton("Cancelar", null);
			builder.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Error al registrar parto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public void _calcularProductividad() {
		//public void _calcularProductividad() {
		try {
			if (conejo.get("sexo").equals("H")) {
				android.database.sqlite.SQLiteDatabase db = dbHelper.getReadableDatabase();
				
				android.database.Cursor cursor = db.rawQuery(
				"SELECT SUM(crias_vivas) FROM partos WHERE madre_id = ?", 
				new String[]{String.valueOf(conejoId)}
				);
				
				int totalCriasVivas = 0;
				if (cursor.moveToFirst()) {
					totalCriasVivas = cursor.getInt(0);
				}
				cursor.close();
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
				java.util.Date fechaNacimiento = sdf.parse(conejo.get("fecha_nacimiento").toString());
				java.util.Date hoy = new java.util.Date();
				long meses = (hoy.getTime() - fechaNacimiento.getTime()) / (30L * 24 * 60 * 60 * 1000);
				
				long mesesReproductora = Math.max(0, meses - 6);
				
				if (mesesReproductora > 0) {
					double indiceProductividad = (double) totalCriasVivas / mesesReproductora;
					
					String mensaje = "Índice productividad: " + String.format(java.util.Locale.getDefault(), "%.1f", indiceProductividad) + " crías/mes";
					tvEstadoCelo.append("\n" + mensaje);
				}
				
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public boolean _esMaduroSexualmente(final String _fechaNacimiento, final String _sexo) {
		//public boolean _esMaduroSexualmente(final String _fechaNacimiento, final String _sexo) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			java.util.Date fechaNacimiento = sdf.parse(_fechaNacimiento);
			java.util.Date hoy = new java.util.Date();
			long diff = hoy.getTime() - fechaNacimiento.getTime();
			long meses = diff / (30L * 24 * 60 * 60 * 1000);
			
			if (_sexo.equals("H")) {
				return meses >= 5; // Hembras maduran a 5 meses
			} else {
				return meses >= 6; // Machos maduran a 6 meses
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public void _actualizarTodo() {
		//public void _actualizarTodo() {
		try {
			// 1. Recargar datos básicos
			DatabaseHelper.Conejo conejoObj = dbHelper.obtenerConejoPorId(conejoId);
			if (conejoObj != null) {
				// Actualizar información básica
				tvNombre.setText(conejoObj.nombre);
				tvRaza.setText(conejoObj.raza != null && !conejoObj.raza.isEmpty() ? conejoObj.raza : "No especificada");
				tvGenero.setText(conejoObj.sexo.equals("M") ? "Macho" : "Hembra");
				tvEdad.setText(_calcularEdad(conejoObj.fechaNacimiento));
				tvPeso.setText(conejoObj.peso + " lb");
				tvFechaNacimiento.setText(conejoObj.fechaNacimiento);
				
				// Actualizar foto
				if (conejoObj.fotoRuta != null && !conejoObj.fotoRuta.isEmpty()) {
					_cargarFoto(conejoObj.fotoRuta);
				}
				
				// 2. Actualizar según género
				if (conejoObj.sexo.equals("H")) {
					// Es HEMBRA
					layoutReproduccion.setVisibility(View.VISIBLE);
					tvTituloReproduccion.setVisibility(View.VISIBLE);
					tvTituloPartos.setVisibility(View.VISIBLE);
					lvPartos.setVisibility(View.VISIBLE);
					
					// Mostrar botones para hembra
					btnRegistrarCelo.setVisibility(View.VISIBLE);
					btnRegistrarParto.setVisibility(View.VISIBLE);
					btnRegistrarMonta.setVisibility(View.GONE);
					
					// Cargar datos específicos de hembra
					_cargarHistorialPartos();
					_verificarCelo();
					
					// Calcular y mostrar madurez sexual
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
					Date fechaNacimiento = sdf.parse(conejoObj.fechaNacimiento);
					Date hoy = new Date();
					long meses = (hoy.getTime() - fechaNacimiento.getTime()) / (30L * 24 * 60 * 60 * 1000);
					
					if (meses >= 5) {
						tvUltimoCelo.setText(tvUltimoCelo.getText() + " (Madura)");
					} else {
						tvUltimoCelo.setText(tvUltimoCelo.getText() + " (Inmadura - " + meses + " meses)");
					}
				} else {
					// Es MACHO
					layoutReproduccion.setVisibility(View.GONE);
					tvTituloReproduccion.setVisibility(View.GONE);
					tvTituloPartos.setVisibility(View.GONE);
					lvPartos.setVisibility(View.GONE);
					
					// Mostrar botones para macho
					btnRegistrarCelo.setVisibility(View.GONE);
					btnRegistrarParto.setVisibility(View.GONE);
					btnRegistrarMonta.setVisibility(View.VISIBLE);
					
					// Calcular y mostrar semental
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
					Date fechaNacimiento = sdf.parse(conejoObj.fechaNacimiento);
					Date hoy = new Date();
					long meses = (hoy.getTime() - fechaNacimiento.getTime()) / (30L * 24 * 60 * 60 * 1000);
					
					boolean esSemental = meses >= 6;
					tvEstado.setText("Semental: " + (esSemental ? "SÍ" : "No (" + meses + " meses)"));
				}
				
				// 3. Actualizar historiales
				_cargarHistorialPesos();
				
				// 4. Guardar en HashMap para otros usos
				conejo.put("nombre", conejoObj.nombre);
				conejo.put("sexo", conejoObj.sexo);
				conejo.put("raza", conejoObj.raza);
				conejo.put("fecha_nacimiento", conejoObj.fechaNacimiento);
				conejo.put("peso", String.valueOf(conejoObj.peso));
				conejo.put("foto_ruta", conejoObj.fotoRuta);
				
			} else {
				Toast.makeText(this, "No se encontró el conejo", Toast.LENGTH_SHORT).show();
				finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Error al actualizar datos", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public ArrayList<HashMap<String, Object>> _obtenerHembrasMaduras() {
		//public ArrayList<HashMap<String, Object>> _obtenerHembrasMaduras() {
		ArrayList<HashMap<String, Object>> hembrasMaduras = new ArrayList<>();
		
		try {
			android.database.sqlite.SQLiteDatabase db = dbHelper.getReadableDatabase();
			
			// Consulta para obtener hembras maduras (más de 5 meses)
			String query = "SELECT id, nombre, fecha_nacimiento FROM conejos WHERE sexo = 'H' AND estado = 'activo' AND fecha_fallecimiento IS NULL";
			
			android.database.Cursor cursor = db.rawQuery(query, null);
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			java.util.Date hoy = new java.util.Date();
			
			while (cursor.moveToNext()) {
				int id = cursor.getInt(0);
				String nombre = cursor.getString(1);
				String fechaNacimiento = cursor.getString(2);
				
				// Calcular edad
				try {
					java.util.Date fechaNac = sdf.parse(fechaNacimiento);
					long diff = hoy.getTime() - fechaNac.getTime();
					long meses = diff / (30L * 24 * 60 * 60 * 1000);
					
					if (meses >= 5) {
						HashMap<String, Object> hembra = new HashMap<>();
						hembra.put("id", id);
						hembra.put("nombre", nombre);
						hembra.put("meses", meses);
						hembra.put("edad", _calcularEdad(fechaNacimiento));
						hembrasMaduras.add(hembra);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cursor.close();
			db.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hembrasMaduras;
		
	}
	
	
	public void _registrarPesoConAlerta() {
		//public void _registrarPesoConAlerta() {
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Registrar Peso y Programar Recordatorio");
			
			LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.setPadding(50, 20, 50, 20);
			
			// 1. Declarar las variables como FINAL
			final EditText etPeso = new EditText(this);
			etPeso.setHint("Peso en kg (ej: 2.5)");
			etPeso.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
			
			final EditText etFecha = new EditText(this);
			etFecha.setHint("Fecha (YYYY-MM-DD)");
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			etFecha.setText(sdf.format(new Date()));
			
			final CheckBox cbRecordatorio = new CheckBox(this);
			cbRecordatorio.setText("Programar recordatorio mensual");
			cbRecordatorio.setChecked(true);
			
			final EditText etObservaciones = new EditText(this);
			etObservaciones.setHint("Observaciones (opcional)");
			
			layout.addView(etPeso);
			layout.addView(etFecha);
			layout.addView(cbRecordatorio);
			layout.addView(etObservaciones);
			
			builder.setView(layout);
			
			builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// 2. Ahora se puede acceder a las variables sin error
					String pesoStr = etPeso.getText().toString();
					String fecha = etFecha.getText().toString();
					String observaciones = etObservaciones.getText().toString();
					boolean programarRecordatorio = cbRecordatorio.isChecked();
					
					if (pesoStr.isEmpty()) {
						Toast.makeText(DetallesActivity.this, "Ingrese el peso", Toast.LENGTH_SHORT).show();
						return;
					}
					
					try {
						double peso = Double.parseDouble(pesoStr);
						
						// 3. Registrar el peso en la base de datos (usando tu dbHelper)
						ContentValues values = new ContentValues();
						values.put("conejo_id", conejoId);
						values.put("peso", peso);
						values.put("fecha", fecha);
						values.put("observaciones", observaciones);
						
						SQLiteDatabase db = dbHelper.getWritableDatabase();
						long resultado = db.insert("pesos", null, values); // Asegúrate de que el nombre de la tabla sea correcto
						db.close();
						
						if (resultado != -1) {
							Toast.makeText(DetallesActivity.this, "Peso registrado", Toast.LENGTH_SHORT).show();
							
							// 4. Programar recordatorio si está marcado
							if (programarRecordatorio && gestorAlertas != null) {
								gestorAlertas.programarAlertaPeso(
								conejoId,
								conejo.get("nombre").toString(),
								fecha
								);
								
								// Actualizar la interfaz si tienes un TextView para mostrar la próxima alerta
								if (tvProximaAlertaPeso != null) {
									try {
										Calendar calendar = Calendar.getInstance();
										Date fechaPeso = sdf.parse(fecha);
										calendar.setTime(fechaPeso);
										calendar.add(Calendar.MONTH, 1);
										String proximaFecha = sdf.format(calendar.getTime());
										tvProximaAlertaPeso.setText("Próximo pesaje: " + proximaFecha);
										tvProximaAlertaPeso.setVisibility(View.VISIBLE);
									} catch (Exception e) { e.printStackTrace(); }
								}
							}
							// 5. Actualizar toda la información en pantalla
							_actualizarTodo();
							
						} else {
							Toast.makeText(DetallesActivity.this, "Error al guardar en BD", Toast.LENGTH_SHORT).show();
						}
						
					} catch (NumberFormatException e) {
						Toast.makeText(DetallesActivity.this, "Peso inválido", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
			builder.setNegativeButton("Cancelar", null);
			builder.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public void _calcularInfoCelo() {
		//public void _calcularInfoCelo() {
		try {
			if (conejo.get("sexo").equals("H")) {
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				
				// Obtener último celo registrado
				Cursor cursor = db.rawQuery(
				"SELECT fecha_celo FROM celos WHERE hembra_id = ? ORDER BY fecha_celo DESC LIMIT 1",
				new String[]{String.valueOf(conejoId)}
				);
				
				if (cursor != null && cursor.moveToFirst()) {
					String ultimoCelo = cursor.getString(0);
					cursor.close();
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
					Date fechaCelo = sdf.parse(ultimoCelo);
					Date hoy = new Date();
					
					Calendar calendarioCelo = Calendar.getInstance();
					calendarioCelo.setTime(fechaCelo);
					
					// Calcular próximo celo (cada 14-16 días)
					calendarioCelo.add(Calendar.DAY_OF_YEAR, 14);
					Date proximoCelo = calendarioCelo.getTime();
					
					// Calcular días hasta próximo celo
					long diff = proximoCelo.getTime() - hoy.getTime();
					long diasHastaCelo = diff / (24 * 60 * 60 * 1000);
					
					// Mostrar información
					if (diasHastaCelo > 3) {
						tvEstadoCelo.setText("Estado: Fuera de celo");
						tvEstadoCelo.setTextColor(Color.BLUE);
						tvProximoCelo.setText("Próximo celo en " + diasHastaCelo + " días (" + 
						sdf.format(proximoCelo) + ")");
					} else if (diasHastaCelo >= 0) {
						tvEstadoCelo.setText("Estado: Celo próximo");
						tvEstadoCelo.setTextColor(Color.YELLOW);
						tvProximoCelo.setText("Celo en " + diasHastaCelo + " días (" + 
						sdf.format(proximoCelo) + ")");
						
						// Mostrar botón de registrar celo
						btnRegistrarCelo.setVisibility(View.VISIBLE);
					} else {
						// Está en período de celo
						long diasDesdeCelo = -diasHastaCelo;
						if (diasDesdeCelo <= 3) {
							tvEstadoCelo.setText("Estado: EN CELO ACTIVO");
							tvEstadoCelo.setTextColor(Color.RED);
							tvProximoCelo.setText("Celo activo (" + diasDesdeCelo + " días)");
							
							// Programar alertas para este ciclo
							gestorAlertas.programarAlertaCelo(
							conejoId,
							conejo.get("nombre").toString(),
							ultimoCelo
							);
						} else {
							tvEstadoCelo.setText("Estado: Celo reciente");
							tvEstadoCelo.setTextColor(Color.GREEN);
							tvProximoCelo.setText("Celo hace " + diasDesdeCelo + " días");
						}
						
						btnRegistrarCelo.setVisibility(View.VISIBLE);
					}
					
					// Hacer visible la sección de celo
					tvEstadoCelo.setVisibility(View.VISIBLE);
					tvProximoCelo.setVisibility(View.VISIBLE);
					
				} else {
					// No hay celos registrados
					tvEstadoCelo.setText("Estado: No hay celos registrados");
					tvEstadoCelo.setTextColor(Color.GRAY);
					tvProximoCelo.setText("Registre el primer celo");
					
					tvEstadoCelo.setVisibility(View.VISIBLE);
					tvProximoCelo.setVisibility(View.VISIBLE);
					btnRegistrarCelo.setVisibility(View.VISIBLE);
				}
				
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void _programarTodasAlertas() {
		//public void _programarTodasAlertas() {
		try {
			DatabaseHelper.Conejo conejoObj = dbHelper.obtenerConejoPorId(conejoId);
			if (conejoObj != null) {
				// 1. Alerta de madurez sexual
				gestorAlertas.programarAlertaMadurez(
				conejoId,
				conejoObj.nombre,
				conejoObj.sexo,
				conejoObj.fechaNacimiento
				);
				
				// 2. Alerta de próximo pesaje (si hay fecha de último peso)
				if (conejo.containsKey("fecha_ultimo_peso")) {
					String fechaUltimoPeso = conejo.get("fecha_ultimo_peso").toString();
					if (fechaUltimoPeso != null && !fechaUltimoPeso.isEmpty()) {
						gestorAlertas.programarAlertaPeso(
						conejoId,
						conejoObj.nombre,
						fechaUltimoPeso
						);
					}
				}
				
				// 3. Alertas de celo (si es hembra y tiene celos registrados)
				if (conejoObj.sexo.equals("H")) {
					SQLiteDatabase db = dbHelper.getReadableDatabase();
					Cursor cursor = db.rawQuery(
					"SELECT fecha_celo FROM celos WHERE hembra_id = ? ORDER BY fecha_celo DESC LIMIT 1",
					new String[]{String.valueOf(conejoId)}
					);
					
					if (cursor != null && cursor.moveToFirst()) {
						String ultimoCelo = cursor.getString(0);
						cursor.close();
						gestorAlertas.programarAlertaCelo(
						conejoId,
						conejoObj.nombre,
						ultimoCelo
						);
					}
					db.close();
				}
				
				// 4. Alertas de parto (si hay montas recientes)
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				Cursor cursorMontas = db.rawQuery(
				"SELECT fecha_monta FROM montas WHERE hembra_id = ? ORDER BY fecha_monta DESC LIMIT 1",
				new String[]{String.valueOf(conejoId)}
				);
				
				if (cursorMontas != null && cursorMontas.moveToFirst()) {
					String ultimaMonta = cursorMontas.getString(0);
					cursorMontas.close();
					gestorAlertas.programarAlertaParto(
					conejoId,
					conejoObj.nombre,
					ultimaMonta
					);
				}
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void _registrarCeloConAlerta() {
		//public void _registrarCeloConAlerta() {
		// Verificar que sea hembra
		if (!conejo.get("sexo").equals("H")) {
			Toast.makeText(this, "❌ Solo las hembras pueden registrar celos", Toast.LENGTH_SHORT).show();
			return;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("💖 Registrar Celo");
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setPadding(40, 30, 40, 30);
		
		// Campo para fecha
		final EditText etFecha = new EditText(this);
		etFecha.setHint("Fecha del celo (YYYY-MM-DD)");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		etFecha.setText(sdf.format(new Date()));
		
		// Campo para observaciones
		final EditText etObservaciones = new EditText(this);
		etObservaciones.setHint("Observaciones (opcional)");
		
		// CheckBox para síntomas
		final CheckBox cbSintomas = new CheckBox(this);
		cbSintomas.setText("Mostró inquietud e intención de montar");
		cbSintomas.setChecked(true);
		
		layout.addView(etFecha);
		layout.addView(etObservaciones);
		layout.addView(cbSintomas);
		
		builder.setView(layout);
		
		builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String fecha = etFecha.getText().toString();
				String observaciones = etObservaciones.getText().toString();
				boolean sintomas = cbSintomas.isChecked();
				
				if (fecha.isEmpty()) {
					Toast.makeText(DetallesActivity.this, "❌ Ingresa una fecha", Toast.LENGTH_SHORT).show();
					return;
				}
				
				try {
					// Agregar síntomas a las observaciones
					String obsCompleta = observaciones;
					if (sintomas) {
						if (obsCompleta.isEmpty()) {
							obsCompleta = "Mostró síntomas claros de celo";
						} else {
							obsCompleta += " - Mostró síntomas claros de celo";
						}
					}
					
					// Registrar en base de datos
					long resultado = dbHelper.registrarCelo(conejoId, fecha, obsCompleta);
					
					if (resultado != -1) {
						// Programar alerta para próximo celo (14-16 días)
						if (gestorAlertas != null) {
							gestorAlertas.programarAlertaCelo(
							conejoId,
							conejo.get("nombre").toString(),
							fecha
							);
						}
						
						Toast.makeText(DetallesActivity.this, "✅ Celo registrado exitosamente", Toast.LENGTH_SHORT).show();
						
						// Actualizar información
						_actualizarTodo();
						_calcularInfoCelo();
						
					} else {
						Toast.makeText(DetallesActivity.this, "❌ Error al registrar celo", Toast.LENGTH_SHORT).show();
					}
					
				} catch (Exception e) {
					Toast.makeText(DetallesActivity.this, "❌ Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		builder.setNegativeButton("Cancelar", null);
		builder.show();
		
	}
	
	
	public void _mostrarMenuRecordatorios() {
		//public void _mostrarMenuRecordatorios() {
		// Crear un diálogo con opciones
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("📅 Programar Recordatorio");
		
		String[] opciones = {
			"⏰ Recordatorio de Peso (mensual)",
			"💖 Recordatorio de Celo (cada 16 días)",
			"🐇 Recordatorio de Parto (31 días después de monta)",
			"📋 Recordatorio de Vacunación",
			"🍎 Recordatorio de Alimentación"
		};
		
		builder.setItems(opciones, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case 0: // Peso
					_programarRecordatorioPeso();
					break;
					case 1: // Celo
					_programarRecordatorioCelo();
					break;
					case 2: // Parto
					_programarRecordatorioParto();
					break;
					case 3: // Vacunación
					_programarRecordatorioVacunacion();
					break;
					case 4: // Alimentación
					_programarRecordatorioAlimentacion();
					break;
				}
			}
		});
		
		builder.setNegativeButton("Cancelar", null);
		builder.show();
	}
	
	
	public void _programarRecordatorioPeso() {
		//public void _programarRecordatorioPeso() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("⏰ Programar Recordatorio de Peso");
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setPadding(40, 30, 40, 30);
		
		final EditText etDias = new EditText(this);
		etDias.setHint("Cada cuántos días (ej: 30)");
		etDias.setInputType(InputType.TYPE_CLASS_NUMBER);
		etDias.setText("30");
		
		final EditText etMensaje = new EditText(this);
		etMensaje.setHint("Mensaje del recordatorio");
		etMensaje.setText("Recordatorio: Pesar a " + (conejo.containsKey("nombre") ? conejo.get("nombre") : "el conejo"));
		
		layout.addView(etDias);
		layout.addView(etMensaje);
		
		builder.setView(layout);
		
		builder.setPositiveButton("Programar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String diasStr = etDias.getText().toString();
				String mensaje = etMensaje.getText().toString();
				
				if (diasStr.isEmpty()) {
					Toast.makeText(DetallesActivity.this, "Ingresa el número de días", Toast.LENGTH_SHORT).show();
					return;
				}
				
				try {
					int dias = Integer.parseInt(diasStr);
					
					// Programar la alerta
					if (gestorAlertas != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
						String fechaHoy = sdf.format(new Date());
						
						// Notificación simple (en un sistema real usarías AlarmManager)
						gestorAlertas.mostrarNotificacion(
						"📅 Recordatorio Programado",
						"Recordatorio de peso cada " + dias + " días para " + conejo.get("nombre"),
						GestorAlertas.ALERTA_PESO
						);
					}
					
					Toast.makeText(DetallesActivity.this, 
					"✅ Recordatorio programado para cada " + dias + " días", 
					Toast.LENGTH_SHORT).show();
					
				} catch (NumberFormatException e) {
					Toast.makeText(DetallesActivity.this, "Número inválido", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		builder.setNegativeButton("Cancelar", null);
		builder.show();
	}
	
	public void _programarRecordatorioCelo() {
		Toast.makeText(this, "💖 Recordatorio de celo programado (cada 16 días)", Toast.LENGTH_SHORT).show();
		if (gestorAlertas != null && conejo.containsKey("nombre")) {
			gestorAlertas.mostrarNotificacion(
			"💖 Recordatorio de Celo",
			"Verificar celo de " + conejo.get("nombre"),
			GestorAlertas.ALERTA_CELO
			);
		}
	}
	
	public void _programarRecordatorioParto() {
		Toast.makeText(this, "🐇 Recordatorio de parto programado", Toast.LENGTH_SHORT).show();
	}
	
	public void _programarRecordatorioVacunacion() {
		Toast.makeText(this, "📋 Recordatorio de vacunación programado", Toast.LENGTH_SHORT).show();
	}
	
	public void _programarRecordatorioAlimentacion() {
		Toast.makeText(this, "🍎 Recordatorio de alimentación programado", Toast.LENGTH_SHORT).show();
		
	}
	
}