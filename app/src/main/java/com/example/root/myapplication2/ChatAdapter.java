package com.example.root.myapplication2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by root on 9/11/14.
 */
public class ChatAdapter extends ArrayAdapter <ChatObject> {
    Context context;
    int resource;
    ArrayList<ChatObject> objects;

    public ChatAdapter(Context context, int resource, ArrayList<ChatObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    private class ChatHolder {
        Boolean visible;
        TextView name, body, time;
        AlertDialog edit_text;
        EditText input_text;
    }

    @Override
    public View getView(int position, View listItem, ViewGroup parent) {
        final ChatHolder chatHolder;

        if (listItem == null) {
            listItem = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.chat_item, parent, false);

            chatHolder = new ChatHolder();

            chatHolder.visible = true;
            chatHolder.name = (TextView) listItem.findViewById(R.id.username);
            chatHolder.body = (TextView) listItem.findViewById(R.id.message_body);
            chatHolder.time = (TextView) listItem.findViewById(R.id.timestamp);

            listItem.setTag(chatHolder);
        }

        else {
            chatHolder = (ChatHolder) listItem.getTag();
        }

        chatHolder.body.setText(objects.get(position).message);
        chatHolder.name.setText(objects.get(position).name);
        chatHolder.time.setText(objects.get(position).timestamp);

        chatHolder.input_text = new EditText(context);
        chatHolder.edit_text = new AlertDialog.Builder(context).setTitle("Edit Message")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chatHolder.body.setText(String.valueOf(chatHolder.input_text.getText()));
                    }
                }).setView(chatHolder.input_text).create();

        chatHolder.body.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        chatHolder.input_text.setText(chatHolder.body.getText());
                        chatHolder.edit_text.show();
                    }
                }
        );
        return listItem;
    }
}