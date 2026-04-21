package cu.dandroid.cunnis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "/storage/emulated/0/Documents/Conejos/crianza_conejos.db";
    private static final int DATABASE_VERSION = 2;
    
    // Tabla conejos
    public static final String TABLE_CONEJOS = "conejos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_IDENTIFICADOR_UNICO = "identificador_unico";
    public static final String COLUMN_SEXO = "sexo";
    public static final String COLUMN_RAZA = "raza";
    public static final String COLUMN_FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String COLUMN_FECHA_FALLECIMIENTO = "fecha_fallecimiento";
    public static final String COLUMN_MOTIVO_RETIRO = "motivo_retiro";
    public static final String COLUMN_PESO_ACTUAL = "peso_actual";
    public static final String COLUMN_FOTO_RUTA = "foto_ruta";
    public static final String COLUMN_MADRE_ID = "madre_id";
    public static final String COLUMN_PADRE_ID = "padre_id";
    public static final String COLUMN_FECHA_REGISTRO = "fecha_registro";
    public static final String COLUMN_FECHA_ULTIMO_PESO = "fecha_ultimo_peso";
    public static final String COLUMN_ESTADO = "estado";
    
    // Tabla pesos
    public static final String TABLE_PESOS = "pesos";
    public static final String COLUMN_PESO_ID = "id";
    public static final String COLUMN_PESO_CONEOJO_ID = "conejo_id";
    public static final String COLUMN_PESO = "peso";
    public static final String COLUMN_FECHA_PESO = "fecha";
    public static final String COLUMN_OBSERVACIONES_PESO = "observaciones";
    
    // Tabla partos
    public static final String TABLE_PARTOS = "partos";
    public static final String COLUMN_PARTO_ID = "id";
    public static final String COLUMN_PARTO_MADRE_ID = "madre_id";
    public static final String COLUMN_PARTO_PADRE_ID = "padre_id";
    public static final String COLUMN_FECHA_MONTA_PARTO = "fecha_monta"; // CAMBIADO NOMBRE
    public static final String COLUMN_FECHA_PARTO = "fecha_parto";
    public static final String COLUMN_CRIAS_VIVAS = "crias_vivas";
    public static final String COLUMN_CRIAS_TOTAL = "crias_total";
    public static final String COLUMN_OBSERVACIONES_PARTO = "observaciones";
    public static final String COLUMN_CRIAS_REGISTRADAS = "crias_registradas";
    
    // Tabla celos
    public static final String TABLE_CELOS = "celos";
    public static final String COLUMN_CELO_ID = "id";
    public static final String COLUMN_CELO_HEMBRA_ID = "hembra_id";
    public static final String COLUMN_FECHA_CELO = "fecha_celo";
    public static final String COLUMN_OBSERVACIONES_CELO = "observaciones";
    
    // Tabla montas (nueva)
    public static final String TABLE_MONTAS = "montas";
    public static final String COLUMN_MONTA_ID = "id";
    public static final String COLUMN_MONTA_MACHO_ID = "macho_id";
    public static final String COLUMN_MONTA_HEMBRA_ID = "hembra_id";
    public static final String COLUMN_FECHA_MONTA_MONTAS = "fecha_monta"; // NOMBRE DIFERENTE
    public static final String COLUMN_EXITO = "exito";
    public static final String COLUMN_OBSERVACIONES_MONTA = "observaciones";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla conejos
        String createConejosTable = "CREATE TABLE " + TABLE_CONEJOS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOMBRE + " TEXT NOT NULL,"
                + COLUMN_IDENTIFICADOR_UNICO + " TEXT UNIQUE,"
                + COLUMN_SEXO + " TEXT CHECK(" + COLUMN_SEXO + " IN ('M', 'H')),"
                + COLUMN_RAZA + " TEXT,"
                + COLUMN_FECHA_NACIMIENTO + " TEXT,"
                + COLUMN_FECHA_FALLECIMIENTO + " TEXT NULL,"
                + COLUMN_MOTIVO_RETIRO + " TEXT NULL,"
                + COLUMN_PESO_ACTUAL + " REAL,"
                + COLUMN_FOTO_RUTA + " TEXT,"
                + COLUMN_MADRE_ID + " INTEGER NULL,"
                + COLUMN_PADRE_ID + " INTEGER NULL,"
                + COLUMN_FECHA_REGISTRO + " TEXT DEFAULT (datetime('now','localtime')),"
                + COLUMN_FECHA_ULTIMO_PESO + " TEXT NULL,"
                + COLUMN_ESTADO + " TEXT DEFAULT 'activo',"
                + "FOREIGN KEY(" + COLUMN_MADRE_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + "),"
                + "FOREIGN KEY(" + COLUMN_PADRE_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(createConejosTable);
        
        // Crear tabla pesos
        String createPesosTable = "CREATE TABLE " + TABLE_PESOS + "("
                + COLUMN_PESO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PESO_CONEOJO_ID + " INTEGER,"
                + COLUMN_PESO + " REAL NOT NULL,"
                + COLUMN_FECHA_PESO + " TEXT DEFAULT (datetime('now','localtime')),"
                + COLUMN_OBSERVACIONES_PESO + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_PESO_CONEOJO_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(createPesosTable);
        
        // Crear tabla partos
        String createPartosTable = "CREATE TABLE " + TABLE_PARTOS + "("
                + COLUMN_PARTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PARTO_MADRE_ID + " INTEGER NOT NULL,"
                + COLUMN_PARTO_PADRE_ID + " INTEGER NULL,"
                + COLUMN_FECHA_MONTA_PARTO + " TEXT," // USANDO NUEVO NOMBRE
                + COLUMN_FECHA_PARTO + " TEXT,"
                + COLUMN_CRIAS_VIVAS + " INTEGER,"
                + COLUMN_CRIAS_TOTAL + " INTEGER,"
                + COLUMN_OBSERVACIONES_PARTO + " TEXT,"
                + COLUMN_CRIAS_REGISTRADAS + " INTEGER DEFAULT 0,"
                + "FOREIGN KEY(" + COLUMN_PARTO_MADRE_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + "),"
                + "FOREIGN KEY(" + COLUMN_PARTO_PADRE_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(createPartosTable);
        
        // Crear tabla celos
        String createCelosTable = "CREATE TABLE " + TABLE_CELOS + "("
                + COLUMN_CELO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CELO_HEMBRA_ID + " INTEGER NOT NULL,"
                + COLUMN_FECHA_CELO + " TEXT,"
                + COLUMN_OBSERVACIONES_CELO + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_CELO_HEMBRA_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(createCelosTable);
        
        // Crear tabla montas
        String createMontasTable = "CREATE TABLE " + TABLE_MONTAS + "("
                + COLUMN_MONTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MONTA_MACHO_ID + " INTEGER NOT NULL,"
                + COLUMN_MONTA_HEMBRA_ID + " INTEGER NOT NULL,"
                + COLUMN_FECHA_MONTA_MONTAS + " TEXT NOT NULL," // USANDO NUEVO NOMBRE
                + COLUMN_EXITO + " INTEGER DEFAULT 1,"
                + COLUMN_OBSERVACIONES_MONTA + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_MONTA_MACHO_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + "),"
                + "FOREIGN KEY(" + COLUMN_MONTA_HEMBRA_ID + ") REFERENCES " + TABLE_CONEJOS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(createMontasTable);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONEJOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CELOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTAS);
        onCreate(db);
    }
    
    // ==================== MÉTODOS PARA CONEJOS ====================
    
    public boolean tieneRegistros() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_CONEJOS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count > 0;
    }
    
    public Cursor obtenerTodosConejosCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + 
                COLUMN_ID + " as _id, " + 
                COLUMN_NOMBRE + ", " + 
                COLUMN_IDENTIFICADOR_UNICO + ", " + 
                COLUMN_SEXO + ", " + 
                COLUMN_RAZA + ", " + 
                COLUMN_FECHA_NACIMIENTO + ", " + 
                COLUMN_PESO_ACTUAL + ", " + 
                COLUMN_FOTO_RUTA +
                " FROM " + TABLE_CONEJOS +
                " WHERE " + COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " + COLUMN_ESTADO + " = 'activo'" +
                " ORDER BY " + COLUMN_NOMBRE;
        return db.rawQuery(query, null);
    }
    
    public Conejo obtenerConejoPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONEJOS,
                new String[]{
                        COLUMN_ID, 
                        COLUMN_NOMBRE, 
                        COLUMN_IDENTIFICADOR_UNICO,
                        COLUMN_SEXO, 
                        COLUMN_RAZA, 
                        COLUMN_FECHA_NACIMIENTO,
                        COLUMN_PESO_ACTUAL, 
                        COLUMN_FOTO_RUTA, 
                        COLUMN_MADRE_ID,
                        COLUMN_PADRE_ID,
                        COLUMN_ESTADO
                },
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, 
                null, null, null);
        
        Conejo conejo = null;
        if (cursor != null && cursor.moveToFirst()) {
            conejo = new Conejo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getDouble(6),
                    cursor.getString(7),
                    cursor.getInt(8),
                    cursor.getInt(9)
            );
            cursor.close();
        }
        db.close();
        return conejo;
    }
    
    public long agregarConejo(String nombre, String sexo, String raza, 
            String fechaNacimiento, double peso, 
            String fotoRuta, int madreId, int padreId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        String identificador = generarIdentificador(nombre);
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_IDENTIFICADOR_UNICO, identificador);
        values.put(COLUMN_SEXO, sexo);
        values.put(COLUMN_RAZA, raza);
        values.put(COLUMN_FECHA_NACIMIENTO, fechaNacimiento);
        values.put(COLUMN_PESO_ACTUAL, peso);
        
        if (fotoRuta != null && !fotoRuta.isEmpty()) {
            values.put(COLUMN_FOTO_RUTA, fotoRuta);
        }
        
        if (madreId > 0) values.put(COLUMN_MADRE_ID, madreId);
        if (padreId > 0) values.put(COLUMN_PADRE_ID, padreId);
        values.put(COLUMN_FECHA_REGISTRO, fechaActual);
        values.put(COLUMN_FECHA_ULTIMO_PESO, fechaActual);
        values.put(COLUMN_ESTADO, "activo");
        
        long id = db.insert(TABLE_CONEJOS, null, values);
        
        if (id != -1) {
            registrarPeso((int) id, peso, "Peso inicial");
        }
        
        db.close();
        return id;
    }
    
    public List<String> listarConejosActivos(String sexo) {
        List<String> conejos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_NOMBRE + ", " + 
                COLUMN_IDENTIFICADOR_UNICO + ", " + COLUMN_SEXO + ", " + 
                COLUMN_RAZA + ", " + COLUMN_FECHA_NACIMIENTO +
                " FROM " + TABLE_CONEJOS + 
                " WHERE " + COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " + COLUMN_ESTADO + " = 'activo'";
        
        String[] params = null;
        if (sexo != null) {
            query += " AND " + COLUMN_SEXO + " = ?";
            params = new String[]{sexo};
        }
        
        query += " ORDER BY " + COLUMN_NOMBRE;
        
        Cursor cursor = db.rawQuery(query, params);
        
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String identificador = cursor.getString(2);
            String sexoConejo = cursor.getString(3);
            String raza = cursor.getString(4);
            String fechaNac = cursor.getString(5);
            
            String sexoStr = sexoConejo.equals("M") ? "Macho" : "Hembra";
            String edad = calcularEdad(fechaNac);
            
            String conejoInfo = id + "|" + nombre + " (" + identificador + ") | " + 
                    sexoStr + " | " + raza + " | Edad: " + edad;
            conejos.add(conejoInfo);
        }
        
        cursor.close();
        db.close();
        return conejos;
    }
    
    public ArrayList<HashMap<String, Object>> obtenerHembrasMaduras() {
        ArrayList<HashMap<String, Object>> hembras = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_NOMBRE + ", " + COLUMN_FECHA_NACIMIENTO +
                " FROM " + TABLE_CONEJOS + 
                " WHERE " + COLUMN_SEXO + " = 'H' AND " + COLUMN_ESTADO + " = 'activo' AND " + 
                COLUMN_FECHA_FALLECIMIENTO + " IS NULL";
        
        Cursor cursor = db.rawQuery(query, null);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date hoy = new Date();
        
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String fechaNacimiento = cursor.getString(2);
            
            try {
                Date fechaNac = sdf.parse(fechaNacimiento);
                long diff = hoy.getTime() - fechaNac.getTime();
                long meses = diff / (30L * 24 * 60 * 60 * 1000);
                
                if (meses >= 5) {
                    HashMap<String, Object> hembra = new HashMap<>();
                    hembra.put("id", id);
                    hembra.put("nombre", nombre);
                    hembra.put("meses", meses);
                    hembra.put("fecha_nacimiento", fechaNacimiento);
                    hembras.add(hembra);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        cursor.close();
        db.close();
        return hembras;
    }
    
    public ArrayList<HashMap<String, Object>> obtenerMachosMaduros() {
        ArrayList<HashMap<String, Object>> machos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_NOMBRE + ", " + COLUMN_FECHA_NACIMIENTO +
                " FROM " + TABLE_CONEJOS + 
                " WHERE " + COLUMN_SEXO + " = 'M' AND " + COLUMN_ESTADO + " = 'activo' AND " + 
                COLUMN_FECHA_FALLECIMIENTO + " IS NULL";
        
        Cursor cursor = db.rawQuery(query, null);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date hoy = new Date();
        
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String fechaNacimiento = cursor.getString(2);
            
            try {
                Date fechaNac = sdf.parse(fechaNacimiento);
                long diff = hoy.getTime() - fechaNac.getTime();
                long meses = diff / (30L * 24 * 60 * 60 * 1000);
                
                if (meses >= 6) {
                    HashMap<String, Object> macho = new HashMap<>();
                    macho.put("id", id);
                    macho.put("nombre", nombre);
                    macho.put("meses", meses);
                    macho.put("fecha_nacimiento", fechaNacimiento);
                    machos.add(macho);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        cursor.close();
        db.close();
        return machos;
    }
    
    private String generarIdentificador(String nombre) {
        String consonantes = nombre.replaceAll("[aeiouAEIOU\\s]", "").toLowerCase();
        if (consonantes.length() < 3) {
            consonantes += "xxx".substring(consonantes.length());
        }
        String baseId = consonantes.substring(0, 3);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss", Locale.getDefault());
        String tiempoStr = sdf.format(new Date());
        
        return baseId + tiempoStr;
    }
    
    private String calcularEdad(String fechaNacimiento) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date nacimiento = sdf.parse(fechaNacimiento);
            Date hoy = new Date();
            
            long diff = hoy.getTime() - nacimiento.getTime();
            long dias = diff / (24 * 60 * 60 * 1000);
            long meses = dias / 30;
            long años = meses / 12;
            
            if (años > 0) {
                return años + " año(s), " + (meses % 12) + " mes(es)";
            } else {
                return meses + " mes(es), " + (dias % 30) + " día(s)";
            }
        } catch (Exception e) {
            return "Desconocida";
        }
    }
    
    // ==================== MÉTODOS PARA PESOS ====================
    
    public void registrarPeso(int conejoId, double peso, String observaciones) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        
        values.put(COLUMN_PESO_CONEOJO_ID, conejoId);
        values.put(COLUMN_PESO, peso);
        values.put(COLUMN_FECHA_PESO, fechaActual);
        values.put(COLUMN_OBSERVACIONES_PESO, observaciones);
        
        db.insert(TABLE_PESOS, null, values);
        
        ContentValues updateValues = new ContentValues();
        updateValues.put(COLUMN_PESO_ACTUAL, peso);
        updateValues.put(COLUMN_FECHA_ULTIMO_PESO, fechaActual);
        
        db.update(TABLE_CONEJOS, updateValues, 
                COLUMN_ID + " = ?", new String[]{String.valueOf(conejoId)});
        
        db.close();
    }
    
    // ==================== MÉTODOS PARA CELOS ====================
    
    public long registrarCelo(int hembraId, String fechaCelo, String observaciones) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(COLUMN_CELO_HEMBRA_ID, hembraId);
        values.put(COLUMN_FECHA_CELO, fechaCelo);
        values.put(COLUMN_OBSERVACIONES_CELO, observaciones);
        
        long id = db.insert(TABLE_CELOS, null, values);
        db.close();
        return id;
    }
    
    public String obtenerUltimoCelo(int hembraId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CELOS,
                new String[]{COLUMN_FECHA_CELO},
                COLUMN_CELO_HEMBRA_ID + " = ?",
                new String[]{String.valueOf(hembraId)},
                null, null,
                COLUMN_FECHA_CELO + " DESC",
                "1");
        
        String ultimoCelo = null;
        if (cursor != null && cursor.moveToFirst()) {
            ultimoCelo = cursor.getString(0);
            cursor.close();
        }
        db.close();
        return ultimoCelo;
    }
    
    // ==================== MÉTODOS PARA MONTAS ====================
    
    public long registrarMonta(int machoId, int hembraId, String fechaMonta, String observaciones, boolean exito) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(COLUMN_MONTA_MACHO_ID, machoId);
        values.put(COLUMN_MONTA_HEMBRA_ID, hembraId);
        values.put(COLUMN_FECHA_MONTA_MONTAS, fechaMonta);
        values.put(COLUMN_OBSERVACIONES_MONTA, observaciones);
        values.put(COLUMN_EXITO, exito ? 1 : 0);
        
        long id = db.insert(TABLE_MONTAS, null, values);
        db.close();
        return id;
    }
    
    public HashMap<String, Integer> obtenerEstadisticasMontas(int machoId) {
        SQLiteDatabase db = this.getReadableDatabase();
        HashMap<String, Integer> estadisticas = new HashMap<>();
        
        Cursor cursorTotal = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_MONTAS + 
                " WHERE " + COLUMN_MONTA_MACHO_ID + " = ?",
                new String[]{String.valueOf(machoId)});
        
        if (cursorTotal.moveToFirst()) {
            estadisticas.put("total", cursorTotal.getInt(0));
        }
        cursorTotal.close();
        
        Cursor cursorExitosas = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_MONTAS + 
                " WHERE " + COLUMN_MONTA_MACHO_ID + " = ? AND " + COLUMN_EXITO + " = 1",
                new String[]{String.valueOf(machoId)});
        
        if (cursorExitosas.moveToFirst()) {
            estadisticas.put("exitosas", cursorExitosas.getInt(0));
        }
        cursorExitosas.close();
        
        db.close();
        return estadisticas;
    }
    
    // ==================== MÉTODOS PARA PARTOS ====================
    
    public long registrarParto(int madreId, Integer padreId, String fechaParto, int criasTotales, int criasVivas, String observaciones) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(COLUMN_PARTO_MADRE_ID, madreId);
        if (padreId != null && padreId > 0) {
            values.put(COLUMN_PARTO_PADRE_ID, padreId);
        }
        values.put(COLUMN_FECHA_PARTO, fechaParto);
        values.put(COLUMN_CRIAS_TOTAL, criasTotales);
        values.put(COLUMN_CRIAS_VIVAS, criasVivas);
        values.put(COLUMN_OBSERVACIONES_PARTO, observaciones);
        values.put(COLUMN_CRIAS_REGISTRADAS, 0);
        
        long id = db.insert(TABLE_PARTOS, null, values);
        db.close();
        return id;
    }
    
    public HashMap<String, Integer> obtenerEstadisticasPartos(int hembraId) {
        SQLiteDatabase db = this.getReadableDatabase();
        HashMap<String, Integer> estadisticas = new HashMap<>();
        
        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + COLUMN_CRIAS_TOTAL + "), SUM(" + COLUMN_CRIAS_VIVAS + "), COUNT(*) FROM " + TABLE_PARTOS + 
                " WHERE " + COLUMN_PARTO_MADRE_ID + " = ?",
                new String[]{String.valueOf(hembraId)});
        
        if (cursor.moveToFirst()) {
            estadisticas.put("total_crias", cursor.getInt(0));
            estadisticas.put("crias_vivas", cursor.getInt(1));
            estadisticas.put("total_partos", cursor.getInt(2));
        }
        cursor.close();
        db.close();
        
        if (estadisticas.containsKey("total_crias") && estadisticas.containsKey("crias_vivas")) {
            int total = estadisticas.get("total_crias");
            int vivas = estadisticas.get("crias_vivas");
            estadisticas.put("crias_muertas", total - vivas);
        }
        
        return estadisticas;
    }
    
    // ==================== MÉTODOS ESTADÍSTICOS ====================
    
    public int getTotalConejos() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_CONEJOS + 
                " WHERE " + COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " + COLUMN_ESTADO + " = 'activo'", 
                null
        );
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        cursor.close();
        db.close();
        return total;
    }
    
    public int getTotalMachos() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_CONEJOS + 
                " WHERE " + COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " + 
                COLUMN_SEXO + " = 'M' AND " + COLUMN_ESTADO + " = 'activo'", 
                null
        );
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        cursor.close();
        db.close();
        return total;
    }
    
    public int getTotalHembras() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_CONEJOS + 
                " WHERE " + COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " + 
                COLUMN_SEXO + " = 'H' AND " + COLUMN_ESTADO + " = 'activo'", 
                null
        );
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        cursor.close();
        db.close();
        return total;
    }
    
    public Estadisticas getEstadisticas() {
        return new Estadisticas(
                getTotalConejos(),
                getTotalMachos(),
                getTotalHembras()
        );
    }
    
    // ==================== CLASES DE MODELO ====================
    
    public static class Conejo {
        public int id;
        public String nombre;
        public String identificador;
        public String sexo;
        public String raza;
        public String fechaNacimiento;
        public double peso;
        public String fotoRuta;
        public int madreId;
        public int padreId;
        
        public Conejo(int id, String nombre, String identificador, String sexo, 
                String raza, String fechaNacimiento, double peso, 
                String fotoRuta, int madreId, int padreId) {
            this.id = id;
            this.nombre = nombre;
            this.identificador = identificador;
            this.sexo = sexo;
            this.raza = raza;
            this.fechaNacimiento = fechaNacimiento;
            this.peso = peso;
            this.fotoRuta = fotoRuta;
            this.madreId = madreId;
            this.padreId = padreId;
        }
        
        public String getSexoTexto() {
            return sexo.equals("M") ? "Macho" : "Hembra";
        }
        
        public String getEdad() {
            return calcularEdad(fechaNacimiento);
        }
        
        private String calcularEdad(String fechaNac) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date nacimiento = sdf.parse(fechaNac);
                Date hoy = new Date();
                
                long diff = hoy.getTime() - nacimiento.getTime();
                long dias = diff / (24 * 60 * 60 * 1000);
                long meses = dias / 30;
                
                if (meses < 1) {
                    return dias + " días";
                } else if (meses < 12) {
                    return meses + " meses";
                } else {
                    return (meses / 12) + " años";
                }
            } catch (Exception e) {
                return "Desconocida";
            }
        }
        
        public String getInformacionBasica() {
            return nombre + " (" + identificador + ") - " + getSexoTexto() + 
                    " - " + raza + " - " + getEdad();
        }
    }
    
    public static class Estadisticas {
        public int total;
        public int machos;
        public int hembras;
        
        public Estadisticas(int total, int machos, int hembras) {
            this.total = total;
            this.machos = machos;
            this.hembras = hembras;
        }
        
        public int getTotal() { return total; }
        public int getMachos() { return machos; }
        public int getHembras() { return hembras; }
    }
}