package gjj_unit_test.buttondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_test)
    Button btnTest;
    @Bind(R.id.btn_view)
    ButtonView btnView;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.btn_view_new)
    ButtonViewNew btnViewNew;
    @Bind(R.id.btn_view_view_number)
    ButtonViewViewNumber btnViewViewNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_test})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                if (TextUtils.isEmpty(etNumber.getText().toString())) {
                    Toast.makeText(MainActivity.this, "测试数字为空", Toast.LENGTH_LONG).show();
                    return;
                }
                btnView.setButtonShow(Integer.parseInt(etNumber.getText().toString()));
                btnViewNew.setButtonShow(Integer.parseInt(etNumber.getText().toString()));

//                btnViewViewNumber.setButtonShow(Integer.parseInt(etNumber.getText().toString()));
                btnViewViewNumber.setShowNumber(true,Integer.parseInt(etNumber.getText().toString()));
                break;
        }
    }
}
