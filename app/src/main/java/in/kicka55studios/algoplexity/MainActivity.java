package in.kicka55studios.algoplexity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlgoDBHandler dbHandler;
    public final static String EXTRA_MESSAGE = "in.kicka55studios.algoplexity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new AlgoDBHandler(this, null, null, 1);

        ArrayList<String> algoList = dbHandler.algoListGenerator();

        ListAdapter algoListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, algoList);
        ListView algoListView = (ListView) findViewById(R.id.algoListView);
        algoListView.setAdapter(algoListAdapter);

        algoListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String algoName = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(MainActivity.this, AlgorithmActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, algoName);
                        //Toast.makeText(MainActivity.this, "Nailed It", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNewAlgo:
                startActivity(new Intent(this, AddNewActivity.class));
                return true;
            case R.id.resetDb:
                dbHandler = new AlgoDBHandler(this, null, null, 1);
                dbHandler.resetDb();
                recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
