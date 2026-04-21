package cu.dandroid.cunnis; // Ajusta el paquete a tu proyecto

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class GestorAlertas {
    private Context context;
    private static final String CHANNEL_ID = "canal_alertas_conejos";
    private static final String CHANNEL_NAME = "Alertas de Conejos";

    public GestorAlertas(Context ctx) {
        this.context = ctx;
        crearCanalNotificacion();
    }

    private void crearCanalNotificacion() {
        // Crear canal solo para Android 8.0 (API 26) y superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription("Notificaciones para peso, celo y partos");
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.enableVibration(true);
            
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public void mostrarNotificacion(String titulo, String texto, int tipoAlerta) {
        // 1. Crear Intent para abrir la app al tocar la notificación
        Intent intent = new Intent(context, DetallesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        // Usar FLAG_IMMUTABLE para Android 6.0+ (API 23+)
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, flags);
        
        // 2. Construir la notificación según la versión de Android
        Notification notification;
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Para Android 8.0+ usar Notification.Builder con channel
            notification = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(obtenerIconoPorTipo(tipoAlerta))
                    .setContentTitle(titulo)
                    .setContentText(texto)
                    .setContentIntent(pendingIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true)
                    .build();
        } else {
            // Para versiones anteriores (API < 26)
            notification = new Notification.Builder(context)
                    .setSmallIcon(obtenerIconoPorTipo(tipoAlerta))
                    .setContentTitle(titulo)
                    .setContentText(texto)
                    .setContentIntent(pendingIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true)
                    .build();
        }
        
        // 3. Mostrar la notificación
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            int notificationId = (int) System.currentTimeMillis(); // ID único
            notificationManager.notify(notificationId, notification);
        }
    }

    private int obtenerIconoPorTipo(int tipo) {
        // Usa el icono de tu app. Asegúrate de que este recurso existe.
        // En Sketchware, el icono por defecto suele ser 'icon' o 'ic_launcher'
        return R.drawable.avatar; // Cambia esto al nombre de tu icono
    }

    // Métodos para programar alertas (mantén tu lógica existente)
    public void programarAlertaPeso(int conejoId, String nombre, String fechaUltimoPeso) {
        // Tu lógica para calcular cuándo mostrar la alerta...
        // Luego muestra la notificación:
        String titulo = "📊 Recordatorio de Peso";
        String texto = "Es hora de pesar a " + nombre;
        mostrarNotificacion(titulo, texto, ALERTA_PESO);
    }
    
    public void programarAlertaCelo(int conejoId, String nombre, String ultimoCelo) {
        String titulo = "💖 Control de Celo";
        String texto = "Verificar estado de celo de " + nombre;
        mostrarNotificacion(titulo, texto, ALERTA_CELO);
    }
    
    public void programarAlertaParto(int conejoId, String nombre, String ultimaMonta) {
        String titulo = "🐇 Alerta de Parto";
        String texto = "Posible parto próximo para " + nombre;
        mostrarNotificacion(titulo, texto, ALERTA_PARTO);
    }
    
    public void programarAlertaMadurez(int conejoId, String nombre, String sexo, String fechaNacimiento) {
        String titulo = "🎯 Madurez Sexual";
        String texto = nombre + " alcanzó madurez sexual";
        mostrarNotificacion(titulo, texto, ALERTA_MADUREZ);
    }

    // Constantes para tipos de alerta
    public static final int ALERTA_PESO = 1;
    public static final int ALERTA_CELO = 2;
    public static final int ALERTA_PARTO = 3;
    public static final int ALERTA_MADUREZ = 4;
}