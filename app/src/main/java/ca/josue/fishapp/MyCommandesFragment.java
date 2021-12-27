//package ca.josue.fishapp;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ca.josue.fishapp.adapter.CommandeAdapter;
//import ca.josue.fishapp.model.MyCommandesItem;
//import ca.josue.fishapp.model.MyLogin;
//import ca.josue.fishapp.model.UserDTO;
//import ca.josue.fishapp.services.API;
//import ca.josue.fishapp.services.ApiInterface;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MyCommandesFragment extends AppCompatActivity   {
//    private Button askLogin;
//    private MainActivity context;
//    private final List<MyCommandesItem> commandeList = new ArrayList<>();
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        askLogin = findViewById(R.id.ask_login_btnJ);
//
//        if(!commandeList.isEmpty() && BaseApplication.ID_USER_CURRENT != null){
//            setContentView(R.layout.activity_my_commandes_fragment);
//        }
//        else if(BaseApplication.ID_USER_CURRENT == null){
//            setContentView(R.layout.commandes_fragment_to_login);
//        }
//        else
//            setContentView(R.layout.commandes_fragment_empty);
//
//        if(!commandeList.isEmpty()) {
//            // Retrieve Vertical RecyclerView
//            RecyclerView commandesRecyclerView = findViewById(R.id.vertical_recyclerview_commandes);
//            commandesRecyclerView.setAdapter(new CommandeAdapter(context, commandeList));
//            commandesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//            //commandesRecyclerView.addItemDecoration(FishItemDecoration());
//        }
//
//        UserDTO user =  new UserDTO("josuelubaki@gmail.com","Heroes");
//
////        askLogin.setOnClickListener(v -> {
////            System.out.println("Tu as cliqué");
////        });
//
//        getLoginUser(user);
//
//
//
//    }
//
//    private void getLoginUser(UserDTO user) {
//        MyApi.getApi().create(ApiInterfaceJ.class).login(user).enqueue(new Callback<MyLogin>(){
//
//            @Override
//            public void onResponse(@NotNull Call<MyLogin> call, @NotNull Response<MyLogin> response) {
//                if(!response.isSuccessful())
//                    return;
//
//                MyLogin myResponse = response.body();
//                System.out.println("Ma Réponse " + myResponse);
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<MyLogin> call, Throwable t) {
//                System.out.println("Erreur survenue" + t.getMessage());
//            }
//        });
//    }
//
//}