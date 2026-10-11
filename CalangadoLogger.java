package com.projeto.calangado;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalangadoLogger {
    private static final String SYSTEM_TAG = "CORE_CALANGADO_IPC";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

    private static String getTimestamp() {
        return dateFormat.format(new Date());
    }

    /**
     * Registra eventos normais de conexão com a infraestrutura
     */
    public static void i(String component, String message) {
        String logMessage = String.format("[%s] [%s] -> %s", getTimestamp(), component, message);
        Log.i(SYSTEM_TAG, logMessage);
    }

    /**
     * Registra latência e alertas quando a IA demorar a responder pelo barramento
     */
    public static void w(String component, String warning) {
        String logMessage = String.format("[%s] [%s] *AVISO DE LATÊNCIA* -> %s", getTimestamp(), component, warning);
        Log.w(SYSTEM_TAG, logMessage);
    }

    /**
     * Captura falhas críticas de buffer ou quebra de segurança no IPC
     */
    public static void e(String component, String error, Throwable throwable) {
        String logMessage = String.format("[%s] [%s] !!! FALHA CRÍTICA !!! -> %s", getTimestamp(), component, error);
        Log.e(SYSTEM_TAG, logMessage, throwable);
        
        // Espaço para futuras rotinas automáticas de recuperação de falha
    }
}