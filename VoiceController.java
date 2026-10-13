package com.projeto.calangado;
import android.speech.SpeechRecognizer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
VoiceController implements RecognitionListener {
private SpeechRecognizer speechRecognizer;
private Context context;
public VoiceController(Context context) { this.context = context; }
public void initSpeechRecognizer() {
speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);