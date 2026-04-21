package cu.dandroid.cunnis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlertasReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        int tipoAlerta = intent.getIntExtra("tipo_alerta", 0);
        int conejoId = intent.getIntExtra("conejo_id", 0);
        String nombreConejo = intent.getStringExtra("conejo_nombre");
        String sexo = intent.getStringExtra("conejo_sexo");
        int diaRelativo = intent.getIntExtra("dia_relativo", 0);
        
        GestorAlertas gestor = new GestorAlertas(context);
        String titulo = "";
        String mensaje = "";
        
        switch (tipoAlerta) {
            case GestorAlertas.ALERTA_PESO:
                titulo = "⏰ Recordatorio de Peso";
                mensaje = "Es hora de pesar a " + nombreConejo;
                break;
                
            case GestorAlertas.ALERTA_CELO:
                if (diaRelativo < 0) {
                    titulo = "🔔 Próximo Celo";
                    mensaje = nombreConejo + " estará en celo en " + (-diaRelativo) + " días";
                } else if (diaRelativo == 0) {
                    titulo = "🔥 Celo Activo";
                    mensaje = nombreConejo + " está en celo HOY";
                } else {
                    titulo = "✅ Celo Pasando";
                    mensaje = nombreConejo + " salió de celo hace " + diaRelativo + " días";
                }
                break;
                
            case GestorAlertas.ALERTA_PARTO:
                if (diaRelativo < 0) {
                    titulo = "👶 Parto Próximo";
                    mensaje = nombreConejo + " parirá en " + (-diaRelativo) + " días";
                } else if (diaRelativo == 0) {
                    titulo = "🎉 ¡Parto Hoy!";
                    mensaje = nombreConejo + " debe parir HOY";
                } else {
                    titulo = "⚠️ Parto Atrasado";
                    mensaje = nombreConejo + " debió parir hace " + diaRelativo + " días";
                }
                break;
                
            case GestorAlertas.ALERTA_MADUREZ:
                titulo = "🎯 Madurez Sexual";
                mensaje = nombreConejo + " (" + (sexo.equals("H") ? "Hembra" : "Macho") + 
                         ") alcanzó la madurez sexual";
                break;
        }
        
        if (!titulo.isEmpty() && !mensaje.isEmpty()) {
            gestor.mostrarNotificacion(titulo, mensaje, tipoAlerta);
        }
    }
}