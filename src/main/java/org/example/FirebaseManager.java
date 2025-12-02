package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseManager {
    private final Firestore firestore;

    public FirebaseManager() throws Exception {
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(getClass()
                            .getResourceAsStream("/dbtest-51eda-firebase-adminsdk-fbsvc-f85a513bbb.json")))
                    .build();

            FirebaseApp.initializeApp(options);
        }
        firestore = FirestoreClient.getFirestore();
    }
    public void deletarPlayer(String playerId) throws ExecutionException, InterruptedException {
        if (playerId == null || playerId.trim().isEmpty()) {
            System.out.println("ID do player inválido");
            return;
        }

        DocumentReference docRef = firestore.collection("players").document(playerId);

        // Verifica se o documento existe antes de deletar
        DocumentSnapshot document = docRef.get().get();

        if (document.exists()) {
            docRef.delete().get();
            System.out.println("Player " + playerId + " deletado com sucesso!");
        } else {
            System.out.println("Player não encontrado.");
        }
    }

    public String salvarPlayer(Player player) throws ExecutionException, InterruptedException {
        if (player == null) return null;

        // gera ID aleatório (token)
        DocumentReference docRef = firestore.collection("players").document();

        player.setId(docRef.getId());

        docRef.set(player).get();

//        System.out.println("Player salvo com sucesso. ID/TOKEN: " + player.getId());
        return player.getId();
    }

    public Player buscarPlayer(String playerId) throws ExecutionException, InterruptedException {
        if (playerId == null || playerId.trim().isEmpty()) {
            System.out.println("ID do player inválido");
            return null;
        }

        DocumentSnapshot document = firestore.collection("players").document(playerId).get().get();

        if (document.exists()) {
            Player player = document.toObject(Player.class);
            System.out.println("Player encontrado: " + player.getNome());
            return player;
        } else {
            System.out.println("Player não encontrado");
            return null;
        }
    }
    public void atualizarCamposPlayer(String playerId, Map<String, Object> updates)
            throws ExecutionException, InterruptedException {

        DocumentReference docRef = firestore.collection("players").document(playerId);
        docRef.update(updates).get();

        System.out.println("Campos atualizados para o player " + playerId);
    }


}