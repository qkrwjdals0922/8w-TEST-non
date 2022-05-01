package com.example.a2022_1_report;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CheckBox view_state;
    LinearLayout state_on, state_off;
    EditText get_school, get_department, get_name;
    Spinner get_department_spin, get_school_spin;
    Button input_btn, delete_btn;
    ListView listView, listView2;
    RadioGroup get_grades;
    Spinner_adapter spinner_adapter;
    TextView empty_layout;

    ArrayAdapter<CharSequence> stringArrayAdapter;
    List<School> schoolArrayList = new ArrayList<>();
    ArrayList<String> arrayList;
    ArrayList<String> arrayListEx;
    String data_str;
    ArrayAdapter<String> listAdapterEx;
    ArrayAdapter<String> listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Student");
        get_id();

        view_state.setChecked(false);

        view_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    change_view(0);
                } else {
                    change_view(1);
                }
            }
        });

        stringArrayAdapter = ArrayAdapter.createFromResource(this, R.array.department_str, android.R.layout.simple_spinner_item);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        get_department_spin.setAdapter(stringArrayAdapter);

        get_department_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                get_department.setText(stringArrayAdapter.getItem(i).toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        spinner_data1();

        arrayListEx = new ArrayList<>();
        arrayList = new ArrayList<>();
        listAdapterEx = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListEx);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        View header = getLayoutInflater().inflate(R.layout.activity_list_header, null, false);
        listView.addHeaderView(header);
        View footer = getLayoutInflater().inflate(R.layout.activity_list_footer, null, false);
        listView.addFooterView(footer);
        listView.setEmptyView(empty_layout);
        listView.setAdapter(listAdapter);
        listView2.setAdapter(listAdapterEx);

        input_btn();
        String[] a = getResources().getStringArray(R.array.department_str);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("학과를 선택하세요");
                builder.setItems(R.array.department_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, a[i] + "를 선택하였습니다.", Toast.LENGTH_SHORT).show();
                        String value = a[i];
                        get_department.setText(value);

                    }
                });
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, data_str + "", Toast.LENGTH_SHORT).show();
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view_result(i);
            }
        });


    }

    private void spinner_data1() {
        School hint = new School();
        hint.setText("학교를 선택하세요");
        hint.setColor(Color.BLUE);
        get_school_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hint.setColor(Color.RED);
                get_school.setText(schoolArrayList.get(i).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        schoolArrayList.add(hint);

        School school1 = new School();
        school1.setText("경복대학교");
        school1.setColor(Color.BLUE);
        schoolArrayList.add(school1);

        School school2 = new School();
        school2.setText("서울대학교");
        school2.setColor(Color.BLUE);
        schoolArrayList.add(school2);

        School school3 = new School();
        school3.setText("이화여자대학교");
        school3.setColor(Color.BLUE);
        schoolArrayList.add(school3);

        School school4 = new School();
        school4.setText("연세대학교");
        school4.setColor(Color.BLUE);
        schoolArrayList.add(school4);

        School school5 = new School();
        school5.setText("고려대학교");
        school5.setColor(Color.BLUE);
        schoolArrayList.add(school5);

        School school6 = new School();
        school6.setText("서강대학교");
        school6.setColor(Color.BLUE);
        schoolArrayList.add(school6);

        spinner_adapter = new Spinner_adapter(this, schoolArrayList);
        get_school_spin.setAdapter(spinner_adapter);

    }

    public static class School {
        private int Color;
        private String school;

        public String getText() {
            return school;
        }

        public void setText(String text) {
            this.school = text;
        }

        public int getColor() {
            return Color;
        }

        public void setColor(int color) {
            Color = color;
        }
    }

    public static class Spinner_adapter extends BaseAdapter {
        private Context context;
        private List<School> getSchool;

        public Spinner_adapter(Context context, List<School> getSchool) {
            this.context = context;
            this.getSchool = getSchool;
        }

        @Override
        public int getCount() {
            return getSchool != null ? getSchool.size() : 0;
        }

        @Override
        public Object getItem(int i) {
            return getSchool.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rootView = LayoutInflater.from(context).
                    inflate(R.layout.dropdown_item, viewGroup, false);

            TextView text = rootView.findViewById(R.id.text);
            text.setText(getSchool.get(i).getText());
            text.setTextColor(getSchool.get(i).getColor());
            text.setTextSize(20);


            return rootView;
        }
    }

    private void input_btn() {
        final String[] grade_val = {""};
        get_grades.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.item1:
                        grade_val[0] = "1학년";
                        break;
                    case R.id.item2:
                        grade_val[0] = "2학년";
                        break;
                    case R.id.item3:
                        grade_val[0] = "3학년";
                        break;
                }
            }
        });

        input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data_str = get_school.getText().toString() + " " + get_department.getText().toString() + " " + grade_val[0] + " " + get_name.getText().toString();
                if (!get_name.equals("")) {
                    arrayList.add(data_str);
                    arrayListEx.add(data_str);
                } else {
                    Toast.makeText(MainActivity.this, "데이터를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                listAdapterEx.notifyDataSetChanged();
                listAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "data_str::" + data_str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void view_result(int i) {
        if (!(arrayList.size() == 0)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.ic_waring).setTitle("경고").setMessage(data_str + "을(를) 정말로 삭제하시겠습니까?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int j) {
                    Log.d("report01::dialog", "remove");
                    arrayList.remove(i);
                    listView2.clearChoices();
                    listView.clearChoices();
                    listAdapterEx.notifyDataSetChanged();
                    listAdapter.notifyDataSetChanged();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int j) {
                    Log.d("report01::dialog", "click");

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            Toast.makeText(MainActivity.this, "입력하세요", Toast.LENGTH_SHORT).show();
        }
    }

    private void change_view(int state) {
        switch (state) {
            case 0:
                state_on.setVisibility(View.VISIBLE);
                state_off.setVisibility(View.INVISIBLE);
                break;
            case 1:
                state_on.setVisibility(View.INVISIBLE);
                state_off.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void get_id() {
        view_state = findViewById(R.id.view_state);
        get_school = findViewById(R.id.get_school);
        get_department = findViewById(R.id.get_department);
        get_name = findViewById(R.id.get_name);
        get_school_spin = findViewById(R.id.get_school_spinner);
        get_department_spin = findViewById(R.id.get_department_spinner);
        input_btn = findViewById(R.id.input);
        delete_btn = findViewById(R.id.delete);
        listView = findViewById(R.id.student_list);
        listView2 = findViewById(R.id.student_list_ex);
        get_grades = findViewById(R.id.grade_group);
        state_on = findViewById(R.id.state_on);
        state_off = findViewById(R.id.state_off);
        empty_layout = findViewById(android.R.id.empty);
    }
}