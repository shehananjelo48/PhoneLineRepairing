package shehan.com.sltphonelinerepairing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class ArrayFault extends ArrayAdapter<Fault> {
    private Activity context;
    private List<Fault> faultlist;

    public ArrayFault (Activity context , List<Fault> faultlist){
        super( context,R.layout.list_layout,faultlist);
        this.context =  context;
        this.faultlist = faultlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewTele = (TextView) listViewItem.findViewById(R.id.tvtp);
        TextView textViewCab  = (TextView) listViewItem.findViewById(R.id.Cab);

        Fault fault = faultlist .get(position);
        textViewTele.setText(fault.getTelephone_No());
        textViewCab.setText(fault.getCabinetNo());

        return listViewItem;
    }
}
