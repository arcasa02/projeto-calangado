package com.projeto.calangado;

import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.util.Log;

public class AudioNoiseFilter {
    private static final String TAG = "AudioNoiseFilter";
    private NoiseSuppressor noiseSuppressor;
    private AcousticEchoCanceler echoCanceler;

    /**
     * Ativa os filtros digitais de limpeza de áudio baseados no hardware do dispositivo
     * @param audioSessionId ID da sessão de áudio ativa capturada pelo microfone
     */
    public void enableHardwareFilters(int audioSessionId) {
        // 1. Ativa a Supressão de Ruído de Fundo (A22 Hardware Optimization)
        if (NoiseSuppressor.isAvailable()) {
            try {
                noiseSuppressor = NoiseSuppressor.create(audioSessionId);
                if (noiseSuppressor != null) {
                    noiseSuppressor.setEnabled(true);
                    Log.d(TAG, "Filtro de Supressão de Ruído ativado com sucesso.");
                }
            } catch (Exception e) {
                Log.e(TAG, "Falha ao inicializar Supressor de Ruído por hardware: " + e.getMessage());
            }
        } else {
            Log.w(TAG, "Aviso: Supressão de ruído por hardware não disponível neste dispositivo.");
        }

        // 2. Ativa o Cancelamento de Eco Acústico
        if (AcousticEchoCanceler.isAvailable()) {
            try {
                echoCanceler = AcousticEchoCanceler.create(audioSessionId);
                if (echoCanceler != null) {
                    echoCanceler.setEnabled(true);
                    Log.d(TAG, "Cancelador de Eco Acústico ativado com sucesso.");
                }
            } catch (Exception e) {
                Log.e(TAG, "Falha ao inicializar Cancelador de Eco: " + e.getMessage());
            }
        } else {
            Log.w(TAG, "Aviso: Cancelador de eco por hardware não disponível.");
        }
    }

    /**
     * Desativa os filtros e libera os recursos do processador
     */
    public void releaseFilters() {
        if (noiseSuppressor != null) {
            noiseSuppressor.release();
            noiseSuppressor = null;
        }
        if (echoCanceler != null) {
            echoCanceler.release();
            echoCanceler = null;
        }
        Log.d(TAG, "Filtros de áudio liberados.");
    }
}
