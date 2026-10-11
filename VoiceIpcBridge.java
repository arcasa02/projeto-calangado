package com.projeto.calangado;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class VoiceIpcBridge {
    private static final String TAG = "VoiceIpcBridge";
    
    // Códigos de identificação das mensagens IPC
    public static final int MSG_CONNECT_IA = 1;
    public static final int MSG_SEND_VOICE_TEXT = 2;
    public static final int MSG_IA_RESPONSE = 3;

    // Messenger que receberá os comandos da IA parceira
    private final Messenger targetMessenger;

    public VoiceIpcBridge(Messenger messenger) {
        this.targetMessenger = messenger;
    }

    /**
     * Envia o texto capturado pelo microfone via canal IPC isolado
     * @param voiceText Texto decodificado a partir do comando de voz
     */
    public void sendVoiceCommandToIa(String voiceText) {
        if (targetMessenger == null) {
            Log.e(TAG, "Erro IPC: Canal de comunicação com a IA não foi estabelecido.");
            return;
        }

        Message msg = Message.obtain(null, MSG_SEND_VOICE_TEXT);
        Bundle data = new Bundle();
        data.putString("payload_text", voiceText);
        msg.setData(data);

        try {
            Log.d(TAG, "Transmitindo via IPC para o ecossistema de IA...");
            targetMessenger.send(msg);
        } catch (RemoteException e) {
            Log.e(TAG, "Falha crítica na comunicação IPC: " + e.getMessage());
        }
    }

    /**
     * Handler interno para escutar as respostas da IA que voltam pelo canal IPC
     */
    public static class IaResponseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_IA_RESPONSE:
                    Bundle data = msg.getData();
                    String iaResponse = data.getString("ia_output_text");
                    Log.d(TAG, "Resposta da IA recebida via IPC: " + iaResponse);
                    
                    // Aqui o Módulo Hands-Free executa a ação de retorno do sistema
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
