package cu.dandroid.cunnis;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
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
import android.widget.ScrollView;
import android.widget.Spinner;
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
import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
// Las siguientes clases NO se necesitan si usas los métodos nativos

public class EditarActivity extends Activity {
	
	public final int REQ_CD_FP = 101;
	
	int conejoId;
	private String currentPhotoPath = "";
	final DatabaseHelper dbHelper = new DatabaseHelper(this);
	private static final int CODIGO_CAMARA = 1001;
	private static final int CODIGO_GALERIA = 1002;
	private static final int CODIGO_SOLICITUD_PERMISOS = 200;
	private static final String CARPETA_BASE = "/storage/emulated/0/Documents/Conejos";
	private static final String CARPETA_FOTOS = CARPETA_BASE + "/fotos";
	private String fotoRuta = "";
	private String fr = "";
	
	private LinearLayout linear14;
	private ScrollView vscroll2;
	private Button btnAtras;
	private TextView textview25;
	private TextView tvIdentificador;
	private LinearLayout linear13;
	private LinearLayout cardview7;
	private LinearLayout cardview8;
	private LinearLayout cardview9;
	private LinearLayout linear46;
	private TextView textview79;
	private LinearLayout linear40;
	private TextView textview67;
	private TextView textview68;
	private EditText etNombre;
	private TextView textview69;
	private TextView tvIdUnico;
	private TextView textview70;
	private RadioGroup rgSexo;
	private TextView textview71;
	private EditText etRaza;
	private TextView textview72;
	private LinearLayout linear41;
	private TextView textview73;
	private EditText etPeso;
	private RadioButton rbMacho;
	private RadioButton rbHembra;
	private EditText etFechaNacimiento;
	private Button btnSeleccionarFecha;
	private LinearLayout linear42;
	private TextView textview74;
	private LinearLayout linear43;
	private ImageView ivFoto;
	private LinearLayout linear44;
	private Button btnEliminarFoto;
	private Button btnTomarFoto;
	private Button btnSeleccionarFoto;
	private LinearLayout linear45;
	private TextView textview75;
	private TextView textview76;
	private Spinner spMadre;
	private TextView textview77;
	private Spinner spPadre;
	private TextView textview78;
	private Button btnCancelar;
	private Button btnGuardar;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editar);
		initialize(_savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
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
		linear14 = findViewById(R.id.linear14);
		vscroll2 = findViewById(R.id.vscroll2);
		btnAtras = findViewById(R.id.btnAtras);
		textview25 = findViewById(R.id.textview25);
		tvIdentificador = findViewById(R.id.tvIdentificador);
		linear13 = findViewById(R.id.linear13);
		cardview7 = findViewById(R.id.cardview7);
		cardview8 = findViewById(R.id.cardview8);
		cardview9 = findViewById(R.id.cardview9);
		linear46 = findViewById(R.id.linear46);
		textview79 = findViewById(R.id.textview79);
		linear40 = findViewById(R.id.linear40);
		textview67 = findViewById(R.id.textview67);
		textview68 = findViewById(R.id.textview68);
		etNombre = findViewById(R.id.etNombre);
		textview69 = findViewById(R.id.textview69);
		tvIdUnico = findViewById(R.id.tvIdUnico);
		textview70 = findViewById(R.id.textview70);
		rgSexo = findViewById(R.id.rgSexo);
		textview71 = findViewById(R.id.textview71);
		etRaza = findViewById(R.id.etRaza);
		textview72 = findViewById(R.id.textview72);
		linear41 = findViewById(R.id.linear41);
		textview73 = findViewById(R.id.textview73);
		etPeso = findViewById(R.id.etPeso);
		rbMacho = findViewById(R.id.rbMacho);
		rbHembra = findViewById(R.id.rbHembra);
		etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
		btnSeleccionarFecha = findViewById(R.id.btnSeleccionarFecha);
		linear42 = findViewById(R.id.linear42);
		textview74 = findViewById(R.id.textview74);
		linear43 = findViewById(R.id.linear43);
		ivFoto = findViewById(R.id.ivFoto);
		linear44 = findViewById(R.id.linear44);
		btnEliminarFoto = findViewById(R.id.btnEliminarFoto);
		btnTomarFoto = findViewById(R.id.btnTomarFoto);
		btnSeleccionarFoto = findViewById(R.id.btnSeleccionarFoto);
		linear45 = findViewById(R.id.linear45);
		textview75 = findViewById(R.id.textview75);
		textview76 = findViewById(R.id.textview76);
		spMadre = findViewById(R.id.spMadre);
		textview77 = findViewById(R.id.textview77);
		spPadre = findViewById(R.id.spPadre);
		textview78 = findViewById(R.id.textview78);
		btnCancelar = findViewById(R.id.btnCancelar);
		btnGuardar = findViewById(R.id.btnGuardar);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		btnAtras.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		//DatabaseHelper.Conejo conejo = dbHelper.obtenerConejoPorId(conejoId);
		//setContentView(R.layout.editar_layout);
		
		// Obtener el ID del conejo
		conejoId = getIntent().getIntExtra("ID_CONEJO", -1);
		if (conejoId == -1) {
			conejoId = getIntent().getIntExtra("conejo_id", -1);
		}
		
		if (conejoId == -1) {
			Toast.makeText(this, "Error: No se especificó el conejo", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		// Inicializar base de datos
		//dbHelper = new DatabaseHelper(this);
		
		// Inicializar vistas
		//initializeViews();
		
		// Cargar datos
		_cargarDatosConejo();
		_cargarListaPadres();
		//_configurarEventos();
		
		//configurar eventos
		// Botón Cancelar
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		// Botón Guardar
		btnGuardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_guardarCambios();
			}
		});
		
		// Botón Seleccionar Fecha
		btnSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_mostrarDatePicker();
			}
		});
		
		// Botón Tomar Foto
		// Configuración CORRECTA del botón Tomar Foto
		btnTomarFoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Aquí solo debe ir la llamada a tu método
				_tomarFoto();
			}
		});
		// Botón Seleccionar Foto
		btnSeleccionarFoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_seleccionarFoto();
			}
		});
		
		// Botón Eliminar Foto
		btnEliminarFoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_eliminarFoto();
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		/*
// @Override
//rotected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
  //  super.onActivityResult(_requestCode, _resultCode, _data);
    
    // Manejar foto de cámara
    if (_requestCode == CODIGO_CAMARA && _resultCode == RESULT_OK) {
        if (_data != null && _data.getExtras() != null) {
            Bitmap fotoBitmap = (Bitmap) _data.getExtras().get("data");
            if (fotoBitmap != null && ivFoto != null) {
                ivFoto.setImageBitmap(fotoBitmap);
                
                // Opcional: Guardar la foto en un archivo
                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    String fileName = "CONEJO_" + conejoId + "_" + timeStamp + ".jpg";
                    
                    File directorio = new File(getExternalFilesDir(null), "fotos_conejos");
                    if (!directorio.exists()) directorio.mkdirs();
                    
                    File archivoFoto = new File(directorio, fileName);
                    FileOutputStream fos = new FileOutputStream(archivoFoto);
                    fotoBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                    fos.close();
                    
                    // Guardar ruta para la base de datos
                    currentPhotoPath = archivoFoto.getAbsolutePath();
                    
                    Toast.makeText(this, "Foto guardada", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Manejar selección de foto de galería
    if (_requestCode == CODIGO_GALERIA && _resultCode == RESULT_OK) {
        if (_data != null) {
            Uri selectedImageUri = _data.getData();
            if (selectedImageUri != null) {
                ivFoto.setImageURI(selectedImageUri);
            }
        }
    }
*/
		// Foto de cámara
		if (_requestCode == CODIGO_CAMARA && _resultCode == RESULT_OK) {
			if (_data != null && _data.getExtras() != null) {
				Bitmap fotoBitmap = (Bitmap) _data.getExtras().get("data");
				if (fotoBitmap != null && ivFoto != null) {
					ivFoto.setImageBitmap(fotoBitmap);
					
					// Guardar la foto en nuestra carpeta organizada (igual que en AgregarConejoActivity)
					try {
						String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
						String fileName = "CONEJO_" + conejoId + "_" + timeStamp + ".jpg";
						
						// Asegurar que existe la carpeta
						File carpetaFotos = new File(CARPETA_FOTOS);
						if (!carpetaFotos.exists()) {
							carpetaFotos.mkdirs();
						}
						
						File archivoFoto = new File(carpetaFotos, fileName);
						FileOutputStream fos = new FileOutputStream(archivoFoto);
						fotoBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
						fos.close();
						
						// Guardar rutas (igual que en AgregarConejoActivity)
						fotoRuta = archivoFoto.getAbsolutePath();
						fr = "fotos/" + fileName; // Ruta relativa para la BD
						
						Toast.makeText(this, "Foto guardada", Toast.LENGTH_SHORT).show();
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
						
						// Copiar la imagen a nuestra carpeta organizada (igual que en AgregarConejoActivity)
						String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
						String fileName = "CONEJO_GAL_" + conejoId + "_" + timeStamp + ".jpg";
						
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
							
							Toast.makeText(this, "Foto copiada", Toast.LENGTH_SHORT).show();
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
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
		
	}
	public void _cargarDatosConejo() {
		/*
//private void _cargarDatosConejo() {
DatabaseHelper.Conejo conejo = dbHelper.obtenerConejoPorId(conejoId);

if (conejo != null) {
	// DEPURACIÓN: Verificar que encontramos el conejo
	Log.d("DEBUG", "Conejo encontrado: " + conejo.nombre + " Sexo: " + conejo.sexo);
	
	etNombre.setText(conejo.nombre);
	tvIdUnico.setText(conejo.identificador);
	
	// DEPURACIÓN: Verificar que las vistas existen
	if (rgSexo == null) {
		Log.e("DEBUG", "rgSexo es NULL!");
	}
	if (rbMacho == null) {
		Log.e("DEBUG", "rbMacho es NULL!");
	}
	if (rbHembra == null) {
		Log.e("DEBUG", "rbHembra es NULL!");
	}
	
	// Sexo - Método mejorado
	if (conejo.sexo.equals("M")) {
		if (rbMacho != null) {
			rbMacho.setChecked(true);
			rbHembra.setChecked(false); // Asegurar que el otro esté desmarcado
		} else {
			// Fallback: usar TextView temporal
			Toast.makeText(this, "Machorro", Toast.LENGTH_SHORT).show();
		}
	} else {
		if (rbHembra != null) {
			rbHembra.setChecked(true);
			rbMacho.setChecked(false);
		} else {
			Toast.makeText(this, "Hembrita", Toast.LENGTH_SHORT).show();
		}
	}
	
	etRaza.setText(conejo.raza);
	etFechaNacimiento.setText(conejo.fechaNacimiento);
	etPeso.setText(String.valueOf(conejo.peso));
	
	// Foto
	if (conejo.fotoRuta != null && !conejo.fotoRuta.isEmpty()) {
		currentPhotoPath = conejo.fotoRuta;
		_cargarFoto(conejo.fotoRuta);
	}
} else {
	Log.e("DEBUG", "Conejo es NULL para ID: " + conejoId);
}

*/
		//public void _cargarDatosConejo() {
		DatabaseHelper.Conejo conejo = dbHelper.obtenerConejoPorId(conejoId);
		
		if (conejo != null) {
			etNombre.setText(conejo.nombre);
			tvIdUnico.setText(conejo.identificador);
			
			// Sexo
			if (conejo.sexo.equals("M")) {
				rbMacho.setChecked(true);
			} else {
				rbHembra.setChecked(true);
			}
			
			etRaza.setText(conejo.raza);
			etFechaNacimiento.setText(conejo.fechaNacimiento);
			etPeso.setText(String.valueOf(conejo.peso));
			
			// Foto - manejar rutas absolutas y relativas
			if (conejo.fotoRuta != null && !conejo.fotoRuta.isEmpty()) {
				// Si la ruta empieza con "fotos/", es una ruta relativa
				if (conejo.fotoRuta.startsWith("fotos/")) {
					fr = conejo.fotoRuta; // Guardar ruta relativa
					fotoRuta = CARPETA_BASE + "/" + conejo.fotoRuta; // Construir ruta absoluta
				} else {
					// Es una ruta absoluta antigua
					fotoRuta = conejo.fotoRuta;
					fr = ""; // No tenemos ruta relativa
				}
				
				// Cargar la foto
				_cargarFoto(fotoRuta);
			} else {
				fotoRuta = "";
				fr = "";
				ivFoto.setImageResource(R.drawable.avatar); // Imagen por defecto
			}
		}
		
	}
	
	
	public void _cargarFoto(final String _fotoRuta) {
		//private void _cargarFoto(String fotoRuta) {
		try {
			File imgFile = new File(_fotoRuta);
			if (imgFile.exists()) {
				Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				ivFoto.setImageBitmap(bitmap);
			}
		} catch (Exception e) {
			Toast.makeText(this, "Error al cargar foto", Toast.LENGTH_SHORT).show();
		}
		//}
		
	}
	
	
	public void _cargarListaPadres() {
		//public void _cargarListaPadres() {
		// Cargar madres (hembras activas) con opción "No especificado"
		List<String> madres = dbHelper.listarConejosActivos("H");
		
		// Crear lista con opción "No especificado" al principio
		List<String> listaMadres = new ArrayList<>();
		listaMadres.add("No especificado"); // Opción por defecto
		if (madres != null) {
			listaMadres.addAll(madres);
		}
		
		ArrayAdapter<String> adapterMadres = new ArrayAdapter<>(this, 
		android.R.layout.simple_spinner_item, listaMadres);
		adapterMadres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spMadre.setAdapter(adapterMadres);
		
		// Cargar padres (machos activos) con opción "No especificado"
		List<String> padres = dbHelper.listarConejosActivos("M");
		
		// Crear lista con opción "No especificado" al principio
		List<String> listaPadres = new ArrayList<>();
		listaPadres.add("No especificado"); // Opción por defecto
		if (padres != null) {
			listaPadres.addAll(padres);
		}
		
		ArrayAdapter<String> adapterPadres = new ArrayAdapter<>(this, 
		android.R.layout.simple_spinner_item, listaPadres);
		adapterPadres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spPadre.setAdapter(adapterPadres);
		
		// Seleccionar madre y padre actuales (si existen)
		DatabaseHelper.Conejo conejo = dbHelper.obtenerConejoPorId(conejoId);
		if (conejo != null) {
			// Madre
			if (conejo.madreId > 0) {
				for (int i = 0; i < listaMadres.size(); i++) {
					String item = listaMadres.get(i);
					// Formato: "id|nombre (identificador) | sexo | raza | Edad: ..."
					// Extraer solo el ID (antes del primer "|")
					String[] partes = item.split("\\|");
					if (partes.length > 0) {
						try {
							int idItem = Integer.parseInt(partes[0].trim());
							if (idItem == conejo.madreId) {
								spMadre.setSelection(i);
								break;
							}
						} catch (Exception e) {
							// Ignorar si no es un número
						}
					}
				}
			} else {
				spMadre.setSelection(0); // Seleccionar "No especificado"
			}
			
			// Padre
			if (conejo.padreId > 0) {
				for (int i = 0; i < listaPadres.size(); i++) {
					String item = listaPadres.get(i);
					// Formato: "id|nombre (identificador) | sexo | raza | Edad: ..."
					String[] partes = item.split("\\|");
					if (partes.length > 0) {
						try {
							int idItem = Integer.parseInt(partes[0].trim());
							if (idItem == conejo.padreId) {
								spPadre.setSelection(i);
								break;
							}
						} catch (Exception e) {
							// Ignorar si no es un número
						}
					}
				}
			} else {
				spPadre.setSelection(0); // Seleccionar "No especificado"
			}
		}
		
	}
	
	
	public void _guardarCambios() {
		/*
//ublic void _guardarCambios() {
    // Validar campos
    String nombre = etNombre.getText().toString().trim();
    String fecha = etFechaNacimiento.getText().toString().trim();
    String pesoStr = etPeso.getText().toString().trim();
    
    if (nombre.isEmpty() || fecha.isEmpty() || pesoStr.isEmpty()) {
        Toast.makeText(this, "Complete campos obligatorios (*)", Toast.LENGTH_SHORT).show();
        return;
    }
    
    // Validar peso
    double peso;
    try {
        peso = Double.parseDouble(pesoStr);
    } catch (Exception e) {
        Toast.makeText(this, "Peso inválido. Use: 2.5", Toast.LENGTH_SHORT).show();
        return;
    }
    
    // Obtener sexo
    String sexo = "";
    if (rbMacho.isChecked()) {
        sexo = "M";
    } else if (rbHembra.isChecked()) {
        sexo = "H";
    }
    
    if (sexo.isEmpty()) {
        Toast.makeText(this, "Seleccione sexo", Toast.LENGTH_SHORT).show();
        return;
    }
    
    // Obtener raza
    String raza = etRaza.getText().toString().trim();
    
    // Obtener madre - CON MANEJO DE "NO ESPECIFICADO"
    int madreId = 0;
    String madreStr = (String) spMadre.getSelectedItem();
    if (madreStr != null && !madreStr.isEmpty() && !madreStr.equals("No especificado")) {
        // Formato: "id|nombre (identificador) | sexo | raza | Edad: ..."
        String[] partes = madreStr.split("\\|");
        if (partes.length > 0) {
            try {
                // Obtener solo el número ID (antes del primer "|")
                String idPart = partes[0].trim();
                madreId = Integer.parseInt(idPart);
            } catch (Exception e) {
                madreId = 0;
            }
        }
    }
    
    // Obtener padre - CON MANEJO DE "NO ESPECIFICADO"
    int padreId = 0;
    String padreStr = (String) spPadre.getSelectedItem();
    if (padreStr != null && !padreStr.isEmpty() && !padreStr.equals("No especificado")) {
        // Formato: "id|nombre (identificador) | sexo | raza | Edad: ..."
        String[] partes = padreStr.split("\\|");
        if (partes.length > 0) {
            try {
                // Obtener solo el número ID (antes del primer "|")
                String idPart = partes[0].trim();
                padreId = Integer.parseInt(idPart);
            } catch (Exception e) {
                padreId = 0;
            }
        }
    }
    
    // Actualizar en base de datos
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    android.content.ContentValues values = new android.content.ContentValues();
    
    values.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
    values.put(DatabaseHelper.COLUMN_SEXO, sexo);
    values.put(DatabaseHelper.COLUMN_RAZA, raza);
    values.put(DatabaseHelper.COLUMN_FECHA_NACIMIENTO, fecha);
    values.put(DatabaseHelper.COLUMN_PESO_ACTUAL, peso);
    
    if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
        values.put(DatabaseHelper.COLUMN_FOTO_RUTA, currentPhotoPath);
    } else if (currentPhotoPath == null) {
        values.putNull(DatabaseHelper.COLUMN_FOTO_RUTA);
    }
    
    // Manejar madreId (puede ser 0 = no especificado)
    if (madreId > 0) {
        values.put(DatabaseHelper.COLUMN_MADRE_ID, madreId);
    } else {
        values.putNull(DatabaseHelper.COLUMN_MADRE_ID);
    }
    
    // Manejar padreId (puede ser 0 = no especificado)
    if (padreId > 0) {
        values.put(DatabaseHelper.COLUMN_PADRE_ID, padreId);
    } else {
        values.putNull(DatabaseHelper.COLUMN_PADRE_ID);
    }
    
    int rows = db.update(DatabaseHelper.TABLE_CONEJOS, 
        values, 
        DatabaseHelper.COLUMN_ID + " = ?", 
        new String[]{String.valueOf(conejoId)});
    
    db.close();
    
    if (rows > 0) {
        Toast.makeText(this, "Conejo actualizado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    } else {
        Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
    }

*/
		//public void _guardarCambios() {
		// Validar campos
		String nombre = etNombre.getText().toString().trim();
		String fecha = etFechaNacimiento.getText().toString().trim();
		String pesoStr = etPeso.getText().toString().trim();
		
		if (nombre.isEmpty() || fecha.isEmpty() || pesoStr.isEmpty()) {
			Toast.makeText(this, "Complete campos obligatorios (*)", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Validar peso
		double peso;
		try {
			peso = Double.parseDouble(pesoStr);
		} catch (Exception e) {
			Toast.makeText(this, "Peso inválido. Use: 2.5", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Obtener sexo
		String sexo = "";
		if (rbMacho.isChecked()) {
			sexo = "M";
		} else if (rbHembra.isChecked()) {
			sexo = "H";
		}
		
		if (sexo.isEmpty()) {
			Toast.makeText(this, "Seleccione sexo", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Obtener raza
		String raza = etRaza.getText().toString().trim();
		
		// Obtener madre
		int madreId = 0;
		String madreStr = (String) spMadre.getSelectedItem();
		if (madreStr != null && !madreStr.isEmpty() && !madreStr.equals("No especificado")) {
			String[] partes = madreStr.split("\\|");
			if (partes.length > 0) {
				try {
					madreId = Integer.parseInt(partes[0].trim());
				} catch (Exception e) {
					madreId = 0;
				}
			}
		}
		
		// Obtener padre
		int padreId = 0;
		String padreStr = (String) spPadre.getSelectedItem();
		if (padreStr != null && !padreStr.isEmpty() && !padreStr.equals("No especificado")) {
			String[] partes = padreStr.split("\\|");
			if (partes.length > 0) {
				try {
					padreId = Integer.parseInt(partes[0].trim());
				} catch (Exception e) {
					padreId = 0;
				}
			}
		}
		
		// Actualizar en base de datos - CÓDIGO CORREGIDO
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		android.content.ContentValues values = new android.content.ContentValues();
		
		values.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
		values.put(DatabaseHelper.COLUMN_SEXO, sexo);
		values.put(DatabaseHelper.COLUMN_RAZA, raza);
		values.put(DatabaseHelper.COLUMN_FECHA_NACIMIENTO, fecha);
		values.put(DatabaseHelper.COLUMN_PESO_ACTUAL, peso);
		
		// Usar la ruta relativa (fr) si existe, si no, usar la absoluta (fotoRuta)
		String rutaFotoParaBD = fr.isEmpty() ? fotoRuta : fr;
		if (rutaFotoParaBD != null && !rutaFotoParaBD.isEmpty()) {
			values.put(DatabaseHelper.COLUMN_FOTO_RUTA, rutaFotoParaBD);
		} else {
			values.putNull(DatabaseHelper.COLUMN_FOTO_RUTA);
		}
		
		// Manejar madreId (puede ser 0 = no especificado)
		if (madreId > 0) {
			values.put(DatabaseHelper.COLUMN_MADRE_ID, madreId);
		} else {
			values.putNull(DatabaseHelper.COLUMN_MADRE_ID);
		}
		
		// Manejar padreId (puede ser 0 = no especificado)
		if (padreId > 0) {
			values.put(DatabaseHelper.COLUMN_PADRE_ID, padreId);
		} else {
			values.putNull(DatabaseHelper.COLUMN_PADRE_ID);
		}
		
		int rows = db.update(DatabaseHelper.TABLE_CONEJOS, 
		values, 
		DatabaseHelper.COLUMN_ID + " = ?", 
		new String[]{String.valueOf(conejoId)});
		
		db.close();
		
		if (rows > 0) {
			Toast.makeText(this, "Conejo actualizado", Toast.LENGTH_SHORT).show();
			setResult(RESULT_OK);
			finish();
		} else {
			Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public void _tomarFoto() {
		//public void _tomarFoto() {
		// 1. Verificar permiso con método nativo
		if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			// Solicitar permiso
			requestPermissions(new String[]{Manifest.permission.CAMERA}, CODIGO_SOLICITUD_PERMISOS);
			return; // Esperar respuesta
		}
		
		// 2. Si ya tiene permiso, abrir cámara
		Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (intentCamara.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intentCamara, CODIGO_CAMARA);
		} else {
			Toast.makeText(this, "No hay app de cámara", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public void _seleccionarFoto() {
		Intent intentGaleria = new Intent(Intent.ACTION_PICK, 
		MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intentGaleria, CODIGO_GALERIA);
		
	}
	
	
	public void _eliminarFoto() {
		/*
//public void _eliminarFoto() {
    currentPhotoPath = null;
    ivFoto.setImageResource(android.R.drawable.ic_menu_gallery);
    Toast.makeText(this, "Foto eliminada", Toast.LENGTH_SHORT).show();

*/
		//public void _eliminarFoto() {
		// Si hay una foto guardada físicamente, opcionalmente puedes borrarla
		if (!fotoRuta.isEmpty()) {
			File fotoFile = new File(fotoRuta);
			if (fotoFile.exists()) {
				fotoFile.delete();
			}
		}
		
		// Limpiar las rutas
		fotoRuta = "";
		fr = "";
		
		// Poner imagen por defecto
		ivFoto.setImageResource(R.drawable.avatar); // Asegúrate de tener este drawable
		Toast.makeText(this, "Foto eliminada", Toast.LENGTH_SHORT).show();
		
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
	
	
	public void _libreo() {
	}
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
	}
	{
	}
	
}