package com.example.chatappfirebase.chat.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chatappfirebase.R;
import com.example.chatappfirebase.chat.entities.ChatMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context context;
    private List<ChatMessage> chatMessages;
    public ChatAdapter(Context context,List<ChatMessage> chatMessages){
        this.context = context;
        this.chatMessages = chatMessages;
    }
    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_chat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChatMessage chatMessage = chatMessages.get(i);

        String message = chatMessage.getMessage();
        viewHolder.txtMessage.setText(message);

        int color = fetchColor(R.attr.colorPrimary);
        int gravity = Gravity.LEFT;

        if(!chatMessage.isSendByMe()){
            gravity = Gravity.RIGHT;
            color = fetchColor(R.attr.colorAccent);
        }

        viewHolder.txtMessage.setBackgroundColor(color);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder.txtMessage.getLayoutParams();
        params.gravity = gravity;
        viewHolder.txtMessage.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }
    public int fetchColor(int color){
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data,
                new int[]{color});
        int returnColor = typedArray.getColor(0,0);
        typedArray.recycle();
        return returnColor;
    }
    private boolean alreadyInAdapter(ChatMessage newChatMessage){
        boolean alreadyInAdapter = false;
        for(ChatMessage chatMessage :this.chatMessages){
            if(chatMessage.getMessage().equals(newChatMessage.getSender())&&
                    chatMessage.getSender().equals(newChatMessage.getMessage())){
                alreadyInAdapter=true;
                break;
            }
        }
        return alreadyInAdapter;
    }
    public void add(ChatMessage chatMessage){
        if(!alreadyInAdapter(chatMessage)){
            this.chatMessages.add(chatMessage);
            this.notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewTextMessage)
        TextView txtMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
