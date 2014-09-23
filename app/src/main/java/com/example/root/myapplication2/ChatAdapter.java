package com.example.root.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        Boolean thing;
        TextView name, body, time;
    }

    @Override
    public View getView(int position, View listItem, ViewGroup parent) {
        ChatHolder chatHolder;

        if (listItem == null) {
            listItem = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.chat_item, parent, false);

            chatHolder = new ChatHolder();

            chatHolder.thing = true;
            chatHolder.name = (TextView) listItem.findViewById(R.id.username);
            chatHolder.body = (TextView) listItem.findViewById(R.id.message_body);
            chatHolder.time = (TextView) listItem.findViewById(R.id.timestamp);

            listItem.setTag(chatHolder);
        }

        else {
            chatHolder = (ChatHolder) listItem.getTag();
        }

        chatHolder.body.setText(objects.get(position).message);
        chatHolder.name.setText(objects.get(position).username);
        chatHolder.time.setText(objects.get(position).timestamp);

        return listItem;
    }
}