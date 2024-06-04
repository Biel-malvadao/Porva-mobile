package com.example.provaoficial2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserDeleteListener {

    private EditText nameField, emailField, phoneField, addressField, usernameField, passwordField, zipCodeField, countryField;
    private Spinner citySpinner, stateSpinner;
    private UserRepository userRepository;
    private RecyclerView userRecyclerView;

    // Inicialize o adaptador
    private UserAdapter userAdapter = new UserAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Inicialize o RecyclerView
        userRecyclerView = findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Defina o adaptador para o RecyclerView
        userRecyclerView.setAdapter(userAdapter);

        userRepository = new UserRepository(getApplication());

        nameField = findViewById(R.id.nameField);
        emailField = findViewById(R.id.emailField);
        phoneField = findViewById(R.id.phoneField);
        addressField = findViewById(R.id.addressField);
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        zipCodeField = findViewById(R.id.zipCodeField);
        countryField = findViewById(R.id.countryField);
        citySpinner = findViewById(R.id.citySpinner);
        stateSpinner = findViewById(R.id.stateSpinner);
        Button submitButton = findViewById(R.id.submitButton);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

        submitButton.setOnClickListener(view -> submitForm());
    }

    private List<User> getUsersFromDatabase() {
        return null;
    }

    private void submitForm() {
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String phone = phoneField.getText().toString();
        String address = addressField.getText().toString();
        String city = citySpinner.getSelectedItem().toString();
        String state = stateSpinner.getSelectedItem().toString();
        String zipCode = zipCodeField.getText().toString();
        String country = countryField.getText().toString();
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if (!ValidationHelper.isNotEmpty(name)) {
            showToast("Nome é obrigatório!");
            return;
        }
        if (!ValidationHelper.isValidEmail(email)) {
            showToast("Email inválido!");
            return;
        }
        if (!ValidationHelper.isValidPhone(phone)) {
            showToast("Telefone inválido! Deve conter 10 dígitos.");
            return;
        }
        if (!ValidationHelper.isNotEmpty(address)) {
            showToast("Endereço é obrigatório!");
            return;
        }
        if (!ValidationHelper.isNotEmpty(city)) {
            showToast("Cidade é obrigatória!");
            return;
        }
        if (!ValidationHelper.isNotEmpty(state)) {
            showToast("Estado é obrigatório!");
            return;
        }
        if (!ValidationHelper.isValidZipCode(zipCode)) {
            showToast("CEP inválido! Deve estar no formato 12345-678.");
            return;
        }
        if (!ValidationHelper.isNotEmpty(country)) {
            showToast("País é obrigatório!");
            return;
        }
        if (!ValidationHelper.isNotEmpty(username)) {
            showToast("Nome de usuário é obrigatório!");
            return;
        }
        if (!ValidationHelper.isValidPassword(password)) {
            showToast("Senha inválida! Deve ter pelo menos 8 caracteres.");
            return;
        }

        User user = new User(name, email, phone, address, city, state, zipCode, country, username, password);

        // Use a thread para inserir o usuário no banco de dados
        new Thread(() -> {
            userRepository.insert(user);
            runOnUiThread(() -> showToast("Usuário cadastrado com sucesso!"));
        }).start();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserDelete(User user) {

    }

    public void onUserDeletedConfirmation() {
        Toast.makeText(this, "Usuário excluído com sucesso!", Toast.LENGTH_SHORT).show();
    }

}