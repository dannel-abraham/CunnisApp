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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.prime.arab.ware.everythingutils.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;  // For Cursor type
import android.database.sqlite.SQLiteDatabase;  // If using SQLite
import android.widget.Toast;
import android.content.DialogInterface;
import cu.dandroid.cunnis.ConejosAdapter;

public class ConejosTodosActivity extends Activity {
	
	final DatabaseHelper dbHelper = new DatabaseHelper(this);;
	private List<Integer> listaIds = new ArrayList<>();
	int finalPosition;
	
	private LinearLayout linear21;
	private LinearLayout linear22;
	private LinearLayout linear28;
	private ImageButton btnMenu;
	private TextView tvTitulo;
	private ImageButton btnAgregar;
	private LinearLayout linear23;
	private View view24;
	private LinearLayout linear25;
	private View view26;
	private LinearLayout linear27;
	private TextView tvTotal;
	private TextView textview16;
	private TextView tvMachos;
	private TextView textview17;
	private TextView tvHembras;
	private TextView textview18;
	private TextView tvEmpty;
	private ListView listaConejos;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.conejos_todos);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear21 = findViewById(R.id.linear21);
		linear22 = findViewById(R.id.linear22);
		linear28 = findViewById(R.id.linear28);
		btnMenu = findViewById(R.id.btnMenu);
		tvTitulo = findViewById(R.id.tvTitulo);
		btnAgregar = findViewById(R.id.btnAgregar);
		linear23 = findViewById(R.id.linear23);
		view24 = findViewById(R.id.view24);
		linear25 = findViewById(R.id.linear25);
		view26 = findViewById(R.id.view26);
		linear27 = findViewById(R.id.linear27);
		tvTotal = findViewById(R.id.tvTotal);
		textview16 = findViewById(R.id.textview16);
		tvMachos = findViewById(R.id.tvMachos);
		textview17 = findViewById(R.id.textview17);
		tvHembras = findViewById(R.id.tvHembras);
		textview18 = findViewById(R.id.textview18);
		tvEmpty = findViewById(R.id.tvEmpty);
		listaConejos = findViewById(R.id.listaConejos);
		
		listaConejos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				// DEBUG: Verificar que se ejecuta
				System.out.println("DEBUG: Click en posición " + _position);
				
				// VERIFICAR que tenemos IDs
				if (listaIds == null || listaIds.isEmpty()) {
					System.out.println("ERROR: Lista de IDs vacía");
					Toast.makeText(ConejosTodosActivity.this, 
					"Error: Datos no disponibles", Toast.LENGTH_SHORT).show();
					return;
				}
				
				// VERIFICAR posición válida
				if (_position < 0 || _position >= listaIds.size()) {
					System.out.println("ERROR: Posición inválida: " + _position);
					return;
				}
				
				// OBTENER ID del conejo
				int conejoId = listaIds.get(_position);
				System.out.println("DEBUG: ID del conejo: " + conejoId);
				
				// VERIFICAR que DetallesActivity existe
				try {
					// Crear Intent
					Intent intent = new Intent();
					intent.setClass(ConejosTodosActivity.this, DetallesActivity.class);
					intent.putExtra("CONEJO_ID", conejoId);
					
					// DEBUG
					System.out.println("DEBUG: Intent creado para ID: " + conejoId);
					
					// Iniciar actividad
					startActivity(intent);
					
					// DEBUG
					System.out.println("DEBUG: Actividad iniciada");
					
				} catch (Exception e) {
					System.out.println("ERROR al abrir DetallesActivity: " + e.getMessage());
					e.printStackTrace();
					
					// Mostrar error al usuario
					Toast.makeText(ConejosTodosActivity.this, 
					"Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}
	
	private void initializeLogic() {
		_cargarEstadisticas();
		_cargarListaConejos();
		// En onCreate
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConejosTodosActivity.this, AgregarConejoActivity.class);
				startActivity(intent);
			}
		});
		/*
// En onCreate, después de cargarListaConejos()
listaConejos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Obtener el ID del conejo seleccionado
        List<Integer> ids = (List<Integer>) listaConejos.getTag();
        if (ids != null && position < ids.size()) {
            int conejoId = ids.get(position);
            // Abrir actividad de detalle
            Intent intent = new Intent(ConejosTodosActivity.this, DetallesActivity.class);
            intent.putExtra("CONEJO_ID", conejoId);
            startActivity(intent);
        }
    }
});
*/
		// En onCreate
		btnMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Crear menú emergente
				PopupMenu popup = new PopupMenu(ConejosTodosActivity.this, v);
				MenuInflater inflater = popup.getMenuInflater();
				inflater.inflate(R.menu.menu_principal, popup.getMenu());
				
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						int id = item.getItemId();
						
						if (id == R.id.menu_estadisticas) {
							Intent intent = new Intent(ConejosTodosActivity.this, EstadisticasActivity.class);
							startActivity(intent);
							return true;
						} else if (id == R.id.control_partos) {
							Intent intent = new Intent(ConejosTodosActivity.this, ControlPartosActivity.class);
							startActivity(intent);
							return true;   
						} else if (id == R.id.control_celos) {
							Intent intent = new Intent(ConejosTodosActivity.this, ControlCelosActivity.class);
							startActivity(intent);
							return true;     
						} else if (id == R.id.menu_buscar) {
							// Implementar búsqueda
							_mostrarDialogoBusqueda();
							return true;
						} else if (id == R.id.menu_configuracion) {
							Intent intent = new Intent(ConejosTodosActivity.this, ConfiguracionActivity.class);
							startActivity(intent);
							return true;
						} else if (id == R.id.menu_exportar) {
							_exportarDatos();
							return true;
						} else if (id == R.id.menu_backup) {
							_backup();
							return true;
						}
						return false;
					}
				});
				
				popup.show();
			}
		});
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_cargarEstadisticas();
		_cargarListaConejos();
	}
	
	public void _cargarListaConejos() {
		Cursor cursor = dbHelper.obtenerTodosConejosCursor();
		
		if (cursor == null || cursor.getCount() == 0) {
			tvEmpty.setVisibility(View.VISIBLE);
			listaConejos.setVisibility(View.GONE);
			if (cursor != null) cursor.close();
			return;
		}
		
		tvEmpty.setVisibility(View.GONE);
		listaConejos.setVisibility(View.VISIBLE);
		
		// LIMPIAR lista de IDs
		listaIds.clear();
		
		List<HashMap<String, Object>> datos = new ArrayList<>(); // Cambia a Object
		
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String nombre = cursor.getString(1);
			String identificador = cursor.getString(2);
			String sexo = cursor.getString(3);
			String raza = cursor.getString(4);
			String fechaNacimiento = cursor.getString(5);
			double peso = cursor.getDouble(6);
			String foto_ruta = cursor.getString(7);
			
			// GUARDAR ID en la lista
			listaIds.add(id);
			
			String edad = _calcularEdad(fechaNacimiento);
			String sexoTexto = sexo.equals("M") ? "♂ Macho" : "♀ Hembra";
			
			HashMap<String, Object> mapa = new HashMap<>(); // Cambia a Object
			mapa.put("nombre", nombre);
			mapa.put("identificador", "#" + identificador);
			mapa.put("sexo", sexoTexto);
			mapa.put("raza", raza.isEmpty() ? "Sin raza" : raza);
			mapa.put("peso", String.format("%.2f lb", peso));
			mapa.put("edad", edad);
			mapa.put("foto_ruta", foto_ruta);
			
			// Convertir ruta a Bitmap
			Bitmap fotoBitmap = null;
			if (foto_ruta != null && !foto_ruta.isEmpty()) {
				File imgFile = new File(foto_ruta);
				if (imgFile.exists()) {
					fotoBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				}
			}
			mapa.put("foto_bitmap", fotoBitmap); // Agregar Bitmap
			
			datos.add(mapa);
		}
		cursor.close();
		
		// Crear adaptador personalizado
		// Crear adaptador personalizado (SIN "public")
		ConejosAdapter adaptador = new ConejosAdapter(
		this,
		datos,
		R.layout.conejos_todos_remote,
		new String[]{"nombre", "identificador", "sexo", "raza", "peso", "edad", "foto_bitmap"},
		new int[]{R.id.nombre, R.id.identificador, R.id.sexo, R.id.raza, R.id.peso, R.id.edad, R.id.foto}
		);
		
		listaConejos.setAdapter(adaptador);
		
		
		
		// GUARDAR los IDs como TAG del ListView
		listaConejos.setTag(new ArrayList<>(listaIds));
	}
	
	
	public void _cargarEstadisticas() {
		// Si usas la clase Estadisticas como inner class:
		DatabaseHelper.Estadisticas stats = dbHelper.getEstadisticas();
		
		// Si getEstadisticas() no retorna un objeto Estadisticas,
		// usa los métodos individuales:
		int total = dbHelper.getTotalConejos();
		int machos = dbHelper.getTotalMachos();
		int hembras = dbHelper.getTotalHembras();
		
		tvTotal.setText(String.valueOf(total));
		tvMachos.setText(String.valueOf(machos));
		tvHembras.setText(String.valueOf(hembras));
		
	}
	
	
	public String _calcularEdad(final String _fechaNacimiento) {
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
			return "Edad desconocida";
		}
	}
	
	
	public void _mostrarDialogoBusqueda() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Buscar conejo");
		
		final EditText input = new EditText(this);
		input.setHint("Nombre o identificador");
		builder.setView(input);
		
		builder.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String busqueda = input.getText().toString();
				if (!busqueda.isEmpty()) {
					_buscarConejos(busqueda);
				}
			}
		});
		
		builder.setNegativeButton("Cancelar", null);
		builder.show();
		
	}
	
	
	public void _exportarDatos() {
		
	}
	
	
	public void _buscarConejos(final String _busqueda) {
		// Implementación básica de búsqueda
		if (_busqueda == null || _busqueda.trim().isEmpty()) {
			_cargarListaConejos(); // Recargar lista completa
			return;
		}
		
		// Buscar en la base de datos
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String query = "SELECT * FROM " + DatabaseHelper.TABLE_CONEJOS + 
		" WHERE " + DatabaseHelper.COLUMN_NOMBRE + " LIKE ? OR " + 
		DatabaseHelper.COLUMN_IDENTIFICADOR_UNICO + " LIKE ?";
		
		Cursor cursor = db.rawQuery(query, 
		new String[]{"%" + _busqueda + "%", "%" + _busqueda + "%"});
		
		// Procesar resultados y actualizar ListView
		// (Similar a cargarListaConejos pero con este cursor)
		
		cursor.close();
		
	}
	
	
	public void _backup() {
		try {
			Zip.zipFolder("/storage/emulated/0/Documents/conejos/fotos","/storage/emulated/0/Documents/conejos/" + "fotos.zip");
		} catch(Exception e) {}
		try {
			Zip.zipFolder("/storage/emulated/0/Documents/conejos","/storage/emulated/0/Documents/" + "conejos.zip");
		} catch(Exception e) {}
		SketchwareUtil.showMessage(getApplicationContext(), "Guardado!");
	}
	
	
	public void _libs() {
	}
	
	
	/*MADE BY ARAB WARE CHANNEL*/
	/*WHAT IS MADE ? ===>ADD FOLDER TO ZIP*/
	
	public void ArabWareAddFolderToZip (String _from,String _to) {
		if (FileUtil.isExistFile(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"))) {
			new UnZip().unZipIt(_to, _to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"));
			new java.io.File(_from).renameTo(new java.io.File(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/").concat(Uri.parse(_from).getLastPathSegment())));
			try {
				Zip.zipFolder(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"),_to.replace(Uri.parse(_to).getLastPathSegment(), "") + Uri.parse(_to).getLastPathSegment());
			} catch(Exception e) {}
			FileUtil.deleteFile(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"));
		}
		else {
			FileUtil.makeDir(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"));
			new UnZip().unZipIt(_to, _to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"));
			new java.io.File(_from).renameTo(new java.io.File(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/").concat(Uri.parse(_from).getLastPathSegment())));
			try {
				Zip.zipFolder(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"),_to.replace(Uri.parse(_to).getLastPathSegment(), "") + Uri.parse(_to).getLastPathSegment());
			} catch(Exception e) {}
			FileUtil.deleteFile(_to.replace(Uri.parse(_to).getLastPathSegment(), "").concat("files/"));
		}
	}
	
	
	
	public void addFilesToZip(java.io.File source, java.io.File files)
	{
		try
		{
			
			
			byte[] buffer = new byte[1024];
			java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(files));
			java.util.zip.ZipOutputStream out = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(source));
			java.io.InputStream in = new java.io.FileInputStream(files);
			out.putNextEntry(new java.util.zip.ZipEntry(files.getName()));
			for(int read = in.read(buffer); read > -1; read = in.read(buffer))
			{
				out.write(buffer, 0, read);
			}
			out.closeEntry();
			in.close();
			
			for(java.util.zip.ZipEntry ze = zin.getNextEntry(); ze != null; ze = zin.getNextEntry())
			{
				out.putNextEntry(ze);
				for(int read = zin.read(buffer); read > -1; read = zin.read(buffer))
				{
					out.write(buffer, 0, read);
				}
				out.closeEntry();
			}
			
			out.close();
			
		}
		catch(Exception e)
		{
			//showMessage(e.getMessage());
		}
	}
	public static class Zip {
		
		public static void zipFolder(String str, String str2) throws Exception {
			java.util.zip.ZipOutputStream zipOutputStream = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(str2));
			addFolderToZip("", str, zipOutputStream);
			zipOutputStream.flush();
			zipOutputStream.close();
		}
		
		
		public static void addFolderToZip(String str, String str2, java.util.zip.ZipOutputStream zipOutputStream) throws Exception {
			java.io.File file = new java.io.File(str2);
			for (String str3 : file.list()) {
				if (str.equals("")) {
					addFileToZip(file.getName(), new StringBuilder(String.valueOf(str2)).append("/").append(str3).toString(), zipOutputStream);
				} else {
					addFileToZip(new StringBuilder(String.valueOf(str)).append("/").append(file.getName()).toString(), new StringBuilder(String.valueOf(str2)).append("/").append(str3).toString(), zipOutputStream);
				}
			}
		}
		
		
		
		public static void addFileToZip(String path, String srcFile, java.util.zip.ZipOutputStream zip)
		throws Exception {
			java.io.File folder = new java.io.File(srcFile);
			if (folder.isDirectory()) {
				
			} else {
				byte[] buf = new byte[1024];
				int len;
				java.io.FileInputStream in = new java.io.FileInputStream(srcFile);
				zip.putNextEntry(new java.util.zip.ZipEntry(path + "/" + folder.getName()));
				while ((len = in.read(buf)) > 0) {
					zip.write(buf, 0, len);
				}
			}
		}
	}
	
	public static class UnZip {
		List<String> fileList;
		
		public void unZipIt(String zipFile, String outputFolder){
			byte[] buffer = new byte[1024];
			try{
				java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(new java.io.FileInputStream(zipFile));
				java.util.zip.ZipEntry ze = zis.getNextEntry();
				while(ze!=null){
					String fileName = ze.getName();
					java.io.File newFile = new java.io.File(outputFolder + java.io.File.separator + fileName);
					new java.io.File(newFile.getParent()).mkdirs();
					java.io.FileOutputStream fos = new java.io.FileOutputStream(newFile);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					ze = zis.getNextEntry(); 
				}
				zis.closeEntry();
				zis.close();
			}catch(java.io.IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
}