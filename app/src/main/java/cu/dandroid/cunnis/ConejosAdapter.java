package cu.dandroid.cunnis;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ConejosAdapter extends SimpleAdapter {
    
    public ConejosAdapter(Context context, List<HashMap<String, Object>> data, 
                         int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        
        // Personalizar la vista aquí si es necesario
        HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
        
        // Configurar imagen si existe
        ImageView foto = view.findViewById(R.id.foto);
        Object fotoObj = item.get("foto_bitmap");
        
        if (fotoObj instanceof Bitmap) {
            foto.setImageBitmap((Bitmap) fotoObj);
        } else {
            // Imagen por defecto
            foto.setImageResource(R.drawable.avatar);
        }
        
        return view;
    }
}