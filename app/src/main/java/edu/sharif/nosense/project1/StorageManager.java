package edu.sharif.nosense.project1;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StorageManager {

    private Context context;
    private String filename;

    public StorageManager(Context context){
        this.context = context;
        this.filename = context.getString(R.string.file_addr);
    }

    public int read(){
        try {
            FileInputStream fileInputStream = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while(( line = br.readLine()) != null ) {
                sb.append( line );
            }
            fileInputStream.close();
            return Integer.parseInt(sb.toString());
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return -1;
    }


    public ArrayList<Integer> load(){
        ArrayList<Integer> list = new ArrayList<>();
        int numberInTheFile = read();
        if(numberInTheFile == -1)
            numberInTheFile = 0;
        for (int i = 1; i <= 10; i++) {
            list.add(numberInTheFile + i);
        }
        save(list.get(list.size()-1));
        return list;
    }

    public void save(int last){
        String fileContents = Integer.toString(last);
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }
}