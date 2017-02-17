package wjc.tools.demo;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import wjc.tools.library.NetUtils;
        import wjc.tools.library.StorageUtils;
        import wjc.tools.library.Tools;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private TextView mContent;
    private String TAG=MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.action);
        mButton.setOnClickListener(this);
        mContent = (TextView)findViewById(R.id.content);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action:
                String str = null;
                //String str = Tools.getFileSuffix("baidu.bak");
/*                if (Tools.hasSubString("baid.com","com1")){
                    str="has sub string";
                } else {
                    str="not has sub string";
                }*/
                StorageUtils.getInternalStorageDir();
                mContent.setText(str);
                break;
            default:
                break;
        }
    }
}
