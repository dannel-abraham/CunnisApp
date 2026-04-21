package cu.dandroid.cunnis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartosStatsHelper {
    
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    
    public PartosStatsHelper(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
    }
    
    // 1. ESTADÍSTICAS GENERALES - MoreBlock
    public HashMap<String, Object> getEstadisticasGenerales() {
        HashMap<String, Object> stats = new HashMap<>();
        
        Cursor c1 = db.rawQuery("SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_PARTOS, null);
        c1.moveToFirst();
        stats.put("total_partos", c1.getInt(0));
        c1.close();
        
        Cursor c2 = db.rawQuery("SELECT SUM(" + DatabaseHelper.COLUMN_CRIAS_TOTAL + ") FROM " + DatabaseHelper.TABLE_PARTOS, null);
        c2.moveToFirst();
        int totalCrias = c2.getInt(0);
        stats.put("total_crias", totalCrias);
        c2.close();
        
        Cursor c3 = db.rawQuery("SELECT SUM(" + DatabaseHelper.COLUMN_CRIAS_VIVAS + ") FROM " + DatabaseHelper.TABLE_PARTOS, null);
        c3.moveToFirst();
        int criasVivas = c3.getInt(0);
        stats.put("crias_vivas", criasVivas);
        c3.close();
        
        int criasMuertas = totalCrias - criasVivas;
        stats.put("crias_muertas", criasMuertas);
        
        double porcentajeSupervivencia = totalCrias > 0 ? (criasVivas * 100.0 / totalCrias) : 0;
        stats.put("porcentaje_supervivencia", String.format("%.1f%%", porcentajeSupervivencia));
        
        double promedioCrias = (int)stats.get("total_partos") > 0 ? (double) totalCrias / (int)stats.get("total_partos") : 0;
        stats.put("promedio_crias", String.format("%.1f", promedioCrias));
        
        Cursor c4 = db.rawQuery("SELECT COUNT(DISTINCT " + DatabaseHelper.COLUMN_PARTO_MADRE_ID + ") FROM " + DatabaseHelper.TABLE_PARTOS, null);
        c4.moveToFirst();
        stats.put("hembras_paridas", c4.getInt(0));
        c4.close();
        
        return stats;
    }
    
    // 2. TOP 5 HEMBRAS - MoreBlock
    public List<HashMap<String, String>> getTopHembrasPartos() {
        List<HashMap<String, String>> topHembras = new ArrayList<>();
        
        String query = "SELECT " +
                "c." + DatabaseHelper.COLUMN_ID + ", " +
                "c." + DatabaseHelper.COLUMN_NOMBRE + ", " +
                "c." + DatabaseHelper.COLUMN_IDENTIFICADOR_UNICO + ", " +
                "COUNT(p." + DatabaseHelper.COLUMN_PARTO_ID + ") as total_partos, " +
                "SUM(p." + DatabaseHelper.COLUMN_CRIAS_TOTAL + ") as total_crias, " +
                "SUM(p." + DatabaseHelper.COLUMN_CRIAS_VIVAS + ") as crias_vivas " +
                "FROM " + DatabaseHelper.TABLE_PARTOS + " p " +
                "JOIN " + DatabaseHelper.TABLE_CONEJOS + " c ON p." + DatabaseHelper.COLUMN_PARTO_MADRE_ID + " = c." + DatabaseHelper.COLUMN_ID + " " +
                "GROUP BY p." + DatabaseHelper.COLUMN_PARTO_MADRE_ID + " " +
                "ORDER BY total_partos DESC, total_crias DESC " +
                "LIMIT 5";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> hembra = new HashMap<>();
            hembra.put("id", cursor.getString(0));
            hembra.put("nombre", cursor.getString(1));
            hembra.put("identificador", cursor.getString(2));
            hembra.put("partos", cursor.getString(3));
            hembra.put("crias_totales", cursor.getString(4));
            hembra.put("crias_vivas", cursor.getString(5));
            
            int criasTotales = cursor.getInt(4);
            int criasVivas = cursor.getInt(5);
            double porcentaje = criasTotales > 0 ? (criasVivas * 100.0 / criasTotales) : 0;
            hembra.put("porcentaje_supervivencia", String.format("%.1f%%", porcentaje));
            
            topHembras.add(hembra);
        }
        cursor.close();
        
        return topHembras;
    }
    
    // 3. PARTOS POR PERIODO - MoreBlock
    public List<HashMap<String, String>> getPartosPorPeriodo(String periodo) {
        List<HashMap<String, String>> datos = new ArrayList<>();
        
        String groupBy;
        String selectField;
        
        if (periodo.equals("anio")) {
            groupBy = "strftime('%Y', " + DatabaseHelper.COLUMN_FECHA_PARTO + ")";
            selectField = "strftime('%Y', " + DatabaseHelper.COLUMN_FECHA_PARTO + ") as periodo";
        } else {
            groupBy = "strftime('%Y-%m', " + DatabaseHelper.COLUMN_FECHA_PARTO + ")";
            selectField = "strftime('%Y-%m', " + DatabaseHelper.COLUMN_FECHA_PARTO + ") as periodo";
        }
        
        String query = "SELECT " +
                selectField + ", " +
                "COUNT(*) as total_partos, " +
                "SUM(" + DatabaseHelper.COLUMN_CRIAS_TOTAL + ") as total_crias, " +
                "SUM(" + DatabaseHelper.COLUMN_CRIAS_VIVAS + ") as crias_vivas " +
                "FROM " + DatabaseHelper.TABLE_PARTOS + " " +
                "WHERE " + DatabaseHelper.COLUMN_FECHA_PARTO + " IS NOT NULL " +
                "GROUP BY " + groupBy + " " +
                "ORDER BY periodo DESC";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> periodoData = new HashMap<>();
            periodoData.put("periodo", cursor.getString(0));
            periodoData.put("partos", cursor.getString(1));
            periodoData.put("crias_totales", cursor.getString(2));
            periodoData.put("crias_vivas", cursor.getString(3));
            
            int criasTotales = cursor.getInt(2);
            int criasVivas = cursor.getInt(3);
            double porcentaje = criasTotales > 0 ? (criasVivas * 100.0 / criasTotales) : 0;
            periodoData.put("porcentaje", String.format("%.1f%%", porcentaje));
            
            datos.add(periodoData);
        }
        cursor.close();
        
        return datos;
    }
    
    // 4. PADRES PRODUCTIVOS - MoreBlock
    public List<HashMap<String, String>> getEstadisticasPadres() {
        List<HashMap<String, String>> padres = new ArrayList<>();
        
        String query = "SELECT " +
                "c." + DatabaseHelper.COLUMN_ID + ", " +
                "c." + DatabaseHelper.COLUMN_NOMBRE + ", " +
                "c." + DatabaseHelper.COLUMN_IDENTIFICADOR_UNICO + ", " +
                "COUNT(p." + DatabaseHelper.COLUMN_PARTO_ID + ") as total_partos, " +
                "SUM(p." + DatabaseHelper.COLUMN_CRIAS_TOTAL + ") as total_crias " +
                "FROM " + DatabaseHelper.TABLE_PARTOS + " p " +
                "JOIN " + DatabaseHelper.TABLE_CONEJOS + " c ON p." + DatabaseHelper.COLUMN_PARTO_PADRE_ID + " = c." + DatabaseHelper.COLUMN_ID + " " +
                "WHERE p." + DatabaseHelper.COLUMN_PARTO_PADRE_ID + " IS NOT NULL " +
                "GROUP BY p." + DatabaseHelper.COLUMN_PARTO_PADRE_ID + " " +
                "ORDER BY total_crias DESC";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> padre = new HashMap<>();
            padre.put("id", cursor.getString(0));
            padre.put("nombre", cursor.getString(1));
            padre.put("identificador", cursor.getString(2));
            padre.put("partos", cursor.getString(3));
            padre.put("crias", cursor.getString(4));
            padres.add(padre);
        }
        cursor.close();
        
        return padres;
    }
    
    // 5. ÚLTIMOS PARTOS - MoreBlock
    public List<HashMap<String, String>> getUltimosPartos(int limite) {
        List<HashMap<String, String>> partos = new ArrayList<>();
        
        String query = "SELECT " +
                "p." + DatabaseHelper.COLUMN_PARTO_ID + ", " +
                "p." + DatabaseHelper.COLUMN_FECHA_PARTO + ", " +
                "p." + DatabaseHelper.COLUMN_CRIAS_TOTAL + ", " +
                "p." + DatabaseHelper.COLUMN_CRIAS_VIVAS + ", " +
                "p." + DatabaseHelper.COLUMN_OBSERVACIONES_PARTO + ", " +
                "m." + DatabaseHelper.COLUMN_NOMBRE + " as madre_nombre, " +
                "pa." + DatabaseHelper.COLUMN_NOMBRE + " as padre_nombre " +
                "FROM " + DatabaseHelper.TABLE_PARTOS + " p " +
                "LEFT JOIN " + DatabaseHelper.TABLE_CONEJOS + " m ON p." + DatabaseHelper.COLUMN_PARTO_MADRE_ID + " = m." + DatabaseHelper.COLUMN_ID + " " +
                "LEFT JOIN " + DatabaseHelper.TABLE_CONEJOS + " pa ON p." + DatabaseHelper.COLUMN_PARTO_PADRE_ID + " = pa." + DatabaseHelper.COLUMN_ID + " " +
                "ORDER BY p." + DatabaseHelper.COLUMN_FECHA_PARTO + " DESC " +
                "LIMIT " + limite;
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> parto = new HashMap<>();
            parto.put("id", cursor.getString(0));
            parto.put("fecha", cursor.getString(1));
            parto.put("crias_totales", cursor.getString(2));
            parto.put("crias_vivas", cursor.getString(3));
            parto.put("observaciones", cursor.getString(4));
            parto.put("madre", cursor.getString(5));
            parto.put("padre", cursor.getString(6));
            partos.add(parto);
        }
        cursor.close();
        
        return partos;
    }
    
    // 6. HEMBRAS SIN PARTOS - MoreBlock
    public List<HashMap<String, String>> getHembrasSinPartos() {
        List<HashMap<String, String>> hembras = new ArrayList<>();
        
        String query = "SELECT " +
                DatabaseHelper.COLUMN_ID + ", " +
                DatabaseHelper.COLUMN_NOMBRE + ", " +
                DatabaseHelper.COLUMN_IDENTIFICADOR_UNICO + ", " +
                DatabaseHelper.COLUMN_FECHA_NACIMIENTO + " " +
                "FROM " + DatabaseHelper.TABLE_CONEJOS + " " +
                "WHERE " + DatabaseHelper.COLUMN_SEXO + " = 'H' " +
                "AND " + DatabaseHelper.COLUMN_ESTADO + " = 'activo' " +
                "AND " + DatabaseHelper.COLUMN_ID + " NOT IN (SELECT DISTINCT " + DatabaseHelper.COLUMN_PARTO_MADRE_ID + " FROM " + DatabaseHelper.TABLE_PARTOS + ")" +
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
    
    // 7. ESTADÍSTICAS POR RAZA - MoreBlock
    public List<HashMap<String, String>> getPartosPorRaza() {
        List<HashMap<String, String>> razas = new ArrayList<>();
        
        String query = "SELECT " +
                "c." + DatabaseHelper.COLUMN_RAZA + ", " +
                "COUNT(p." + DatabaseHelper.COLUMN_PARTO_ID + ") as total_partos, " +
                "SUM(p." + DatabaseHelper.COLUMN_CRIAS_TOTAL + ") as total_crias, " +
                "SUM(p." + DatabaseHelper.COLUMN_CRIAS_VIVAS + ") as crias_vivas " +
                "FROM " + DatabaseHelper.TABLE_PARTOS + " p " +
                "JOIN " + DatabaseHelper.TABLE_CONEJOS + " c ON p." + DatabaseHelper.COLUMN_PARTO_MADRE_ID + " = c." + DatabaseHelper.COLUMN_ID + " " +
                "WHERE c." + DatabaseHelper.COLUMN_RAZA + " IS NOT NULL AND c." + DatabaseHelper.COLUMN_RAZA + " != '' " +
                "GROUP BY c." + DatabaseHelper.COLUMN_RAZA + " " +
                "ORDER BY total_crias DESC";
        
        Cursor cursor = db.rawQuery(query, null);
        
        while (cursor.moveToNext()) {
            HashMap<String, String> raza = new HashMap<>();
            raza.put("raza", cursor.getString(0));
            raza.put("partos", cursor.getString(1));
            raza.put("crias_totales", cursor.getString(2));
            raza.put("crias_vivas", cursor.getString(3));
            
            int criasTotales = cursor.getInt(2);
            int criasVivas = cursor.getInt(3);
            double porcentaje = criasTotales > 0 ? (criasVivas * 100.0 / criasTotales) : 0;
            raza.put("porcentaje", String.format("%.1f%%", porcentaje));
            
            razas.add(raza);
        }
        cursor.close();
        
        return razas;
    }
    
    // 8. CERRAR CONEXIÓN
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}