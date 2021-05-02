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

public class Fragment_Color extends android.app.Fragment {


    BetterSpinner fragment_color_Spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_color, container, false);
        fragment_color_Spinner = v.findViewById(R.id.fragment_color_Spinner);
        String[] color = new String[]{"آبی", "قرمز", "سبز"
                , "بنفش", "نیلی", "نارنجی", "خاکستری", "آبی آسمانی"
        };
            ArrayAdapter<String> adapter_Document_Student = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line, color);
        fragment_color_Spinner.setAdapter(adapter_Document_Student);
        fragment_color_Spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = fragment_color_Spinner.getText().toString();
            }
        });



        return v;

    }
}
