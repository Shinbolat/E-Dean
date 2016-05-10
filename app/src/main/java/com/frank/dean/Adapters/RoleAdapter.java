/*package com.frank.dean.Adapters;
package com.frank.e_dean.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frank.e_dean.R;

*/
/**
 * Created by shinbolat on 5/3/16.
 *//*

public class RoleAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String role[];

    public RoleAdapter(Context context, String role[]){

        this.role = role;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return role.length;
    }

    @Override
    public Object getItem(int position) {
        return role[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.item, parent, false);
        }

        ((TextView)view.findViewById(R.id.role_text)).setText(role[position]);

        return view;
    }
}
*/
