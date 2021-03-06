package com.example.root.myapplication2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MyFragment extends android.app.Fragment {

    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        final ListView myListView = (ListView) rootView.findViewById(R.id.my_list_view);
        final EditText myEditText = (EditText) rootView.findViewById(R.id.my_edit_text);
        final Button sendButton = (Button) rootView.findViewById(R.id.send_button);
        final Button nameButton = (Button) rootView.findViewById(R.id.change_username);
        final TextView username = (TextView) rootView.findViewById(R.id.current_username);
        //final HandlerDatabase hdb = new HandlerDatabase(getActivity());
        final Firebase firebase = new Firebase("https://boiling-inferno-4244.firebaseio.com/");


        //Making Alert Dialog for Username
        final EditText inputName = new EditText(getActivity());
        final AlertDialog name_dialog = new AlertDialog.Builder(getActivity()).setTitle("Username")
                .setMessage("Enter desired username")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        username.setText(String.valueOf(inputName.getText()));
                    }
                }).setView(inputName).create();

        //final ArrayList<ChatObject> listChats = hdb.getAllChatObjects();
        final ArrayList<ChatObject> listChats = new ArrayList<ChatObject>();
        final ChatAdapter adapter = new ChatAdapter(getActivity(), R.layout.chat_item, listChats);
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    ChatObject chat = new ChatObject(child.get)
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//        firebase.child("username").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                listChats.add((ChatObject) snapshot.getValue());
//                adapter.notifyDataSetChanged();
//            }
//            @Override public void onCancelled(FirebaseError error) { }
//        });
//
//        firebase.child("message").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                listChats.add((ChatObject)snapshot.getValue());
//                adapter.notifyDataSetChanged();
//            }
//            @Override public void onCancelled(FirebaseError error) { }
//        });
//
//        firebase.child("timestamp").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                listChats.add((ChatObject)snapshot.getValue());
//                adapter.notifyDataSetChanged();
//            }
//            @Override public void onCancelled(FirebaseError error) { }
//        });

        myListView.setAdapter(adapter);
        nameButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        name_dialog.show();
                    }
                }
        );

        sendButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Timestamp ts = new Timestamp(System.currentTimeMillis());
                        //ChatObject chat = new ChatObject("true",username.getText().toString(),myEditText.getText().toString(),ts.toString());
                        //firebase.child("message").push().setValue(username.getText().toString(),myEditText.getText().toString(),ts.toString());

                        firebase.child("username").setValue(username.getText().toString());
                        firebase.child("message").setValue(myEditText.getText().toString());
                        firebase.child("timestamp").setValue(ts.toString());
                        ChatObject chat = new ChatObject("true",username.getText().toString(),myEditText.getText().toString(),ts.toString());
                        adapter.add(chat);
                        adapter.notifyDataSetChanged();
                        myListView.setSelection(adapter.getCount() - 1);
                        myEditText.setText("");
                    }
                });

        return rootView;
    }
}
