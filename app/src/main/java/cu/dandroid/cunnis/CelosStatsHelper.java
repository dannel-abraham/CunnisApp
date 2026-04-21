package cu.dandroid.cunnis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CelosStatsHelper {
    
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    
    public CelosStatsHelper(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
    }
    
    // 1. ESTADÍSTICAS GENERALES - MoreBlock
    public HashMap<String, Object> getEstadisticasGenerales() {
        HashMap<String, Object> stats = new HashMap<>();
        
        // Total de celos registrados
        Cursor c1 = db.rawQuery("SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CELOS, null);
        c1.moveToFirst();
        stats.put("total_celos", c1.getInt(0));
        c1.close();
        
        // Total de hembras en celo
        Cursor c2 = db.rawQuery("SELECT COUNT(DISTINCT " + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + ") FROM " + DatabaseHelper.TABLE_CELOS, null);
        c2.moveToFirst();
        stats.put("hembras_celo", c2.getInt(0));
        c2.close();
        
        // Primer celo registrado
        Cursor c3 = db.rawQuery("SELECT MIN(" + DatabaseHelper.COLUMN_FECHA_CELO + ") FROM " + DatabaseHelper.TABLE_CELOS, null);
        c3.moveToFirst();
        String primerCelo = c3.getString(0);
        stats.put("primer_celo", primerCelo != null ? primerCelo : "No hay");
        c3.close();
        
        // Último celo registrado
        Cursor c4 = db.rawQuery("SELECT MAX(" + DatabaseHelper.COLUMN_FECHA_CELO + ") FROM " + DatabaseHelper.TABLE_CELOS, null);
        c4.moveToFirst();
        String ultimoCelo = c4.getString(0);
        stats.put("ultimo_celo", ultimoCelo != null ? ultimoCelo : "No hay");
        c4.close();
        
        // Celos en el último mes
        String queryUltimoMes = "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CELOS + 
                               " WHERE " + DatabaseHelper.COLUMN_FECHA_CELO + 
                               " >= date('now','-1 month')";
        Cursor c5 = db.rawQuery(queryUltimoMes, null);
        c5.moveToFirst();
        stats.put("celos_ultimo_mes", c5.getInt(0));
        c5.close();
        
        return stats;
    }
    
    // 2. HEMBRAS CON MÁS CELOS (TOP 5) - MoreBlock
    public List<HashMap<String, String>> getTopHembrasCelos() {
        List<HashMap<String, String>> topHembras = new ArrayList<>();
        
        String query = "SELECT " +
                "c." + DatabaseHelper.COLUMN_ID + ", " +
                "c." + DatabaseHelper.COLUMN_NOMBRE + ", " +
                "c." + DatabaseHelper.COLUMN_IDENTIFICADOR_UNICO + ", " +
                "COUNT(cl." + DatabaseHelper.COLUMN_CELO_ID + ") as total_celos, " +
                "MIN(cl." + DatabaseHelper.COLUMN_FECHA_CELO + ") as primer_celo, " +
                "MAX(cl." + DatabaseHelper.COLUMN_FECHA_CELO + ") as ultimo_celo " +
                "FROM " + DatabaseHelper.TABLE_CELOS + " cl " +
                "JOIN " + DatabaseHelper.TABLE_CONEJOS + " c ON cl." + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + " = c." + DatabaseHelper.COLUMN_ID + " " +
                "GROUP BY cl." + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + " " +
                "ORDER BY total_celos DESC " +
                "LIMIT 5";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> hembra = new HashMap<>();
            hembra.put("id", cursor.getString(0));
            hembra.put("nombre", cursor.getString(1));
            hembra.put("identificador", cursor.getString(2));
            hembra.put("total_celos", cursor.getString(3));
            hembra.put("primer_celo", cursor.getString(4));
            hembra.put("ultimo_celo", cursor.getString(5));
            topHembras.add(hembra);
        }
        cursor.close();
        
        return topHembras;
    }
    
    // 3. CELOS POR PERIODO (Año/Mes) - MoreBlock
    public List<HashMap<String, String>> getCelosPorPeriodo(String periodo) {
        List<HashMap<String, String>> datos = new ArrayList<>();
        
        String groupBy;
        String selectField;
        
        if (periodo.equals("anio")) {
            groupBy = "strftime('%Y', " + DatabaseHelper.COLUMN_FECHA_CELO + ")";
            selectField = "strftime('%Y', " + DatabaseHelper.COLUMN_FECHA_CELO + ") as periodo";
        } else {
            groupBy = "strftime('%Y-%m', " + DatabaseHelper.COLUMN_FECHA_CELO + ")";
            selectField = "strftime('%Y-%m', " + DatabaseHelper.COLUMN_FECHA_CELO + ") as periodo";
        }
        
        String query = "SELECT " +
                selectField + ", " +
                "COUNT(*) as total_celos, " +
                "COUNT(DISTINCT " + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + ") as hembras " +
                "FROM " + DatabaseHelper.TABLE_CELOS + " " +
                "WHERE " + DatabaseHelper.COLUMN_FECHA_CELO + " IS NOT NULL " +
                "GROUP BY " + groupBy + " " +
                "ORDER BY periodo DESC";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> periodoData = new HashMap<>();
            periodoData.put("periodo", cursor.getString(0));
            periodoData.put("total_celos", cursor.getString(1));
            periodoData.put("hembras", cursor.getString(2));
            datos.add(periodoData);
        }
        cursor.close();
        
        return datos;
    }
    
    // 4. ÚLTIMOS CELOS REGISTRADOS - MoreBlock
    public List<HashMap<String, String>> getUltimosCelos(int limite) {
        List<HashMap<String, String>> celos = new ArrayList<>();
        
        String query = "SELECT " +
                "cl." + DatabaseHelper.COLUMN_CELO_ID + ", " +
                "cl." + DatabaseHelper.COLUMN_FECHA_CELO + ", " +
                "cl." + DatabaseHelper.COLUMN_OBSERVACIONES_CELO + ", " +
                "c." + DatabaseHelper.COLUMN_NOMBRE + " as hembra_nombre, " +
                "c." + DatabaseHelper.COLUMN_IDENTIFICADOR_UNICO + " as hembra_identificador " +
                "FROM " + DatabaseHelper.TABLE_CELOS + " cl " +
                "JOIN " + DatabaseHelper.TABLE_CONEJOS + " c ON cl." + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + " = c." + DatabaseHelper.COLUMN_ID + " " +
                "ORDER BY cl." + DatabaseHelper.COLUMN_FECHA_CELO + " DESC " +
                "LIMIT " + limite;
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> celo = new HashMap<>();
            celo.put("id", cursor.getString(0));
            celo.put("fecha", cursor.getString(1));
            celo.put("observaciones", cursor.getString(2));
            celo.put("hembra_nombre", cursor.getString(3));
            celo.put("hembra_identificador", cursor.getString(4));
            celos.add(celo);
        }
        cursor.close();
        
        return celos;
    }
    
    // 5. HEMBRAS ACTIVAS SIN CELOS REGISTRADOS - MoreBlock
    public List<HashMap<String, String>> getHembrasSinCelos() {
        List<HashMap<String, String>> hembras = new ArrayList<>();
        
        String query = "SELECT " +
                DatabaseHelper.COLUMN_ID + ", " +
                DatabaseHelper.COLUMN_NOMBRE + ", " +
                DatabaseHelper.COLUMN_IDENTIFICADOR_UNICO + ", " +
                DatabaseHelper.COLUMN_FECHA_NACIMIENTO + " " +
                "FROM " + DatabaseHelper.TABLE_CONEJOS + " " +
                "WHERE " + DatabaseHelper.COLUMN_SEXO + " = 'H' " +
                "AND " + DatabaseHelper.COLUMN_ESTADO + " = 'activo' " +
                "AND " + DatabaseHelper.COLUMN_ID + " NOT IN (SELECT DISTINCT " + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + " FROM " + DatabaseHelper.TABLE_CELOS + ")" +
                "ORDER BY " + DatabaseHelper.COLUMN_FECHA_NACIMIENTO + " DESC";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> hembra = new HashMap<>();
            hembra.put("id", cursor.getString(0));
            hembra.put("nombre", cursor.getString(1));
            hembra.put("identificador", cursor.getString(2));
            hembra.put("fecha_nacimiento", cursor.getString(3));
            hembras.add(hembra);
        }
        cursor.close();
        
        return hembras;
    }
    
    // 6. CELOS POR EDAD DE LA HEMBRA - MoreBlock
    public List<HashMap<String, String>> getCelosPorEdadHembra() {
        List<HashMap<String, String>> datos = new ArrayList<>();
        
        String query = "SELECT " +
                "CASE " +
                "   WHEN (strftime('%Y', 'now') - strftime('%Y', c." + DatabaseHelper.COLUMN_FECHA_NACIMIENTO + ")) < 1 THEN 'Menos de 1 año' " +
                "   WHEN (strftime('%Y', 'now') - strftime('%Y', c." + DatabaseHelper.COLUMN_FECHA_NACIMIENTO + ")) BETWEEN 1 AND 2 THEN '1-2 años' " +
                "   WHEN (strftime('%Y', 'now') - strftime('%Y', c." + DatabaseHelper.COLUMN_FECHA_NACIMIENTO + ")) BETWEEN 3 AND 4 THEN '3-4 años' " +
                "   ELSE 'Más de 4 años' " +
                "END as rango_edad, " +
                "COUNT(cl." + DatabaseHelper.COLUMN_CELO_ID + ") as total_celos, " +
                "COUNT(DISTINCT c." + DatabaseHelper.COLUMN_ID + ") as hembras " +
                "FROM " + DatabaseHelper.TABLE_CELOS + " cl " +
                "JOIN " + DatabaseHelper.TABLE_CONEJOS + " c ON cl." + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + " = c." + DatabaseHelper.COLUMN_ID + " " +
                "WHERE c." + DatabaseHelper.COLUMN_FECHA_NACIMIENTO + " IS NOT NULL " +
                "GROUP BY rango_edad " +
                "ORDER BY total_celos DESC";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> dato = new HashMap<>();
            dato.put("rango_edad", cursor.getString(0));
            dato.put("total_celos", cursor.getString(1));
            dato.put("hembras", cursor.getString(2));
            datos.add(dato);
        }
        cursor.close();
        
        return datos;
    }
    
    // 7. FRECUENCIA DE CELOS - MoreBlock
    public HashMap<String, Object> getEstadisticasFrecuencia() {
        HashMap<String, Object> stats = new HashMap<>();
        
        // Celos en los últimos 30 días
        String query30Dias = "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CELOS + 
                           " WHERE " + DatabaseHelper.COLUMN_FECHA_CELO + 
                           " >= date('now','-30 days')";
        Cursor c1 = db.rawQuery(query30Dias, null);
        c1.moveToFirst();
        stats.put("celos_30_dias", c1.getInt(0));
        c1.close();
        
        // Celos en los últimos 60 días
        String query60Dias = "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CELOS + 
                           " WHERE " + DatabaseHelper.COLUMN_FECHA_CELO + 
                           " >= date('now','-60 days')";
        Cursor c2 = db.rawQuery(query60Dias, null);
        c2.moveToFirst();
        stats.put("celos_60_dias", c2.getInt(0));
        c2.close();
        
        // Promedio de celos por hembra
        String queryPromedio = "SELECT " +
                "COUNT(DISTINCT " + DatabaseHelper.COLUMN_CELO_HEMBRA_ID + ") as hembras, " +
                "COUNT(*) as total_celos " +
                "FROM " + DatabaseHelper.TABLE_CELOS;
        Cursor c3 = db.rawQuery(queryPromedio, null);
        c3.moveToFirst();
        int hembras = c3.getInt(0);
        int totalCelos = c3.getInt(1);
        c3.close();
        
        double promedio = hembras > 0 ? (double) totalCelos / hembras : 0;
        stats.put("promedio_celos_hembra", String.format("%.1f", promedio));
        
        return stats;
    }
    
    // 8. CERRAR CONEXIÓN
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}