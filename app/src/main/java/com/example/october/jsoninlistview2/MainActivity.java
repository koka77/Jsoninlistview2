package com.example.october.jsoninlistview2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
    //json string
    private String jsonString = "{\"employee\":[{\"emp_name\":\"employee1\",\"emp_no\":\"101700\"},{\"emp_name\":\"employee2\",\"emp_no\":\"101701\"},{\"emp_name\":\"employee3\",\"emp_no\":\"101702\"},"+
            "{\"emp_name\":\"employee4\",\"emp_no\":\"101703\"},{\"emp_name\":\"employee5\",\"emp_no\":\"101704\"},{\"emp_name\":\"employee6\",\"emp_no\":\"101705\"},"+
            "{\"emp_name\":\"employee7\",\"emp_no\":\"101706\"},{\"emp_name\":\"employee8\",\"emp_no\":\"101707\"},{\"emp_name\":\"employee9\",\"emp_no\":\"101708\"},"+
            "{\"emp_name\":\"employee10\",\"emp_no\":\"101709\"},{\"emp_name\":\"employee11\",\"emp_no\":\"101710\"},{\"emp_name\":\"employee12\",\"emp_no\":\"101711\"},"+
            "{\"emp_name\":\"employee13\",\"emp_no\":\"101712\"},{\"emp_name\":\"employee14\",\"emp_no\":\"101713\"},{\"emp_name\":\"employee15\",\"emp_no\":\"101712\"}]}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        ListView listView = (ListView) findViewById(R.id.listView1);
        /////////////////
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, employeeList, android.R.layout.simple_list_item_2,
                new String[] {"Name", "Number"}, new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
    }

    List<Map<String,String>> employeeList = new ArrayList<Map<String,String>>();
    private void initList(){

        try{
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("employee");

            for(int i = 0; i < jsonMainNode.length(); i++ ){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String name = jsonChildNode.optString("emp_name");
                String number = jsonChildNode.optString("emp_no");
                // String outPut = name + " -|- " +number;
                employeeList.add(createEmployee(name, number));
            }
        }
        catch(JSONException e){
            Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private HashMap<String, String> createEmployee(String name,String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put("Name", name);
        employeeNameNo.put("Number", number);

        return employeeNameNo;
    }

}