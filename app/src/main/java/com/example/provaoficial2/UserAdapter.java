package com.example.provaoficial2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends ListAdapter<User, UserAdapter.UserViewHolder> {

    private static OnUserDeleteListener deleteListener; // Alterado para estático

    protected UserAdapter() { // Adicionado deleteListener como parâmetro
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = getItem(position);
        holder.bind(currentUser);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView emailTextView;
        private final TextView phoneTextView;
        private OnUserDeleteListener listener; // Adicione esta linha

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            Button deleteButton = itemView.findViewById(R.id.deleteButton);

            // Configurar o ouvinte de clique para o botão de exclusão
            deleteButton.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    User user = getItem(position);
                    listener.onUserDelete(user);
                }
            });
        }

        void bind(User user) {
            nameTextView.setText(user.getName());
            emailTextView.setText(user.getEmail());
            phoneTextView.setText(user.getPhone());
        }
    }

    interface OnUserDeleteListener {
        void onUserDelete(User user);
        void onUserDeletedConfirmation();
    }
}
