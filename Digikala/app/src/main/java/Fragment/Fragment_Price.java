package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.farshid.digikala.R;
import com.weiwangcn.betterspinner.library.BetterSpinner;

public class Fragment_Price extends android.app.Fragment {
    BetterSpinner fragment_price_Spinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price, container, false);
        fragment_price_Spinner = v.findViewById(R.id.fragment_price_Spinner);
        String[] color = new String[]{"کمتر از 500 تومان", "کمتر از یک میلیون تومان", "کمتر از 2 میلیون تومان"
                , "کمتر از 3 میلیون تومان", "کمتر از 4 میلیون تومان"
        };
        ArrayAdapter<String> adapter_Document_Student = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, color);
        fragment_price_Spinner.setAdapter(adapter_Document_Student);

        fragment_price_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getActivity(), ""+selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }
}
