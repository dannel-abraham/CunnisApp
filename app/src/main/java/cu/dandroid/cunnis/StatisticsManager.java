package cu.dandroid.cunnis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Locale;

public class StatisticsManager {
    
    private Context context;
    private DatabaseHelper dbHelper;
    
    // Constructor
    public StatisticsManager(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }
    
    // ==================== MÉTODO PRINCIPAL CORREGIDO ====================
    
    /**
     * Obtiene TODAS las estadísticas juntas
     * Retorna: Map (con Map y ListMap dentro)
     */
    public HashMap<String, Object> getAllStats() {
        HashMap<String, Object> allStats = new HashMap<String, Object>();
        
        // ABRIR la base de datos UNA SOLA VEZ
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        try {
            // 1. Estadísticas básicas
            allStats.put("basic", getBasicStatistics(db));
            
            // 2. Estadísticas de peso
            allStats.put("weight", getWeightStatistics(db));
            
            // 3. Estadísticas de edad
            allStats.put("age", getAgeStatistics(db));
            
            // 4. Distribución por raza
            allStats.put("breeds", getBreedDistribution(db));
            
            // 5. Estadísticas de reproducción
            allStats.put("reproduction", getReproductionStatistics(db));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // CERRAR la base de datos SOLO AL FINAL
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        
        return allStats;
    }
    
    // ==================== MÉTODOS INTERNOS (NO cierran la db) ====================
    
    private HashMap<String, Object> getBasicStatistics(SQLiteDatabase db) {
        HashMap<String, Object> stats = new HashMap<String, Object>();
        
        // Total activos
        Cursor cursor = db.rawQuery(
            "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CONEJOS + 
            " WHERE " + DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL", 
            null
        );
        if (cursor.moveToFirst()) stats.put("total", cursor.getInt(0));
        cursor.close();
        
        // Machos
        cursor = db.rawQuery(
            "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CONEJOS + 
            " WHERE " + DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " + 
            DatabaseHelper.COLUMN_SEXO + " = 'M'", 
            null
        );
        if (cursor.moveToFirst()) stats.put("males", cursor.getInt(0));
        cursor.close();
        
        // Hembras
        cursor = db.rawQuery(
            "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CONEJOS + 
            " WHERE " + DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " + 
            DatabaseHelper.COLUMN_SEXO + " = 'H'", 
            null
        );
        if (cursor.moveToFirst()) stats.put("females", cursor.getInt(0));
        cursor.close();
        
        return stats;
    }
    
    private HashMap<String, Object> getWeightStatistics(SQLiteDatabase db) {
        HashMap<String, Object> stats = new HashMap<String, Object>();
        
        // Peso promedio
        Cursor cursor = db.rawQuery(
            "SELECT AVG(" + DatabaseHelper.COLUMN_PESO_ACTUAL + ") FROM " + 
            DatabaseHelper.TABLE_CONEJOS + " WHERE " + 
            DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " +
            DatabaseHelper.COLUMN_PESO_ACTUAL + " > 0", 
            null
        );
        if (cursor.moveToFirst()) {
            double avg = cursor.getDouble(0);
            stats.put("average", String.format("%.2f", avg));
        } else {
            stats.put("average", "0.00");
        }
        cursor.close();
        
        // Peso máximo
        cursor = db.rawQuery(
            "SELECT MAX(" + DatabaseHelper.COLUMN_PESO_ACTUAL + ") FROM " + 
            DatabaseHelper.TABLE_CONEJOS + " WHERE " + 
            DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL", 
            null
        );
        if (cursor.moveToFirst()) {
            double max = cursor.getDouble(0);
            stats.put("max", String.format("%.2f", max));
        } else {
            stats.put("max", "0.00");
        }
        cursor.close();
        
        // Peso mínimo
        cursor = db.rawQuery(
            "SELECT MIN(" + DatabaseHelper.COLUMN_PESO_ACTUAL + ") FROM " + 
            DatabaseHelper.TABLE_CONEJOS + " WHERE " + 
            DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " +
            DatabaseHelper.COLUMN_PESO_ACTUAL + " > 0", 
            null
        );
        if (cursor.moveToFirst()) {
            double min = cursor.getDouble(0);
            stats.put("min", String.format("%.2f", min));
        } else {
            stats.put("min", "0.00");
        }
        cursor.close();
        
        // Peso promedio machos
        cursor = db.rawQuery(
            "SELECT AVG(" + DatabaseHelper.COLUMN_PESO_ACTUAL + ") FROM " + 
            DatabaseHelper.TABLE_CONEJOS + " WHERE " + 
            DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " +
            DatabaseHelper.COLUMN_SEXO + " = 'M' AND " +
            DatabaseHelper.COLUMN_PESO_ACTUAL + " > 0", 
            null
        );
        if (cursor.moveToFirst()) {
            double avgM = cursor.getDouble(0);
            stats.put("averageMales", String.format("%.2f", avgM));
        } else {
            stats.put("averageMales", "0.00");
        }
        cursor.close();
        
        // Peso promedio hembras
        cursor = db.rawQuery(
            "SELECT AVG(" + DatabaseHelper.COLUMN_PESO_ACTUAL + ") FROM " + 
            DatabaseHelper.TABLE_CONEJOS + " WHERE " + 
            DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " +
            DatabaseHelper.COLUMN_SEXO + " = 'H' AND " +
            DatabaseHelper.COLUMN_PESO_ACTUAL + " > 0", 
            null
        );
        if (cursor.moveToFirst()) {
            double avgF = cursor.getDouble(0);
            stats.put("averageFemales", String.format("%.2f", avgF));
        } else {
            stats.put("averageFemales", "0.00");
        }
        cursor.close();
        
        return stats;
    }
    
    private HashMap<String, Object> getAgeStatistics(SQLiteDatabase db) {
        HashMap<String, Object> stats = new HashMap<String, Object>();
        
        Cursor cursor = db.rawQuery(
            "SELECT " + DatabaseHelper.COLUMN_FECHA_NACIMIENTO + " FROM " + 
            DatabaseHelper.TABLE_CONEJOS + " WHERE " + 
            DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL AND " +
            DatabaseHelper.COLUMN_FECHA_NACIMIENTO + " IS NOT NULL", 
            null
        );
        
        int total = 0;
        int babies = 0;
        int young = 0;
        int adults = 0;
        long totalDays = 0;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date today = new Date();
        
        while (cursor.moveToNext()) {
            String birthDateStr = cursor.getString(0);
            try {
                Date birthDate = sdf.parse(birthDateStr);
                long diff = today.getTime() - birthDate.getTime();
                long days = diff / (24 * 60 * 60 * 1000);
                long months = days / 30;
                
                totalDays += days;
                total++;
                
                if (months < 3) {
                    babies++;
                } else if (months < 12) {
                    young++;
                } else {
                    adults++;
                }
                
            } catch (Exception e) {
                // Ignorar fechas inválidas
            }
        }
        cursor.close();
        
        long averageDays = total > 0 ? totalDays / total : 0;
        
        stats.put("averageDays", averageDays);
        stats.put("babies", babies);
        stats.put("young", young);
        stats.put("adults", adults);
        stats.put("total", total);
        
        return stats;
    }
    
    private ArrayList<HashMap<String, Object>> getBreedDistribution(SQLiteDatabase db) {
        ArrayList<HashMap<String, Object>> breeds = new ArrayList<HashMap<String, Object>>();
        
        // Primero obtener total
        int total = 0;
        Cursor countCursor = db.rawQuery(
            "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_CONEJOS + 
            " WHERE " + DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL", 
            null
        );
        if (countCursor.moveToFirst()) {
            total = countCursor.getInt(0);
        }
        countCursor.close();
        
        // Obtener distribución por raza
        Cursor cursor = db.rawQuery(
            "SELECT " + DatabaseHelper.COLUMN_RAZA + ", COUNT(*) FROM " + 
            DatabaseHelper.TABLE_CONEJOS + " WHERE " + 
            DatabaseHelper.COLUMN_FECHA_FALLECIMIENTO + " IS NULL " +
            "GROUP BY " + DatabaseHelper.COLUMN_RAZA + 
            " ORDER BY COUNT(*) DESC", 
            null
        );
        
        while (cursor.moveToNext()) {
            String breed = cursor.getString(0);
            int count = cursor.getInt(1);
            
            if (breed == null || breed.trim().isEmpty()) {
                breed = "Sin raza especificada";
            }
            
            double percentage = total > 0 ? (count * 100.0) / total : 0;
            
            HashMap<String, Object> breedInfo = new HashMap<String, Object>();
            breedInfo.put("name", breed);
            breedInfo.put("count", count);
            breedInfo.put("percentage", String.format("%.1f", percentage));
            
            breeds.add(breedInfo);
        }
        
        cursor.close();
        
        return breeds;
    }
    
    private HashMap<String, Object> getReproductionStatistics(SQLiteDatabase db) {
        HashMap<String, Object> stats = new HashMap<String, Object>();
        
        // Total partos
        Cursor cursor = db.rawQuery(
            "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_PARTOS,
            //"SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_PARTOS", 
            null
        );
        if (cursor.moveToFirst()) {
            stats.put("totalBirths", cursor.getInt(0));
        } else {
            stats.put("totalBirths", 0);
        }
        cursor.close();
        
        // Total crías y promedio
        cursor = db.rawQuery(
            "SELECT SUM(" + DatabaseHelper.COLUMN_CRIAS_VIVAS + "), " +
            "AVG(" + DatabaseHelper.COLUMN_CRIAS_VIVAS + ") FROM " + 
            DatabaseHelper.TABLE_PARTOS + " WHERE " + 
            DatabaseHelper.COLUMN_CRIAS_VIVAS + " > 0", 
            null
        );
        if (cursor.moveToFirst()) {
            int totalOffspring = cursor.getInt(0);
            double averageOffspring = cursor.getDouble(1);
            
            stats.put("totalOffspring", totalOffspring);
            stats.put("averageOffspring", String.format("%.1f", averageOffspring));
        } else {
            stats.put("totalOffspring", 0);
            stats.put("averageOffspring", "0.0");
        }
        cursor.close();
        
        return stats;
    }
    
    // Método para formatear edad (se mantiene igual)
    public String formatAge(long days) {
        if (days < 30) {
            return days + " días";
        } else if (days < 365) {
            long months = days / 30;
            return months + " mes" + (months != 1 ? "es" : "");
        } else {
            long years = days / 365;
            long months = (days % 365) / 30;
            if (months > 0) {
                return years + " año" + (years != 1 ? "s" : "") + 
                       ", " + months + " mes" + (months != 1 ? "es" : "");
            } else {
                return years + " año" + (years != 1 ? "s" : "");
            }
        }
    }
    
    // Método para cerrar - AHORA SOLO CIERRA EL HELPER
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}