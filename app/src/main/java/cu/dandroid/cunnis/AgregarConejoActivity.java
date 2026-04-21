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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.prime.arab.ware.everythingutils.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;
import android.database.Cursor;  // For Cursor type
import android.database.sqlite.SQLiteDatabase;  // If using SQLite


public class AgregarConejoActivity extends Activity {
	
	private String sexoSeleccionado = "";
	private int madreId;
	private int padreId;
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int REQUEST_PICK_IMAGE = 2;
	private String fotoRuta = "";
	private String currentPhotoPath = "";
	private String fr = "";
	private static final int CODIGO_CAMARA = 1001;
	private static final int CODIGO_GALERIA = 1002;
	private static final int CODIGO_SOLICITUD_PERMISOS = 200;
	private File directorioFotosApp;
	private String nombreArchivoFoto = "";
	private File directorioBaseApp;
	final DatabaseHelper dbHelper = new DatabaseHelper(this);
	private static final String CARPETA_BASE = "/storage/emulated/0/Documents/Conejos";
	private static final String CARPETA_FOTOS = CARPETA_BASE + "/fotos";
	private static final String CARPETA_BACKUPS = CARPETA_BASE + "/backups";
	
	private LinearLayout linear13;
	private LinearLayout linear14;
	private LinearLayout linear16;
	private Button btnAtras;
	private TextView textview25;
	private View view15;
	private TextView textview26;
	private EditText etNombre;
	private TextView textview27;
	private TextView tvIdentificador;
	private TextView textview28;
	private Spinner spSexo;
	private TextView textview29;
	private EditText etRaza;
	private TextView textview30;
	private LinearLayout linear20;
	private TextView textview31;
	private EditText etPeso;
	private TextView textview32;
	private LinearLayout linear17;
	private TextView textview33;
	private TextView textview34;
	private Spinner spMadre;
	private TextView textview35;
	private Spinner spPadre;
	private LinearLayout linear18;
	private TextView textview36;
	private EditText etFechaNacimiento;
	private Button btnSeleccionarFecha;
	private ImageView ivFoto;
	private Button btnTomarFoto;
	private Button btnCancelar;
	private Button btnGuardar;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.agregar_conejo);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		linear16 = findViewById(R.id.linear16);
		btnAtras = findViewById(R.id.btnAtras);
		textview25 = findViewById(R.id.textview25);
		view15 = findViewById(R.id.view15);
		textview26 = findViewById(R.id.textview26);
		etNombre = findViewById(R.id.etNombre);
		textview27 = findViewById(R.id.textview27);
		tvIdentificador = findViewById(R.id.tvIdentificador);
		textview28 = findViewById(R.id.textview28);
		spSexo = findViewById(R.id.spSexo);
		textview29 = findViewById(R.id.textview29);
		etRaza = findViewById(R.id.etRaza);
		textview30 = findViewById(R.id.textview30);
		linear20 = findViewById(R.id.linear20);
		textview31 = findViewById(R.id.textview31);
		etPeso = findViewById(R.id.etPeso);
		textview32 = findViewById(R.id.textview32);
		linear17 = findViewById(R.id.linear17);
		textview33 = findViewById(R.id.textview33);
		textview34 = findViewById(R.id.textview34);
		spMadre = findViewById(R.id.spMadre);
		textview35 = findViewById(R.id.textview35);
		spPadre = findViewById(R.id.spPadre);
		linear18 = findViewById(R.id.linear18);
		textview36 = findViewById(R.id.textview36);
		etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
		btnSeleccionarFecha = findViewById(R.id.btnSeleccionarFecha);
		ivFoto = findViewById(R.id.ivFoto);
		btnTomarFoto = findViewById(R.id.btnTomarFoto);
		btnCancelar = findViewById(R.id.btnCancelar);
		btnGuardar = findViewById(R.id.btnGuardar);
		
		btnSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_mostrarDatePicker();
			}
		});
		
		ivFoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				// Mismo código que el botón de foto
				btnTomarFoto.performClick();
			}
		});
		
		btnTomarFoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				/*
String[] opciones = {"Tomar foto", "Elegir de galería", "Eliminar foto", "Cancelar"};

android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(AgregarConejoActivity.this);
builder.setTitle("Seleccionar foto");

builder.setItems(opciones, new DialogInterface.OnClickListener() {
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (which == 0) {

_tomarFoto();
        } else if (which == 1) {
_seleccionarFoto();
} else if (which == 2) {
	// Eliminar foto
	fotoRuta = "";
	ivFoto.setImageResource(R.drawable.avatar);
}
else{
    textview25.setText("pene");
}
}
}).show();

*/
				// Crear el diálogo correctamente
				AlertDialog.Builder builder = new AlertDialog.Builder(AgregarConejoActivity.this);
				builder.setTitle("Seleccionar foto");
				
				String[] opciones = {"Tomar foto", "Elegir de galería", "Eliminar foto", "Cancelar"};
				
				builder.setItems(opciones, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) {
							// Tomar foto
							_tomarFoto();
						} else if (which == 1) {
							// Elegir de galería
							_seleccionarFoto();
						} else if (which == 2) {
							// Eliminar foto
							fotoRuta = "";
							fr = "";
							ivFoto.setImageResource(R.drawable.avatar);
							Toast.makeText(AgregarConejoActivity.this, "Foto eliminada", Toast.LENGTH_SHORT).show();
						}
						// which == 3 es "Cancelar", no hacer nada
					}
				});
				
				// Crear y mostrar el diálogo
				AlertDialog dialog = builder.create();
				dialog.show();
				
				
				
			}
		});
		
		btnGuardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				btnGuardar.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// 1. Validar campos
						if (etNombre.getText().toString().isEmpty()) {
							etNombre.setError("El nombre es obligatorio");
							return;
						}
						
						if (etPeso.getText().toString().isEmpty()) {
							etPeso.setError("El peso es obligatorio");
							return;
						}
						
						// 2. Obtener valores
						String nombre = etNombre.getText().toString();
						String raza = etRaza.getText().toString();
						String fecha = etFechaNacimiento.getText().toString();
						String pesoStr = etPeso.getText().toString();
						
						// 3. Si fecha está vacía, usar fecha actual
						if (fecha.isEmpty()) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
							fecha = sdf.format(new Date());
						}
						
						// 4. Validar fecha
						if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
							Toast.makeText(AgregarConejoActivity.this, 
							"Formato de fecha inválido. Use YYYY-MM-DD", 
							Toast.LENGTH_LONG).show();
							return;
						}
						
						// 5. Convertir peso
						double peso;
						try {
							peso = Double.parseDouble(pesoStr);
						} catch (Exception e) {
							etPeso.setError("Peso inválido");
							return;
						}
						
						// 6. Guardar en base de datos (usando fr que es la ruta relativa)
						long id = dbHelper.agregarConejo(
						nombre,
						sexoSeleccionado,
						raza,
						fecha,
						peso,
						fr, // <-- Esto debería ser el nombre del archivo o ruta relativa
						madreId,
						padreId
						);
						
						// 7. Verificar resultado
						if (id != -1) {
							Toast.makeText(AgregarConejoActivity.this, 
							"¡Conejo registrado exitosamente!", 
							Toast.LENGTH_SHORT).show();
							
							// Regresar a lista
							Intent intent = new Intent();
							intent.setClass(AgregarConejoActivity.this, ConejosTodosActivity.class);
							startActivity(intent);
							finish();
						} else {
							Toast.makeText(AgregarConejoActivity.this, 
							"Error al registrar el conejo", 
							Toast.LENGTH_SHORT).show();
						}
					}
				});
				
			}
		});
	}
	
	private void initializeLogic() {
		
		// Crear estructura de carpetas
		File carpetaBase = new File(CARPETA_BASE);
		if (!carpetaBase.exists()) {
			carpetaBase.mkdirs();
		}
		
		File carpetaFotos = new File(CARPETA_FOTOS);
		if (!carpetaFotos.exists()) {
			carpetaFotos.mkdirs();
		}
		
		File carpetaBackups = new File(CARPETA_BACKUPS);
		if (!carpetaBackups.exists()) {
			carpetaBackups.mkdirs();
		}
		
		// Configurar spinner de sexo
		ArrayAdapter<String> sexoAdapter = new ArrayAdapter<>(
		this, 
		android.R.layout.simple_spinner_item, 
		new String[]{"Macho", "Hembra"}
		);
		sexoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spSexo.setAdapter(sexoAdapter);
		
		// Configurar spinner de madre (solo hembras)
		List<String> listaHembras = dbHelper.listarConejosActivos("H");
		listaHembras.add(0, "Seleccionar madre (opcional)");
		ArrayAdapter<String> madreAdapter = new ArrayAdapter<>(
		this,
		android.R.layout.simple_spinner_item,
		listaHembras
		);
		spMadre.setAdapter(madreAdapter);
		
		// Configurar spinner de padre (solo machos)
		List<String> listaMachos = dbHelper.listarConejosActivos("M");
		listaMachos.add(0, "Seleccionar padre (opcional)");
		ArrayAdapter<String> padreAdapter = new ArrayAdapter<>(
		this,
		android.R.layout.simple_spinner_item,
		listaMachos
		);
		spPadre.setAdapter(padreAdapter);
		spSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					sexoSeleccionado = "M";
				} else {
					sexoSeleccionado = "H";
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				sexoSeleccionado = "M";
			}
		});
		spMadre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position > 0) {
					String seleccion = (String) parent.getItemAtPosition(position);
					// Extraer ID del formato "ID|Nombre..."
					String[] partes = seleccion.split("\\|");
					try {
						madreId = Integer.parseInt(partes[0].trim());
					} catch (Exception e) {
						madreId = 0;
					}
				} else {
					madreId = 0;
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				madreId = 0;
			}
		});
		
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Crear AlertDialog
				AlertDialog.Builder builder = new AlertDialog.Builder(AgregarConejoActivity.this);
				builder.setTitle("Cancelar");
				builder.setMessage("¿Desea cancelar el registro? Se perderán los datos ingresados.");
				builder.setPositiveButton("Sí, cancelar", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
				builder.setNegativeButton("No, continuar", null);
				builder.show();
			}
		});
		
		btnAtras.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AgregarConejoActivity.this);
				builder.setTitle("Formato de fecha");
				builder.setMessage("Ingrese la fecha en formato YYYY-MM-DD\nEjemplo: 2024-01-15");
				builder.setPositiveButton("Entendido", null);
				builder.show();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		// Foto de cámara
		if (_requestCode == CODIGO_CAMARA && _resultCode == RESULT_OK) {
			if (_data != null && _data.getExtras() != null) {
				Bitmap fotoBitmap = (Bitmap) _data.getExtras().get("data");
				if (fotoBitmap != null && ivFoto != null) {
					ivFoto.setImageBitmap(fotoBitmap);
					
					// Guardar la foto en nuestra carpeta organizada
					try {
						String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
						String fileName = "CONEJO_" + timeStamp + ".jpg";
						
						// Asegurar que existe la carpeta
						File carpetaFotos = new File(CARPETA_FOTOS);
						if (!carpetaFotos.exists()) {
							carpetaFotos.mkdirs();
						}
						
						File archivoFoto = new File(carpetaFotos, fileName);
						FileOutputStream fos = new FileOutputStream(archivoFoto);
						fotoBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
						fos.close();
						
						// Guardar ruta absoluta y relativa
						fotoRuta = archivoFoto.getAbsolutePath();
						// Guardar ruta relativa: solo "fotos/nombre_archivo.jpg"
						fr = "fotos/" + fileName;
						
						Toast.makeText(this, "Foto guardada en " + CARPETA_FOTOS, Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(this, "Error al guardar foto", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
		
		// Foto de galería
		if (_requestCode == CODIGO_GALERIA && _resultCode == RESULT_OK) {
			if (_data != null) {
				Uri selectedImage = _data.getData();
				if (selectedImage != null) {
					try {
						// Mostrar preview
						ivFoto.setImageURI(selectedImage);
						
						// Copiar la imagen a nuestra carpeta organizada
						String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
						String fileName = "CONEJO_GAL_" + timeStamp + ".jpg";
						
						// Obtener ruta original
						String rutaOriginal = obtenerRutaReal(selectedImage);
						if (rutaOriginal != null) {
							// Copiar a nuestra carpeta
							File destino = new File(CARPETA_FOTOS, fileName);
							
							// Asegurar carpeta
							File carpetaFotos = new File(CARPETA_FOTOS);
							if (!carpetaFotos.exists()) {
								carpetaFotos.mkdirs();
							}
							
							// Copiar archivo
							FileUtil.copyFile(rutaOriginal, destino.getAbsolutePath());
							
							// Actualizar rutas
							fotoRuta = destino.getAbsolutePath();
							fr = "fotos/" + fileName;  // Ruta relativa
							
							Toast.makeText(this, "Foto copiada a " + CARPETA_FOTOS, Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(this, "Error al copiar foto", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _mostrarDatePicker() {
		//private void _mostrarDatePicker() {
		// Obtener la fecha actual para inicializar el DatePicker
		final Calendar calendario = Calendar.getInstance();
		int año = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		
		// Crear DatePickerDialog
		DatePickerDialog datePickerDialog = new DatePickerDialog(
		this,
		new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int añoSeleccionado, 
			int mesSeleccionado, int diaSeleccionado) {
				// Formatear la fecha como YYYY-MM-DD
				String fechaFormateada = String.format(Locale.getDefault(),
				"%04d-%02d-%02d", añoSeleccionado, (mesSeleccionado + 1), diaSeleccionado);
				etFechaNacimiento.setText(fechaFormateada);
			}
		},
		año, mes, dia
		);
		
		// Mostrar el diálogo
		datePickerDialog.show();
		
	}
	
	
	public void _tomarFoto() {
		//public void _tomarFoto() {
		// 1. Verificar permiso con método nativo
		if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			// Solicitar permiso
			requestPermissions(new String[]{Manifest.permission.CAMERA}, CODIGO_SOLICITUD_PERMISOS);
			return;
		}
		
		// 2. Si ya tiene permiso, abrir cámara
		Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (intentCamara.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intentCamara, CODIGO_CAMARA);
		} else {
			Toast.makeText(this, "No hay app de cámara", Toast.LENGTH_SHORT).show();
		}
		//}
	}
	
	
	public void _seleccionarFoto() {
		//public void _seleccionarFoto() {
		// Verificar permiso de lectura
		if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODIGO_SOLICITUD_PERMISOS);
			return;
		}
		
		Intent intentGaleria = new Intent(Intent.ACTION_PICK, 
		MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intentGaleria, CODIGO_GALERIA);
		
	}
	
	
	public void _unmetodo() {
	}
	// Método auxiliar para obtener ruta real desde Uri
	private String obtenerRutaReal(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		if (cursor != null) {
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			String path = cursor.getString(column_index);
			cursor.close();
			return path;
		}
		return uri.getPath();
	}{
		
	}
	
}