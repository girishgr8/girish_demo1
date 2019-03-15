package com.example.android.chat_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage>  {



    private TextView chatText;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();

    public List<ChatMessage> getChatMessageList() {
        chatMessageList.add(new ChatMessage(true, "Hey there!\nHow can I help you?\nPlease choose:\n1) Mobile Phones\n2) Clothing\n3) Groceries"));
        return chatMessageList;
    }

    private Context context;

    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (chatMessageObj.left) {
            row = inflater.inflate(R.layout.user, parent, false);
        }else{
            row = inflater.inflate(R.layout.bot, parent, false);
        }
        chatText = (TextView) row.findViewById(R.id.msgr);

        chatText.setText(chatMessageObj.message);
        return row;
    }
}
