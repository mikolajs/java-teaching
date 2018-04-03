package osp.edu.pl.shopingassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class GoodsAdapter extends BaseAdapter {
    String[] names;
    Context context;
    LayoutInflater inflter;
    String value;

    public GoodsAdapter(Context context) {

        this.context = context;
        this.names = this.getGoods();
        inflter = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addItem(String item){

    }
    @Override
    public View getView(int index, View reuse, ViewGroup parent) {

        CheckedTextView item = parent.findViewById(R.id.checkedTextView);
                //this.inflter.inflate( android.R.layout.simple_list_item_multiple_choice, null);
        String name = names[index];
        item.setText(name);
        return item;
    }


    private String[] getGoods(){
        return new String[]{"mydło", "powidło", "szynka", "masło"};
    }
}