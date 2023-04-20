package com.example.appschool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.appschool.databinding.FragmentLoginBinding;

import java.util.ArrayList;

public class LoginFragment extends Fragment {

    FragmentLoginBinding loginBinding;
    UsuarioDao usuarioDao;
    ArrayList<Usuario> usuarios;
    EditText nomeText;
    EditText categoriaText;

    public LoginFragment() {
        super(R.layout.fragment_login);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return loginBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usuarioDao = DatabaseSingleton.getInstance(getContext()).appDatabase.usuarioDao();
        usuarios = (ArrayList<Usuario>) usuarioDao.listarTodos();

        loginBinding.botaoCriar.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.cadastrarFragment, null));

        loginBinding.botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telaCampoNome = loginBinding.campoNome.toString();
                String telaCampoDesc = loginBinding.campoCategoria.toString();

                for(Usuario usuario: usuarios){
                    if(usuario.nome == telaCampoNome){
                        if(usuario.categoria == "aluno"){
                            Navigation.findNavController(view).navigate(R.id.alunoFragment, null);
                        } else {
                            Navigation.findNavController(view).navigate(R.id.professorFragment, null);
                        }

                    }
                }

            }
        });


    }
}